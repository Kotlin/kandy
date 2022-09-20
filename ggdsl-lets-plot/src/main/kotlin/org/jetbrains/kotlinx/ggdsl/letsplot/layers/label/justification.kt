/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers.label

import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.SimpleValueWrapper

public data class HorizontalJustification internal constructor(override val value: Any) : SimpleValueWrapper {
    public companion object {
        public val LEFT: HorizontalJustification = HorizontalJustification("left")
        public val MIDDLE: HorizontalJustification = HorizontalJustification("middle")
        public val RIGHT: HorizontalJustification = HorizontalJustification("right")

        public fun custom(value: Double): HorizontalJustification = HorizontalJustification(value)
    }
}

public data class VerticalJustification internal constructor(override val value: Any) : SimpleValueWrapper {
    public companion object {
        public val BOTTOM: VerticalJustification = VerticalJustification("bottom")
        public val CENTER: VerticalJustification = VerticalJustification("center")
        public val TOP: VerticalJustification = VerticalJustification("top")

        public fun custom(value: Double): VerticalJustification = VerticalJustification(value)
    }
}
