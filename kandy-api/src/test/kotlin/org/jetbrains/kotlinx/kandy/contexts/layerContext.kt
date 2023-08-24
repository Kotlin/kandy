/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.contexts

import io.mockk.every
import io.mockk.mockk
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class LayerContextTest {

    private lateinit var layerContext: LayerContext
    private lateinit var parentContext: LayerCollectorContext

    private val aes = mockk<Aes>()
    private val name = "columnName"
    private val positionalParameters = mockk<PositionalMappingParameters<Any>>()
    private val nonPositionalParameters = mockk<NonPositionalMappingParameters<Any, Any>>()
    private val columnID = "columnId"

    @BeforeTest
    fun setup() {
        parentContext = mockk<LayerCollectorContext> {
            every { datasetIndex } returns 0
            every { plotContext } returns mockk(relaxed = true) {
                every { datasetHandlers } returns mutableListOf(mockk(relaxed = true))
            }
        }

        layerContext = object : LayerContext(parentContext) {
            override val geom: Geom = mockk()
            override val requiredAes: Set<Aes> = mockk()
        }
    }

    @Test
    fun `test addNonPositionalMapping with columnID`() {
        every { layerContext.datasetHandler.takeColumn(columnID) } returns columnID

        val result = layerContext.addNonPositionalMapping(aes, columnID, nonPositionalParameters)
        val expectedMapping = NonPositionalMapping(aes, columnID, nonPositionalParameters)

        assertEquals(expectedMapping, result)
    }

    @Test
    fun `test addNonPositionalMapping with DataColumn and overrideDataset`() {
        val dataColumn = mockk<DataColumn<Any>> {
            every { size() } returns 3
        }

        every { layerContext.datasetHandler.addColumn(dataColumn) } returns columnID

        val result = layerContext.addNonPositionalMapping(aes, dataColumn, nonPositionalParameters)
        val expectedMapping = NonPositionalMapping(aes, columnID, nonPositionalParameters)

        assertEquals(1, layerContext.datasetIndex)
        assertEquals(expectedMapping, result)
    }

    @Test
    fun `test addNonPositionalMapping with list of values and overrideDataset`() {
        val values = listOf<Any>("test1", "test2")
        every { layerContext.datasetHandler.addColumn(values, name) } returns columnID

        val result = layerContext.addNonPositionalMapping(aes, values, name, nonPositionalParameters)
        val expectedMapping = NonPositionalMapping(aes, columnID, nonPositionalParameters)

        assertEquals(1, layerContext.datasetIndex)
        assertEquals(expectedMapping, result)
    }

    @Test
    fun `test addPositionalMapping with columnID`() {
        every { layerContext.datasetHandler.takeColumn(columnID) } returns columnID

        val result = layerContext.addPositionalMapping(aes, columnID, positionalParameters)
        val expectedMapping = PositionalMapping(aes, columnID, positionalParameters)

        assertEquals(expectedMapping, result)
    }

    @Test
    fun `test addPositionalMapping with DataColumn and overrideDataset`() {
        val dataColumn = mockk<DataColumn<Any>> {
            every { size() } returns 3
        }

        every { layerContext.datasetHandler.addColumn(dataColumn) } returns columnID

        val result = layerContext.addPositionalMapping(aes, dataColumn, positionalParameters)
        val expectedMapping = PositionalMapping(aes, columnID, positionalParameters)

        assertEquals(1, layerContext.datasetIndex)
        assertEquals(expectedMapping, result)
    }

    @Test
    fun `test addPositionalMapping with list of values and overrideDataset`() {
        val values = listOf<Any>("test1", "test2")
        every { layerContext.datasetHandler.addColumn(values, name) } returns columnID

        val result = layerContext.addPositionalMapping(aes, values, name, positionalParameters)
        val expectedMapping = PositionalMapping(aes, columnID, positionalParameters)

        assertEquals(1, layerContext.datasetIndex)
        assertEquals(expectedMapping, result)
    }
}
