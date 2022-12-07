package org.jetbrains.kotlinx.ggdsl.echarts.translator.option

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
internal data class AngleAxis(
    val id: String?,
    val polarIndex: Int?,
    val startAngle: Int?,
    val clockwise: Boolean?,
    val type: AxisType?,
    val boundaryGap: @Contextual Any?, // boolean array
    val min: @Contextual Any?, // number string Function
    val max: @Contextual Any?, // number string Function
    val scale: Boolean?,
    val splitNumber: Int?,
    val minInterval: Int?,
    val maxInterval: Int?,
    val interval: Int?,
    val logBase: Int?,
    val silent: Boolean?,
    val triggerEvent: Boolean?,
//    val axisLine: AxisLine?,
//    val axisTick: AxisTick?,
//    val minorTick: MinorTick?,
//    val axisLabel: AxisLabel?,
//    val splitLine: SplitLine?,
//    val minorSplitLine: MinorSplitLine?,
//    val splitArea: SplitArea?,
//    val data: !!!!,
//    val axisPointer: AxisPointer?,
//    val zlevel: Int?,
//    val z: Int?
)