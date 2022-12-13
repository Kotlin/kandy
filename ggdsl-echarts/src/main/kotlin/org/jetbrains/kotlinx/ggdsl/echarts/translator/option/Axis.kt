/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.translator.option

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

internal enum class AxisType(val value: String) {
    VALUE("value"),
    CATEGORY("category"),
    TIME("time"),
    LOG("log"),
}

@Serializable
internal data class Axis(
    val id: String? = null,
    val show: Boolean? = null,
    val gridIndex: Int? = null,
    val alignTicks: Boolean? = null,
    val position: String? = null,
    val offset: Int? = null,
    val type: String,
    val name: String? = null,
    val nameLocation: String? = null,
//    val nameTextStyle: TextStyle?,
    val nameGap: Int? = null,
    val nameRotate: Int? = null,
    val inverse: Boolean? = null,
    val boundaryGap: @Contextual Any? = null, // boolean array
    val min: String? = null, // number string function
    val max: String? = null, // number string function
    val scale: Boolean? = null,
    val splitNumber: Int? = null,
    val minInterval: Int? = null,
    val interval: Int? = null,
    val logBase: Int? = null,
    val silent: Boolean? = null,
    val triggerEvent: Boolean? = null,
//    val axisLine: AxisLine?,
//    val axisTick: AxisTick?,
//    val minorTick: MinorTick?,
//    val axisLabel: AxisLabel?,
//    val splitLine: SplitLine?,
//    val minorSplitLine: MinorSplitLine?,
//    val splitArea: SplitArea?,
    val data: List<@Contextual Any>? = null,
//    val axisPointer: AxisPointer?,
    val zlevel: Int? = null,
    val z: Int? = null
)
