package org.jetbrains.kotlinx.ggdsl.echarts.util.linetype


public data class LineType internal constructor(val name: String) {
    public companion object {
        public val SOLID: LineType = LineType("solid")
        public val DASHED: LineType = LineType("dashed")
        public val DOTTED: LineType = LineType("dotted")
    }
}
