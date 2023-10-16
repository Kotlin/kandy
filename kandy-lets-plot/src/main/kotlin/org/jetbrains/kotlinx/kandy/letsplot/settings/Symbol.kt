/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.settings

/**
 * Point symbol.
 */
public data class Symbol internal constructor(val shape: Int) {
    public companion object {
        public val SQUARE_OPEN: Symbol = Symbol(0)
        public val CIRCLE_OPEN: Symbol = Symbol(1)
        public val TRIANGLE_OPEN: Symbol = Symbol(2)
        public val PLUS: Symbol = Symbol(3)
        public val CROSS: Symbol = Symbol(4)
        public val DIAMOND_OPEN: Symbol = Symbol(5)
        public val TRIANGLE_DOWN_OPEN: Symbol = Symbol(6)
        public val SQUARE_CROSS: Symbol = Symbol(7)
        public val ASTERIX: Symbol = Symbol(8)
        public val DIAMOND_PLUS: Symbol = Symbol(9)
        public val CIRCLE_PLUS: Symbol = Symbol(10)
        public val STAR: Symbol = Symbol(11)
        public val SQUARE_PLUS: Symbol = Symbol(12)
        public val CIRCLE_CROSS: Symbol = Symbol(13)
        public val SQUARE_TRIANGLE: Symbol = Symbol(14)
        public val SQUARE: Symbol = Symbol(15)
        public val CIRCLE: Symbol = Symbol(16)
        public val TRIANGLE: Symbol = Symbol(17)
        public val DIAMOND: Symbol = Symbol(18)
        public val CIRCLE_SMALL: Symbol = Symbol(19)
        public val BULLET: Symbol = Symbol(20)
        public val CIRCLE_FILLED: Symbol = Symbol(21)
        public val SQUARE_FILLED: Symbol = Symbol(22)
        public val DIAMOND_FILLED: Symbol = Symbol(23)
        public val TRIANGLE_FILLED: Symbol = Symbol(24)
        public val TRIANGLE_DOWN_FILLED: Symbol = Symbol(25)
    }
}
