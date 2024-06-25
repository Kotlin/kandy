/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.builders.dataframe

import io.mockk.every
import io.mockk.mockk
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.*
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParameters
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.NamedData
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import kotlin.test.*

class LayerBuilderImplTest {

    private lateinit var layerBuilder: LayerBuilderImpl
    private lateinit var parentBuilder: LayerCreatorScope

    private val aes = mockk<Aes>()
    private val positionalParameters = mockk<PositionalMappingParameters<Any>>()
    private val nonPositionalParameters = mockk<NonPositionalMappingParameters<Any, Any>>()
    private val columnID = "columnId"
    private val layersInheritMappings = false
    private val mockGeom = mockk<Geom>()

    private lateinit var dataHandler: DatasetBuilderImpl
    private lateinit var dataHandlerNew: DatasetBuilderImpl

    @BeforeTest
    fun setup() {
        dataHandler = mockk<DatasetBuilderImpl> {
            every { buffer } returns DataFrame.Empty
            every { baseDataFrame } returns DataFrame.Empty
            every { rowsCount() } returns 2
        }

        dataHandlerNew = mockk<DatasetBuilderImpl> {
            every { buffer } returns DataFrame.Empty
            every { baseDataFrame } returns DataFrame.Empty
            every { rowsCount() } returns 3
        }

        parentBuilder = object : LayerCreatorScope() {
            override val plotBuilder = object: MultiLayerPlotBuilder() {
                override val datasetBuilders: MutableList<DatasetBuilder> = mutableListOf(dataHandler)
                override fun addDataset(dataset: TableData, initialBuilder: DatasetBuilder?): Int {
                    return datasetBuilders.also {
                        it.add(dataHandlerNew)
                    }.indices.last
                }
                override fun addEmptyDataset(): Int {
                    return datasetBuilders.also {
                        it.add(dataHandlerNew)
                    }.indices.last
                }
            }
            override val datasetIndex: Int = 0
            override val layersInheritMappings: Boolean = this@LayerBuilderImplTest.layersInheritMappings
        }

        layerBuilder = object : LayerBuilderImpl(parentBuilder) {
            override val geom: Geom = mockk()
            override var requiredAes: Set<Aes> = mockk()
        }
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

        every { dataHandlerNew.addColumn(dataColumn) } returns columnID

        val result = layerBuilder.bindingHandler.addNonPositionalMapping(aes, dataColumn, nonPositionalParameters)
        val expectedMapping = NonPositionalMapping(aes, columnID, nonPositionalParameters)

        assertEquals(1, layerBuilder.datasetIndex)
        assertEquals(expectedMapping, result)
    }

    @Test
    fun `test addNonPositionalMapping with list of values and overrideDataset`() {
        val values = listOf<Any>("test1", "test2", "test3")
        every { dataHandlerNew.addColumn(values, columnID) } returns columnID

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

        every { dataHandlerNew.addColumn(dataColumn) } returns columnID

        val result = layerBuilder.bindingHandler.addPositionalMapping(aes, dataColumn, positionalParameters)
        val expectedMapping = PositionalMapping(aes, columnID, positionalParameters)

        assertEquals(1, layerBuilder.datasetIndex)
        assertEquals(expectedMapping, result)
    }

    @Test
    fun `test addPositionalMapping with list of values and overrideDataset`() {
        val values = listOf<Any>("test1", "test2", "test3")
        every { dataHandlerNew.addColumn(values, columnID) } returns columnID

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
