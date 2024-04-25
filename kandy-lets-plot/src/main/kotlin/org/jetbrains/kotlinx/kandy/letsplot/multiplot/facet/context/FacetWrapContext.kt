/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.context


import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetHandler
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.Direction
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.OrderDirection
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.ScalesSharing
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.feature.FacetWrapFeature

/**
 * Represents the context for configuring and adding facets to a plot within the [org.jetbrains.kotlinx.kandy.dsl.internal.MultiLayerPlotBuilder].
 * This context is initiated by invoking [org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.facetWrap],
 * which collects variables that are used to create facet plots.
 */
public class FacetWrapContext @PublishedApi internal constructor() {
    private val facets = mutableListOf<ColumnReference<*>>()
    private val orders = mutableListOf<OrderDirection>()
    private val formats = mutableListOf<String?>()

    /**
     * Adds a new facet to the plot, defined by the specified variable.
     * This method allows the division of data into different subsets,
     * where each subset is represented as a separate panel in the plot.
     *
     * @param variable a reference to the column that defines the variable for this facet.
     * The values in this column are used to create different subsets of the data, which are then plotted as separate panels.
     * @param order specifies the ordering direction of the values in this facet.
     * It can be either [OrderDirection.ASCENDING] or [OrderDirection.DESCENDING].
     * The default value is [OrderDirection.ASCENDING].
     * @param format a string that specifies the format pattern for displaying the values in this facet in rows.
     * This can either be a simple number format (like "d")
     * or a string template where the number format is surrounded by curly braces (for example, "{d} cylinders").
     * > That the "$" character must be escaped as "\$".
     *
     *
     * Examples of format patterns:
     * - ".2f" -> "12.45"
     * - "Score: {.2f}" -> "Score: 12.45"
     * - "'Score: {}'" -> "Score: 12.454789"
     */
    public fun facet(
        variable: ColumnReference<*>, order: OrderDirection = OrderDirection.ASCENDING, format: String? = null
    ) {
        facets.add(variable)
        orders.add(order)
        formats.add(format)
    }

    internal fun toFeature(
        datasetHandler: DatasetHandler, nCol: Int?, nRow: Int?,
        scalesSharing: ScalesSharing, direction: Direction,
    ) =
        FacetWrapFeature(
            facets.map { datasetHandler.takeColumn(it.name()) },
            nCol,
            nRow,
            orders,
            scalesSharing,
            direction,
            formats
        )
}