/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.dsl


import org.jetbrains.kotlinx.ggdsl.dsl.NamedData
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
    private val emptyDataset = NamedData(mapOf())
    @Test
    fun testSimple() {
        val plot = plot(emptyDataset) {
            points {
                position = Position.Identity
            }
        }
        assertEquals(
            Plot(
                emptyDataset,
                listOf(
                    Layer(
                        emptyDataset,
                        POINT,
                        mapOf(),
                        mapOf(),
                        mapOf(Position.FEATURE_NAME to Position.Identity)
                    )
                ),
                emptyMap(),
                mapOf(),
                emptyMap()
            ),
            plot
        )
    }

    @Test
    fun testComplex() {
        val plot = plot(emptyDataset) {
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
                emptyDataset,
                listOf(
                    Layer(
                        emptyDataset,
                        BAR,
                        mapOf(),
                        mapOf(),
                        mapOf(Position.FEATURE_NAME to Position.Stack)
                    ),
                    Layer(
                        emptyDataset,
                        POINT,
                        mapOf(),
                        mapOf(),
                        mapOf(
                            Position.FEATURE_NAME to
                                Position.JitterDodge(2.0, 3.0, 1.0)
                        )
                    ),
                    Layer(
                        emptyDataset,
                        POINT,
                        mapOf(),
                        mapOf(),
                        mapOf(Position.FEATURE_NAME to Position.Dodge(0.9))
                    )
                ),
                mapOf(),
                emptyMap(),
                emptyMap()
            ),
            plot
        )
    }
}
