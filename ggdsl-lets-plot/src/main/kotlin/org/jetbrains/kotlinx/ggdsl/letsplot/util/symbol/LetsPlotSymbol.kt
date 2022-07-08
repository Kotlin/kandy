package org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol

import org.jetbrains.kotlinx.ggdsl.util.symbol.Symbol

// todo complete
sealed interface LetsPlotSymbol : Symbol {
    val shape: Int
    data class Filled(override val shape: Int): LetsPlotSymbol
    data class Unfilled(override val shape: Int): LetsPlotSymbol
    companion object {
        val SQUARE_OPEN = Unfilled(0)
        val CIRCLE_OPEN = Unfilled(1)
        val TRIANGLE_OPEN = Unfilled(2)
        val PLUS = Unfilled(3)
        val CROSS = Unfilled(4)
        val DIAMOND_OPEN = Unfilled(5)
        val TRIANGLE_DOWN_OPEN = Unfilled(6)
        val SQUARE_CROSS = Unfilled(7)
        val ASTERIX = Unfilled(8)
        val DIAMOND_PLUS = Unfilled(9)
        val CIRCLE_PLUS = Unfilled(10)
        val STAR = Unfilled(11) // todo name
        val SQUARE_PLUS = Unfilled(12)
        val CIRCLE_CROSS = Unfilled(13)
        val SQUARE_TRIANGLE = Unfilled(14)
        val SQUARE = Unfilled(15)
        val CIRCLE = Unfilled(16)
        val TRIANGLE = Unfilled(17)
        val DIAMOND = Unfilled(18)
        val CIRCLE_SMALL = Unfilled(19)
        val BULLET = Unfilled(20)
        val CIRCLE_FILLED = Filled(21)
        val SQUARE_FILLED = Filled(22)
        val DIAMOND_FILLED = Filled(23)
        val TRIANGLE_FILLED = Filled(24)
        val TRIANGLE_DOWN_FILLED = Filled(25)
    }
}
