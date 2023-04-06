/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.util.font

import org.jetbrains.kotlinx.kandy.letsplot.util.SimpleValueWrapper

/**
 * Text horizontal justification.
 */
//@Serializable
public data class HorizontalJustification internal constructor(override val value: Double) : SimpleValueWrapper {
    public companion object {
        public val LEFT: HorizontalJustification = HorizontalJustification(0.0)
        public val MIDDLE: HorizontalJustification = HorizontalJustification(0.5)
        public val RIGHT: HorizontalJustification = HorizontalJustification(1.0)

        public fun custom(value: Double): HorizontalJustification = HorizontalJustification(value)
    }
}

/**
 * Text vertical justification.
 */
//@Serializable
public data class VerticalJustification internal constructor(override val value: Double) : SimpleValueWrapper {
    public companion object {
        public val BOTTOM: VerticalJustification = VerticalJustification(0.0)
        public val CENTER: VerticalJustification = VerticalJustification(0.5)
        public val TOP: VerticalJustification = VerticalJustification(1.0)

        public fun custom(value: Double): VerticalJustification = VerticalJustification(value)
    }
}
