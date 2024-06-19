/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.builders


/* TODO rewrite
class PlotBuildersTest {
    private val mockData = mockk<TableData>()
    private val mockDataHandler = mockk<DatasetHandler> {
        every { data() } returns mockData
    }
    private val mockPlotFeatures = mockk<MutableMap<FeatureName, PlotFeature>>()
    private val mockMappings = mockk<MutableMap<Aes, Mapping>>()
    private val mockSettings = mockk<MutableMap<Aes, Setting>>()
    private val mockFreeScales = mockk<MutableMap<Aes, FreeScale>>()

    @Test
    fun `test singleLayerPlotBuilder toPlot`() {
        val mockLayer: Layer = mockk<Layer>()

        val singleLayerPlotBuilder = object :
            SingleLayerPlotBuilder() {
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

        val plot = singleLayerPlotBuilder.toPlot()

        assertEquals(expectedPlot, plot)
    }

    @Test
    fun `test layerPlotBuilder toPlot`() {
        val mockLayerFirst: Layer = mockk<Layer>()
        val mockLayerSecond: Layer = mockk<Layer>()

        val layerPlotBuilder = mockk<MultiLayerPlotBuilder> {
           every { datasetHandlers} returns mutableListOf(mockDataHandler)
            every { layers } returns mutableListOf(mockLayerFirst, mockLayerSecond)
            every { bindingCollector } returns mockk<BindingCollector>() {
                every { mappings } returns mockMappings
                every { settings } returns mockSettings
                every { freeScales } returns mockFreeScales
            }
           every { plotFeatures } returns mockPlotFeatures
            *//*it.bindingCollector.mappings.putAll(mockMappings)
            it.bindingCollector.settings.putAll(mockSettings)
            it.bindingCollector.freeScales.putAll(mockFreeScales)
            it.plotFeatures.putAll(mockPlotFeatures)*//*
        }

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


}*/
