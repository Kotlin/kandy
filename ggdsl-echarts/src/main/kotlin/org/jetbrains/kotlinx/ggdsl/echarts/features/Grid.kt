package org.jetbrains.kotlinx.ggdsl.echarts.features

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.settings.SizeUnit
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.EchartsGrid
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.toEchartsColor
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.context.SelfInvocationContext

@PlotDslMarker
public class Grid(
    public var left: SizeUnit? = null,
    public var top: SizeUnit? = null,
    public var right: SizeUnit? = null,
    public var bottom: SizeUnit? = null,
    public var width: SizeUnit? = null,
    public var height: SizeUnit? = null,
    public var backgroundColor: Color? = null,
    public var borderColor: Color? = null,
    public var borderWidth: Int? = null,
    public var shadowBlur: Int? = null,
    public var shadowColor: Color? = null,
    public var tooltip: Tooltip? = null,
) : SelfInvocationContext {

    internal fun isEmpty(): Boolean =
        left == null && top == null && right == null && bottom == null && width == null && height == null
            && backgroundColor == null && borderColor == null && borderWidth == null && shadowBlur == null
            && shadowColor == null && tooltip == null

    internal fun isNotEmpty(): Boolean = !isEmpty()

    internal fun toEchartsGrid(): EchartsGrid? =
        if (this.isNotEmpty())
            EchartsGrid(
                left = left,
                top = top,
                right = right,
                bottom = bottom,
                width = width,
                height = height,
                backgroundColor = backgroundColor?.toEchartsColor(),
                borderColor = borderColor?.toEchartsColor(),
                borderWidth = borderWidth,
                shadowBlur = shadowBlur,
                shadowColor = shadowColor?.toEchartsColor(),
                tooltip = tooltip?.toEchartsTooltip(),
            )
        else
            null
}