/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColRef
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.FacetGridFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.FacetWrapContext
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.OrderDirection
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.ScalesSharing

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
public fun PlotContext.facetGridX(
    x: ColumnReference<*>,
    scalesSharing: ScalesSharing? = null,
    order: OrderDirection = OrderDirection.ASCENDING,
    format: String? = null,
) {
    features[FacetGridFeature.FEATURE_NAME] =
        FacetGridFeature(x.name(), null, scalesSharing, order, OrderDirection.ASCENDING, format, null)

}

public fun PlotContext.facetGridY(
    y: ColumnReference<*>,
    scalesSharing: ScalesSharing? = null,
    order: OrderDirection = OrderDirection.ASCENDING,
    format: String? = null,
) {
    features[FacetGridFeature.FEATURE_NAME] =
        FacetGridFeature(null, y.name(), scalesSharing, OrderDirection.ASCENDING, order, null, format)

}

public fun PlotContext.facetGrid(
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

public fun PlotContext.facetGrid(
    x: ColumnReference<*>,
    y: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<*>,
    scalesSharing: ScalesSharing? = null,
    xOrder: OrderDirection = OrderDirection.ASCENDING,
    yOrder: OrderDirection = OrderDirection.ASCENDING,
    xFormat: String? = null,
    yFormat: String? = null
) {
    features[FacetGridFeature.FEATURE_NAME] =
        FacetGridFeature(x.name(), y.id, scalesSharing, xOrder, yOrder, xFormat, yFormat)

}


public fun PlotContext.facetGrid(
    x: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<*>,
    y: ColumnReference<*>,
    scalesSharing: ScalesSharing? = null,
    xOrder: OrderDirection = OrderDirection.ASCENDING,
    yOrder: OrderDirection = OrderDirection.ASCENDING,
    xFormat: String? = null,
    yFormat: String? = null
) {
    features[FacetGridFeature.FEATURE_NAME] =
        FacetGridFeature(x.id, y.name(), scalesSharing, xOrder, yOrder, xFormat, yFormat)

}

// todo
public inline fun <reified T : Any> FacetWrapContext.facet(
    source: ColumnReference<T>,
    order: OrderDirection = OrderDirection.ASCENDING,
    format: String? = null
): Unit = facet(source.toColRef(), order, format)
