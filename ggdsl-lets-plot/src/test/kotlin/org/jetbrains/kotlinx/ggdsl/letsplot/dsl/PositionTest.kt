package org.jetbrains.kotlinx.ggdsl.letsplot.dsl

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.*
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.letsplot.position.POSITION_FEATURE_NAME
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import org.jetbrains.kotlinx.ggdsl.letsplot.position.position
import kotlin.test.Test
import kotlin.test.assertEquals

internal class PositionTest {
    @Test
    fun testSimple(){
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
                        Geom.POINT,
                        mapOf(),
                        mapOf(),
                        mapOf(POSITION_FEATURE_NAME to Position.Identity)
                    )
                ),
                Layout(),
                mapOf()
            ),
            plot
        )
    }

    @Test
    fun testComplex(){
        val plot = plot {
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
                mapOf(),
                listOf(
                    Layer(
                        mapOf(),
                        Geom.BAR,
                        mapOf(),
                        mapOf(),
                        mapOf(POSITION_FEATURE_NAME to Position.Stack)
                    ),
                    Layer(
                        mapOf(),
                        Geom.POINT,
                        mapOf(),
                        mapOf(),
                        mapOf(POSITION_FEATURE_NAME to
                                Position.JitterDodge(2.0, 3.0, 1.0))
                    ),
                    Layer(
                        mapOf(),
                        Geom.POINT,
                        mapOf(),
                        mapOf(),
                        mapOf(POSITION_FEATURE_NAME to Position.Dodge(0.9))
                    )
                ),
                Layout(),
                mapOf()
            ),
            plot
        )
    }
}