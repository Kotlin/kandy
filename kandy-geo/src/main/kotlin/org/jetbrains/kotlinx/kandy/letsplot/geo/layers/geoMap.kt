@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package org.jetbrains.kotlinx.kandy.letsplot.geo.layers

import org.geotools.api.referencing.crs.CoordinateReferenceSystem
import org.geotools.referencing.CRS
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.Infer
import org.jetbrains.kotlinx.dataframe.api.all
import org.jetbrains.kotlinx.dataframe.geo.GeoDataFrame
import org.jetbrains.kotlinx.dataframe.impl.asList
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.feature.Coordinates
import org.jetbrains.kotlinx.kandy.letsplot.feature.coordinates
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.GeoDataScope
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.crs
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.geometry
import org.jetbrains.kotlinx.kandy.letsplot.geo.mercator
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.PolygonBuilder
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.MultiPolygon
import org.locationtech.jts.geom.Polygon
import kotlin.reflect.typeOf

// TODO add ColumnAccessor & String api

@PublishedApi
internal fun DataColumn<Geometry>.isValidInWGS84(): Boolean {
    return all { geometry ->
        geometry.coordinates.all { coord ->
            coord.y in -90.0..90.0 && coord.x in -180.0..180.0
        }
    }
}

@PublishedApi
internal fun CoordinateReferenceSystem.isWGS84(): Boolean {
    return CRS.equalsIgnoreMetadata(this, GeoDataFrame.DEFAULT_CRS)
}

public inline fun GeoDataScope.geoMap(
    block: PolygonBuilder.() -> Unit = {}
) {
  //  val crs = crs()
    //if ((crs == null && geometry().isValidInWGS84()) || crs?.isWGS84() == true){
        (this as LayerCreatorScope).plotBuilder.coordinates = Coordinates.mercator()
  //  } else {
        // TODO handling other CRS
  //  }
    geoPolygon(block)
}

public fun LayerCreatorScope.geoMap(
    geometry: DataColumn<Geometry>,
    block: PolygonBuilder.() -> Unit = {}
) {
    plotBuilder.coordinates = Coordinates.mercator()
    geoLayer(geometry, { geoMap(it) }, block)
}

public fun LayerCreatorScope.geoMap(
    geometry: Iterable<Geometry>,
    block: PolygonBuilder.() -> Unit = {}
) {
    geoMap(DataColumn.createValueColumn("geometry", geometry.asList(), typeOf<Geometry>(), Infer.Type), block)
}

public fun LayerCreatorScope.geoMap(
    polygon: Polygon,
    block: PolygonBuilder.() -> Unit = {}
) {
    geoMap(listOf(polygon), block)
}

public fun LayerCreatorScope.geoMap(
    multiPolygon: MultiPolygon,
    block: PolygonBuilder.() -> Unit = {}
) {
    geoMap(listOf(multiPolygon), block)
}
