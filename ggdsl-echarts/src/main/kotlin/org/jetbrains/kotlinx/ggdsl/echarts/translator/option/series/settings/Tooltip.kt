package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.Color

@Serializable
public data class Tooltip(
    val position: String? = null,
    val formatter: String? = null,
    val valueFormatter: String? = null,
    val backgroundColor: Color? = null,
    val borderColor: Color? = null,
    val borderWidth: Int? = null,
    val padding: Int? = null,
//    val textStyle: TextStyle? = null, // TODO
    val extraCssText: String? = null
)