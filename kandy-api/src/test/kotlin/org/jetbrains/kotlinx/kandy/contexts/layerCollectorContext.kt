/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.contexts

import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotContext
import org.jetbrains.kotlinx.kandy.ir.Layer
import kotlin.test.Test

class LayerCollectorContextTest {

    @Test
    fun `test addLayer`() {
        val mockLayer = mockk<Layer>()
        val layers = mutableListOf<Layer>()
        val layersInheritMappings = true
        val mockLayerContext = mockk<LayerContextInterface>(relaxed = true) {
            every { toLayer(any()) } returns mockLayer
        }

        val layerCollectorContext = object : LayerCollectorContext() {
            override val _plotContext: PlotContext = mockk(relaxed = true)
            override val _datasetIndex: Int = 0
            override val _layers: MutableList<Layer> = layers
            override val _layersInheritMappings: Boolean = layersInheritMappings
        }

        layerCollectorContext.addLayer(mockLayerContext)

        assertEquals(1, layerCollectorContext.layers.size)
        assertEquals(mockLayer, layerCollectorContext.layers.first())
    }
}


