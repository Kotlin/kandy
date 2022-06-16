package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.dsl.scaled
import org.jetbrains.kotlinx.ggdsl.dsl.source
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.aes.*
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalCategoricalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalContinuousDefaultScale
import org.jetbrains.kotlinx.ggdsl.letsplot.position.POSITION_FEATURE_NAME
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import jetbrains.letsPlot.intern.toSpec
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

internal class LayerWrapperTest {
    @Test
    fun testSimple() {
        val layer = Layer(
            mapOf(),
            Geom.POINT,
            mapOf(
                COLOR to ScaledUnspecifiedDefaultMapping(
                    COLOR,
                    source<Int>("F").scaled(),
                    typeOf<Int>()
                )
            ),
            mapOf(
                BORDER_COLOR to NonPositionalSetting(
                    BORDER_COLOR,
                    Color.RED
                )
            ),
            mapOf()
        )

        val wrappedLayer = LayerWrapper(layer)
        assertEquals(
            mapOf<String, Any>(
                "mapping" to mapOf(
                    "fill" to "F"
                ),
                "stat" to "identity",
                "data" to mapOf<String, Any>(),
                "shape" to 21.0,
                "color" to "red",
                "position" to "identity",
                "geom" to "point"
            ),
            wrappedLayer.toSpec()
        )
        /*
        println(wrappedLayer.toSpec())

        LayerAssert.assertThat(wrappedLayer)
            .aes("fill", "F")
            .parameter("color", "red")
            .geom()
            .kind(GeomKind.POINT)
            .noMapping()
        LayerAssert.assertThat(wrappedLayer)
            .stat()
            .kind(StatKind.IDENTITY)
        LayerAssert.assertThat(wrappedLayer)
            .position(PosKind.IDENTITY)

         */

    }

    @Test
    fun testComplex() {
        /*
        val dataset: NamedData = mapOf(
            "TIME_T" to listOf(1.0F, 2.0F, 3.0F),
            "VAL_V" to listOf(19921.44, 42423.324, 34242.1987)
        )

         */
        val layer = Layer(
            mapOf(),
            Geom.BAR,
            mapOf(
                X to ScaledUnspecifiedDefaultMapping(
                    X,
                    SourceScaledUnspecifiedDefault(
                        source<Float>("TIME_T")
                    ),
                    typeOf<Float>()
                ),
                Y to ScaledPositionalDefaultMapping(
                    Y,
                    SourceScaledPositionalDefault(
                        source<Double>("VAL_V"),
                        PositionalContinuousDefaultScale
                    ),
                    typeOf<Double>()
                ),
                COLOR to ScaledNonPositionalMapping(
                    COLOR,
                    SourceScaledNonPositional(
                        source<String>("BAFGA"),
                        NonPositionalCategoricalScale(
                            rangeValues = listOf(Color.BLACK, Color.WHITE, Color.GREY)
                        ),
                    ),
                    typeOf<String>(),
                    //typeOf<Color>()
                )
            ),
            mapOf(
                BORDER_COLOR to NonPositionalSetting(
                    BORDER_COLOR,
                    Color.RED
                ),
                WIDTH to NonPositionalSetting(
                    WIDTH,
                    5.0
                ),
            ),
            mapOf(
                POSITION_FEATURE_NAME to Position.Dodge(0.9)
            )
        )

        val wrappedLayer = LayerWrapper(layer)
println(wrappedLayer.toSpec())
        assertEquals(
            mapOf<String, Any>(
                "mapping" to mapOf(
                    "x" to "TIME_T",
                    "y" to "VAL_V",
                    "fill" to "BAFGA",
                ),
                "stat" to "identity",
                "data" to mapOf<String, Any>(),
                "width" to 5.0,
                "color" to "red",
                "position" to mapOf(
                    "name" to "dodge",
                    "width" to 0.9,
                    "kind" to "pos"
                ),
                "geom" to "bar"
            ),
            wrappedLayer.toSpec()
        )

        /*
        LayerAssert.assertThat(wrappedLayer)
            .aes("x", "TIME_T")
            .aes("y", "VAL_V")
            .aes("fill", "BAFGA")
            .parameter("color", "red")
            .parameter("width", 5.0)
            .geom()
            .kind(GeomKind.BAR)
            .noMapping()
        LayerAssert.assertThat(wrappedLayer)
            .stat()
            .kind(StatKind.IDENTITY)

        LayerAssert.assertThat(wrappedLayer)
            .position(PosKind.DODGE)
            .parameter("width", 0.9)


         */
    }
}