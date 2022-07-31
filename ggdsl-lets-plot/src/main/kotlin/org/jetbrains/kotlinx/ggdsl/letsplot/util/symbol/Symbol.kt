package org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol

data class Symbol(val shape: Int) {
    companion object {
        val SQUARE_OPEN = Symbol(0)
        val CIRCLE_OPEN = Symbol(1)
        val TRIANGLE_OPEN = Symbol(2)
        val PLUS = Symbol(3)
        val CROSS = Symbol(4)
        val DIAMOND_OPEN = Symbol(5)
        val TRIANGLE_DOWN_OPEN = Symbol(6)
        val SQUARE_CROSS = Symbol(7)
        val ASTERIX = Symbol(8)
        val DIAMOND_PLUS = Symbol(9)
        val CIRCLE_PLUS = Symbol(10)
        val STAR = Symbol(11)
        val SQUARE_PLUS = Symbol(12)
        val CIRCLE_CROSS = Symbol(13)
        val SQUARE_TRIANGLE = Symbol(14)
        val SQUARE = Symbol(15)
        val CIRCLE = Symbol(16)
        val TRIANGLE = Symbol(17)
        val DIAMOND = Symbol(18)
        val CIRCLE_SMALL = Symbol(19)
        val BULLET = Symbol(20)
        val CIRCLE_FILLED = Symbol(21)
        val SQUARE_FILLED = Symbol(22)
        val DIAMOND_FILLED = Symbol(23)
        val TRIANGLE_FILLED = Symbol(24)
        val TRIANGLE_DOWN_FILLED = Symbol(25)
    }
}
