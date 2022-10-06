/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.echarts.BAR
import org.jetbrains.kotlinx.ggdsl.echarts.LINE
import org.jetbrains.kotlinx.ggdsl.echarts.POINT


public inline fun PlotContext.points(block: PointsContext.() -> Unit) {
    addLayer(PointsContext(this).apply(block), POINT)
}

public inline fun PlotContext.bars(block: BarsContext.() -> Unit) {
    addLayer(BarsContext(this).apply(block), BAR)
}

public inline fun PlotContext.line(block: LineContext.() -> Unit) {
    addLayer(LineContext(this).apply(block), LINE)
}
