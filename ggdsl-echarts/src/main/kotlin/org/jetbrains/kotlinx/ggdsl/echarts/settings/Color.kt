package org.jetbrains.kotlinx.ggdsl.echarts.settings

import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.color.StandardColor

internal fun Color.toHexString(): String = when (this) {
    is StandardColor -> this.description
    else -> throw Exception("colors mismatch")
}
