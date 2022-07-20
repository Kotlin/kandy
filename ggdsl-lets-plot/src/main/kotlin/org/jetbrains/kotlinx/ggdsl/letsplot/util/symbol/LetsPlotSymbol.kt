package org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol

import org.jetbrains.kotlinx.ggdsl.util.symbol.Symbol

// todo complete
data class LetsPlotSymbol(val shape: Int) : Symbol {
    companion object {
        val SQUARE_OPEN = LetsPlotSymbol(0)
        val CIRCLE_OPEN = LetsPlotSymbol(1)
        val TRIANGLE_OPEN = LetsPlotSymbol(2)
        val PLUS = LetsPlotSymbol(3)
        val CROSS = LetsPlotSymbol(4)
        val DIAMOND_OPEN = LetsPlotSymbol(5)
        val TRIANGLE_DOWN_OPEN = LetsPlotSymbol(6)
        val SQUARE_CROSS = LetsPlotSymbol(7)
        val ASTERIX = LetsPlotSymbol(8)
        val DIAMOND_PLUS = LetsPlotSymbol(9)
        val CIRCLE_PLUS = LetsPlotSymbol(10)
        val STAR = LetsPlotSymbol(11) // todo name
        val SQUARE_PLUS = LetsPlotSymbol(12)
        val CIRCLE_CROSS = LetsPlotSymbol(13)
        val SQUARE_TRIANGLE = LetsPlotSymbol(14)
        val SQUARE = LetsPlotSymbol(15)
        val CIRCLE = LetsPlotSymbol(16)
        val TRIANGLE = LetsPlotSymbol(17)
        val DIAMOND = LetsPlotSymbol(18)
        val CIRCLE_SMALL = LetsPlotSymbol(19)
        val BULLET = LetsPlotSymbol(20)
        val CIRCLE_FILLED = LetsPlotSymbol(21)
        val SQUARE_FILLED = LetsPlotSymbol(22)
        val DIAMOND_FILLED = LetsPlotSymbol(23)
        val TRIANGLE_FILLED = LetsPlotSymbol(24)
        val TRIANGLE_DOWN_FILLED = LetsPlotSymbol(25)
    }
}
