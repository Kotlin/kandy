package org.jetbrains.kotlinx.ggdsl.letsplot.dsl


import org.jetbrains.kotlinx.ggdsl.dsl.plot

import org.jetbrains.kotlinx.ggdsl.ir.Layer

import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.BAR
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.POINT
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.bar
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.points

import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import org.jetbrains.kotlinx.ggdsl.letsplot.position.position
import kotlin.test.Test
import kotlin.test.assertEquals

internal class PositionTest {
    @Test
    fun testSimple() {
        val plot = plot {
            points {
                position = Position.Identity
            }
        }
        assertEquals(
            Plot(
                mapOf(),
                listOf(
                    Layer(
                        mapOf(),
                        POINT,
                        mapOf(),
                        mapOf(),
                        mapOf(Position.FEATURE_NAME to Position.Identity)
                    )
                ),
                null,
                mapOf()
            ),
            plot
        )
    }

    @Test
    fun testComplex() {
        val plot = plot {
            bar {
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
                mapOf(),
                listOf(
                    Layer(
                        mapOf(),
                        BAR,
                        mapOf(),
                        mapOf(),
                        mapOf(Position.FEATURE_NAME to Position.Stack)
                    ),
                    Layer(
                        mapOf(),
                        POINT,
                        mapOf(),
                        mapOf(),
                        mapOf(
                            Position.FEATURE_NAME to
                                Position.JitterDodge(2.0, 3.0, 1.0)
                        )
                    ),
                    Layer(
                        mapOf(),
                        POINT,
                        mapOf(),
                        mapOf(),
                        mapOf(Position.FEATURE_NAME to Position.Dodge(0.9))
                    )
                ),
                null,
                mapOf()
            ),
            plot
        )
    }
}
