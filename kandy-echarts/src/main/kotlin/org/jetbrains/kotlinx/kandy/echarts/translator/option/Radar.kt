/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.option

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
internal data class Radar(
    val id: String?,
    val zlevel: Int?,
    val z: Int?,
    val center: List<@Contextual Any>?, // ['50%', '50%']
    val radius: @Contextual Any?, // number, string, array
    val startAngle: Int?,
//    val axisName: AxisName?,
    val nameGap: Int?,
    val splitNumber: Int?,
//    val shape: Shape?, // Radar render type, in which 'polygon' and 'circle' are supported.
    val scale: Boolean?,
    val silent: Boolean?,
    val triggerEvent: Boolean?,
//    val axisLine: AxisLine?,
//    val axisTick: AxisTick?,
//    val axisLabel: AxisLabel?,
//    val splitLine: SplitLine?,
//    val splitArea: SplitArea?,
//    val indicator: List?, //  { name , max , min , color }
)
