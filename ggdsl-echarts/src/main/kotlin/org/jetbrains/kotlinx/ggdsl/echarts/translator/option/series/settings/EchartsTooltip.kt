/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.EchartsColor

@Serializable
internal data class EchartsTooltip(
    val trigger: String? = null,
    val position: String? = null,
    val formatter: String? = null,
    val valueFormatter: String? = null,
    val backgroundColor: EchartsColor? = null,
    val borderColor: EchartsColor? = null,
    val borderWidth: Int? = null,
    val padding: Int? = null,
//    val textStyle: TextStyle? = null, // TODO
    val extraCssText: String? = null
)