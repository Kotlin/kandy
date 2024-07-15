/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.builders

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingHandler
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetBuilder
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.*
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalFreeScale
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class BindingHandlerTest {

    private lateinit var bindingHandler: BindingHandler
    private lateinit var mockDatasetBuilder: DatasetBuilder

    private val aes = mockk<Aes>()
    private val value = "testValue"
    private val values = listOf<Any>()
    private val name = "columnName"
    private val positionalParameters = mockk<PositionalMappingParameters<Any>>()
    private val nonPositionalParameters = mockk<NonPositionalMappingParameters<Any, Any>>()
    private val columnID = "columnId"

    @BeforeTest
    fun setup() {
        mockDatasetBuilder = mockk<DatasetBuilder>() {
            every { rowsCount() } returns 0
        }

        bindingHandler = object : BindingHandler({ mockDatasetBuilder }) {}
    }

    /**
     * Assertion method to compare the given mapping parameters to the expected result.
     *
     * @param aes The AES name to compare with the mapping result.
     * @param columnID The column ID to compare with the mapping result.
     * @param parameters The mapping parameters to compare with the mapping result.
     * @param result The actual mapping result.
     */
    private fun assertMapping(aes: Aes, columnID: String, parameters: MappingParameters, result: Mapping) {
        assertEquals(aes, result.aes)
        assertEquals(columnID, result.columnID)
        assertEquals(parameters, result.parameters)

        assertEquals(bindingHandler.bindingCollector.mappings[aes], result)
    }

    @Test
    fun `test addNonPositionalSetting`() {
        val setting = NonPositionalSetting(aes, value)

        val result = bindingHandler.addNonPositionalSetting(aes, value)

        assertEquals(setting, result)
        assertEquals(bindingHandler.bindingCollector.settings[aes], setting)
    }

    @Test
    fun `test addPositionalSetting`() {
        val setting = PositionalSetting(aes, value)

        val result = bindingHandler.addPositionalSetting(aes, value)

        assertEquals(setting, result)
        assertEquals(setting, bindingHandler.bindingCollector.settings[aes])
    }

    @Test
    fun `test addPositionalMapping with list of values`() {
        every { mockDatasetBuilder.addColumn(values, name) } returns columnID

        val result = bindingHandler.addPositionalMapping(aes, values, name, positionalParameters)

        assertMapping(aes, columnID, positionalParameters, result)
        verify { mockDatasetBuilder.addColumn(values, name) }
    }

    @Test
    fun `test addPositionalMapping with columnID`() {
        val newColumnID = "newColumnId"

        every { mockDatasetBuilder.takeColumn(columnID) } returns newColumnID

        val result = bindingHandler.addPositionalMapping(aes, columnID, positionalParameters)

        assertMapping(aes, newColumnID, positionalParameters, result)
        verify { mockDatasetBuilder.takeColumn(columnID) }
    }

    @Test
    fun `test addNonPositionalMapping with list of values`() {
        every { mockDatasetBuilder.addColumn(values, name) } returns columnID

        val result = bindingHandler.addNonPositionalMapping(aes, values, name, nonPositionalParameters)

        assertMapping(aes, columnID, nonPositionalParameters, result)
        verify { mockDatasetBuilder.addColumn(values, name) }
    }

    @Test
    fun `test addNonPositionalMapping with columnID`() {
        val newColumnID = "newColumnId"

        every { mockDatasetBuilder.takeColumn(columnID) } returns newColumnID

        val result = bindingHandler.addNonPositionalMapping(aes, columnID, nonPositionalParameters)

        assertMapping(aes, newColumnID, nonPositionalParameters, result)
        verify { mockDatasetBuilder.takeColumn(columnID) }
    }

    @Test
    fun `test addPositionalFreeScale`() {
        val parameters = mockk<PositionalMappingParameters<Any>>()

        bindingHandler.addPositionalFreeScale(aes, parameters)

        val expectedFreeScale = PositionalFreeScale(aes, parameters)
        assertEquals(expectedFreeScale, bindingHandler.bindingCollector.freeScales[aes])
    }
}