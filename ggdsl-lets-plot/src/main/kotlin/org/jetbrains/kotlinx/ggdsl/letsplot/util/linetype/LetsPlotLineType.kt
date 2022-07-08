package org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype

import org.jetbrains.kotlinx.ggdsl.util.linetype.CommonLineType
import org.jetbrains.kotlinx.ggdsl.util.linetype.LineType

// todo name
class LetsPlotLineType internal constructor(val description: String, val codeNumber: Int) : LineType {
    //todo
    companion object {
        val BLANK = LetsPlotLineType("blank", 0)
        val SOLID = LetsPlotLineType("solid", 1)
        val DASHED = LetsPlotLineType("dashed", 2)
        val DOTTED = LetsPlotLineType("dotted", 3)
        val DOTDASH = LetsPlotLineType("dotdash", 4)
        val LONGDASH = LetsPlotLineType("longdash", 5)
        val TWODASH = LetsPlotLineType("twodash", 6)
    }
}
