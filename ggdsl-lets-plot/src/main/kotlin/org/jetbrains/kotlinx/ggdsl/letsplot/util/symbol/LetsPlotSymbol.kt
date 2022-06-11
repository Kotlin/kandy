package org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol

import org.jetbrains.kotlinx.ggdsl.util.symbol.Symbol

// todo complete
class LetsPlotSymbol internal constructor(val shape: Int): Symbol{
    companion object {
        val DIAMOND = LetsPlotSymbol(23)
    }
}
