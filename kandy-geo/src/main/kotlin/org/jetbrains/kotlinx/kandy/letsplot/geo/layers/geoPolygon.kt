package org.jetbrains.kotlinx.kandy.letsplot.geo.layers

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.geo.geometry
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.geo.GeoDataScope
import org.jetbrains.kotlinx.kandy.letsplot.geo.geoDataFrame
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.PolygonBuilder
import org.jetbrains.kotlinx.kandy.letsplot.layers.polygon
import org.locationtech.jts.geom.Geometry

public inline fun GeoDataScope.geoPolygon(
    geometry: DataColumn<Geometry>? = null,
    block: PolygonBuilder.() -> Unit
) {
    this as LayerCreatorScope
    val geometryColumn = geometry ?: geoDataFrame().df.geometry
    polygon {
        x.constant(null)
        y.constant(null)
        block()
    }
}
