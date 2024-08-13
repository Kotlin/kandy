/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.datasetBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.plotFeatures
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.ScalesSharing.Companion.FIXED
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.ScalesSharing.Companion.FREE
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.ScalesSharing.Companion.FREE_X
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.ScalesSharing.Companion.FREE_Y
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.context.FacetWrapContext
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.feature.FacetGridFeature
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.feature.FacetWrapFeature

/**
 * Scales sharing parameter across all facets.
 *
 * @property FIXED fixed scales.
 * @property FREE free scales.
 * @property FREE_X free scales across X.
 * @property FREE_Y free scales across Y.
 */
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
 * @param order Specifies the ordering direction of columns
 * @param format Specifies the format pattern for displaying faceting values in columns.
 *
 * Format pattern in the format parameters can be just a number format (like "d") or
 * a string template where a number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$"
 *
 * Examples:
 * ".2f" -> "12.45"
 * "Score: {.2f}" -> "Score: 12.45"
 * "'Score: {}' "-> "Score: 12.454789"
 */
public fun PlotBuilder.facetGridX(
    x: ColumnReference<*>,
    scalesSharing: ScalesSharing? = null,
    order: OrderDirection = OrderDirection.ASCENDING,
    format: String? = null
) {
    @Suppress("INVISIBLE_MEMBER")
    val xColName = datasetBuilder.takeColumn(x.name())
    @Suppress("INVISIBLE_MEMBER")
    plotFeatures[FacetGridFeature.FEATURE_NAME] =
        FacetGridFeature(
            xColName, null, scalesSharing, order, OrderDirection.ASCENDING, format, null
        )
}

/**
 * Splits data by a variable across Y.
 * For each data subset creates a plot panel and lays out panels as grid.
 *
 * @param y variable which defines rows of the facet grid.
 * @param scalesSharing specifies whether scales are shared across all facets.
 * @param order specifies the ordering direction of rows
 * @param format specifies the format pattern for displaying faceting values in rows.
 *
 * Format pattern in the format parameters can be just a number format (like "d") or
 * a string template where the number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$"
 *
 * Examples:
 * ".2f" -> "12.45"
 * "Score: {.2f}" -> "Score: 12.45"
 * "'Score: {}' "-> "Score: 12.454789"
 */
public fun PlotBuilder.facetGridY(
    y: ColumnReference<*>,
    scalesSharing: ScalesSharing? = null,
    order: OrderDirection = OrderDirection.ASCENDING,
    format: String? = null
) {
    @Suppress("INVISIBLE_MEMBER")
    val yColName = datasetBuilder.takeColumn(y.name())
    @Suppress("INVISIBLE_MEMBER")
    plotFeatures[FacetGridFeature.FEATURE_NAME] =
        FacetGridFeature(null, yColName, scalesSharing, OrderDirection.ASCENDING, order, null, format)
}

/**
 * Splits data by two faceting variables across X and Y.
 * For each data subset creates a plot panel and lays out panels as grid.
 * The grid columns are defined by X faceting variable, and rows are defined by Y faceting variable.
 *
 * @param x variable which defines columns of the facet grid.
 * @param y variable which defines rows of the facet grid.
 * @param scalesSharing specifies whether scales are shared across all facets.
 * @param xOrder specifies the ordering direction of columns
 * @param yOrder specifies the ordering direction of rows
 * @param xFormat specifies the format pattern for displaying faceting values in columns.
 * @param yFormat specifies the format pattern for displaying faceting values in rows.
 *
 * Format pattern in the xFormat/yFormat parameters can be just a number format (like "d") or
 * a string template where the number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$"
 *
 * Examples:
 * ".2f" -> "12.45"
 * "Score: {.2f}" -> "Score: 12.45"
 * "'Score: {}' "-> "Score: 12.454789"
 */
@Suppress("INVISIBLE_MEMBER")
public fun PlotBuilder.facetGrid(
    x: ColumnReference<*>,
    y: ColumnReference<*>,
    scalesSharing: ScalesSharing? = null,
    xOrder: OrderDirection = OrderDirection.ASCENDING,
    yOrder: OrderDirection = OrderDirection.ASCENDING,
    xFormat: String? = null,
    yFormat: String? = null
) {
    val xColName = datasetBuilder.takeColumn(x.name())
    val yColName = datasetBuilder.takeColumn(y.name())
    plotFeatures[FacetGridFeature.FEATURE_NAME] =
        FacetGridFeature(xColName, yColName, scalesSharing, xOrder, yOrder, xFormat, yFormat)
}

/**
 * Splits data by one or more faceting variables.
 * For each data subset creates a plot panel and lays out panels according to the `
 * nCol`, `nRow` and `direction` settings.
 *
 * Opens a [FacetWrapContext]. [FacetWrapContext.facets] is defined in this context. This method adds
 * a new facet to a given variable.
 *
 * ```
 * facetWrap(nRow = 3, scalesSharing = ScalesSharing.FREE_X) {
 *    facet(col1)
 *    facet(col2, OrderDirection.DESCENDING)
 *    facet(col3, format = {.2f})
 * }
 * ```
 *
 * @param nCol number of columns.
 * @param nRow number of rows.
 * @param scalesSharing specifies whether scales are shared across all facets.
 * @param direction direction of the facet.
 */
public fun PlotBuilder.facetWrap(
    nCol: Int? = null,
    nRow: Int? = null,
    scalesSharing: ScalesSharing = FIXED,
    direction: Direction = Direction.HORIZONTAL,
    block: FacetWrapContext.() -> Unit
) {
    @Suppress("INVISIBLE_MEMBER")
    plotFeatures[FacetWrapFeature.FEATURE_NAME] =
        FacetWrapContext().apply(block).toFeature(datasetBuilder, nCol, nRow, scalesSharing, direction)
}
