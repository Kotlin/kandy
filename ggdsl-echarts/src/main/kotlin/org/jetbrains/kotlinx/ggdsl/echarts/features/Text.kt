/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.settings.LineType
import org.jetbrains.kotlinx.ggdsl.echarts.settings.Pixel
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.EchartsTextStyle
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.toEchartsColor
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringNumberArray
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringValue
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.singleOf
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.context.SelfInvocationContext

/**
 * Font style.
 *
 * @property NORMAL
 * @property ITALIC
 * @property OBLIQUE
 */
public enum class FontStyle(public val style: String) {
    NORMAL("normal"), ITALIC("italic"), OBLIQUE("oblique")
}

/**
 * Font thick weight.
 *
 * @property NORMAL
 * @property BOLD
 * @property BOLDER
 * @property LIGHTER
 */
public class FontWeight private constructor(internal val weight: StringNumberArray) {
    public companion object {
        public val NORMAL: FontWeight = FontWeight(StringValue("normal"))
        public val BOLD: FontWeight = FontWeight(StringValue("bold"))
        public val BOLDER: FontWeight = FontWeight(StringValue("bolder"))
        public val LIGHTER: FontWeight = FontWeight(StringValue("lighter"))

        /**
         * Sets the text width in pixels: `100 | 200 | 300 | 400...`
         */
        public fun px(px: Pixel): FontWeight = FontWeight(singleOf(px))
    }
}

/**
 * Font Family.
 *
 * @property SANS_SERIF
 * @property SERIF
 * @property MONOSPACE
 */
public enum class FontFamily(public val family: String) {
    SANS_SERIF("sans-serif"), SERIF("serif"), MONOSPACE("monospace")
}

/**
 * Font style settings.
 *
 * @property color text [color][Color]
 * @property fontStyle [font style][FontStyle]
 * @property fontWeight [font weight][FontWeight]
 * @property fontFamily [font family][FontFamily]
 * @property fontSize font size
 * @property lineHeight line height of the text fragment
 * @property width width of the text block
 * @property height height of the text block
 * @property textBorderColor stroke [color][Color] of the text
 * @property textBorderWidth stroke line width of the text
 * @property textBorderType stroke [line type][LineType] of the text
 *
 * @see FontStyle
 * @see FontWeight
 * @see FontFamily
 * @see Color
 * @see LineType
 */
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
                textBorderType = textBorderType?.value
            )
    }
}