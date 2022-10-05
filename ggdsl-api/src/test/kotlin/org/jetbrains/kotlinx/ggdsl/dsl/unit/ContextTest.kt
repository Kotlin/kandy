package org.jetbrains.kotlinx.ggdsl.dsl.unit

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

internal class ContextTest {
    private val emptyDataset = NamedData(mapOf())
    @Test
    fun testPoints() {
        val context = NamedDataPlotContext(emptyDataset).apply {
            points {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    emptyDataset,
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
        val context = NamedDataPlotContext(emptyDataset).apply {
            line {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    emptyDataset,
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
        val context = NamedDataPlotContext(emptyDataset).apply {
            bars {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    emptyDataset,
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
        val plot = plot(emptyDataset) { }
        assertEquals(
            Plot(
                emptyDataset,
                listOf(),
                emptyMap(),
                emptyMap(),
            ),
            plot
        )
    }
}