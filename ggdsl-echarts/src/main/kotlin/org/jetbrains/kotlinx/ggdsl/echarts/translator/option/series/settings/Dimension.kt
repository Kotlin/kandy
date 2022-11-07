package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable

@Serializable
public data class Dimension(
    val name: String? = null,
    val type: String? = null, // todo(TypeDimension)
    val displayName: String? = null
)