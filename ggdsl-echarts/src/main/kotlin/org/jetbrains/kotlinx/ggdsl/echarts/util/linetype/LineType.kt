package org.jetbrains.kotlinx.ggdsl.echarts.util.linetype


data class LineType internal constructor(val name: String) {
    companion object {
        val SOLID = LineType("solid")
        val DASHED = LineType("dashed")
        val DOTTED = LineType("dotted")
    }
}
