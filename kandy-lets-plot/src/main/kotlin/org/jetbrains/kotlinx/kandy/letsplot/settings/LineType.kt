/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.settings

/**
 * Type of line.
 */
public data class LineType internal constructor(val description: String, val codeNumber: Int) {
    public companion object {
        public val BLANK: LineType = LineType("blank", 0)
        public val SOLID: LineType = LineType("solid", 1)
        public val DASHED: LineType = LineType("dashed", 2)
        public val DOTTED: LineType = LineType("dotted", 3)
        public val DOTDASH: LineType = LineType("dotdash", 4)
        public val LONGDASH: LineType = LineType("longdash", 5)
        public val TWODASH: LineType = LineType("twodash", 6)
    }
}
