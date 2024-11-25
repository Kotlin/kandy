package org.jetbrains.kotlinx.kandy.letsplot.feature

import io.mockk.every
import io.mockk.mockk
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilderImpl
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import kotlin.test.Test
import kotlin.test.assertEquals

class CoordinatesTest {
    private val geom = mockk<Geom>()
    private  val mockLayer = Layer(
        datasetIndex = 0, geom = geom,
        mappings = mapOf(Aes("a") to mockk<Mapping>()),
        settings = mapOf(Aes("a") to mockk<Setting>()),
        features = emptyMap(), freeScales = emptyMap(), inheritsBindings = true
    )
    private val layerBuilder = mockk<LayerBuilderImpl> {
        every { toLayer() } returns mockLayer
    }

    @Test
    fun `test coordinates cartesian`() {
        val plot = plot {
            createLayer(layerBuilder) {}
            coordinatesTransformation = CoordinatesTransformation.cartesian()
        }
        assertEquals(CartesianCoordinatesTransformation, plot.features[CoordinatesTransformation.FEATURE_NAME])
    }

    @Test
    fun `test coordinates cartesian fixed`() {
        val ratio = 0.8
        val plot = plot {
            createLayer(layerBuilder) {}
            coordinatesTransformation = CoordinatesTransformation.cartesianFixed(ratio)
        }
        assertEquals(CartesianFixedCoordinatesTransformation(ratio), plot.features[CoordinatesTransformation.FEATURE_NAME])
    }

    @Test
    fun `test coordinates cartesian flipped`() {
        val plot = plot {
            createLayer(layerBuilder) {}
            coordinatesTransformation = CoordinatesTransformation.cartesianFlipped()
        }
        assertEquals(CartesianFlippedCoordinatesTransformation, plot.features[CoordinatesTransformation.FEATURE_NAME])
    }
}
