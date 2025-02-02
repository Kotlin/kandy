@file:Suppress("CANNOT_OVERRIDE_INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.geo.dsl

import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.geo.GeoDataFrame
import org.jetbrains.kotlinx.dataframe.geo.WithGeometry
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.dsl.internal.MultiLayerPlotBuilder

/**
 * Represents a plot builder geo data scope with [GeoDataFrame] as a dataset
 * created by [MultiLayerPlotBuilder.withData].
 *
 * In this scope, you can create geo layers that will use the `geometry` column of this GeoDataFrame
 * by default.
 *
 * @param T The type of the [GeoDataFrame].
 */
public class GeoDataFrameScope<T : WithGeometry> @PublishedApi internal constructor(
    geoDataFrame: GeoDataFrame<T>,
    override val plotBuilder: MultiLayerPlotBuilder,
    override val datasetIndex: Int,
) : LayerCreatorScope(), GeoDataScope, ColumnsContainer<T> by geoDataFrame.df {
    override val layersInheritMappings: Boolean = false
}
