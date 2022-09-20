package org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat

public enum class ViolinScale {
    AREA, COUNT, WIDTH;

    override fun toString(): String = super.toString().lowercase()
}