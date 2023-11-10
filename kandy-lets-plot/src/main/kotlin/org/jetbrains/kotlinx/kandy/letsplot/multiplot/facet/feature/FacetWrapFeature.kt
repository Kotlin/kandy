/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.feature

import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.Direction
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.OrderDirection
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.ScalesSharing

/**
 * Splits data by one or more faceting variables.
 * For each data subset creates a plot panel and lays out panels according to the `
 * nCol`, `nRow` and `direction` settings.
 *
 * @param facets list of faceting variable names.
 * @param nCol number of columns.
 * @param nRow number of rows.
 * @param scalesSharing specifies whether scales are shared across all facets.
 * @param orders list of [OrderDirection] values specifying ordering direction panels.
 * The `order` values are positionally matched to variables in `facets`.
 * @param formats list of strings specifying the format pattern for displaying faceting values.
 * The `format` values are positionally matched to variables in `facets`.
 * @param direction direction of the facet.
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where the number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$"
 * For more info see: https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md
 *
 * Examples:
 * ".2f" -> "12.45"
 * "Score: {.2f}" -> "Score: 12.45"
 * "Score: {}" -> "Score: 12.454789"
 */
public data class FacetWrapFeature(
    val facets: List<String>,
    var nCol: Int?,
    var nRow: Int?,
    var orders: List<OrderDirection>,
    val scalesSharing: ScalesSharing,
    val direction: Direction,
    val formats: List<String?>,
) : PlotFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("FACET_WRAP_FEATURE")
    }
}
