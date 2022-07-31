package org.jetbrains.kotlinx.ggdsl.dsl

/**
 * Symbol described by one string.
 *
 * @param description the string describing this symbol.
 */
data class Symbol(val description: String) {
    companion object {
        val CIRCLE = Symbol("circle")
        val TRIANGLE = Symbol("triangle")
        val RECT = Symbol("rect")
    }
}
