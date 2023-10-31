/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.dsl


import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.BAR
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.POINT
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.position
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.letsplot.y
import kotlin.test.Test
import kotlin.test.assertEquals

internal class PositionTest {
    private val dataFrame = dataFrameOf(
        "x" to listOf(1, 2, 3),
        "y" to listOf(0.1, 0.2, 3.0)
    )
    @Test
    fun testSimple() {
        val plot = plot(dataFrame) {
            x("x")
            y("y")
            points {
                position = Position.Identity
            }
        }
        assertEquals(
            Plot(
                listOf(NamedData(dataFrame)),
                listOf(
                    Layer(
                        0,
                        POINT,
                        mapOf(),
                        mapOf(),
                        mapOf(Position.FEATURE_NAME to Position.Identity),
                        emptyMap()
                    )
                ),
                mapOf(
                    X to PositionalMapping<Int>(X, "x", LetsPlotPositionalMappingParametersContinuous()),
                    Y to PositionalMapping<Int>(Y, "y", LetsPlotPositionalMappingParametersContinuous())
                ),
                emptyMap(),
                emptyMap(),
                emptyMap(),
            ),
            plot
        )
    }

    @Test
    fun testComplex() {
        val plot = plot(dataFrame) {
            x("x")
            y("y")
            bars {
                position = Position.Stack
            }
            points {
                position = Position.JitterDodge(2.0, 3.0, 1.0)
            }
            points {
                position = Position.Dodge(0.9)
            }
        }
        assertEquals(
            Plot(
                listOf(NamedData(dataFrame)),
                listOf(
                    Layer(
                        0,
                        BAR,
                        mapOf(),
                        mapOf(),
                        mapOf(Position.FEATURE_NAME to Position.Stack),
                        emptyMap()
                    ),
                    Layer(
                        0,
                        POINT,
                        mapOf(),
                        mapOf(),
                        mapOf(
                            Position.FEATURE_NAME to
                                Position.JitterDodge(2.0, 3.0, 1.0)
                        ),
                        emptyMap()
                    ),
                    Layer(
                        0,
                        POINT,
                        mapOf(),
                        mapOf(),
                        mapOf(Position.FEATURE_NAME to Position.Dodge(0.9)),
                        emptyMap()
                    )
                ),
                mapOf(
                    X to PositionalMapping<Int>(X, "x", LetsPlotPositionalMappingParametersContinuous()),
                    Y to PositionalMapping<Int>(Y, "y", LetsPlotPositionalMappingParametersContinuous())
                ),
                emptyMap(),
                emptyMap(),
                emptyMap(),
            ),
            plot
        )
    }
}
