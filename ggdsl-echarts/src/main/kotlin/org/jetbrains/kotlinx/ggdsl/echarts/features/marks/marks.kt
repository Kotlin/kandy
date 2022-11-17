package org.jetbrains.kotlinx.ggdsl.echarts.features.marks

import org.jetbrains.kotlinx.ggdsl.echarts.layers.LineContextImmutable

public fun LineContextImmutable.markPoint(block: MarkPointFeature.() -> Unit) {
    features[MarkPointFeature.FEATURE_NAME] = MarkPointFeature().apply(block)
}

public fun LineContextImmutable.markLine(block: MarkLineFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkLineFeature().apply(block)
}