package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class CoordinateSystem {
    @SerialName("cartesian2d")
    CARTESIAN2D,

    @SerialName("polar")
    POLAR
}