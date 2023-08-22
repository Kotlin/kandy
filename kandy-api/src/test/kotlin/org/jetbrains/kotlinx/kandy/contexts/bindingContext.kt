/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.contexts

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingCollector
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetHandler
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotContext
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.*
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalFreeScale
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class BindingContextTest {

    private lateinit var bindingContext: BindingContext
    private lateinit var mockDatasetHandler: DatasetHandler

    private val aes = mockk<Aes>()
    private val value = "testValue"
    private val values = listOf<Any>()
    private val name = "columnName"
    private val positionalParameters = mockk<PositionalMappingParameters<Any>>()
    private val nonPositionalParameters = mockk<NonPositionalMappingParameters<Any, Any>>()
    private val columnID = "columnId"

    @BeforeTest
    fun setup() {
        mockDatasetHandler = mockk<DatasetHandler>()

        bindingContext = object : BindingContext {
            override val plotContext: PlotContext = mockk()
            override val datasetIndex: Int = 0
            override val bindingCollector: BindingCollector = BindingCollector()
            override val datasetHandler: DatasetHandler = mockDatasetHandler
        }
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

        assertEquals(bindingContext.bindingCollector.mappings[aes], result)
    }

    @Test
    fun `test addNonPositionalSetting`() {
        val setting = NonPositionalSetting(aes, value)

        val result = bindingContext.addNonPositionalSetting(aes, value)

        assertEquals(setting, result)
        assertEquals(bindingContext.bindingCollector.settings[aes], setting)
    }

    @Test
    fun `test addPositionalSetting`() {
        val setting = PositionalSetting(aes, value)

        val result = bindingContext.addPositionalSetting(aes, value)

        assertEquals(setting, result)
        assertEquals(setting, bindingContext.bindingCollector.settings[aes])
    }

    @Test
    fun `test addPositionalMapping with list of values`() {
        every { mockDatasetHandler.addColumn(values, name) } returns columnID

        val result = bindingContext.addPositionalMapping(aes, values, name, positionalParameters)

        assertMapping(aes, columnID, positionalParameters, result)
        verify { mockDatasetHandler.addColumn(values, name) }
    }

    @Test
    fun `test addPositionalMapping with columnID`() {
        val newColumnID = "newColumnId"

        every { mockDatasetHandler.takeColumn(columnID) } returns newColumnID

        val result = bindingContext.addPositionalMapping(aes, columnID, positionalParameters)

        assertMapping(aes, newColumnID, positionalParameters, result)
        verify { mockDatasetHandler.takeColumn(columnID) }
    }

    @Test
    fun `test addPositionalMapping with DataColumn`() {
        val dataColumn = mockk<DataColumn<Any>>()

        every { mockDatasetHandler.addColumn(dataColumn) } returns columnID

        val result = bindingContext.addPositionalMapping(aes, dataColumn, positionalParameters)

        assertMapping(aes, columnID, positionalParameters, result)
        verify { mockDatasetHandler.addColumn(dataColumn) }
    }

    @Test
    fun `test addNonPositionalMapping with list of values`() {
        every { mockDatasetHandler.addColumn(values, name) } returns columnID

        val result = bindingContext.addNonPositionalMapping(aes, values, name, nonPositionalParameters)

        assertMapping(aes, columnID, nonPositionalParameters, result)
        verify { mockDatasetHandler.addColumn(values, name) }
    }

    @Test
    fun `test addNonPositionalMapping with columnID`() {
        val newColumnID = "newColumnId"

        every { mockDatasetHandler.takeColumn(columnID) } returns newColumnID

        val result = bindingContext.addNonPositionalMapping(aes, columnID, nonPositionalParameters)

        assertMapping(aes, newColumnID, nonPositionalParameters, result)
        verify { mockDatasetHandler.takeColumn(columnID) }
    }

    @Test
    fun `test addNonPositionalMapping with DataColumn`() {
        val dataColumn = mockk<DataColumn<Any>>()

        every { mockDatasetHandler.addColumn(dataColumn) } returns columnID

        val result = bindingContext.addNonPositionalMapping(aes, dataColumn, nonPositionalParameters)

        assertMapping(aes, columnID, nonPositionalParameters, result)
        verify { mockDatasetHandler.addColumn(dataColumn) }
    }

    @Test
    fun `test addPositionalFreeScale`() {
        val parameters = mockk<PositionalMappingParameters<Any>>()

        bindingContext.addPositionalFreeScale(aes, parameters)

        val expectedFreeScale = PositionalFreeScale(aes, parameters)
        assertEquals(expectedFreeScale, bindingContext.bindingCollector.freeScales[aes])
    }
}