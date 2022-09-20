package org.jetbrains.kotlinx.ggdsl.echarts.animation

import org.jetbrains.kotlinx.ggdsl.ir.Plot

public data class PlotChangeAnimation(
    val plots: List<Plot>,
    val interval: Int,
)
