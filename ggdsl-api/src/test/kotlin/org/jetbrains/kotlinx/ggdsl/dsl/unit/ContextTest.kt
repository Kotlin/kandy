package org.jetbrains.kotlinx.ggdsl.dsl.unit

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

internal class ContextTest {
    @Test
    fun testPoints() {
        val context = NamedDataPlotContext(NamedData(mapOf())).apply {
            points {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    null,
                    POINT,
                    emptyMap(),
                    emptyMap(),
                )
            ),
            context.layers
        )
    }

    @Test
    fun testLine() {
        val context = NamedDataPlotContext(NamedData(mapOf())).apply {
            line {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    null,
                    LINE,
                    emptyMap(),
                    emptyMap(),
                )
            ),
            context.layers
        )
    }

    @Test
    fun testBars() {
        val context = NamedDataPlotContext(NamedData(mapOf())).apply {
            bars {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    null,
                    BAR,
                    emptyMap(),
                    emptyMap(),
                )
            ),
            context.layers
        )
    }

    @Test
    fun testPlotEmpty() {
        val plot = plot(NamedData(mapOf())) { }
        assertEquals(
            Plot(
                null,
                listOf(),
                emptyMap(),
                emptyMap(),
            ),
            plot
        )
    }
}