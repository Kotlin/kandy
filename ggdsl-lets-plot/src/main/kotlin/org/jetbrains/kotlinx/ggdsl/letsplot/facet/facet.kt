package org.jetbrains.kotlinx.ggdsl.letsplot.facet

import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

class FacetAes(val name: String)

val FACET_X = FacetAes("x")
val FACET_Y = FacetAes("y")

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

data class FacetGridFeature(
    val mappings: MutableMap<FacetAes, DataSource<*>> = mutableMapOf(),
    val scalesSharing: ScalesSharing = ScalesSharing.FIXED,
    val xOrder: OrderDirection = OrderDirection.ASCENDING,
    val yOrder: OrderDirection = OrderDirection.ASCENDING
) : PlotFeature {
    override val featureName: FeatureName = FEATURE_NAME
    /* TODO
    val xFormat: String? = null
    val yFormat: String? = null

     */

    companion object {
        val FEATURE_NAME = FeatureName("FACET_GRID_FEATURE")
    }

}

data class FacetWrapFeature(
    val facets: List<DataSource<*>>,
    var nCol: Int? = null,
    var nRow: Int? = null,
    var order: OrderDirection = OrderDirection.ASCENDING,
    val scalesSharing: ScalesSharing = ScalesSharing.FIXED,
    val direction: Direction = Direction.HORIZONTAL
) : PlotFeature {
    override val featureName: FeatureName = FEATURE_NAME
    /* TODO
    val format: String? = null


     */

    companion object {
        val FEATURE_NAME = FeatureName("FACET_WRAP_FEATURE")
    }

}


class FacetGridContext {
    val mappings: MutableMap<FacetAes, DataSource<*>> = mutableMapOf()
    val x = FACET_X
    val y = FACET_Y
    var scalesSharing = ScalesSharing.FIXED
    var xOrder: OrderDirection = OrderDirection.ASCENDING
    var yOrder: OrderDirection = OrderDirection.ASCENDING

    inline operator fun <reified DomainType : Any> FacetAes.invoke(dataSource: DataSource<DomainType>) {
        mappings[this] = dataSource
    }
}

class FacetWrapContext {
    var facets: List<DataSource<*>> = listOf() //TODO
    var nCol: Int? = null
    var nRow: Int? = null
    var order: OrderDirection = OrderDirection.ASCENDING
    var scalesSharing: ScalesSharing = ScalesSharing.FIXED
    val direction: Direction = Direction.HORIZONTAL
}

// TODO removecontext

/**
 * Splits data by one or two faceting variables. For each data subset creates
 * a plot panel and lays out panels as grid.
 * The grid columns are defined by X faceting variable
 * and rows are defined by Y faceting variable.
 *
 * Creates a [FacetGridContext]. In this context you can map data source to
 * facet attributes [x][FacetGridContext.x] and [y][FacetGridContext.y] by invocation them
 * with raw source as argument.
 *
 * [xOrder][FacetGridContext.xOrder] and [yOrder][FacetGridContext.yOrder] define the facet order by X and Y
 * correspondingly (ascending by default).
 *
 * ```
 * facetGrid {
 *    x(source<String>("type"))
 *    y(source<Int>("number of hands"))
 *
 *    yOrder = OrderDirection.DESCENDING
 * }
 * ```
 */
fun PlotContext.facetGrid(block: FacetGridContext.() -> Unit) {
    features[FacetGridFeature.FEATURE_NAME] =
        with(FacetGridContext().apply(block)) {
            FacetGridFeature(
                mappings,
                scalesSharing,
                xOrder,
                yOrder
            )
        }
}

/**
 * Splits data by one or more faceting variables.
 * For each data subset creates a plot panel and lays out panels according to the `nCol`, `nRow` and `direction` settings.
 *
 * TODO params
 */
fun PlotContext.facetWrap(
    vararg facets: DataSource<*>,
    nCol: Int? = null,
    nRow: Int? = null,
    order: OrderDirection = OrderDirection.ASCENDING,
    scalesSharing: ScalesSharing = ScalesSharing.FIXED,
    direction: Direction = Direction.HORIZONTAL
) {
    features[FacetWrapFeature.FEATURE_NAME] =
        FacetWrapFeature(
            facets.toList(), nCol, nRow, order, scalesSharing, direction
        )

}
