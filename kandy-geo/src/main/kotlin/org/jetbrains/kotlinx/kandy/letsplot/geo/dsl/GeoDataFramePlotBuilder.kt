@file:Suppress("CANNOT_OVERRIDE_INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.geo.dsl

import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.geo.GeoDataFrame
import org.jetbrains.kotlinx.dataframe.geo.WithGeometry
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.MultiLayerPlotBuilderImpl

/**
 * Represents a geo plotting context initialized with a [GeoDataFrame] as its primary dataset.
 * The class allows the seamless integration of the inner dataframe's columns into the plotting process.
 *
 * The implemented [ColumnsContainer] enables the user
 * to leverage the columns of the dataframe directly in the plotting process.
 *
 * In this scope, you can create geo layers that will use the `geometry` column of this GeoDataFrame
 * by default.
 *
 * @param T the type of the GeoDataFrame.
 */
public class GeoDataFramePlotBuilder<T : WithGeometry> @PublishedApi internal constructor(
    @PublishedApi
    internal val geodf: GeoDataFrame<T>,
) : MultiLayerPlotBuilderImpl(), ColumnsContainer<T> by geodf.df, GeoDataScope {
    override val datasetBuilders: MutableList<DatasetBuilder> = mutableListOf(GeoDataBuilder(geodf))
}
