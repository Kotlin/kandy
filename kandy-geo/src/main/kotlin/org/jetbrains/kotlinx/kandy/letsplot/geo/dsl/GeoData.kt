package org.jetbrains.kotlinx.kandy.letsplot.geo.dsl

import org.geotools.geojson.geom.GeometryJSON
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.remove
import org.jetbrains.kotlinx.dataframe.api.toMap
import org.jetbrains.kotlinx.dataframe.geo.GeoDataFrame
import org.jetbrains.kotlinx.dataframe.geo.WithGeometry
import org.jetbrains.kotlinx.dataframe.geo.geometry
import org.jetbrains.kotlinx.kandy.letsplot.data.GeoSpatialData
import org.jetbrains.letsPlot.spatial.SpatialDataset

internal class GeoData(val geoDataFrame: GeoDataFrame<*>) : GeoSpatialData {
    override val dataFrame: DataFrame<WithGeometry> = geoDataFrame.df

    override fun toSpatialDataset(): SpatialDataset {
        with(geoDataFrame) {
            // TODO encoding precision
            val geojson = GeometryJSON(10)
            return SpatialDataset.withGEOJSON(
                df.remove { geometry }.toMap(),
                df.geometry.toList().map { geojson.toString(it) },
                crs = crs?.name?.code
            )
        }
    }
}
