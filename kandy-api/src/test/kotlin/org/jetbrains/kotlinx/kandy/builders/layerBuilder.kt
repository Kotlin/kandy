/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.builders

import io.mockk.every
import io.mockk.mockk
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetHandler
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilderImpl
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.dsl.internal.MultiLayerPlotBuilder
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class LayerBuilderImplTest {

    private lateinit var layerBuilder: LayerBuilderImpl
    private lateinit var parentBuilder: LayerCreatorScope

    private val aes = mockk<Aes>()
    private val positionalParameters = mockk<PositionalMappingParameters<Any>>()
    private val nonPositionalParameters = mockk<NonPositionalMappingParameters<Any, Any>>()
    private val columnID = "columnId"
    private val layersInheritMappings = false
    private val mockGeom = mockk<Geom>()

    private lateinit var dataHandler: DatasetHandler

    @BeforeTest
    fun setup() {
        dataHandler = mockk<DatasetHandler> {
            every { buffer } returns DataFrame.Empty
            every { initialNamedData } returns NamedData(DataFrame.Empty)
        }

        parentBuilder = object : LayerCreatorScope() {
            override val plotBuilder = object: MultiLayerPlotBuilder() {
                override val datasetHandlers: MutableList<DatasetHandler> = mutableListOf(dataHandler)
            }
            override val datasetIndex: Int = 0
            override val layersInheritMappings: Boolean = this@LayerBuilderImplTest.layersInheritMappings
        }

        layerBuilder = object : LayerBuilderImpl(parentBuilder) {
            override val geom: Geom = mockk()
            override var requiredAes: Set<Aes> = mockk()
        }

       // every { layerBuilder.bindingHandler } returns this@LayerBuilderImplTest.bindingHandler
    }

    @Test
    fun `test addNonPositionalMapping with columnID`() {
        every { dataHandler.takeColumn(columnID) } returns columnID

        val result = layerBuilder.bindingHandler.addNonPositionalMapping(aes, columnID, nonPositionalParameters)
        val expectedMapping = NonPositionalMapping(aes, columnID, nonPositionalParameters)

        assertEquals(expectedMapping, result)
    }

    @Test
    fun `test addNonPositionalMapping with DataColumn and overrideDataset`() {
        val dataColumn = mockk<DataColumn<Any>> {
            every { size() } returns 3
            every { name() } returns columnID
        }

        every { dataHandler.addColumn(dataColumn) } returns columnID

        val result = layerBuilder.bindingHandler.addNonPositionalMapping(aes, dataColumn, nonPositionalParameters)
        val expectedMapping = NonPositionalMapping(aes, columnID, nonPositionalParameters)

        assertEquals(1, layerBuilder.datasetIndex)
        assertEquals(expectedMapping, result)
    }

    @Test
    fun `test addNonPositionalMapping with list of values and overrideDataset`() {
        val values = listOf<Any>("test1", "test2")
        every { dataHandler.addColumn(values, columnID) } returns columnID

        val result = layerBuilder.bindingHandler.addNonPositionalMapping(aes, values, columnID, nonPositionalParameters)
        val expectedMapping = NonPositionalMapping(aes, columnID, nonPositionalParameters)

        assertEquals(1, layerBuilder.datasetIndex)
        assertEquals(expectedMapping, result)
    }

    @Test
    fun `test addPositionalMapping with columnID`() {
        every { dataHandler.takeColumn(columnID) } returns columnID

        val result = layerBuilder.bindingHandler.addPositionalMapping(aes, columnID, positionalParameters)
        val expectedMapping = PositionalMapping(aes, columnID, positionalParameters)

        assertEquals(expectedMapping, result)
    }

    @Test
    fun `test addPositionalMapping with DataColumn and overrideDataset`() {
        val dataColumn = mockk<DataColumn<Any>> {
            every { size() } returns 3
            every { name() } returns columnID
        }

        every { dataHandler.addColumn(dataColumn) } returns columnID

        val result = layerBuilder.bindingHandler.addPositionalMapping(aes, dataColumn, positionalParameters)
        val expectedMapping = PositionalMapping(aes, columnID, positionalParameters)

        assertEquals(1, layerBuilder.datasetIndex)
        assertEquals(expectedMapping, result)
    }

    @Test
    fun `test addPositionalMapping with list of values and overrideDataset`() {
        val values = listOf<Any>("test1", "test2")
        every { dataHandler.addColumn(values, columnID) } returns columnID

        val result = layerBuilder.bindingHandler.addPositionalMapping(aes, values, columnID, positionalParameters)
        val expectedMapping = PositionalMapping(aes, columnID, positionalParameters)

        assertEquals(1, layerBuilder.datasetIndex)
        assertEquals(expectedMapping, result)
    }

    @Test
    fun `test toLayer`() {
        val layerBuilderImpl = object : LayerBuilderImpl(parentBuilder) {
            override val geom: Geom = mockGeom
            override val requiredAes: Set<Aes> = setOf()
        }

        val layer = layerBuilderImpl.toLayer()

        assertEquals(mockGeom, layer.geom)
        assertEquals(layerBuilderImpl.bindingCollector.mappings, layer.mappings)
        assertEquals(layerBuilderImpl.bindingCollector.settings, layer.settings)
        assertEquals(layerBuilderImpl.bindingCollector.freeScales, layer.freeScales)
        assertEquals(layerBuilderImpl.layerFeatures, layer.features)
        assertEquals(layersInheritMappings, layer.inheritsBindings)
    }

}
