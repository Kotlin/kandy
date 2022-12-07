package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable

@Serializable
internal data class Dimension(
    val name: String? = null,
    val type: String? = null,
    val displayName: String? = null
)