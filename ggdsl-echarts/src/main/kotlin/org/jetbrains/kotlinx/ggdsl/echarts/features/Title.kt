/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.EchartsTitle
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.toEchartsColor
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.context.SelfInvocationContext

@Serializable
public enum class TextAlign(public val align: String) {
    AUTO("auto"), LEFT("left"), RIGHT("right"), CENTER("center")
}

@PlotDslMarker
public class Title(
    public var text: String? = null,
    public var subtext: String? = null,
    public var align: TextAlign? = null,
    public var verticalAlign: TextAlign? = null,
    public var backgroundColor: Color? = null,
    public var borderColor: Color? = null,
    public var borderWidth: Int? = null,
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
            borderColor = borderColor?.toEchartsColor(),
            borderWidth = borderWidth
        ).takeIf { !it.isEmpty() }
}
