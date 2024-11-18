/*
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
import org.locationtech.jts.geom.Envelope
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.MultiPoint
import org.locationtech.jts.geom.Point
import org.locationtech.jts.geom.Puntal
import kotlin.reflect.typeOf

// TODO add ColumnAccessor & String api

/public inline fun GeoDataScope.geoRect(
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

public fun LayerCreatorScope.geoRect(
    geometry: DataColumn<Geometry>,
    block: PointsBuilder.() -> Unit = {}
) {
    geoLayer(geometry, { geoRect(it) }, block)
}

public fun LayerCreatorScope.geoRect(
    geometry: Iterable<Geometry>,
    block: PointsBuilder.() -> Unit = {}
) {
    geoRect(DataColumn.createValueColumn("geometry", geometry.asList(), typeOf<Geometry>(), Infer.Type), block)
}

public fun LayerCreatorScope.geoRect(
    envelope: Envelope,
    block: RectBuilder.() -> Unit = {}
) {
    geoRect(listOf(points), block)
}
*/

/*public fun LayerCreatorScope.geoRect(
    multiPoints: MultiPoint,
    block: PointsBuilder.() -> Unit = {}
) {
    geoRect(listOf(multiPoints), block)
}*/
