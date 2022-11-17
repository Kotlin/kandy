package org.jetbrains.kotlinx.ggdsl.echarts.features

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.settings.Color
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.BaseColor
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.TextStyle
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature


public fun LayerPlotContext.textStyle(block: TextStyleFeature.() -> Unit) {
    features[TextStyleFeature.FEATURE_NAME] = TextStyleFeature().apply(block)
}

@PlotDslMarker
public class TextStyleFeature(
    public var color: Color? = null,
    public var fontStyle: String? = null,
    public var fontWeight: String? = null,
    public var fontFamily: String? = null,
    public var fontSize: Int? = null,
    public var lineHeight: Int? = null,
    public var width: Int? = null,
    public var height: Int? = null,
    public var textBorderColor: Color? = null,
    public var textBorderWidth: Int? = null,
    public var textBorderType: String? = null
) : PlotFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("TEXT_STYLE_FEATURE")
    }

    public fun isEmpty(): Boolean =
        color == null && fontStyle == null && fontWeight == null && fontFamily == null
            && fontSize == null && lineHeight == null && width == null && height == null
            && textBorderColor == null && textBorderWidth == null && textBorderType == null

    internal fun toTextStyle(): TextStyle? {
        return if (this.isEmpty())
            null
        else
            TextStyle(
                color = color?.let { BaseColor(it.hex) },
                fontStyle = fontStyle,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                fontSize = fontSize,
                lineHeight = lineHeight,
                width = width,
                height = height,
                textBorderColor = textBorderColor?.let { BaseColor(it.hex) },
                textBorderWidth = textBorderWidth,
                textBorderType = textBorderType
            )
    }
}