/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features.marks

import org.jetbrains.kotlinx.ggdsl.echarts.layers.EchartsLayerContextImmutable

public fun EchartsLayerContextImmutable.markPoint(block: MarkPointContext.() -> Unit) {
    features[MarkPointFeature.FEATURE_NAME] = MarkPointContext().apply(block).toMarkPointFeature()
}

public fun EchartsLayerContextImmutable.markLine(block: MarkLineContext.() -> Unit) {
    features[MarkLineFeature.FEATURE_NAME] = MarkLineContext().apply(block).toMarkLineFeature()
}

public fun EchartsLayerContextImmutable.markArea(block: MarkAreaContext.() -> Unit) {
    features[MarkAreaFeature.FEATURE_NAME] = MarkAreaContext().apply(block).toMarkAreaFeature()
}
