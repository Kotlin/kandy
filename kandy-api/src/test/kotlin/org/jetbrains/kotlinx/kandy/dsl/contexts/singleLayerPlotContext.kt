package org.jetbrains.kotlinx.kandy.dsl.contexts

import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingCollector
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetHandler
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotContext
import org.jetbrains.kotlinx.kandy.dsl.internal.SingleLayerPlotContext
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.ir.bindings.FreeScale
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import kotlin.test.Test

class SingleLayerPlotContextTest {
    @Test
    fun `test toPlot`() {
        val mockLayer = mockk<Layer>()
        val mockData = mockk<TableData>()
        val mockDataHandler = mockk<DatasetHandler> {
            every { data() } returns mockData
        }
        val plotFeatures = mockk<MutableMap<FeatureName, PlotFeature>>()
        val mockMappings = mockk<MutableMap<AesName, Mapping>>()
        val mockSettings = mockk<MutableMap<AesName, Setting>>()
        val mockFreeScales = mockk<MutableMap<AesName, FreeScale>>()


        val singleLayerPlotContext = object : SingleLayerPlotContext {
            override val layer: Layer = mockLayer
            override val datasetHandlers: MutableList<DatasetHandler> = mutableListOf(mockDataHandler)
            override val plotFeatures: MutableMap<FeatureName, PlotFeature> = plotFeatures
            override val bindingCollector: BindingCollector = mockk() {
                every { mappings } returns mockMappings
                every { settings } returns mockSettings
                every { freeScales } returns mockFreeScales
            }
            override val plotContext: PlotContext = mockk()
            override val datasetIndex: Int = 0
        }

        val expectedPlot = Plot(
            datasets = listOf(mockData),
            layers = listOf(mockLayer),
            globalMappings = mockMappings,
            globalSettings = mockSettings,
            features = plotFeatures,
            freeScales = mockFreeScales
        )

        val plot = singleLayerPlotContext.toPlot()

        assertEquals(expectedPlot, plot)
    }
}