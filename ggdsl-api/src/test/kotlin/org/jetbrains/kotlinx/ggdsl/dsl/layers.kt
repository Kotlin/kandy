package org.jetbrains.kotlinx.ggdsl.dsl

inline fun PlotContext.points(block: PointsContext.() -> Unit) {
    layers.add(PointsContext(data).apply { copyFrom(this@points) }.apply(block).toLayer(POINT))
}

inline fun PlotContext.bars(block: BarsContext.() -> Unit) {
    layers.add(BarsContext(data).apply { copyFrom(this@bars) }.apply(block).toLayer(BAR))
}

inline fun PlotContext.line(block: LineContext.() -> Unit) {
    layers.add(LineContext(data).apply { copyFrom(this@line) }.apply(block).toLayer(LINE))
}
