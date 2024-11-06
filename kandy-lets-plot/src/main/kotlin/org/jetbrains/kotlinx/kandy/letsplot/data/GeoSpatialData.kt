package org.jetbrains.kotlinx.kandy.letsplot.data

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import org.jetbrains.letsPlot.spatial.SpatialDataset

public interface GeoSpatialData: TableData {
    public val dataFrame: DataFrame<*>
    public fun toSpatialDataset(): SpatialDataset
}
