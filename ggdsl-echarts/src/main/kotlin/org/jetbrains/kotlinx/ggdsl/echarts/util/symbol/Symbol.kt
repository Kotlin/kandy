package org.jetbrains.kotlinx.ggdsl.echarts.util.symbol

data class Symbol internal constructor(val name: String) {
    companion object {
        val CIRCLE = Symbol("circle")
        val TRIANGLE = Symbol("triangle")
        val RECT = Symbol("rect")
        val DIAMOND = Symbol("diamond")
        val ROUND_RECT = Symbol("roundRect")
        val PIN = Symbol("pin")
        val ARROW = Symbol("arrow")
        val NONE = Symbol("none")
    }
}
