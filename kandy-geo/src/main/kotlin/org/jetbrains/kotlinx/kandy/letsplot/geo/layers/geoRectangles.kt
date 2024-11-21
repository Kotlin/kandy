package org.jetbrains.kotlinx.kandy.letsplot.geo.layers

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.Infer
import org.jetbrains.kotlinx.dataframe.api.map
import org.jetbrains.kotlinx.dataframe.impl.asList
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.GeoData
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.GeoDataScope
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.RectanglesBuilder
import org.jetbrains.kotlinx.kandy.letsplot.layers.rectangles
import org.locationtech.jts.geom.*
import kotlin.reflect.typeOf

// TODO add ColumnAccessor & String api
@Suppress("INVISIBLE_REFERENCE")
public fun GeoDataScope.geoRectangles(
    block: RectanglesBuilder.() -> Unit = {}
) {
    // TODO checks?
    (this as LayerCreatorScope).rectangles {
        xMin.constant(null)
        yMin.constant(null)
        xMax.constant(null)
        yMax.constant(null)
        block()
    }
}

@JvmName("geoRectanglesGeometry")
public fun LayerCreatorScope.geoRectangles(
    geometry: DataColumn<Geometry>,
    block: RectanglesBuilder.() -> Unit = {}
) {
    geoLayer(geometry, { geoRectangles(it) }, block)
}

@JvmName("geoRectanglesEnvelope")
public fun LayerCreatorScope.geoRectangles(
    geometry: DataColumn<Envelope>,
    block: RectanglesBuilder.() -> Unit = {}
) {
    val factory = GeometryFactory()
    geoLayer(geometry.map { factory.toGeometry(it) }, { geoRectangles(it) }, block)
}

@JvmName("geoRectanglesGeometry")
public fun LayerCreatorScope.geoRectangles(
    geometry: Iterable<Geometry>,
    block: RectanglesBuilder.() -> Unit = {}
) {
    geoRectangles(DataColumn.createValueColumn("geometry", geometry.asList(), typeOf<Geometry>(), Infer.Type), block)
}

@JvmName("geoRectanglesEnvelope")
public fun LayerCreatorScope.geoRectangles(
    geometry: Iterable<Envelope>,
    block: RectanglesBuilder.() -> Unit = {}
) {
    geoRectangles(DataColumn.createValueColumn("geometry", geometry.asList(), typeOf<Envelope>(), Infer.Type), block)
}

public fun LayerCreatorScope.geoRectangles(
    envelope: Envelope,
    block: RectanglesBuilder.() -> Unit = {}
) {
    geoRectangles(listOf(envelope), block)
}

public fun LayerCreatorScope.geoRectangles(
    polygon: Polygon,
    block: RectanglesBuilder.() -> Unit = {}
) {
    geoRectangles(listOf(polygon), block)
}

public fun LayerCreatorScope.geoRectangles(
    multiPolygon: MultiPolygon,
    block: RectanglesBuilder.() -> Unit = {}
) {
    geoRectangles(listOf(multiPolygon), block)
}
