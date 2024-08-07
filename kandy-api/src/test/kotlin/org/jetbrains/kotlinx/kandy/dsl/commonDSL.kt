/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl

import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.toDataFrame
import org.jetbrains.kotlinx.kandy.dsl.impl.*
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.NamedData
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalCategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.test.Test
import kotlin.test.assertEquals

class CommonDSLTest {

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
                                Y, srcY.name(), CommonPositionalMappingParametersContinuous()
                            ),
                            COLOR to NonPositionalMapping<String, Color>(
                                COLOR, type.name(), CommonNonPositionalMappingParametersContinuous()
                            ),
                        ),
                        settings = mapOf(
                            SIZE to NonPositionalSetting(SIZE, 4.5)
                        ),
                        emptyMap(),
                        emptyMap(),
                    )
                ),
                mapOf(
                    X to PositionalMapping<Float>(
                        X, srcX.name(), CommonPositionalMappingParametersContinuous()
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
            X, width.name(), CommonPositionalMappingParametersContinuous()
        )
        val yMapping = PositionalMapping(
            Y, height.name(), CommonPositionalMappingParametersContinuous(
                PositionalContinuousScale(1f, 15f, null, null)
            )
        )
        val colorMapping = NonPositionalMapping(
            COLOR,
            type.name(),
            CommonNonPositionalMappingParametersContinuous(
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
                                SIZE, noa.name(), CommonNonPositionalMappingParametersContinuous()
                            )
                        ),
                        settings = mapOf(),
                        emptyMap(),
                        emptyMap(),
                    ),
                    Layer(
                        0,
                        BAR,
                        mappings = mapOf(),
                        settings = mapOf(
                            WIDTH to NonPositionalSetting(WIDTH, 5.0)
                        ),
                        emptyMap(),
                        emptyMap(),
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
}

