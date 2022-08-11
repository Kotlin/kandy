package org.jetbrains.kotlinx.ggdsl.letsplot.dsl

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.aes.Y
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalCategoricalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalContinuousUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.AREA
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.area
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

internal class LayersTest {
    @Test
    fun testArea() {
        val time = source<Int>("time")
        val type = source<String>("type")
        val plot = plot {
            area {
                y(time.scaled(continuousPos()))
                color(
                    type.scaled(
                        categorical(
                            rangeValues = listOf(Color.RED, Color.BLUE)
                        )
                    )
                )
                // TODO
                alpha(0.7)
                borderLine.width(2.0)

            }
        }
        // TODO
        assertEquals(
            Plot(
                mapOf(),
                listOf(
                    Layer(
                        mapOf(),
                        AREA,
                        mapOf(
                            Y to ScaledPositionalDefaultMapping(
                                Y, SourceScaledPositionalUnspecified(
                                    time,
                                    PositionalContinuousUnspecifiedScale()
                                ), typeOf<Int>()
                            ),
                            FILL to ScaledNonPositionalMapping(
                                FILL,
                                SourceScaledNonPositional(
                                    type,
                                    NonPositionalCategoricalScale(
                                        rangeValues = listOf(Color.RED, Color.BLUE)
                                    )
                                ),
                                typeOf<String>(),
                                //  typeOf<Color>()
                            )
                        ),
                        mapOf(
                            ALPHA to NonPositionalSetting(
                                ALPHA,
                                0.7
                            ),
                            SIZE to NonPositionalSetting(
                                SIZE,
                                2.0
                            )
                        ),
                        mapOf()
                    )
                ),
                null,
                mapOf()
            ),
            plot
        )
    }
    // todo others???
}


