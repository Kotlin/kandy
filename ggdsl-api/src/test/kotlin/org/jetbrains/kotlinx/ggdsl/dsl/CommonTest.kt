/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalCategoricalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalContinuousUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CommonTest {

    @Test
    fun oneLayer() {
        val dataset = NamedData(mapOf(
            "x" to listOf(1.0, 2.0, 3.0),
            "y" to listOf(3F, 12F, 5.5F),
            "type" to listOf("A", "B", "A")
        ))
        val srcX = columnPointer<Double>("x")
        val srcY = columnPointer<Float>("y")
        val type = columnPointer<String>("type")

        val plot = plot(dataset) {
            x(srcX)
            points {
                y(srcY)
                color(type)
                size(4.5)
            }
        }

        assertEquals(
            Plot(
                dataset,
                listOf(
                    Layer(
                        null,
                        POINT,
                        mappings = mapOf(
                            X to ScaledUnspecifiedDefaultPositionalMapping(
                                X, ColumnScaledUnspecifiedDefault(srcX), typeOf<Double>()
                            ),
                            Y to ScaledUnspecifiedDefaultPositionalMapping(
                                Y, ColumnScaledUnspecifiedDefault(srcY), typeOf<Float>()
                            ),
                            COLOR to ScaledUnspecifiedDefaultNonPositionalMapping<String, Color>(
                                COLOR, ColumnScaledUnspecifiedDefault(type), typeOf<String>()
                            ),
                        ),
                        settings = mapOf(
                            SIZE to NonPositionalSetting(SIZE, 4.5)
                        )
                    )
                ),
                mapOf(
                    X to ScaledUnspecifiedDefaultPositionalMapping(
                        X, ColumnScaledUnspecifiedDefault(srcX), typeOf<Double>()
                    ),
                ),
                emptyMap(),
                emptyMap()
            ),
            plot
        )
    }

    @Test
    fun severalLayersAndScales() {
        val dataset = NamedData(mapOf(
            "width" to listOf(1.0, 2.0, 3.0, 3.0),
            "height" to listOf(3F, 12F, 5.5F, 8F),
            "type" to listOf("A", "B", "A", "B"),
            "number of attachments" to listOf(2, 5, 2, 4),
        ))
        val width = columnPointer<Double>("width")
        val height = columnPointer<Float>("height")
        val type = columnPointer<String>("type")
        val noa = columnPointer<Int>("number of attachments")

        val plot = plot(dataset) {
            x(width.scaled(continuousPos()))
            y(
                height.scaled(
                    continuousPos(
                        1f to 15f
                    )
                )
            )
            points {
                color(
                    type.scaled(
                        categorical(
                            domainCategories = listOf("A", "B"),
                            rangeValues = listOf(Color.RED, Color.named("blue"))
                        )
                    )
                )
                size(noa)
            }
            bars {
                width(5.0)
            }
        }

        val xMapping = ScaledPositionalUnspecifiedMapping(
            X, ColumnScaledPositionalUnspecified(width, PositionalContinuousUnspecifiedScale()), typeOf<Double>()
        )
        val yMapping = ScaledPositionalMapping(
            Y, ColumnScaledPositional(
            height, PositionalContinuousScale(
            limits = 1f to 15f
        )
        ), typeOf<Float>()
        )
        val colorMapping = ScaledNonPositionalMapping(
            COLOR,
            ColumnScaledNonPositional(
                type,
                NonPositionalCategoricalScale(
                    domainCategories = listOf("A", "B"),
                    rangeValues = listOf(Color.RED, Color.named("blue"))
                )
            ),
            typeOf<String>(),
            //typeOf<Color>()
        )

        assertEquals(
            Plot(
                dataset,
                listOf(
                    Layer(
                        null,
                        POINT,
                        mappings = mapOf(
                            X to xMapping,
                            Y to yMapping,
                            COLOR to colorMapping,
                            SIZE to ScaledUnspecifiedDefaultNonPositionalMapping<Int, Double>(
                                SIZE, ColumnScaledUnspecifiedDefault(noa), typeOf<Int>()
                            )
                        ),
                        settings = mapOf()
                    ),
                    Layer(
                        null,
                        BAR,
                        mappings = mapOf(
                            X to xMapping,
                            Y to yMapping,
                        ),
                        settings = mapOf(
                            WIDTH to NonPositionalSetting(WIDTH, 5.0)
                        )
                    )
                ),
                mapOf(
                    X to xMapping,
                    Y to yMapping,
                ),
                emptyMap(),
                emptyMap()
            ),
            plot
        )
    }
/*
    @Test
    fun datasetOverriding() {
        val emptyDataset = NamedData(mapOf())
        val realDataset = NamedData(mapOf(
            "name" to listOf("Tiny", "Pudge", "Spirit Breaker"),
            "winRate" to listOf(100.0, 0.01, 50.0),
            "iq" to listOf(12, 12, 1000)
        ))
        val wr = columnPointer<Double>("winRate")
        val iq = columnPointer<Int>("iq")
        val name = columnPointer<String>("name")

        val plot = plot(emptyDataset) {
            x(wr)
            y(iq)
            points {
                data = realDataset.toMutableMap()
                color(name)
            }
        }
        assertEquals(
            Plot(
                emptyDataset,
                listOf(
                    Layer(
                        realDataset,
                        POINT,
                        mappings = mapOf(
                            X to ScaledUnspecifiedDefaultPositionalMapping(
                                X, SourceScaledUnspecifiedDefault(wr), typeOf<Double>()
                            ),
                            Y to ScaledUnspecifiedDefaultPositionalMapping(
                                Y, SourceScaledUnspecifiedDefault(iq), typeOf<Int>()
                            ),
                            COLOR to ScaledUnspecifiedDefaultNonPositionalMapping<String, Color>(
                                COLOR, SourceScaledUnspecifiedDefault(name), typeOf<String>()
                            ),
                        ),
                        settings = mapOf()
                    )
                ),
                null,
            ),
            plot
        )
    }

 */
}

