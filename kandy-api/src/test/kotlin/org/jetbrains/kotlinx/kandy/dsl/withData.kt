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
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.test.Test
import kotlin.test.assertEquals

class WithDataTest {

    @Test
    fun withDataTest() {
        val datasetMain = mapOf(
            "x" to listOf(1.0, 2.0, 3.0),
            "y" to listOf(3F, 12F, 5.5F),
        ).toDataFrame()
        val srcX = column<Double>("x")
        val srcY = column<Float>("y")

        val datasetSecondary = mapOf(
            "width" to listOf(1.0, 2.0, 3.0, 3.0),
            "height" to listOf(3F, 12F, 5.5F, 8F),
            "type" to listOf("A", "B", "A", "B"),
        ).toDataFrame()

        val width = column<Double>("width")
        val height = column<Float>("height")
        val type = column<String>("type")

        val plot = datasetMain.plot {
            x(srcX)
            points {
                y(srcY)
            }
            withData(datasetSecondary) {
                line {
                    x(width)
                    y(height) {
                        scale = continuous(1f..15f)
                    }
                    color(type)
                }
            }
        }

        assertEquals(
            Plot(
                listOf(NamedData(datasetMain), NamedData(datasetSecondary)),
                listOf(
                    Layer(
                        0,
                        POINT,
                        mappings = mapOf(
                            Y to PositionalMapping<Float>(
                                Y, srcY.name(), CommonPositionalMappingParametersContinuous()
                            ),
                        ),
                        settings = emptyMap(),
                        emptyMap(),
                        emptyMap(),
                    ),
                    Layer(
                        1,
                        LINE,
                        mappings = mapOf(
                            X to PositionalMapping<Double>(
                                X, width.name(), CommonPositionalMappingParametersContinuous()
                            ),
                            Y to PositionalMapping(
                                Y, height.name(), CommonPositionalMappingParametersContinuous(
                                    PositionalContinuousScale(1f, 15f, null, null)
                                )
                            ),
                            COLOR to NonPositionalMapping<String, Color>(
                                COLOR, type.name(), CommonNonPositionalMappingParametersContinuous()
                            ),
                        ),
                        emptyMap(),
                        emptyMap(),
                        emptyMap(),
                        inheritsBindings = false
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
}

