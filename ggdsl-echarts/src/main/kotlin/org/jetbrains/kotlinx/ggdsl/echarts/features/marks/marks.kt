package org.jetbrains.kotlinx.ggdsl.echarts.features.marks

import org.jetbrains.kotlinx.ggdsl.echarts.layers.LineContext

public fun LineContext.markPoint(block: MarkPointFeature.() -> Unit) {
    features[MarkPointFeature.FEATURE_NAME] = MarkPointFeature().apply(block)
}

public fun LineContext.markLine(block: MarkLineFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkLineFeature().apply(block)
}