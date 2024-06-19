package org.jetbrains.kotlinx.kandy.letsplot.dsl.layers

import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalCategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.letsplot.dsl.dataset
import org.jetbrains.kotlinx.kandy.letsplot.dsl.time
import org.jetbrains.kotlinx.kandy.letsplot.dsl.type
import org.jetbrains.kotlinx.kandy.letsplot.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.area
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.AREA
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.test.Test
import kotlin.test.assertEquals

internal class AreaTest {
    @Test
    fun testArea() {
        val plot = dataset.plot {
            area {
                x(time)
                y(time) {
                    scale = continuous()
                }
                fillColor(type) {
                    scale =
                        categorical(
                            range = listOf(Color.RED, Color.BLUE)
                        )

                }
                alpha = 0.7
                borderLine.width = 2.0
            }
        }

        assertEquals(
            Plot(
                listOf(NamedData(dataset)),
                listOf(
                    Layer(
                        0,
                        AREA,
                        mapOf(
                            X to PositionalMapping<Int>(
                                X, time.name(), LetsPlotPositionalMappingParametersContinuous()
                            ),
                            Y to PositionalMapping<Int>(
                                Y, time.name(), LetsPlotPositionalMappingParametersContinuous(
                                    PositionalContinuousScale(null, null, null, null)
                                )
                            ),
                            FILL to NonPositionalMapping<String, Color>(
                                FILL,
                                type.name(),
                                LetsPlotNonPositionalMappingParametersContinuous(
                                    NonPositionalCategoricalScale<String, Color>(
                                        null,
                                        rangeValues = listOf(Color.RED, Color.BLUE),
                                    )
                                )
                            )
                        ),
                        mapOf(
                            ALPHA to NonPositionalSetting<Double>(
                                ALPHA,
                                0.7
                            ),
                            SIZE to NonPositionalSetting<Double>(
                                SIZE,
                                2.0
                            )
                        ),
                        mapOf(),
                        emptyMap()
                    )
                ),
                mapOf(), emptyMap(), emptyMap(), emptyMap()
            ),
            plot
        )
    }
}