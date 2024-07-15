/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.features

import org.jetbrains.kotlinx.kandy.echarts.layers.EChartsLayout
import org.jetbrains.kotlinx.kandy.echarts.settings.SizeUnit
import org.jetbrains.kotlinx.kandy.echarts.translator.option.EchartsLegend

/**
 * Shows name, symbol and color of different layers.
 *
 * - [type][Legend.type] - [type][LegendType] of legend. `plain` is default.
 * - [left][Legend.left] - distance between a legend component and the left side of the container. `auto` by default.
 * - [top][Legend.top] - distance between a legend component and the top side of the container. `auto` by default.
 * - [right][Legend.right] - distance between a legend component and the right side of the container. `auto` by default.
 * - [bottom][Legend.bottom] - distance between a legend component and the bottom side of the container. `auto` by default.
 * - [width][Legend.width] - width of a legend component. `auto` by default.
 * - [height][Legend.height] - height of a legend component. `auto` by default.
 * - [orient][Legend.orient] - the layout [orientation][Orient] of legend. `horizontal` by default.
 * - [formatter][Legend.formatter] - formatter is used to format label of legend.
 *
 * ```kotlin
 * plot(mapOf()) {
 *     layout {
 *         legend {
 *             type = LegendType.PLAIN
 *             left = 10.pct
 *             top = 60.px
 *             right = 10.pct
 *             bottom = 60.px
 *             width = 300.px
 *             height = 10.pct
 *             orient = Orient.VERTICAL
 *             formatter = "Legend {name}"
 *         }
 *     }
 * }
 * ```
 *
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.layout
 * @see SizeUnit
 * @see LegendType
 * @see Orient
 */
public inline fun EChartsLayout.legend(crossinline block: Legend.() -> Unit) {
    this.legend = Legend().apply(block)
}

public enum class LegendType(public val type: String) {
    PLAIN("plain"), SCROLL("scroll")
}

public enum class Orient(public val type: String) {
    HORIZONTAL("horizontal"), VERTICAL("vertical")
}

/**
 * Legend settings for plot.
 *
 * @property type [type][LegendType] of legend. `plain` is default.
 * @property left distance between a legend component and the left side of the container. `auto` by default.
 * @property top distance between a legend component and the top side of the container. `auto` by default.
 * @property right distance between a legend component and the right side of the container. `auto` by default.
 * @property bottom distance between a legend component and the bottom side of the container. `auto` by default.
 * @property width width of a legend component. `auto` by default.
 * @property height height of a legend component. `auto` by default.
 * @property orient the layout [orientation][Orient] of legend. `horizontal` by default.
 * @property formatter formatter is used to format label of legend.
 *
 * @see LegendType
 * @see Orient
 * @see SizeUnit
 */
public class Legend(
    public var type: LegendType? = null,
    public var left: SizeUnit? = null,
    public var top: SizeUnit? = null,
    public var right: SizeUnit? = null,
    public var bottom: SizeUnit? = null,
    public var width: SizeUnit? = null,
    public var height: SizeUnit? = null,
    public var orient: Orient? = null,
    public var formatter: String? = null,
) {

    internal fun isEmpty(): Boolean =
        type == null && left == null && top == null && right == null && bottom == null && width == null
                && height == null && orient == null && formatter == null

    internal fun isNotEmpty(): Boolean = !isEmpty()

    internal fun toEchartsLegend(): EchartsLegend =
        EchartsLegend(
            type = type?.type, left = left, top = top, right = right, bottom = bottom,
            width = width, height = height, orient = orient?.type, formatter = formatter
        )
}