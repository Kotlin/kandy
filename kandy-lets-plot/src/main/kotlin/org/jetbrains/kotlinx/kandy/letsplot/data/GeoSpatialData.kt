package org.jetbrains.kotlinx.kandy.letsplot.data

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import org.jetbrains.letsPlot.spatial.SpatialDataset

/**
 * Represents geospatial data wrapper.
 *
 * This interface extends the `TableData` interface, providing specific capabilities for handling geospatial data.
 *
 * @property dataFrame The [DataFrame] containing the geospatial data.
 * @property toSpatialDataset converts dataset to Lets-Plot [SpatialDataset].
 */
public interface GeoSpatialData : TableData {
    public val dataFrame: DataFrame<*>
    public fun toSpatialDataset(): SpatialDataset
}
