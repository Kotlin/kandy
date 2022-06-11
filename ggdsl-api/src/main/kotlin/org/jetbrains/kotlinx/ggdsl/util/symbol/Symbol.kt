package org.jetbrains.kotlinx.ggdsl.util.symbol

/**
 * Symbol base interface
 */
interface Symbol {
    companion object {
        val CIRCLE = CommonSymbol("circle")
        val RECTANGLE = CommonSymbol("rect")
        val TRIANGLE = CommonSymbol("triangle")
    }
}
