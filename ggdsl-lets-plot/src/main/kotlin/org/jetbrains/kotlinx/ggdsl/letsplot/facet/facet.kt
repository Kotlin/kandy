package org.jetbrains.kotlinx.ggdsl.letsplot.facet

import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature


data class OrderDirection internal constructor(val value: Int) {
    companion object {
        val ASCENDING = OrderDirection(1)
        val DESCENDING = OrderDirection(-1)
    }
}

data class Direction internal constructor(val name: String) {
    companion object {
        val VERTICAL = Direction("v")
        val HORIZONTAL = Direction("h")
    }
}

data class ScalesSharing internal constructor(val name: String) {
    companion object {
        val FIXED = ScalesSharing("fixed")
        val FREE = ScalesSharing("free")
        val FREE_X = ScalesSharing("free_x")
        val FREE_Y = ScalesSharing("free_y")
    }
}

data class FacetGridFeature  constructor(
    val x: String?,
    val y: String?,
    val scalesSharing: ScalesSharing?,
    val xOrder: OrderDirection,
    val yOrder: OrderDirection,
    val xFormat: String?,
    val yFormat: String?
) : PlotFeature {
    override val featureName: FeatureName = FEATURE_NAME

    companion object {
        val FEATURE_NAME = FeatureName("FACET_GRID_FEATURE")
    }

}

data class FacetWrapFeature constructor(
    val facets: List<String>,
    var nCol: Int?,
    var nRow: Int?,
    var orders: List<OrderDirection>,
    val scalesSharing: ScalesSharing,
    val direction: Direction,
    val formats: List<String?>,
) : PlotFeature {
    override val featureName: FeatureName = FEATURE_NAME

    companion object {
        val FEATURE_NAME = FeatureName("FACET_WRAP_FEATURE")
    }

}


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
fun PlotContext.facetGridX(
    x: DataSource<*>,
    scalesSharing: ScalesSharing? = null,
    xOrder: OrderDirection = OrderDirection.ASCENDING,
    yOrder: OrderDirection = OrderDirection.ASCENDING,
    xFormat: String? = null,
    yFormat: String? = null
) {
    features[FacetGridFeature.FEATURE_NAME] =
        FacetGridFeature(x.id, null, scalesSharing, xOrder, yOrder, xFormat, yFormat)
}

fun PlotContext.facetGridY(
    y: DataSource<*>,
    scalesSharing: ScalesSharing? = null,
    xOrder: OrderDirection = OrderDirection.ASCENDING,
    yOrder: OrderDirection = OrderDirection.ASCENDING,
    xFormat: String? = null,
    yFormat: String? = null
) {
    features[FacetGridFeature.FEATURE_NAME] =
        FacetGridFeature(null, y.id, scalesSharing, xOrder, yOrder, xFormat, yFormat)
}

fun PlotContext.facetGrid(
    x: DataSource<*>,
    y: DataSource<*>,
    scalesSharing: ScalesSharing? = null,
    xOrder: OrderDirection = OrderDirection.ASCENDING,
    yOrder: OrderDirection = OrderDirection.ASCENDING,
    xFormat: String? = null,
    yFormat: String? = null
) {
    features[FacetGridFeature.FEATURE_NAME] =
        FacetGridFeature(x.id, y.id, scalesSharing, xOrder, yOrder, xFormat, yFormat)
}

@PlotDslMarker
class FacetWrapContext @PublishedApi internal constructor(){
    private val facets = mutableListOf<DataSource<*>>()
    private val orders= mutableListOf<OrderDirection>()
    private val formats = mutableListOf<String?>()

    fun facet(source: DataSource<*>, order: OrderDirection = OrderDirection.ASCENDING, format: String? = null) {
        facets.add(source)
        orders.add(order)
        formats.add(format)
    }

    internal fun toFeature(
        nCol: Int? = null,
        nRow: Int? = null,
        scalesSharing: ScalesSharing = ScalesSharing.FIXED,
        direction: Direction = Direction.HORIZONTAL,
    ) =
        FacetWrapFeature(
            facets.map { it.toString() },
            nCol,
            nRow,
            orders,
            scalesSharing,
            direction,
            formats
        )
}

/**
 * Splits data by one or more faceting variables.
 * For each data subset creates a plot panel and lays out panels according to the `nCol`, `nRow` and `direction` settings.
 *
 * TODO params
 * @see org.jetbrains.letsPlot.facet.facetWrap
 */
fun PlotContext.facetWrap(
    nCol: Int? = null,
    nRow: Int? = null,
    scalesSharing: ScalesSharing = ScalesSharing.FIXED,
    direction: Direction = Direction.HORIZONTAL,
    block: FacetWrapContext.() -> Unit
) {
    features[FacetWrapFeature.FEATURE_NAME] =
        FacetWrapContext().apply(block).toFeature(nCol, nRow, scalesSharing, direction)
}
