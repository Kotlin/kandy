@file:Suppress("CANNOT_OVERRIDE_INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.geo.dsl

import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.geo.GeoDataFrame
import org.jetbrains.kotlinx.dataframe.geo.WithGeometry
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.MultiLayerPlotBuilderImpl

public class GeoDataFramePlotBuilder<T : WithGeometry> @PublishedApi internal constructor(
    @PublishedApi
    internal val geodf: GeoDataFrame<T>,
) : MultiLayerPlotBuilderImpl(), ColumnsContainer<T> by geodf.df, GeoDataScope {
    internal override val datasetBuilders: MutableList<DatasetBuilder> = mutableListOf(GeoDataBuilder(geodf))
}
