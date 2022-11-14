/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.echarts.BAR
import org.jetbrains.kotlinx.ggdsl.echarts.LINE
import org.jetbrains.kotlinx.ggdsl.echarts.POINT


public inline fun LayerCollectorContextImmutable.points(block: PointsContextImmutable.() -> Unit) {
    addLayer(PointsContextImmutable(this).apply(block), POINT)
}

public inline fun LayerCollectorContextImmutable.bars(block: BarsContextImmutable.() -> Unit) {
    addLayer(BarsContextImmutable(this).apply(block), BAR)
}

public inline fun LayerCollectorContextImmutable.line(block: LineContextImmutable.() -> Unit) {
    addLayer(LineContextImmutable(this).apply(block), LINE)
}
