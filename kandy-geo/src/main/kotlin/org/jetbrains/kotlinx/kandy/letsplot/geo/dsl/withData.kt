@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package org.jetbrains.kotlinx.kandy.letsplot.geo.dsl

import org.jetbrains.kotlinx.dataframe.geo.GeoDataFrame
import org.jetbrains.kotlinx.dataframe.geo.WithGeometry
import org.jetbrains.kotlinx.kandy.dsl.internal.MultiLayerPlotBuilder

/**
 * Creates and initializes a new geo layer creator scope with the given [GeoDataFrame] as a dataset.
 *
 * In this scope, you can create geo layers that will use the `geometry` column of this GeoDataFrame
 * by default.
 *
 * @param geoDataFrame The `GeoDataFrame` to be used as a dataset within the scope.
 * @param block layer creator scope with a new dataset.
 */
public fun <T : WithGeometry> MultiLayerPlotBuilder.withData(
    geoDataFrame: GeoDataFrame<T>,
    block: GeoDataFrameScope<T>.() -> Unit
) {
    datasetBuilders.add(GeoDataBuilder(geoDataFrame))
    GeoDataFrameScope(geoDataFrame, this, datasetBuilders.lastIndex).apply(block)
}
