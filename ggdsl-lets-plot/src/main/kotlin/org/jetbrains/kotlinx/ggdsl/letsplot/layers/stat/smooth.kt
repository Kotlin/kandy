package org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat

public sealed interface SmoothMethod {
    public val name: String

    public data class Linear(val degree: Int = 1) : SmoothMethod {
        override val name: String = "lm"
    }

    public data class Loess(
        val span: Double = 0.5,
        val maxNumber: Int = 1000,
        val seed: Long? = null
    ) : SmoothMethod {
        override val name: String = "loess"
    }
}