package org.jetbrains.kotlinx.kandy.letsplot.geo.util

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.geo.GeoDataFrame
import org.jetbrains.kotlinx.dataframe.geo.toGeo
import org.locationtech.jts.geom.Geometry

@PublishedApi
internal fun Iterable<Geometry>.toGeoDataFrame(): GeoDataFrame<*> {
    return dataFrameOf("geometry" to listOf(this)).toGeo()
}
