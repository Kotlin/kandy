package org.jetbrains.kotlinx.ggdsl.letsplot.layers.label

import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.SimpleValueWrapper

data class HorizontalJustification internal constructor(override val value: Any): SimpleValueWrapper {
    companion object {
        val LEFT = HorizontalJustification("left")
        val MIDDLE = HorizontalJustification("middle")
        val RIGHT = HorizontalJustification("right")

        fun custom(value: Double) = HorizontalJustification(value)
    }
}

data class VerticalJustification internal constructor(override val value: Any): SimpleValueWrapper {
    companion object {
        val BOTTOM = VerticalJustification("bottom")
        val CENTER = VerticalJustification("center")
        val TOP = VerticalJustification("top")

        fun custom(value: Double) = VerticalJustification(value)
    }
}
