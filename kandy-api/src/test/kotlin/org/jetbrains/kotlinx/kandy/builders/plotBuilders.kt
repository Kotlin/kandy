/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.builders


import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.MultiLayerPlotBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.SingleLayerPlotBuilder
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.NamedData
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.ir.scale.FreeScale
import kotlin.test.Test

class PlotBuildersTest {
    private val mockData = mockk<NamedData>()
    private val mockGeom = mockk<Geom>()
    private val mockFeatureName = mockk<FeatureName>()
    private val mockFeature = mockk<PlotFeature>()
    private val mockPlotFeatures = mutableMapOf(mockFeatureName to mockFeature)
    private val mockMappings = mockk<MutableMap<Aes, Mapping>>()
    private val mockSettings = mockk<MutableMap<Aes, Setting>>()
    private val mockFreeScales = mockk<MutableMap<Aes, FreeScale>>()

    @Test
    fun `test singleLayerPlotBuilder toPlot`() {
        val mockLayer: Layer = mockk<Layer>() {
            every { geom } returns mockGeom
        }

        val singleLayerPlotBuilder = spyk(object : SingleLayerPlotBuilder() {
            override val plotFeatures: MutableMap<FeatureName, PlotFeature> = mockPlotFeatures
            override val geom: Geom = mockGeom
            override val requiredAes: Set<Aes> = setOf()
            override val datasetBuilder: DatasetBuilder = mockk() {
                every { build() } returns mockData
            }
        })

        every { singleLayerPlotBuilder.datasetBuilder.build() } returns mockData
        every { singleLayerPlotBuilder.toLayer() } returns mockLayer
        every { singleLayerPlotBuilder.bindingCollector.mappings } returns mockMappings
        every { singleLayerPlotBuilder.bindingCollector.settings } returns mockSettings
        every { singleLayerPlotBuilder.bindingCollector.freeScales } returns mockFreeScales
        every { singleLayerPlotBuilder.plotFeatures } returns mockPlotFeatures

        val expectedPlot = Plot(
            datasets = listOf(mockData),
            layers = listOf(mockLayer),
            globalMappings = mockMappings,
            globalSettings = mockSettings,
            features = mockPlotFeatures,
            freeScales = mockFreeScales
        )

        val plot = singleLayerPlotBuilder.toPlot()

        assertEquals(expectedPlot, plot)
    }


    @Test
    fun `test layerPlotBuilder toPlot`() {
        val mockLayerFirst: Layer = mockk<Layer>()
        val mockLayerSecond: Layer = mockk<Layer>()

        val datasetBuilder: DatasetBuilder = mockk {
            every { build() } returns mockData
        }

        val layerPlotBuilder = spyk(object : MultiLayerPlotBuilder() {
            override val datasetBuilders: MutableList<DatasetBuilder> = mutableListOf(
                datasetBuilder
            )

            override fun addDataset(dataset: TableData, initialBuilder: DatasetBuilder?): Int {
                return -1
            }

            override fun addEmptyDataset(): Int {
                return -1
            }
        })
        layerPlotBuilder.layers.addAll(listOf(mockLayerFirst, mockLayerSecond))
        layerPlotBuilder.plotFeatures.putAll(mockPlotFeatures)

        every { layerPlotBuilder.datasetBuilder.build() } returns mockData

        every { layerPlotBuilder.bindingCollector.mappings } returns mockMappings
        every { layerPlotBuilder.bindingCollector.settings } returns mockSettings
        every { layerPlotBuilder.bindingCollector.freeScales } returns mockFreeScales


        val expectedPlot = Plot(
            datasets = listOf(mockData),
            layers = listOf(mockLayerFirst, mockLayerSecond),
            globalMappings = mockMappings,
            globalSettings = mockSettings,
            features = mockPlotFeatures,
            freeScales = mockFreeScales
        )

        val plot = layerPlotBuilder.toPlot()

        assertEquals(expectedPlot, plot)
    }



}
