/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl

import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.toDataFrame
import org.jetbrains.kotlinx.kandy.dsl.impl.*
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalCategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.dsl.impl.*
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CommonTest {

    @Test
    fun oneLayer() {
        val dataset = mapOf(
            "x" to listOf(1.0, 2.0, 3.0),
            "y" to listOf(3F, 12F, 5.5F),
            "type" to listOf("A", "B", "A")
        )
        val srcX = column<Double>("x")
        val srcY = column<Float>("y")
        val type = column<String>("type")

        val plot = plot(dataset) {
            x(srcX)
            points {
                y(srcY)
                color(type)
                size = 4.5
            }
        }

        assertEquals(
            Plot(
                listOf(NamedData(dataset.toDataFrame())),
                listOf(
                    Layer(
                        0,
                        POINT,
                        mappings = mapOf(
                            Y to PositionalMapping<Float>(
                                Y, srcY.name(), CommonPositionalMappingParameters()
                            ),
                            COLOR to NonPositionalMapping<String, Color>(
                                COLOR, type.name(), CommonNonPositionalMappingParameters()
                            ),
                        ),
                        settings = mapOf(
                            SIZE to NonPositionalSetting<Double>(SIZE, 4.5)
                        )
                    )
                ),
                mapOf(
                    X to PositionalMapping<Float>(
                        X, srcX.name(), CommonPositionalMappingParameters()
                    ),
                ),
                emptyMap(),
                emptyMap(),
                emptyMap()
            ),
            plot
        )
    }

    @Test
    fun severalLayersAndScales() {
        val dataset = mapOf(
            "width" to listOf(1.0, 2.0, 3.0, 3.0),
            "height" to listOf(3F, 12F, 5.5F, 8F),
            "type" to listOf("A", "B", "A", "B"),
            "number of attachments" to listOf(2, 5, 2, 4),
        ).toDataFrame()
        val width = column<Double>("width")
        val height = column<Float>("height")
        val type = column<String>("type")
        val noa = column<Int>("number of attachments")

        val plot = dataset.plot {
            x(width)
            y(height) {
                scale = continuous(1f..15f)
            }
            points {
                color(type) {
                    scale = categorical(
                        "A" to Color.RED, "B" to Color.named("blue")
                    )
                }
                size(noa)
            }
            bars {
                this.width = 5.0
            }
        }

        val xMapping = PositionalMapping<Double>(
            X, width.name(), CommonPositionalMappingParameters()
        )
        val yMapping = PositionalMapping<Float>(
            Y, height.name(), CommonPositionalMappingParameters<Float>(
                PositionalContinuousScale(1f,15f, null, null)
            )
        )
        val colorMapping = NonPositionalMapping<String, Color>(
            COLOR,
            type.name(),
            CommonNonPositionalMappingParameters(
                NonPositionalCategoricalScale<String, Color>(
                    domainCategories = listOf("A", "B"),
                    rangeValues = listOf(Color.RED, Color.named("blue")),
                )
            )
        )

        assertEquals(
            Plot(
                listOf(NamedData(dataset)),
                listOf(
                    Layer(
                        0,
                        POINT,
                        mappings = mapOf(
                            COLOR to colorMapping,
                            SIZE to NonPositionalMapping<Int, Double>(
                                SIZE, noa.name(), CommonNonPositionalMappingParameters()
                            )
                        ),
                        settings = mapOf()
                    ),
                    Layer(
                        0,
                        BAR,
                        mappings = mapOf(),
                        settings = mapOf(
                            WIDTH to NonPositionalSetting<Double>(WIDTH, 5.0)
                        )
                    )
                ),
                mapOf(
                    X to xMapping,
                    Y to yMapping,
                ),
                emptyMap(),
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
            val wr = ColumnReference<Double>("winRate")
            val iq = ColumnReference<Int>("iq")
            val name = ColumnReference<String>("name")

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

