@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package org.jetbrains.kotlinx.kandy.letsplot.geo.layers

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.Infer
import org.jetbrains.kotlinx.dataframe.api.forEach
import org.jetbrains.kotlinx.dataframe.impl.asList
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.GeoDataScope
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.geometry
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.PointsBuilder
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.MultiPoint
import org.locationtech.jts.geom.Point
import org.locationtech.jts.geom.Puntal
import kotlin.reflect.typeOf

// TODO add ColumnAccessor & String api

public inline fun GeoDataScope.geoPoints(
    block: PointsBuilder.() -> Unit = {}
) {
    geometry().forEach {
        if (it !is Puntal) {
            error("Not a points geometry: $it")
        }
    }
    (this as LayerCreatorScope).points {
        x.constant(null)
        y.constant(null)
        block()
    }
}

public fun LayerCreatorScope.geoPoints(
    geometry: DataColumn<Geometry>,
    block: PointsBuilder.() -> Unit = {}
) {
    geoLayer(geometry, { geoPoints(it) }, block)
}

public fun LayerCreatorScope.geoPoints(
    geometry: Iterable<Geometry>,
    block: PointsBuilder.() -> Unit = {}
) {
    geoPoints(DataColumn.createValueColumn("geometry", geometry.asList(), typeOf<Geometry>(), Infer.Type), block)
}

public fun LayerCreatorScope.geoPoints(
    points: Point,
    block: PointsBuilder.() -> Unit = {}
) {
    geoPoints(listOf(points), block)
}

public fun LayerCreatorScope.geoPoints(
    multiPoints: MultiPoint,
    block: PointsBuilder.() -> Unit = {}
) {
    geoPoints(listOf(multiPoints), block)
}
