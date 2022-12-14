/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features.label

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.features.TextStyle
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

@PlotDslMarker
public class LabelContext(
    public var position: LabelPosition? = null,
    public var formatter: String? = null,
    public var textStyle: TextStyle = TextStyle(),
    public var border: Border = Border()

) {
    internal fun toLabelFeature(): LabelFeature? {
        val textStyle = textStyle.takeIf { it.isNotEmpty() }
        val border = border.takeIf { !it.isNotEmpty() }
        return if (position == null && formatter == null && textStyle == null && border == null)
            null
        else
            LabelFeature(position, formatter, textStyle, border)
    }
}

internal class LabelFeature(
    val position: LabelPosition?,
    val formatter: String?,
    val textStyle: TextStyle?,
    val border: Border?
) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    companion object {
        val FEATURE_NAME: FeatureName = FeatureName("LABEL_FEATURE")
    }
}
