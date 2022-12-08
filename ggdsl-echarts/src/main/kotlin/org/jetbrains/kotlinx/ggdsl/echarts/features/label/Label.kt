package org.jetbrains.kotlinx.ggdsl.echarts.features.label

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.features.text.TextStyle
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

@PlotDslMarker
public class LabelContext(
    public var position: LabelPosition? = null,
    public var formatter: String? = null,
    public var textStyle: TextStyle = TextStyle(),
    public var border: Border = Border()

) {
    internal fun toLabelFeature(): LabelFeature? = LabelFeature(
        position,
        formatter,
        textStyle.takeIf { it.isNotEmpty() },
        border.takeIf { !it.isNotEmpty() })
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
