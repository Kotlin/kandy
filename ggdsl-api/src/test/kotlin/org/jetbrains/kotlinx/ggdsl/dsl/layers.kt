package org.jetbrains.kotlinx.ggdsl.dsl

inline fun LayerCollectorContext.points(block: PointsContext.() -> Unit) {
    addLayer(PointsContext(this).apply(block), POINT)
}

inline fun LayerCollectorContext.bars(block: BarsContext.() -> Unit) {
    addLayer(BarsContext(this).apply(block), BAR)
}

inline fun LayerCollectorContext.line(block: LineContext.() -> Unit) {
    addLayer(LineContext(this).apply(block), LINE)
}
