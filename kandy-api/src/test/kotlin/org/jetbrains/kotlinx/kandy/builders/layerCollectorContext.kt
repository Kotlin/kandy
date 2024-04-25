/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.builders

import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilderImpl
import org.jetbrains.kotlinx.kandy.dsl.internal.MultiLayerPlotBuilder
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import kotlin.test.Test

class LayerCreatorScopeTest {

    @Test
    fun `test addLayer`() {
        val mockLayer = mockk<Layer>()
        val layers = mutableListOf<Layer>()
        val plotBuilder = mockk<MultiLayerPlotBuilder>() {
            every { addLayer(any()) } answers { layers.add(firstArg()) }
        }
        val layersInheritMappings = true
        val mockLayerBuilderImpl = mockk<LayerBuilderImpl>(relaxed = true) {
            every { toLayer() } returns mockLayer
        }


        val layerCreatorScopeImpl = object : LayerCreatorScope() {
            override val plotBuilder: MultiLayerPlotBuilder = plotBuilder
            override val datasetIndex: Int = 0
            override val layersInheritMappings: Boolean = layersInheritMappings
        }

        layerCreatorScopeImpl.createLayer(mockLayerBuilderImpl, {})

        assertEquals(1, layers.size)
        assertEquals(mockLayer, layers.first())
    }
}


