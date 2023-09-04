/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.features

import org.jetbrains.kotlinx.kandy.echarts.settings.SizeUnit
import org.jetbrains.kotlinx.kandy.echarts.translator.option.EchartsGrid
import org.jetbrains.kotlinx.kandy.echarts.translator.option.toEchartsColor
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext

/**
 * Grid settings.
 *
 * @property left distance between a grid component and the left side of the container. `10%` by default.
 * @property top distance between a grid component and the top side of the container. `60` by default.
 * @property right distance between a grid component and the right side of the container. `10%` by default.
 * @property bottom distance between a grid component and the bottom side of the container. `60` by default.
 * @property width width of a grid component. Adaptive by default.
 * @property height height of a grid component. Adaptive by default.
 * @property backgroundColor background [color][Color] of the grid.
 * @property borderColor border [color][Color] of the grid.
 * @property borderWidth border width of the grid. `0` by default.
 * @property shadowBlur the size of shadow blur.
 * @property shadowColor shadow [color][Color].
 * @property tooltip [tooltip][Tooltip] settings in the coordinate system component.
 *
 * @see SizeUnit
 * @see Color
 * @see Tooltip
 */
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