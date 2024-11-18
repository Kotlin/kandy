@file:Suppress("INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.geo.dsl

import org.jetbrains.kotlinx.dataframe.geo.GeoDataFrame
import org.jetbrains.kotlinx.dataframe.geo.WithGeometry
import org.jetbrains.kotlinx.kandy.dsl.internal.MultiLayerPlotBuilder

public fun <T : WithGeometry> MultiLayerPlotBuilder.withData(
    geoDataFrame: GeoDataFrame<T>,
    block: GeoDataFrameScope<T>.() -> Unit
) {
    datasetBuilders.add(GeoDataBuilder(geoDataFrame))
    GeoDataFrameScope(geoDataFrame, this, datasetBuilders.lastIndex).apply(block)
}
