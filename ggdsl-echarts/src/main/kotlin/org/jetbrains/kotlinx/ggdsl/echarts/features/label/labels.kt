/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features.label

import org.jetbrains.kotlinx.ggdsl.echarts.layers.*

public fun LineContextImmutable.label(block: LabelContext.() -> Unit) {
    val label = LabelContext().apply(block).toLabelFeature()
    if (label != null) features[LabelFeature.FEATURE_NAME] = label
}

public fun AreaContextImmutable.label(block: LabelContext.() -> Unit) {
    val label = LabelContext().apply(block).toLabelFeature()
    if (label != null) features[LabelFeature.FEATURE_NAME] = label
}

public fun BarContextImmutable.label(block: LabelContext.() -> Unit) {
    val label = LabelContext().apply(block).toLabelFeature()
    if (label != null) features[LabelFeature.FEATURE_NAME] = label
}

public fun PointContextImmutable.label(block: LabelContext.() -> Unit) {
    val label = LabelContext().apply(block).toLabelFeature()
    if (label != null) features[LabelFeature.FEATURE_NAME] = label
}

public fun PieContextImmutable.label(block: LabelContext.() -> Unit) {
    val label = LabelContext().apply(block).toLabelFeature()
    if (label != null) features[LabelFeature.FEATURE_NAME] = label
}
