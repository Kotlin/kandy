package org.jetbrains.kotlinx.ggdsl.letsplot.facet.feature

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.Direction
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.OrderDirection
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.ScalesSharing

/**
 * Splits data by one or more faceting variables.
 * For each data subset creates a plot panel and lays out panels according to the `
 * nCol`, `nRow` and `direction` settings.
 *
 * @param facets list of faceting variable names.
 * @param nCol Number of columns.
 * @param nRow Number of rows.
 * @param scalesSharing Specifies whether scales are shared across all facets.
 * @param orders list of [OrderDirection] values specifying ordering direction panels.
 * The `order` values are positionally matched to variables in `facets`.
 * @param formats list of strings specifying the format pattern for displaying faceting values.
 * The `format` values are positionally matched to variables in `facets`.
 * @param direction direction of the facet.
 *
 * Format patterns in the `format` parameter can be just a number format (like "d") or
 * a string template where number format is surrounded by curly braces: "{d} cylinders".
 * Note: the "$" must be escaped as "\$"
 * For more info see: https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md
 *
 * Examples:
 * ".2f" -> "12.45"
 * "Score: {.2f}" -> "Score: 12.45"
 * "Score: {}" -> "Score: 12.454789"
 */
//@Serializable
public data class FacetWrapFeature constructor(
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
