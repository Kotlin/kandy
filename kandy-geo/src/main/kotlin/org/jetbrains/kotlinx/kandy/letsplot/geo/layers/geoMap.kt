@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package org.jetbrains.kotlinx.kandy.letsplot.geo.layers

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.Infer
import org.jetbrains.kotlinx.dataframe.api.forEach
import org.jetbrains.kotlinx.dataframe.impl.asList
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.feature.Coordinates
import org.jetbrains.kotlinx.kandy.letsplot.feature.coordinates
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.GeoDataScope
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.geometry
import org.jetbrains.kotlinx.kandy.letsplot.geo.mercator
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.PolygonBuilder
import org.jetbrains.kotlinx.kandy.letsplot.layers.polygon
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.MultiPolygon
import org.locationtech.jts.geom.Polygon
import org.locationtech.jts.geom.Polygonal
import kotlin.reflect.typeOf

// TODO add ColumnAccessor & String api

public inline fun GeoDataScope.geoMap(
    block: PolygonBuilder.() -> Unit = {}
) {
    (this as LayerCreatorScope).plotBuilder.coordinates = Coordinates.mercator()
    geoPolygon(block)
}

public fun LayerCreatorScope.geoMap(
    geometry: DataColumn<Geometry>,
    block: PolygonBuilder.() -> Unit = {}
) {
    plotBuilder.coordinates = Coordinates.mercator()
    geoPolygon(geometry, block)
}

public fun LayerCreatorScope.geoMap(
    geometry: Iterable<Geometry>,
    block: PolygonBuilder.() -> Unit = {}
) {
    plotBuilder.coordinates = Coordinates.mercator()
    geoPolygon(geometry, block)}

public fun LayerCreatorScope.geoMap(
    polygon: Polygon,
    block: PolygonBuilder.() -> Unit = {}
) {
    plotBuilder.coordinates = Coordinates.mercator()
    geoPolygon(polygon, block)
}

public fun LayerCreatorScope.geoMap(
    multiPolygon: MultiPolygon,
    block: PolygonBuilder.() -> Unit = {}
) {
    plotBuilder.coordinates = Coordinates.mercator()
    geoPolygon(multiPolygon, block)
}
