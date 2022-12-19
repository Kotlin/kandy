/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable

@Serializable
internal data class LabelLine(
    val show: Boolean? = null,
    val showAbove: Boolean? = null,
    val length2: Int? = null,
    val smooth: String? = null,
    val minTurnAngle: Int? = null,
    val lineStyle: LineStyle? = null,
)