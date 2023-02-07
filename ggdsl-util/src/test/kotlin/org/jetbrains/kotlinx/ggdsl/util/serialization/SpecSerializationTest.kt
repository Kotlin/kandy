package org.jetbrains.kotlinx.ggdsl.util.serialization

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.dsl.column.columnPointer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.points
import org.jetbrains.kotlinx.ggdsl.letsplot.translator.toLetsPlot
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.Symbol
import org.jetbrains.kotlinx.ggdsl.letsplot.x
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.letsPlot.intern.toSpec
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SpecSerializationTest {
    @Test
    fun testSimpleCase() = doTest(
        plot(dataOf {
            "origin" to listOf("x", "y", "z")
            "mpg" to listOf(1.3, 8.1, 5.0)
        }) {
            x(columnPointer<String>("origin"))
            points {
                y(columnPointer<Double>("mpg").scaled(continuousPos(limits = 1.0 to 5.0)))
                symbol(Symbol.CIRCLE_FILLED)
                fillColor(Color.RED)
            }
        }
    )

    private fun doTest(plot: Plot) {
        val spec = plot.toLetsPlot().toSpec()
        val serializedSpec = serializeSpec(spec)
        val deserializedSpec = deserializeSpec(serializedSpec)
        assertEquals(spec, deserializedSpec)
    }
}