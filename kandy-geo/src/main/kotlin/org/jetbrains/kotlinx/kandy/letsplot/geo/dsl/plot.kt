package org.jetbrains.kotlinx.kandy.letsplot.geo.dsl

import org.jetbrains.kotlinx.dataframe.geo.GeoDataFrame
import org.jetbrains.kotlinx.dataframe.geo.WithGeometry
import org.jetbrains.kotlinx.kandy.ir.Plot

/**
 * Returns a new [Plot].
 *
 * Creates a [GeoDataFramePlotBuilder] plotting scope, in which you can configure a plot.
 * In this scope, you can create geo layers that will use the `geometry` column of this GeoDataFrame
 * by default.
 *
 * ## Example
 *
 * ```kotlin
 * geoDF.plot {
 *    geoMap()
 * }
 * ```
 *
 * @receiver plot dataset.
 * @param T the type of the [GeoDataFrame].
 */
public fun <T : WithGeometry> GeoDataFrame<T>.plot(block: GeoDataFramePlotBuilder<T>.() -> Unit): Plot {
    return GeoDataFramePlotBuilder<T>(this).apply(block).toPlot()
}
