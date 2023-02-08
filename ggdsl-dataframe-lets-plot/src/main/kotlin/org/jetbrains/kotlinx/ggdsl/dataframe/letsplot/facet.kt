/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.internal.toColumnPointer
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.OrderDirection
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.ScalesSharing
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.context.FacetWrapContext
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.feature.FacetGridFeature

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
    x: ColumnReference<*>,
    scalesSharing: ScalesSharing? = null,
    order: OrderDirection = OrderDirection.ASCENDING,
    format: String? = null,
) {
    features[FacetGridFeature.FEATURE_NAME] =
        FacetGridFeature(x.name(), null, scalesSharing, order, OrderDirection.ASCENDING, format, null)

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
    y: ColumnReference<*>,
    scalesSharing: ScalesSharing? = null,
    order: OrderDirection = OrderDirection.ASCENDING,
    format: String? = null,
) {
    features[FacetGridFeature.FEATURE_NAME] =
        FacetGridFeature(null, y.name(), scalesSharing, OrderDirection.ASCENDING, order, null, format)

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
    x: ColumnReference<*>,
    y: ColumnReference<*>,
    scalesSharing: ScalesSharing? = null,
    xOrder: OrderDirection = OrderDirection.ASCENDING,
    yOrder: OrderDirection = OrderDirection.ASCENDING,
    xFormat: String? = null,
    yFormat: String? = null
) {
    features[FacetGridFeature.FEATURE_NAME] =
        FacetGridFeature(x.name(), y.name(), scalesSharing, xOrder, yOrder, xFormat, yFormat)

}

/**
 * Adds a a new facet by a given variable.
 *
 * @param variable Variable which defines this facet.
 * @param order Specifies ordering direction of this facet.
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
public inline fun <reified T> FacetWrapContext.facet(
    variable: ColumnReference<T>,
    order: OrderDirection = OrderDirection.ASCENDING,
    format: String? = null
): Unit = facet(variable.toColumnPointer(), order, format)
