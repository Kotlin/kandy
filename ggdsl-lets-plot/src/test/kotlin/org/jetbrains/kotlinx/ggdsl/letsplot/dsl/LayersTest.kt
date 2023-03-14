/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.dsl

import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalCategoricalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalContinuousUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.ALPHA
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.FILL
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.SIZE
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.Y
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.AREA
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.area
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

internal class LayersTest {
    private val dataset = NamedData(dataFrameOf(
        "time" to listOf(1, 2),
        "type" to listOf("a", "b")
    ))
    @Test
    fun testArea() {
        val time = column<Int>("time")
        val type = column<String>("type")
        val plot = plot(dataset) {
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
                dataset,
                listOf(
                    Layer(
                        null,
                        AREA,
                        mapOf(
                            Y to ScaledPositionalUnspecifiedMapping(
                                Y, ColumnScaledPositionalUnspecified(
                                time,
                                PositionalContinuousUnspecifiedScale()
                            ), typeOf<Int>()
                            ),
                            FILL to ScaledNonPositionalMapping(
                                FILL,
                                ColumnScaledNonPositional(
                                    type,
                                    NonPositionalCategoricalScale<String, Color>(
                                        null,
                                        rangeValues = listOf(Color.RED, Color.BLUE),
                                        //null,
                                    )
                                ),
                                typeOf<String>(),
                                //  typeOf<Color>()
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
                mapOf(),emptyMap(),emptyMap()
            ),
            plot
        )
    }
    // todo others???
}


