package org.jetbrains.kotlinx.ggdsl.letsplot.util.font

import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.SimpleValueWrapper

public data class FontFamily internal constructor(override val value: String) : SimpleValueWrapper {
    public companion object {
        public val SANS: FontFamily = FontFamily("sans")
        public val SERIF: FontFamily = FontFamily("serif")
        public val MONO: FontFamily = FontFamily("mono")

        public fun custom(name: String): FontFamily = FontFamily(name)
    }
}
