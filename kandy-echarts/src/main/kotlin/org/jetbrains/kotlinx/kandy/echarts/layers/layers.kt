/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.echarts.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.dsl.internal.MultiLayerPlotBuilder
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
public inline fun MultiLayerPlotBuilder.layout(block: EChartsLayout.() -> Unit) {
    plotFeatures[EChartsLayout.FEATURE_NAME] = EChartsLayout().apply(block)
}

/**
 * Adds a new [line] layer.
 *
 * Creates a [context][LineHandler]
 * in which you can create bindings using aesthetic attribute properties invocation and add features.
 *
 * - [ x][LineHandler.x] - mapping data on the x-axis.
 * - [y][LineHandler.y] - mapping data on the y-axis.
 * - [color][LineHandler.color] - line [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 * - [symbol][LineHandler.symbol] - [symbol][org.jetbrains.kotlinx.kandy.echarts.settings.Symbol] on line.
 *  Symbols are not shown by default.
 * - [smooth][LineHandler.smooth] - smooth curve.
 * `false` by default.
 * - [alpha][LineHandler.alpha] - line opacity.
 * - [width][LineHandler.width] - line width.
 * `2` by default.
 * - [lineType][LineHandler.lineType] - [line type][org.jetbrains.kotlinx.kandy.echarts.settings.LineType].
 *  `solid` by default.
 * - [step][LineHandler.step] - step line.
 * `false` by default.
 * - [cap][LineHandler.cap] - [end points][org.jetbrains.kotlinx.kandy.echarts.settings.Cap] of line.
 *  `butt` by default.
 * - [shadowColor][LineHandler.shadowColor] - shadow color of line.
 * - [shadowBlur][LineHandler.shadowBlur] - shadow blur size of line.
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
 * @see LineHandler
 */
public inline fun LayerCreatorScope.line(block: LineHandler.() -> Unit) {
    createLayer(LineHandler(this), block)
}

/**
 * Adds a new [area] layer.
 *
 * Creates a [context][AreaHandler]
 * in which you can create bindings using aesthetic attribute properties invocation and add features.
 *
 *  - [ x][AreaHandler.x] - mapping data on the x-axis.
 *  - [y][AreaHandler.y] - mapping data on the y-axis.
 *  - [color][AreaHandler.color] - area fill [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 *  - [position][AreaHandler.position] -
 *  origin [position][org.jetbrains.kotlinx.kandy.echarts.settings.AreaPosition] of area.
 *  `auto` by default.
 *  - [shadowBlur][AreaHandler.shadowBlur] - size of shadow blur of area.
 *  - [shadowColor][AreaHandler.shadowColor] -
 *  shadow [color][org.jetbrains.kotlinx.kandy.util.color.Color] of area.
 *  - [alpha][AreaHandler.alpha] - opacity of area.
 *  - [lineColor][AreaHandler.lineColor] - line [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 *  - [symbol][AreaHandler.symbol] - [symbol][org.jetbrains.kotlinx.kandy.echarts.settings.Symbol] on line.
 *  Symbols are not shown by default.
 *  - [smooth][AreaHandler.smooth] - smooth curve.
 *  `false` by default.
 *  - [lineAlpha][AreaHandler.lineAlpha] - line opacity.
 *  - [lineWidth][AreaHandler.lineWidth] - line width.
 *  `2` by default.
 *  - [lineType][AreaHandler.lineType] - [line type][org.jetbrains.kotlinx.kandy.echarts.settings.LineType].
 *  `solid` by default.
 *  - [step][AreaHandler.step] - step line.
 *  `false` by default.
 *  - [cap][AreaHandler.cap] - [end points][org.jetbrains.kotlinx.kandy.echarts.settings.Cap] of line.
 *  `butt` by default.
 *  - [lineShadowColor][AreaHandler.lineShadowColor] - shadow color of line.
 *  - [lineShadowBlur][AreaHandler.lineShadowBlur] - shadow blur size of line.
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
 * @see AreaHandler
 */
public inline fun LayerCreatorScope.area(block: AreaHandler.() -> Unit) {
    createLayer(AreaHandler(this), block)
}

/**
 * Adds a new [bars] layer.
 *
 * Creates a [context][BarHandler]
 * in which you can create bindings using aesthetic attribute properties invocation and add features.
 *
 * - [ x][BarHandler.x] - mapping data on the x-axis.
 * - [y][BarHandler.y] - mapping data on the y-axis.
 * - [color][BarHandler.color] - bars [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 * - [alpha][BarHandler.alpha] - bars opacity.
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
 * @see BarHandler
 */
public inline fun LayerCreatorScope.bars(block: BarHandler.() -> Unit) {
    createLayer(BarHandler(this), block)
}

public inline fun LayerCreatorScope.pie(block: PieHandler.() -> Unit) {
    createLayer(PieHandler(this), block)
}

/**
 * Adds a new [points] layer.
 *
 * Creates a [context][PointHandler]
 * in which you can create bindings using aesthetic attribute properties invocation and add features.
 *
 * - [ x][PointHandler.x] - mapping data on the x-axis.
 * - [y][PointHandler.y] - mapping data on the y-axis.
 * - [color][PointHandler.color] - points [color][org.jetbrains.kotlinx.kandy.util.color.Color].
 * - [symbol][PointHandler.symbol] - [symbol][org.jetbrains.kotlinx.kandy.echarts.settings.Symbol] of points.
 * `circle` by default.
 * - [size][PointHandler.size] - [symbol][org.jetbrains.kotlinx.kandy.echarts.settings.Symbol] size.
 * `10` by default.
 * - [alpha][PointHandler.alpha] - opacity of points.
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
 * @see PointHandler
 */
public inline fun LayerCreatorScope.points(block: PointHandler.() -> Unit) {
    createLayer(PointHandler(this), block)
}

public inline fun LayerCreatorScope.candlestick(block: CandlestickHandler.() -> Unit) {
    createLayer(CandlestickHandler(this), block)
}

public inline fun LayerCreatorScope.boxplot(block: BoxplotContext.() -> Unit) {
    createLayer(BoxplotContext(this), block)
}
