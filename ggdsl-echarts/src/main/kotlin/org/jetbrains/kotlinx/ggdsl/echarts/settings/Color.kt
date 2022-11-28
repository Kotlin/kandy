package org.jetbrains.kotlinx.ggdsl.echarts.settings

import org.jetbrains.kotlinx.ggdsl.util.color.Color

internal fun Color.toHexString(): String = when (this) {
    is Color.Hex -> this.hex
    is Color.RGB -> this.toHex().hex
    is Color.RGBA -> this.toHex().hex
    is Color.Named -> this.name
}
