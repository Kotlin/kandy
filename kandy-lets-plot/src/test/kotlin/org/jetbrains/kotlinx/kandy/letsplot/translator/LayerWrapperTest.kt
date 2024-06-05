/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.translator

import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalCategoricalScale
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.BAR
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.POINT
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.letsPlot.intern.toSpec
import kotlin.test.Test
import kotlin.test.assertEquals

internal class LayerWrapperTest {
    @Test
    fun testSimple() {
        val mappings: Map<Aes, Mapping> = mapOf(
            FILL to NonPositionalMapping<Int, Color>(
                FILL,
                "F",
                LetsPlotNonPositionalMappingParametersContinuous()
            )
        )
        val layer = Layer(
            0,
            POINT,
            mappings,
            mapOf(
                COLOR to NonPositionalSetting<Color>(
                    COLOR,
                    Color.RED
                )
            ),
            mapOf(),
            emptyMap()
        )

        val wrappedLayer = LayerWrapper(layer, false, null, mappings, layer.settings, null)
        assertEquals(
            mapOf(
                "mapping" to mapOf(
                    "fill" to "F"
                ),
                "sampling" to "none",
                "stat" to "identity",
                "color" to "#ee6666",
                "position" to "identity",
                "geom" to "point"
            ),
            wrappedLayer.toSpec()
        )
    }

    @Test
    fun testComplex() {

        val dataset = mapOf(
            "TIME_T" to listOf(1.0F, 2.0F, 3.0F),
            "VAL_V" to listOf(19921.44, 42423.324, 34242.1987)
        )


        val mappings: Map<Aes, Mapping> = mapOf(
            X to PositionalMapping<Float>(
                X,
                "TIME_T",
                LetsPlotPositionalMappingParametersContinuous()
            ),
            Y to PositionalMapping<Double>(
                Y,
                "VAL_V",
                LetsPlotPositionalMappingParametersContinuous()
            ),
            FILL to NonPositionalMapping(
                FILL,
                "BAFGA",
                LetsPlotNonPositionalMappingParametersContinuous(
                    NonPositionalCategoricalScale<String, Color>(
                        null,
                        rangeValues = listOf(Color.BLACK, Color.WHITE, Color.GREY),
                        //null,
                    )
                )
            )
        )

        val layer = Layer(
            1,
            BAR,
            mappings,
            mapOf(
                COLOR to NonPositionalSetting<Color>(
                    COLOR,
                    Color.RED
                ),
                WIDTH to NonPositionalSetting<Double>(
                    WIDTH,
                    5.0
                ),
            ),
            mapOf(
                Position.FEATURE_NAME to Position.Dodge(0.9)
            ),
            emptyMap()
        )

        val wrappedLayer = LayerWrapper(layer, false, dataset, mappings, layer.settings, null)

        assertEquals(
            mapOf(
                "mapping" to mapOf(
                    "x" to "TIME_T",
                    "y" to "VAL_V",
                    "fill" to "BAFGA",
                ),
                "sampling" to "none",
                "stat" to "identity",
                "data" to dataset.map {
                    it.key to it.value.map { it.toDouble() }
                }.toMap(),
                "width" to 5.0,
                "color" to "#ee6666",
                "position" to mapOf(
                    "name" to "dodge",
                    "width" to 0.9,
                    // "kind" to "pos"
                ),
                "geom" to "bar",
                /*"data_meta" to mapOf<String, Any>(
                    "mapping_annotations" to
                        listOf(
                            mapOf(
                                "aes" to "fill",
                                "annotation" to "as_discrete",
                                "parameters" to mapOf<String, Any?>(
                                    "label" to "BAFGA",
                                    "order_by" to null,
                                    "order" to null
                                )
                            )
                        )
                )*/
            ),
            wrappedLayer.toSpec()
        )

    }

    @Test
    fun testBarNoSampling() {
        val data = mapOf(
            "v1" to List(100) { it },
            "v2" to List(100) { it }
        )
        val mappings: Map<Aes, Mapping> = mapOf(
            X to PositionalMapping<Int>(
                X, "v1", LetsPlotPositionalMappingParametersContinuous()
            ),
            Y to PositionalMapping<Int>(
                Y, "v2", LetsPlotPositionalMappingParametersContinuous()
            )
        )
        val layer = Layer(
            1,
            BAR,
            mappings,
            mapOf(),
            mapOf(),
            emptyMap()
        )

        val wrappedLayer = LayerWrapper(layer, false, data, mappings, emptyMap(), null)
        assertEquals(
            mapOf(
                "mapping" to mapOf(
                    "x" to "v1",
                    "y" to "v2",
                ),
                "stat" to "identity",
                "data" to data.map {
                    it.key to it.value.map { it.toDouble() }
                }.toMap(),
                "position" to "identity",
                "geom" to "bar",
                "sampling" to "none",
            ),
            wrappedLayer.toSpec()
        )
    }
}


