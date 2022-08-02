package org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype

/**
 * Type of line.
 *
 * TODO detailed?
 */
class LineType internal constructor(val description: String, val codeNumber: Int)  {
    companion object {
        val BLANK = LineType("blank", 0)
        val SOLID = LineType("solid", 1)
        val DASHED = LineType("dashed", 2)
        val DOTTED = LineType("dotted", 3)
        val DOTDASH = LineType("dotdash", 4)
        val LONGDASH = LineType("longdash", 5)
        val TWODASH = LineType("twodash", 6)
    }
}
