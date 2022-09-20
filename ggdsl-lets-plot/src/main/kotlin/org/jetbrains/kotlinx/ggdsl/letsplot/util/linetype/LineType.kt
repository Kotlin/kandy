package org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype

/**
 * Type of line.
 *
 * TODO detailed?
 */
public class LineType internal constructor(public val description: String, public val codeNumber: Int) {
    public companion object {
        public val BLANK: LineType = LineType("blank", 0)
        public val SOLID: LineType = LineType("solid", 1)
        public val DASHED: LineType = LineType("dashed", 2)
        public val DOTTED: LineType = LineType("dotted", 3)
        public val DOTDASH: LineType = LineType("dotdash", 4)
        public val LONGDASH: LineType = LineType("longdash", 5)
        public val TWODASH: LineType = LineType("twodash", 6)
    }
}
