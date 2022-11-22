package org.jetbrains.kotlinx.ggdsl.echarts.features.text

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.layers.EChartsLayout
import org.jetbrains.kotlinx.ggdsl.echarts.settings.Color
import org.jetbrains.kotlinx.ggdsl.echarts.settings.LineType
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.BaseColor
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.TextStyle
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature


public fun EChartsLayout.textStyle(block: TextStyleFeature.() -> Unit) {
    this.textStyle = TextStyleFeature().apply(block)
}

public enum class FontStyle(public val style: String) {
    NORMAL("normal"), ITALIC("italic"), OBLIQUE("oblique")
}

public class FontWeight private constructor(public val weight: String) {
    public companion object {
        public val NORMAL: FontWeight = FontWeight("normal")
        public val BOLD: FontWeight = FontWeight("bold")
        public val BOLDER: FontWeight = FontWeight("bolder")
        public val LIGHTER: FontWeight = FontWeight("lighter")

        public fun px(px: Int): FontWeight = FontWeight(px.toString())
    }
}

public enum class FontFamily(public val family: String) {
    SANS_SERIF("sans-serif"), SERIF("serif"), MONOSPACE("monospace")
}

@PlotDslMarker
public class TextStyleFeature(
    public var color: Color? = null,
    public var fontStyle: FontStyle? = null,
    public var fontWeight: FontWeight? = null,
    public var fontFamily: FontFamily? = null,
    public var fontSize: Int? = null,
    public var lineHeight: Int? = null,
    public var width: Int? = null,
    public var height: Int? = null,
    public var textBorderColor: Color? = null,
    public var textBorderWidth: Int? = null,
    public var textBorderType: LineType? = null
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
                fontStyle = fontStyle?.style,
                fontWeight = fontWeight?.weight,
                fontFamily = fontFamily?.family,
                fontSize = fontSize,
                lineHeight = lineHeight,
                width = width,
                height = height,
                textBorderColor = textBorderColor?.let { BaseColor(it.hex) },
                textBorderWidth = textBorderWidth,
                textBorderType = textBorderType?.type
            )
    }
}