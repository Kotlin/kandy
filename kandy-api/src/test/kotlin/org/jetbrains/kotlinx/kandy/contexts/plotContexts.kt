/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.contexts

import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.ir.scale.FreeScale
import kotlin.test.Test

class PlotContextsTest {
    private val mockData = mockk<TableData>()
    private val mockDataHandler = mockk<DatasetHandler> {
        every { data() } returns mockData
    }
    private val mockPlotFeatures = mockk<MutableMap<FeatureName, PlotFeature>>()
    private val mockMappings = mockk<MutableMap<Aes, Mapping>>()
    private val mockSettings = mockk<MutableMap<Aes, Setting>>()
    private val mockFreeScales = mockk<MutableMap<Aes, FreeScale>>()

    @Test
    fun `test singleLayerPlotContext toPlot`() {
        val mockLayer: Layer = mockk<Layer>()

        val singleLayerPlotContext = object : SingleLayerPlotContext {
            override val layer: Layer = mockLayer
            override val datasetHandlers: MutableList<DatasetHandler> = mutableListOf(mockDataHandler)
            override val plotFeatures: MutableMap<FeatureName, PlotFeature> = mockPlotFeatures
            override val bindingCollector: BindingCollector = mockk {
                every { mappings } returns mockMappings
                every { settings } returns mockSettings
                every { freeScales } returns mockFreeScales
            }
        }

        val expectedPlot = Plot(
            datasets = listOf(mockData),
            layers = listOf(mockLayer),
            globalMappings = mockMappings,
            globalSettings = mockSettings,
            features = mockPlotFeatures,
            freeScales = mockFreeScales
        )

        val plot = singleLayerPlotContext.toPlot()

        assertEquals(expectedPlot, plot)
    }

    @Test
    fun `test layerPlotContext toPlot`() {
        val mockLayerFirst: Layer = mockk<Layer>()
        val mockLayerSecond: Layer = mockk<Layer>()

        val layerPlotContext = object : LayerPlotContext() {
            override val _layers: MutableList<Layer> = mutableListOf(mockLayerFirst, mockLayerSecond)
            override val datasetHandlers: MutableList<DatasetHandler> = mutableListOf(mockDataHandler)
            override val plotFeatures: MutableMap<FeatureName, PlotFeature> = mockPlotFeatures
            override val bindingCollector: BindingCollector = mockk {
                every { mappings } returns mockMappings
                every { settings } returns mockSettings
                every { freeScales } returns mockFreeScales
            }
            override val _plotContext: PlotContext = mockk()
            override val _layersInheritMappings: Boolean = false
            override val _datasetIndex: Int = 0
        }

        val expectedPlot = Plot(
            datasets = listOf(mockData),
            layers = listOf(mockLayerFirst, mockLayerSecond),
            globalMappings = mockMappings,
            globalSettings = mockSettings,
            features = mockPlotFeatures,
            freeScales = mockFreeScales
        )

        val plot = layerPlotContext.toPlot()

        assertEquals(expectedPlot, plot)
    }
}