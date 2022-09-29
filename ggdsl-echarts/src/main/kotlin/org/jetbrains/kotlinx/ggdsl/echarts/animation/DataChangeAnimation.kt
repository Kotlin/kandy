package org.jetbrains.kotlinx.ggdsl.echarts.animation

import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface


data class DataChangeAnimation internal constructor(
    val plot: Plot,
    val interval: Int,
    val dataChange: NamedDataInterface.() -> Unit
)

fun Plot.withDataChangeAnimation(
    interval: Int,
    dataChange: NamedDataInterface.() -> Unit
) = DataChangeAnimation(this, interval, dataChange)

