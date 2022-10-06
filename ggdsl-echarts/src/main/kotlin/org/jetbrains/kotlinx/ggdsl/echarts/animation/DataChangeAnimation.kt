/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.animation

import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface


public data class DataChangeAnimation internal constructor(
    val plot: Plot,
    val interval: Int,
    val dataChange: NamedDataInterface.() -> Unit
)

public fun Plot.withDataChangeAnimation(
    interval: Int,
    dataChange: NamedDataInterface.() -> Unit
): DataChangeAnimation = DataChangeAnimation(this, interval, dataChange)

