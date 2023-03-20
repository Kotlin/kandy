/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.dsl

import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.ggdsl.dsl.categorical
import org.jetbrains.kotlinx.ggdsl.dsl.continuous
import org.jetbrains.kotlinx.ggdsl.dsl.plot
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalCategoricalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.AREA
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.area
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import kotlin.test.Test
import kotlin.test.assertEquals

internal class LayersTest {
    private val dataset = dataFrameOf(
        "time" to listOf(1, 2),
        "type" to listOf("a", "b")
    )

    @Test
    fun testArea() {
        val time = column<Int>("time")
        val type = column<String>("type")
        val plot = dataset.plot {
            area {
                y(time) {
                    scale = continuous()
                }
                fillColor(type) {
                    scale =
                        categorical(
                            range = listOf(Color.RED, Color.BLUE)
                        )

                }
                // TODO
                alpha = 0.7
                borderLine.width = 2.0

            }
        }
        // TODO
        assertEquals(
            Plot(
                listOf(NamedData(dataset)),
                listOf(
                    Layer(
                        0,
                        AREA,
                        mapOf(
                            Y to PositionalMapping<Int>(
                                Y, time.name(), LetsPlotPositionalMappingParameters(
                                    PositionalContinuousScale(null, null, null, null)
                                )
                            ),
                            FILL to NonPositionalMapping<String, Color>(
                                FILL,
                                type.name(),
                                LetsPlotNonPositionalMappingParameters(
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
                        mapOf()
                    )
                ),
                mapOf(), emptyMap(), emptyMap(), emptyMap()
            ),
            plot
        )
    }
    // todo others???
}


