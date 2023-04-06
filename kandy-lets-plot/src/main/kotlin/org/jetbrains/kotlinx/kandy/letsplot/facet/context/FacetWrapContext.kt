/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.facet.context

// import org.jetbrains.kotlinx.kandy.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetHandler
import org.jetbrains.kotlinx.kandy.letsplot.facet.Direction
import org.jetbrains.kotlinx.kandy.letsplot.facet.OrderDirection
import org.jetbrains.kotlinx.kandy.letsplot.facet.ScalesSharing
import org.jetbrains.kotlinx.kandy.letsplot.facet.feature.FacetWrapFeature

/**
 * Context opened by [facetWrap].
 */
/*@PlotDslMarker*/
public class FacetWrapContext @PublishedApi internal constructor() {
    private val facets = mutableListOf<ColumnReference<*>>()
    private val orders = mutableListOf<OrderDirection>()
    private val formats = mutableListOf<String?>()

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
    public fun facet(
        variable: ColumnReference<*>,
        order: OrderDirection = OrderDirection.ASCENDING,
        format: String? = null
    ) {
        facets.add(variable)
        orders.add(order)
        formats.add(format)
    }

    internal fun toFeature(
        datasetHandler: DatasetHandler,
        nCol: Int?,
        nRow: Int?,
        scalesSharing: ScalesSharing,
        direction: Direction,
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