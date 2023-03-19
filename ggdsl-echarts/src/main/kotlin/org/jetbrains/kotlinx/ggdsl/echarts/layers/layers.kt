/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.echarts.aes.x
import org.jetbrains.kotlinx.ggdsl.echarts.aes.y

/**
 * Adds a layer with settings and features to plot.
 *
 * - [size][EChartsLayout.size] - plot size.
 * - [title][EChartsLayout.title] - [title][org.jetbrains.kotlinx.ggdsl.echarts.features.Title] of plot.
 * - [textStyle][EChartsLayout.textStyle] - [font style][org.jetbrains.kotlinx.ggdsl.echarts.features.TextStyle].
 * - [grid][EChartsLayout.grid] - [grid][org.jetbrains.kotlinx.ggdsl.echarts.features.Grid] settings.
 * - [legend][EChartsLayout.legend] - [legend][org.jetbrains.kotlinx.ggdsl.echarts.features.Legend].
 * - [tooltip][EChartsLayout.tooltip] - [tooltip][org.jetbrains.kotlinx.ggdsl.echarts.features.Tooltip].
 *
 * ```kotlin
 * plot {
 *     layout {
 *         size = 500 to 400
 *         title {
 *             text = "Main Title"
 *             subtext = "Subtitle"
 *         }
 *         legend {}
 *         tooltip { trigger = Trigger.AXIS }
 *         grid { left = 60.px; top = 10.pct; right = 60.px; bottom = 10.pct }
 *         textStyle.color = Color.RED
 *     }
 * }
 * ```
 *
 * @see EChartsLayout
 */
public inline fun LayerPlotContext.layout(block: EChartsLayout.() -> Unit) {
    features[EChartsLayout.FEATURE_NAME] = EChartsLayout().apply(block)
}

/**
 * Adds a new [line] layer.
 *
 * Creates a [context][LineContextImmutable]
 * in which you can create bindings using aesthetic attribute properties invocation and add features.
 *
 * - [ x][LineContextImmutable.x] - mapping data on the x-axis.
 * - [y][LineContextImmutable.y] - mapping data on the y-axis.
 * - [color][LineContextImmutable.color] - line [color][org.jetbrains.kotlinx.ggdsl.util.color.Color].
 * - [symbol][LineContextImmutable.symbol] - [symbol][org.jetbrains.kotlinx.ggdsl.echarts.settings.Symbol] on line.
 *  Symbols are not shown by default.
 * - [smooth][LineContextImmutable.smooth] - smooth curve.
 * `false` by default.
 * - [alpha][LineContextImmutable.alpha] - line opacity.
 * - [width][LineContextImmutable.width] - line width.
 * `2` by default.
 * - [lineType][LineContextImmutable.lineType] - [line type][org.jetbrains.kotlinx.ggdsl.echarts.settings.LineType].
 *  `solid` by default.
 * - [step][LineContextImmutable.step] - step line.
 * `false` by default.
 * - [cap][LineContextImmutable.cap] - [end points][org.jetbrains.kotlinx.ggdsl.echarts.settings.Cap] of line.
 *  `butt` by default.
 * - [shadowColor][LineContextImmutable.shadowColor] - shadow color of line.
 * - [shadowBlur][LineContextImmutable.shadowBlur] - shadow blur size of line.
 *
 * ```kotlin
 * line {
 *    x(time)
 *    y(values)
 *    color(Color.RED)
 *    symbol(Symbol.CIRCLE)
 *    smooth(true)
 *    alpha(.9)
 *    width(2.0)
 *    lineType(LineType.SOLID)
 *    step(Step.FALSE)
 *    cap(Cap.BUTT)
 *    shadowColor(Color.GREY)
 *    shadowBlur(10)
 * }
 * ```
 *
 * @see LineContextImmutable
 */
public inline fun LayerCollectorContext.line(block: LineContextImmutable.() -> Unit) {
    addLayer(LineContextImmutable(this).apply(block), LINE)
}

