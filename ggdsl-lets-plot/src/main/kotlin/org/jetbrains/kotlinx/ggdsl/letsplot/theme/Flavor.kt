package org.jetbrains.kotlinx.ggdsl.letsplot.theme

public enum class Flavor {
    DARCULA,
    SOLARIZED_LIGHT,
    SOLARIZED_DARK,
    HIGH_CONTRAST_LIGHT,
    HIGH_CONTRAST_DARK;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}
