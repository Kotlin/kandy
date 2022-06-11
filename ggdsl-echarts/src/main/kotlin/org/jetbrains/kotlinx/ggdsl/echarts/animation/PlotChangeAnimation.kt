package org.jetbrains.kotlinx.ggdsl.echarts.animation

import org.jetbrains.kotlinx.ggdsl.ir.Plot

data class PlotChangeAnimation(
    val plots: List<Plot>,
    val interval: Int,
)
