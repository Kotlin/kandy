/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.features.title

import org.jetbrains.kotlinx.kandy.echarts.features.text.TextStyle
import org.jetbrains.kotlinx.kandy.echarts.features.title.TextAlign.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.EchartsTitle
import org.jetbrains.kotlinx.kandy.echarts.translator.option.toEchartsColor
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext

/**
 * The align of the text.
 *
 * @property AUTO
 * @property LEFT
 * @property RIGHT
 * @property CENTER
 */
public enum class TextAlign(public val align: String) {
    AUTO("auto"), LEFT("left"), RIGHT("right"), CENTER("center")
}

/**
 * Title of the plot.
 *
 * @property text the main title text
 * @property subtext subtitle text
 * @property align the horizontal [align][TextAlign] of the text and subtext
 * @property verticalAlign the vertical [align][TextAlign] of the text and subtext
 * @property backgroundColor background [color][Color] of title. `transparent` by default
 * @property border border of title, includes `borderColor`, `borderWidth`
 * @property textStyle [font style][TextStyle] of the [text]
 * @property subtextStyle [font style][TextStyle] of the [subtext]
 *
 * @see TextAlign
 * @see TextStyle
 * @see Color
 */
public class Title(
    public var text: String? = null,
    public var subtext: String? = null,
    public var align: TextAlign? = null,
    public var verticalAlign: TextAlign? = null,
    public var backgroundColor: Color? = null,
    public var border: TitleBorder = TitleBorder(),
    public var textStyle: TextStyle = TextStyle(),
    public var subtextStyle: TextStyle = TextStyle()
) : SelfInvocationContext {

    internal fun toEchartsTitle(): EchartsTitle? =
        EchartsTitle(
            text = text,
            textStyle = textStyle.toTextStyle(),
            subtext = subtext,
            subtextStyle = subtextStyle.toTextStyle(),
            textAlign = align?.align,
            textVerticalAlign = verticalAlign?.align,
            backgroundColor = backgroundColor?.toEchartsColor(),
            borderColor = border.color?.toEchartsColor(),
            borderWidth = border.width,
            borderRadius = border.radius,
        ).takeIf { !it.isEmpty() }
}
