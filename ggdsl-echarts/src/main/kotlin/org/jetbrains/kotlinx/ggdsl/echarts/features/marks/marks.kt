package org.jetbrains.kotlinx.ggdsl.echarts.features.marks

import org.jetbrains.kotlinx.ggdsl.echarts.layers.EchartsLayerContextImmutable

public fun EchartsLayerContextImmutable.markPoint(block: MarkPointFeature.() -> Unit) {
    features[MarkPointFeature.FEATURE_NAME] = MarkPointFeature().apply(block)
}

public fun EchartsLayerContextImmutable.markLine(block: MarkLineFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkLineFeature().apply(block)
}

public fun EchartsLayerContextImmutable.markArea(block: MarkAreaFeature.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkAreaFeature().apply(block)
}
