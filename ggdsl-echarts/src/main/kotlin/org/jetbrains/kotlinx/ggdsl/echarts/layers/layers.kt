/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext

public inline fun LayerPlotContext.layout(block: EChartsLayout.() -> Unit) {
    features[EChartsLayout.FEATURE_NAME] = EChartsLayout().apply(block)
}

public inline fun LayerCollectorContextImmutable.line(block: LineContextImmutable.() -> Unit) {
    addLayer(LineContextImmutable(this).apply(block), LINE)
}

public inline fun LayerCollectorContextImmutable.area(block: AreaContextImmutable.() -> Unit) {
    addLayer(AreaContextImmutable(this).apply(block), AREA)
}

public inline fun LayerCollectorContextImmutable.bars(block: BarContextImmutable.() -> Unit) {
    addLayer(BarContextImmutable(this).apply(block), BAR)
}

public inline fun LayerCollectorContextImmutable.pie(block: PieContextImmutable.() -> Unit) {
    addLayer(PieContextImmutable(this).apply(block), PIE)
}

public inline fun LayerCollectorContextImmutable.points(block: PointContextImmutable.() -> Unit) {
    addLayer(PointContextImmutable(this).apply(block), POINT)
}

public inline fun LayerCollectorContextImmutable.candlestick(block: CandlestickContextImmutable.() -> Unit) {
    addLayer(CandlestickContextImmutable(this).apply(block), CANDLESTICK)
}

public inline fun LayerCollectorContextImmutable.boxplot(block: BoxplotContextImmutable.() -> Unit) {
    addLayer(BoxplotContextImmutable(this).apply(block), BOXPLOT)
}
