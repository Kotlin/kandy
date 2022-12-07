package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class StackStrategy {
    @SerialName("samesign")
    SAMESIGN,

    @SerialName("all")
    ALL,

    @SerialName("positive")
    POSITIVE,

    @SerialName("negative")
    NEGATIVE
}