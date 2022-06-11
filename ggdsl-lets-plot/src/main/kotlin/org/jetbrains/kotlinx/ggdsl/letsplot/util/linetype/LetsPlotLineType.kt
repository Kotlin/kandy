package org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype

import org.jetbrains.kotlinx.ggdsl.util.linetype.LineType

class LetsPlotLineType internal constructor(val description: String): LineType {
    //todo
    companion object {
        val BLANK = LetsPlotLineType("blank")
        val DOTDASH = LetsPlotLineType("dotdash")
        val LONGDASH = LetsPlotLineType("longdash")
        val TWODASH = LetsPlotLineType("twodash")
    }
}
