/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.x
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.y
import org.jetbrains.kotlinx.kandy.echarts.layers.context.*

/**
 * Adds a layer with settings and features to plot.
 *
 * - [size][EChartsLayout.size] - plot size.
 * - [title][EChartsLayout.title] - [title][org.jetbrains.kotlinx.kandy.echarts.features.Title] of plot.
 * - [textStyle][EChartsLayout.textStyle] - [font style][org.jetbrains.kotlinx.kandy.echarts.features.TextStyle].
 * - [grid][EChartsLayout.grid] - [grid][org.jetbrains.kotlinx.kandy.echarts.features.Grid] settings.
 * - [legend][EChartsLayout.legend] - [legend][org.jetbrains.kotlinx.kandy.echarts.features.Legend].
 * - [tooltip][EChartsLayout.tooltip] - [tooltip][org.jetbrains.kotlinx.kandy.echarts.features.Tooltip].
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
 * Creates a [context][LineContext]
 * in which you can create bindings using aesthetic attribute properties invocation and add features.
 *
 * - [ x][LineContext.x] - mapping data on the x-axis.
 * - [y][LineContext.y] - mapping data on the y-axis.
 * - [color][LineContext.color] - line [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 * - [symbol][LineContext.symbol] - [symbol][org.jetbrains.kotlinx.kandy.echarts.settings.Symbol] on line.
 *  Symbols are not shown by default.
 * - [smooth][LineContext.smooth] - smooth curve.
 * `false` by default.
 * - [alpha][LineContext.alpha] - line opacity.
 * - [width][LineContext.width] - line width.
 * `2` by default.
 * - [lineType][LineContext.lineType] - [line type][org.jetbrains.kotlinx.kandy.echarts.settings.LineType].
 *  `solid` by default.
 * - [step][LineContext.step] - step line.
 * `false` by default.
 * - [cap][LineContext.cap] - [end points][org.jetbrains.kotlinx.kandy.echarts.settings.Cap] of line.
 *  `butt` by default.
 * - [shadowColor][LineContext.shadowColor] - shadow color of line.
 * - [shadowBlur][LineContext.shadowBlur] - shadow blur size of line.
 *
 * ```kotlin
 * line {
 *    x(time)
 *    y(values)
 *    color = Color.RED
 *    symbol = Symbol.CIRCLE
 *    smooth = true
 *    alpha = .9
 *    width = 2.0
 *    lineType = LineType.SOLID
 *    step = Step.FALSE
 *    cap = Cap.BUTT
 *    shadowColor = Color.GREY
 *    shadowBlur = 10
 * }
 * ```
 *
 * @see LineContext
 */
public inline fun LayerCollectorContext.line(block: LineContext.() -> Unit) {
    addLayer(LineContext(this).apply(block), LINE)
}

/**
 * Adds a new [area] layer.
 *
 * Creates a [context][AreaContext]
 * in which you can create bindings using aesthetic attribute properties invocation and add features.
 *
 *  - [ x][AreaContext.x] - mapping data on the x-axis.
 *  - [y][AreaContext.y] - mapping data on the y-axis.
 *  - [color][AreaContext.color] - area fill [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 *  - [position][AreaContext.position] -
 *  origin [position][org.jetbrains.kotlinx.kandy.echarts.settings.AreaPosition] of area.
 *  `auto` by default.
 *  - [shadowBlur][AreaContext.shadowBlur] - size of shadow blur of area.
 *  - [shadowColor][AreaContext.shadowColor] -
 *  shadow [color][org.jetbrains.kotlinx.kandy.util.color.Color] of area.
 *  - [alpha][AreaContext.alpha] - opacity of area.
 *  - [lineColor][AreaContext.lineColor] - line [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 *  - [symbol][AreaContext.symbol] - [symbol][org.jetbrains.kotlinx.kandy.echarts.settings.Symbol] on line.
 *  Symbols are not shown by default.
 *  - [smooth][AreaContext.smooth] - smooth curve.
 *  `false` by default.
 *  - [lineAlpha][AreaContext.lineAlpha] - line opacity.
 *  - [lineWidth][AreaContext.lineWidth] - line width.
 *  `2` by default.
 *  - [lineType][AreaContext.lineType] - [line type][org.jetbrains.kotlinx.kandy.echarts.settings.LineType].
 *  `solid` by default.
 *  - [step][AreaContext.step] - step line.
 *  `false` by default.
 *  - [cap][AreaContext.cap] - [end points][org.jetbrains.kotlinx.kandy.echarts.settings.Cap] of line.
 *  `butt` by default.
 *  - [lineShadowColor][AreaContext.lineShadowColor] - shadow color of line.
 *  - [lineShadowBlur][AreaContext.lineShadowBlur] - shadow blur size of line.
 *
 * ```kotlin
 * area {
 *    x(time)
 *    y(values)
 *    color = Color.GREEN
 *    position = AreaPosition.START
 *    shadowBlur = 10
 *    shadowColor = Color.GREY
 *    alpha = .7
 * }
 * ```
 *
 * @see AreaContext
 */
public inline fun LayerCollectorContext.area(block: AreaContext.() -> Unit) {
    addLayer(AreaContext(this).apply(block), AREA)
}

/**
 * Adds a new [bars] layer.
 *
 * Creates a [context][BarContext]
 * in which you can create bindings using aesthetic attribute properties invocation and add features.
 *
 * - [ x][BarContext.x] - mapping data on the x-axis.
 * - [y][BarContext.y] - mapping data on the y-axis.
 * - [color][BarContext.color] - bars [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 * - [alpha][BarContext.alpha] - bars opacity.
 *
 * ```kotlin
 * bars {
 *    x(categories)
 *    y(values)
 *    color = Color.LIGHT_GREEN
 *    alpha = .5
 * }
 * ```
 *
 * @see BarContext
 */
public inline fun LayerCollectorContext.bars(block: BarContext.() -> Unit) {
    addLayer(BarContext(this).apply(block), BAR)
}

public inline fun LayerCollectorContext.pie(block: PieContext.() -> Unit) {
    addLayer(PieContext(this).apply(block), PIE)
}

/**
 * Adds a new [points] layer.
 *
 * Creates a [context][PointContext]
 * in which you can create bindings using aesthetic attribute properties invocation and add features.
 *
 * - [ x][PointContext.x] - mapping data on the x-axis.
 * - [y][PointContext.y] - mapping data on the y-axis.
 * - [color][PointContext.color] - points [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 * - [symbol][PointContext.symbol] - [symbol][org.jetbrains.kotlinx.kandy.echarts.settings.Symbol] of points.
 * `circle` by default.
 * - [size][PointContext.size] - [symbol][org.jetbrains.kotlinx.kandy.echarts.settings.Symbol] size.
 * `10` by default.
 * - [alpha][PointContext.alpha] - opacity of points.
 *
 * ```kotlin
 * points {
 *    x(col1)
 *    y(col2)
 *    color = Color.PURPLE
 *    symbol = Symbol.DIAMOND
 *    size = 20.0
 *    alpha = 1.0
 * }
 * ```
 *
 * @see PointContext
 */
public inline fun LayerCollectorContext.points(block: PointContext.() -> Unit) {
    addLayer(PointContext(this).apply(block), POINT)
}

public inline fun LayerCollectorContext.candlestick(block: CandlestickContext.() -> Unit) {
    addLayer(CandlestickContext(this).apply(block), CANDLESTICK)
}

public inline fun LayerCollectorContext.boxplot(block: BoxplotContext.() -> Unit) {
    addLayer(BoxplotContext(this).apply(block), BOXPLOT)
}
