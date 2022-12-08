package org.jetbrains.kotlinx.ggdsl.echarts.features.text

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.settings.LineType
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.EchartsTextStyle
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.toEchartsColor
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.context.SelfInvocationContext

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
public class TextStyle(
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
) : SelfInvocationContext {

    internal fun isEmpty(): Boolean =
        color == null && fontStyle == null && fontWeight == null && fontFamily == null
            && fontSize == null && lineHeight == null && width == null && height == null
            && textBorderColor == null && textBorderWidth == null && textBorderType == null

    internal fun isNotEmpty(): Boolean = !isEmpty()

    internal fun toTextStyle(): EchartsTextStyle? {
        return if (this.isEmpty())
            null
        else
            EchartsTextStyle(
                color = color?.toEchartsColor(),
                fontStyle = fontStyle?.style,
                fontWeight = fontWeight?.weight,
                fontFamily = fontFamily?.family,
                fontSize = fontSize,
                lineHeight = lineHeight,
                width = width,
                height = height,
                textBorderColor = textBorderColor?.toEchartsColor(),
                textBorderWidth = textBorderWidth,
                textBorderType = textBorderType?.type
            )
    }
}