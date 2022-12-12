package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable

@Serializable
public data class UniversalTransition(
    val enabled: Boolean? = null,
    val seriesKey: String? = null,
    val divideShape: String? = null,
    val delay: String? = null // TODO function
)