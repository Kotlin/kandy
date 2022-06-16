package org.jetbrains.kotlinx.ggdsl.dsl.unit

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.*
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

internal class ContextTest {
    @Test
    fun testPoints() {
        val context = org.jetbrains.kotlinx.ggdsl.dsl.PlotContext().apply {
            points {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    mapOf(),
                    Geom.POINT,
                    mapOf(),
                    mapOf()
                )
            ),
            context.layers
        )
    }

    @Test
    fun testLine() {
        val context = org.jetbrains.kotlinx.ggdsl.dsl.PlotContext().apply {
            line {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    mapOf(),
                    Geom.LINE,
                    mapOf(),
                    mapOf()
                )
            ),
            context.layers
        )
    }

    @Test
    fun testBars() {
        val context = org.jetbrains.kotlinx.ggdsl.dsl.PlotContext().apply {
            bars {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    mapOf(),
                    Geom.BAR,
                    mapOf(),
                    mapOf()
                )
            ),
            context.layers
        )
    }

    @Test
    fun testPlotEmpty(){
        val plot = plot {  }
        assertEquals(
            Plot(
                mapOf(),
                listOf(),
                Layout(),
                mapOf()
            ),
            plot
        )
    }
}