package org.jetbrains.kotlinx.ggdsl.letsplot.util.font

public enum class FontFace {
    PLAIN, ITALIC, BOLD, BOLD_ITALIC;

    override fun toString(): String = super.toString().lowercase()
}