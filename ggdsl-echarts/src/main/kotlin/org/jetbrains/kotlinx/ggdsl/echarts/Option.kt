/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.util.color.EchartsColorOption

@Serializable
data class Option(
    val dataset: Dataset,
    val xAxis: List<Axis>,
    val yAxis: List<Axis>,
    val visualMap: List<VisualMap>? = null,
    val series: List<Series>,
    val title: Title? = null,

    var animation: Boolean = true, // TODO
    var animationThreshold: Int = 2000,
    var animationDuration: Int = 1000,
    var animationEasing: String = "cubicOut",
    var animationDelay: Int = 0,
)

@Serializable
data class Title(
    val text: String
)

@Serializable
data class Dataset(
    // TODO val source: List<List<@Contextual Any>>
    val source: List<List<String>>
)

@Serializable
data class Axis(
    val type: String,

    val show: Boolean? = null,
    val name: String? = null,

    val min: String? = null,
    val max: String? = null,

    val data: List<String>? = null,

    val axisTick: AxisTick? = AxisTick(),

    // todo
    val scale: Boolean = false,
)

@Serializable
data class AxisTick(
    val alignWithLabel: Boolean = true,
)

@Serializable
data class Series(
    //  val name: String,
    val type: String,
    val encode: XYEncode,
    val barWidth: Double? = null,
    val itemStyle: ItemStyle? = null,
    val symbolSize: Int? = null,
    val symbol: String? = null,
    val lineStyle: LineStyle? = null,
    val showSymbol: Boolean = false, //TODO
    //val stack: String? = null,
    // TODO
    val universalTransition: Boolean = true,
    val stack: String? = null,

    val data: List<List<String>>? = null,
)

@Serializable
data class XYEncode(
    val x: String,
    val y: String
)

@Serializable
data class ItemStyle(
    val color: EchartsColorOption? = null,
    val opacity: Double? = null,
    //val borderColor: String? = null,
    val borderColor: EchartsColorOption? = null,
    val borderWidth: Double? = null, // todo string?
)

@Serializable
data class LineStyle(
    val color: EchartsColorOption? = null,
    val width: Double? = null,
    val type: String? = null,
)

@Serializable
data class VisualMap(
    val type: String,

    val show: Boolean? = null,
    val text: List<String>? = null,
    val calculable: Boolean? = null,
    val dimension: Int,
    val seriesIndex: Int,

    // todo
    @Contextual
    val min: Double? = null,
    // todo
    @Contextual
    val max: Double? = null,

    val categories: List<String>? = null,

    val inRange: InRange,


    val top: Int? = null,
    val right: Int? = null,
)

@Serializable
data class InRange(
    // TODO
    val symbolSize: List<Double>? = null,
    val color: List<EchartsColorOption>? = null, // todo add gradient???
    val colorAlpha: List<Double>? = null,
    val symbol: List<String>? = null,
)
