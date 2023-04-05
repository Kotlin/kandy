/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable

@Serializable
internal data class Select(
    val disabled: Boolean? = null,
    val label: Label? = null,
    val labelLine: LabelLine? = null,
    val itemStyle: ItemStyle? = null,
    val lineStyle: LineStyle? = null,
    val areaStyle: AreaStyle? = null,
    val endLabel: Label? = null
)