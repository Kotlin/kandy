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

    companion object {
        // TODO lets-plot default, encoding precision customization
        const val DEFAULT_PRECISION = 10
    }

    override fun toSpatialDataset(): SpatialDataset {
        with(geoDataFrame) {
            val geojson = GeometryJSON(DEFAULT_PRECISION)
            return SpatialDataset.withGEOJSON(
                df.remove { geometry }.toMap(),
                df.geometry.toList().map { geojson.toString(it) },
                crs = crs?.name?.code
            )
        }
    }
}
