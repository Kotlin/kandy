package org.jetbrains.kotlinx.kandy.dsl.contexts

import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotContext
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import kotlin.test.Test

class LayerCollectorContextTest {

    @Test
    fun `test addLayer`() {
        val mockGeom = mockk<Geom>()
        val mockLayer = mockk<Layer>()
        val layers = mutableListOf<Layer>()
        val layersInheritMappings = true
        val mockLayerContext = mockk<LayerContextInterface>(relaxed = true) {
            every { toLayer(any(), any()) } returns mockLayer
        }

        val layerCollectorContext = object : LayerCollectorContext {
            override val plotContext: PlotContext = mockk(relaxed = true)
            override val datasetIndex: Int = 0
            override val layers: MutableList<Layer> = layers
            override val layersInheritMappings: Boolean = layersInheritMappings
        }

        layerCollectorContext.addLayer(mockLayerContext, mockGeom)

        assertEquals(1, layerCollectorContext.layers.size)
        assertEquals(mockLayer, layerCollectorContext.layers.first())
    }
}


