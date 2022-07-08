package org.jetbrains.kotlinx.ggdsl.echarts.animation

import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData


data class DataChangeAnimation internal constructor(
    val plot: Plot,
    val interval: Int,
    val dataChange: NamedData.() -> Unit
)

fun Plot.withDataChangeAnimation(
    interval: Int,
    dataChange: NamedData.() -> Unit
) = DataChangeAnimation(this, interval, dataChange)