/**
 * Adds a new [area] layer.
 *
 * Creates a [context][AreaContextImmutable]
 * in which you can create bindings using aesthetic attribute properties invocation and add features.
 *
 *  - [ x][AreaContextImmutable.x] - mapping data on the x-axis.
 *  - [y][AreaContextImmutable.y] - mapping data on the y-axis.
 *  - [color][AreaContextImmutable.color] - area fill [color][org.jetbrains.kotlinx.ggdsl.util.color.Color].
 *  - [position][AreaContextImmutable.position] -
 *  origin [position][org.jetbrains.kotlinx.ggdsl.echarts.settings.AreaPosition] of area.
 *  `auto` by default.
 *  - [shadowBlur][AreaContextImmutable.shadowBlur] - size of shadow blur of area.
 *  - [shadowColor][AreaContextImmutable.shadowColor] -
 *  shadow [color][org.jetbrains.kotlinx.ggdsl.util.color.Color] of area.
 *  - [alpha][AreaContextImmutable.alpha] - opacity of area.
 *  - [lineColor][AreaContextImmutable.lineColor] - line [color][org.jetbrains.kotlinx.ggdsl.util.color.Color].
 *  - [symbol][AreaContextImmutable.symbol] - [symbol][org.jetbrains.kotlinx.ggdsl.echarts.settings.Symbol] on line.
 *  Symbols are not shown by default.
 *  - [smooth][AreaContextImmutable.smooth] - smooth curve.
 *  `false` by default.
 *  - [lineAlpha][AreaContextImmutable.lineAlpha] - line opacity.
 *  - [lineWidth][AreaContextImmutable.lineWidth] - line width.
 *  `2` by default.
 *  - [lineType][AreaContextImmutable.lineType] - [line type][org.jetbrains.kotlinx.ggdsl.echarts.settings.LineType].
 *  `solid` by default.
 *  - [step][AreaContextImmutable.step] - step line.
 *  `false` by default.
 *  - [cap][AreaContextImmutable.cap] - [end points][org.jetbrains.kotlinx.ggdsl.echarts.settings.Cap] of line.
 *  `butt` by default.
 *  - [lineShadowColor][AreaContextImmutable.lineShadowColor] - shadow color of line.
 *  - [lineShadowBlur][AreaContextImmutable.lineShadowBlur] - shadow blur size of line.
 *
 * ```kotlin
 * area {
 *    x(time)
 *    y(values)
 *    color(Color.GREEN)
 *    position(AreaPosition.START)
 *    shadowBlur(10)
 *    shadowColor(Color.GREY)
 *    alpha(.7)
 * }
 * ```
 *
 * @see AreaContextImmutable
 */
public inline fun LayerCollectorContext.area(block: AreaContextImmutable.() -> Unit) {
    addLayer(AreaContextImmutable(this).apply(block), AREA)
}

/**
 * Adds a new [bars] layer.
 *
 * Creates a [context][BarContextImmutable]
 * in which you can create bindings using aesthetic attribute properties invocation and add features.
 *
 * - [ x][BarContextImmutable.x] - mapping data on the x-axis.
 * - [y][BarContextImmutable.y] - mapping data on the y-axis.
 * - [color][BarContextImmutable.color] - bars [color][org.jetbrains.kotlinx.ggdsl.util.color.Color].
 * - [alpha][BarContextImmutable.alpha] - bars opacity.
 *
 * ```kotlin
 * bars {
 *    x(categories)
 *    y(values)
 *    color(Color.LIGHT_GREEN)
 *    alpha(.5)
 * }
 * ```
 *
 * @see BarContextImmutable
 */
public inline fun LayerCollectorContext.bars(block: BarContextImmutable.() -> Unit) {
    addLayer(BarContextImmutable(this).apply(block), BAR)
}

public inline fun LayerCollectorContext.pie(block: PieContextImmutable.() -> Unit) {
    addLayer(PieContextImmutable(this).apply(block), PIE)
}

/**
 * Adds a new [points] layer.
 *
 * Creates a [context][PointContextImmutable]
 * in which you can create bindings using aesthetic attribute properties invocation and add features.
 *
 * - [ x][PointContextImmutable.x] - mapping data on the x-axis.
 * - [y][PointContextImmutable.y] - mapping data on the y-axis.
 * - [color][PointContextImmutable.color] - points [color][org.jetbrains.kotlinx.ggdsl.util.color.Color].
 * - [symbol][PointContextImmutable.symbol] - [symbol][org.jetbrains.kotlinx.ggdsl.echarts.settings.Symbol] of points.
 * `circle` by default.
 * - [size][PointContextImmutable.size] - [symbol][org.jetbrains.kotlinx.ggdsl.echarts.settings.Symbol] size.
 * `10` by default.
 * - [alpha][PointContextImmutable.alpha] - opacity of points.
 *
 * ```kotlin
 * points {
 *    x(col1)
 *    y(col2)
 *    color(Color.PURPLE)
 *    symbol(Symbol.DIAMOND)
 *    size(20.0)
 *    alpha(1.0)
 * }
 * ```
 *
 * @see PointContextImmutable
 */
public inline fun LayerCollectorContext.points(block: PointContextImmutable.() -> Unit) {
    addLayer(PointContextImmutable(this).apply(block), POINT)
}

public inline fun LayerCollectorContext.candlestick(block: CandlestickContextImmutable.() -> Unit) {
    addLayer(CandlestickContextImmutable(this).apply(block), CANDLESTICK)
}

public inline fun LayerCollectorContext.boxplot(block: BoxplotContextImmutable.() -> Unit) {
    addLayer(BoxplotContextImmutable(this).apply(block), BOXPLOT)
}
