/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.facet

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.Direction.Companion.HORIZONTAL
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.Direction.Companion.VERTICAL
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.OrderDirection.Companion.ASCENDING
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.OrderDirection.Companion.DESCENDING
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.ScalesSharing.Companion.FIXED
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.ScalesSharing.Companion.FREE
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.ScalesSharing.Companion.FREE_X
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.ScalesSharing.Companion.FREE_Y
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.context.FacetWrapContext
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.feature.FacetGridFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.feature.FacetWrapFeature

/**
 * Specifies ordering direction of columns and rows in the facet.
 *
 * @property ASCENDING ascending ordering direction (by default).
 * @property DESCENDING descending ordering direction.
 */
@Serializable
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
@Serializable
public data class Direction internal constructor(val name: String) {
    public companion object {
        public val VERTICAL: Direction = Direction("v")
        public val HORIZONTAL: Direction = Direction("h")
    }
}

/**
 * Scales sharing parameter across all facets.
 *
 * @property FIXED fixed scales.
 * @property FREE free scales.
 * @property FREE_X free scales across X.
 * @property FREE_Y free scales across Y.
 */
@Serializable
public data class ScalesSharing internal constructor(val name: String) {
    public companion object {
        public val FIXED: ScalesSharing = ScalesSharing("fixed")
        public val FREE: ScalesSharing = ScalesSharing("free")
        public val FREE_X: ScalesSharing = ScalesSharing("free_x")
        public val FREE_Y: ScalesSharing = ScalesSharing("free_y")
    }
}

/**
 * Splits data by a variable across X.
 * For each data subset creates a plot panel and lays out panels as grid.
 *
 * @param x Variable which defines columns of the facet grid.
 * @param scalesSharing Specifies whether scales are shared across all facets.
 * @param order Specifies ordering direction of columns
 * @param format Specifies the format pattern for displaying faceting values in columns.
 *
 * Format pattern in the format parameters can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$"
 *
 * Examples:
 * ".2f" -> "12.45"
 * "Score: {.2f}" -> "Score: 12.45"
 * "'Score: {}' "-> "Score: 12.454789"
 */
public fun LayerPlotContext.facetGridX(
    x: ColumnPointer<*>,
    scalesSharing: ScalesSharing? = null,
    order: OrderDirection = OrderDirection.ASCENDING,
    format: String? = null
) {
    features[FacetGridFeature.FEATURE_NAME] =
        FacetGridFeature(
            x.name, null, scalesSharing, order, ASCENDING, format, null
        )
}

/**
 * Splits data by a variable across Y.
 * For each data subset creates a plot panel and lays out panels as grid.
 *
 * @param y Variable which defines rows of the facet grid.
 * @param scalesSharing Specifies whether scales are shared across all facets.
 * @param order Specifies ordering direction of rows
 * @param format Specifies the format pattern for displaying faceting values in rows.
 *
 * Format pattern in the format parameters can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$"
 *
 * Examples:
 * ".2f" -> "12.45"
 * "Score: {.2f}" -> "Score: 12.45"
 * "'Score: {}' "-> "Score: 12.454789"
 */
public fun LayerPlotContext.facetGridY(
    y: ColumnPointer<*>,
    scalesSharing: ScalesSharing? = null,
    order: OrderDirection = OrderDirection.ASCENDING,
    format: String? = null
) {
    features[FacetGridFeature.FEATURE_NAME] =
        FacetGridFeature(null, y.name, scalesSharing, ASCENDING, order, null, format)
}

/**
 * Splits data by two faceting variables across X and Y.
 * For each data subset creates a plot panel and lays out panels as grid.
 * The grid columns are defined by X faceting variable and rows are defined by Y faceting variable.
 *
 * @param x Variable which defines columns of the facet grid.
 * @param y Variable which defines rows of the facet grid.
 * @param scalesSharing Specifies whether scales are shared across all facets.
 * @param xOrder Specifies ordering direction of columns
 * @param yOrder Specifies ordering direction of rows
 * @param xFormat Specifies the format pattern for displaying faceting values in columns.
 * @param yFormat Specifies the format pattern for displaying faceting values in rows.
 *
 * Format pattern in the xFormat/yFormat parameters can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$"
 *
 * Examples:
 * ".2f" -> "12.45"
 * "Score: {.2f}" -> "Score: 12.45"
 * "'Score: {}' "-> "Score: 12.454789"
 */
public fun LayerPlotContext.facetGrid(
    x: ColumnPointer<*>,
    y: ColumnPointer<*>,
    scalesSharing: ScalesSharing? = null,
    xOrder: OrderDirection = OrderDirection.ASCENDING,
    yOrder: OrderDirection = OrderDirection.ASCENDING,
    xFormat: String? = null,
    yFormat: String? = null
) {
    features[FacetGridFeature.FEATURE_NAME] =
        FacetGridFeature(x.name, y.name, scalesSharing, xOrder, yOrder, xFormat, yFormat)
}

/**
 * Splits data by one or more faceting variables.
 * For each data subset creates a plot panel and lays out panels according to the `
 * nCol`, `nRow` and `direction` settings.
 *
 * Opens a [FacetWrapContext]. [FacetWrapContext.facets] is defined in this context. This method adds
 * a new facet by a given variable.
 *
 * ```
 * facetWrap(nRow = 3, scalesSharing = ScalesSharing.FREE_X) {
 *    facet(col1)
 *    facet(col2, OrderDirection.DESCENDING)
 *    facet(col3, format = {.2f})
 * }
 * ```
 *
 * @param nCol Number of columns.
 * @param nRow Number of rows.
 * @param scalesSharing Specifies whether scales are shared across all facets.
 * @param direction direction of the facet.
 */
public fun LayerPlotContext.facetWrap(
    nCol: Int? = null,
    nRow: Int? = null,
    scalesSharing: ScalesSharing = ScalesSharing.FIXED,
    direction: Direction = Direction.HORIZONTAL,
    block: FacetWrapContext.() -> Unit
) {
    features[FacetWrapFeature.FEATURE_NAME] =
        FacetWrapContext().apply(block).toFeature(nCol, nRow, scalesSharing, direction)
}
