package org.jetbrains.kotlinx.kandy.letsplot.export

import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotBunch
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotGrid
import kotlin.test.Test
import kotlin.test.assertNotNull

class SVGTest {
    @Test
    fun `export plot as SVG`() {
        val plot: Plot = plot { line { x(listOf(1, 2, 3)); y(listOf(0.3, 0.15, 0.53)) } }

        val svgString = plot.toSVG()

        assertNotNull(svgString)
        assert(svgString.isNotBlank())
    }

    @Test
    fun `export plot grid as SVG`() {
        val plot: Plot = plot { line { x(listOf(1, 2, 3)); y(listOf(0.3, 0.15, 0.53)) } }
        val grid = plotGrid(listOf(plot, plot, plot))

        val svgString = grid.toSVG()

        assertNotNull(svgString)
        assert(svgString.isNotBlank())
    }

    @Test
    fun `export plot bunch as SVG`() {
        val plot: Plot = plot { line { x(listOf(1, 2, 3)); y(listOf(0.3, 0.15, 0.53)) } }
        val bunch = plotBunch {
            add(plot, 0, 0)
            add(plot, 400, 600)
            add(plot, 800, 600)
        }

        val svgString = bunch.toSVG()

        assertNotNull(svgString)
        assert(svgString.isNotBlank())
    }
}