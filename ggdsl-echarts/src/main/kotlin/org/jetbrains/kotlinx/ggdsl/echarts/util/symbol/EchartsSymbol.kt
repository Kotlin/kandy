package org.jetbrains.kotlinx.ggdsl.echarts.util.symbol

import org.jetbrains.kotlinx.ggdsl.util.symbol.Symbol

data class EchartsSymbol internal constructor(val name: String) : Symbol {
    companion object {
        val DIAMOND = EchartsSymbol("diamond")
        val ROUND_RECT = EchartsSymbol("roundRect")
        val PIN = EchartsSymbol("pin")
        val ARROW = EchartsSymbol("arrow")
        val NONE = EchartsSymbol("none")
    }
}
