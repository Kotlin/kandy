package org.jetbrains.kotlinx.kandy.letsplot.export

import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotBunch
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotGrid
import kotlin.test.Test
import kotlin.test.assertNotNull

class HTMLTest {
    @Test
    fun `export plot as HTML`() {
        val plot: Plot = plot { line { x(listOf(1, 2, 3)); y(listOf(0.3, 0.15, 0.53)) } }

        val svgString = plot.toHTML()

        assertNotNull(svgString)
        assert(svgString.isNotBlank())
    }

    @Test
    fun `export plot grid as HTML`() {
        val plot: Plot = plot { line { x(listOf(1, 2, 3)); y(listOf(0.3, 0.15, 0.53)) } }
        val grid = plotGrid(listOf(plot, plot, plot))

        val svgString = grid.toHTML()

        assertNotNull(svgString)
        assert(svgString.isNotBlank())
    }

    @Test
    fun `export plot bunch as HTML`() {
        val plot: Plot = plot { line { x(listOf(1, 2, 3)); y(listOf(0.3, 0.15, 0.53)) } }
        val bunch = plotBunch {
            add(plot, 0, 0)
            add(plot, 400, 600)
            add(plot, 800, 600)
        }

        val svgString = bunch.toHTML()

        assertNotNull(svgString)
        assert(svgString.isNotBlank())
    }
}