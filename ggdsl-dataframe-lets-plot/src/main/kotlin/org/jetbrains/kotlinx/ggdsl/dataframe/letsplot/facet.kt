package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.*

/**
 * Splits data by one or two faceting variables. For each data subset creates
 * a plot panel and lays out panels as grid.
 * The grid columns are defined by X faceting variable
 * and rows are defined by Y faceting variable.
 *
 * ```
 * facetGrid(
 *    source<String>("type"),
 *    source<Int>("number of hands"),
 *    yOrder = OrderDirection.DESCENDING
 * )
 * ```
 * TODO params
 * @see org.jetbrains.letsPlot.facet.facetGrid
 */
fun PlotContext.facetGrid(
    x: ColumnReference<*>? = null,
    y: ColumnReference<*>? = null,
    scalesSharing: ScalesSharing? = null,
    xOrder: OrderDirection = OrderDirection.ASCENDING,
    yOrder: OrderDirection = OrderDirection.ASCENDING,
    xFormat: String? = null,
    yFormat: String? = null
) {
    features[FacetGridFeature.FEATURE_NAME] =
        FacetGridFeature(x?.name(), y?.name(), scalesSharing, xOrder, yOrder, xFormat, yFormat)

}

/**
 * Splits data by one or more faceting variables.
 * For each data subset creates a plot panel and lays out panels according to the `nCol`, `nRow` and `direction` settings.
 *
 * TODO params
 * @see org.jetbrains.letsPlot.facet.facetWrap
 */
fun PlotContext.facetWrap(
    vararg facets: ColumnReference<*>,
    nCol: Int? = null,
    nRow: Int? = null,
    order: OrderDirection = OrderDirection.ASCENDING,
    scalesSharing: ScalesSharing = ScalesSharing.FIXED,
    direction: Direction = Direction.HORIZONTAL
) {
    features[FacetWrapFeature.FEATURE_NAME] =
        FacetWrapFeature(
            facets.map { it.name() }, nCol, nRow, order, scalesSharing, direction
        )

}
