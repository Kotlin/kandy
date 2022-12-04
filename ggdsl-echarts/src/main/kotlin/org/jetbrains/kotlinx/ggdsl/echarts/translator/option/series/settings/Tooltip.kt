package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.EchartsColor

@Serializable
public data class Tooltip(
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