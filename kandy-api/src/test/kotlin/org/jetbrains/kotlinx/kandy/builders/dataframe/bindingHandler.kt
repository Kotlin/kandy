/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.builders.dataframe

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingHandler
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.DatasetBuilderImpl
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.addNonPositionalMapping
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.addPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.MappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParameters
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class BindingHandlerTest {

    private lateinit var bindingHandler: BindingHandler
    private lateinit var mockDatasetBuilder: DatasetBuilderImpl

    private val aes = mockk<Aes>()
    private val positionalParameters = mockk<PositionalMappingParameters<Any>>()
    private val nonPositionalParameters = mockk<NonPositionalMappingParameters<Any, Any>>()
    private val columnID = "columnId"

    @BeforeTest
    fun setup() {
        mockDatasetBuilder = mockk<DatasetBuilderImpl>() {
            every { rowsCount() } returns 1
        }

        bindingHandler = object : BindingHandler({ mockDatasetBuilder }) {}
    }

    private fun assertMapping(aes: Aes, columnID: String, parameters: MappingParameters, result: Mapping) {
        assertEquals(aes, result.aes)
        assertEquals(columnID, result.columnID)
        assertEquals(parameters, result.parameters)

        assertEquals(bindingHandler.bindingCollector.mappings[aes], result)
    }

    @Test
    fun `test addPositionalMapping with DataColumn`() {
        val dataColumn = mockk<DataColumn<Any>>() {
            every { size() } returns 1
        }

        every { mockDatasetBuilder.addColumn(dataColumn) } returns columnID

        val result = bindingHandler.addPositionalMapping(aes, dataColumn, positionalParameters)

        assertMapping(aes, columnID, positionalParameters, result)
        verify { mockDatasetBuilder.addColumn(dataColumn) }
    }

    @Test
    fun `test addNonPositionalMapping with DataColumn`() {
        val dataColumn = mockk<DataColumn<Any>>() {
            every { size() } returns 1
        }

        every { mockDatasetBuilder.addColumn(dataColumn) } returns columnID

        val result = bindingHandler.addNonPositionalMapping(aes, dataColumn, nonPositionalParameters)

        assertMapping(aes, columnID, nonPositionalParameters, result)
        verify { mockDatasetBuilder.addColumn(dataColumn) }
    }
}