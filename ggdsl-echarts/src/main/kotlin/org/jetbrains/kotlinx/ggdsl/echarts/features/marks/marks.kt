package org.jetbrains.kotlinx.ggdsl.echarts.features.marks

import org.jetbrains.kotlinx.ggdsl.echarts.layers.AreaContextImmutable
import org.jetbrains.kotlinx.ggdsl.echarts.layers.BarContextImmutable
import org.jetbrains.kotlinx.ggdsl.echarts.layers.LineContextImmutable

public fun LineContextImmutable.markPoint(block: MarkPointFeature.() -> Unit) {
    features[MarkPointFeature.FEATURE_NAME] = MarkPointFeature().apply(block)
}

public fun LineContextImmutable.markLine(block: MarkLineFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkLineFeature().apply(block)
}

public fun AreaContextImmutable.markPoint(block: MarkPointFeature.() -> Unit) {
    features[MarkPointFeature.FEATURE_NAME] = MarkPointFeature().apply(block)
}

public fun AreaContextImmutable.markLine(block: MarkLineFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkLineFeature().apply(block)
}

public fun BarContextImmutable.markPoint(block: MarkPointFeature.() -> Unit) {
    features[MarkPointFeature.FEATURE_NAME] = MarkPointFeature().apply(block)
}

public fun BarContextImmutable.markLine(block: MarkLineFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkLineFeature().apply(block)
}