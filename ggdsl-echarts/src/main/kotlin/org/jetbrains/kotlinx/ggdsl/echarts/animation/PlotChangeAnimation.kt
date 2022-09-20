/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.animation

import org.jetbrains.kotlinx.ggdsl.ir.Plot

public data class PlotChangeAnimation(
    val plots: List<Plot>,
    val interval: Int,
)
