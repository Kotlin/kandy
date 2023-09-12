package org.jetbrains.kotlinx.kandy.letsplot.facet

/**
 * Specifies ordering direction of columns and rows in the facet.
 *
 * @property ASCENDING ascending ordering direction (by default).
 * @property DESCENDING descending ordering direction.
 */
public data class OrderDirection internal constructor(val value: Int) {
    public companion object {
        public val ASCENDING: OrderDirection = OrderDirection(1)
        public val DESCENDING: OrderDirection = OrderDirection(-1)
    }
}

/**
 * Direction of the [facetWrap] parameter.
 *
 * @property VERTICAL vertical direction.
 * @property HORIZONTAL descending direction (by default).
 */
public data class Direction internal constructor(val name: String) {
    public companion object {
        public val VERTICAL: Direction = Direction("v")
        public val HORIZONTAL: Direction = Direction("h")
    }
}