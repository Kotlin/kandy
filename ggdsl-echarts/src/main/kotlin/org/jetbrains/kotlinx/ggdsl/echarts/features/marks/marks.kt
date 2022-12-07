package org.jetbrains.kotlinx.ggdsl.echarts.features.marks

import org.jetbrains.kotlinx.ggdsl.echarts.layers.*

public fun LineContextImmutable.markPoint(block: MarkPointFeature.() -> Unit) {
    features[MarkPointFeature.FEATURE_NAME] = MarkPointFeature().apply(block)
}

public fun LineContextImmutable.markLine(block: MarkLineFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkLineFeature().apply(block)
}

public fun LineContextImmutable.markArea(block: MarkAreaFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkAreaFeature().apply(block)
}

public fun AreaContextImmutable.markPoint(block: MarkPointFeature.() -> Unit) {
    features[MarkPointFeature.FEATURE_NAME] = MarkPointFeature().apply(block)
}

public fun AreaContextImmutable.markLine(block: MarkLineFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkLineFeature().apply(block)
}

public fun AreaContextImmutable.markArea(block: MarkAreaFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkAreaFeature().apply(block)
}

public fun BarContextImmutable.markPoint(block: MarkPointFeature.() -> Unit) {
    features[MarkPointFeature.FEATURE_NAME] = MarkPointFeature().apply(block)
}

public fun BarContextImmutable.markLine(block: MarkLineFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkLineFeature().apply(block)
}

public fun BarContextImmutable.markArea(block: MarkAreaFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkAreaFeature().apply(block)
}

public fun PointContextImmutable.markPoint(block: MarkPointFeature.() -> Unit) {
    features[MarkPointFeature.FEATURE_NAME] = MarkPointFeature().apply(block)
}

public fun PointContextImmutable.markLine(block: MarkLineFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkLineFeature().apply(block)
}

public fun PointContextImmutable.markArea(block: MarkAreaFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkAreaFeature().apply(block)
}

public fun BoxplotContextImmutable.markPoint(block: MarkPointFeature.() -> Unit) {
    features[MarkPointFeature.FEATURE_NAME] = MarkPointFeature().apply(block)
}

public fun BoxplotContextImmutable.markLine(block: MarkLineFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkLineFeature().apply(block)
}

public fun BoxplotContextImmutable.markArea(block: MarkAreaFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkAreaFeature().apply(block)
}

public fun CandlestickContextImmutable.markPoint(block: MarkPointFeature.() -> Unit) {
    features[MarkPointFeature.FEATURE_NAME] = MarkPointFeature().apply(block)
}

public fun CandlestickContextImmutable.markLine(block: MarkLineFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkLineFeature().apply(block)
}

public fun CandlestickContextImmutable.markArea(block: MarkAreaFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkAreaFeature().apply(block)
}

public fun PieContextImmutable.markPoint(block: MarkPointFeature.() -> Unit) {
    features[MarkPointFeature.FEATURE_NAME] = MarkPointFeature().apply(block)
}

public fun PieContextImmutable.markLine(block: MarkLineFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkLineFeature().apply(block)
}

public fun PieContextImmutable.markArea(block: MarkAreaFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkAreaFeature().apply(block)
}