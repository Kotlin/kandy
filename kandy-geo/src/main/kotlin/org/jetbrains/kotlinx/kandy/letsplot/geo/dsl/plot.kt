package org.jetbrains.kotlinx.kandy.letsplot.geo.dsl

import org.jetbrains.kotlinx.dataframe.geo.GeoDataFrame
import org.jetbrains.kotlinx.dataframe.geo.WithGeometry
import org.jetbrains.kotlinx.kandy.ir.Plot

public fun <T : WithGeometry> GeoDataFrame<T>.plot(block: GeoDataFramePlotBuilder<T>.() -> Unit): Plot {
    return GeoDataFramePlotBuilder<T>(this).apply(block).toPlot()
}
