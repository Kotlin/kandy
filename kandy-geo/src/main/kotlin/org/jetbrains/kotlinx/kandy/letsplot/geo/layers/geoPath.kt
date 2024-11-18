@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package org.jetbrains.kotlinx.kandy.letsplot.geo.layers

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.Infer
import org.jetbrains.kotlinx.dataframe.api.forEach
import org.jetbrains.kotlinx.dataframe.impl.asList
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.GeoDataScope
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.geometry
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.PathBuilder
import org.jetbrains.kotlinx.kandy.letsplot.layers.path
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.LineString
import org.locationtech.jts.geom.Lineal
import org.locationtech.jts.geom.MultiLineString
import kotlin.reflect.typeOf

// TODO add ColumnAccessor & String api

public inline fun GeoDataScope.geoPath(
    block: PathBuilder.() -> Unit = {}
) {
    geometry().forEach {
        if (it !is Lineal) {
            error("Not a path geometry: $it")
        }
    }
    (this as LayerCreatorScope).path {
        x.constant(null)
        y.constant(null)
        block()
    }
}

public fun LayerCreatorScope.geoPath(
    geometry: DataColumn<Geometry>,
    block: PathBuilder.() -> Unit = {}
) {
    geoLayer(geometry, { geoPath(it) }, block)
}

public fun LayerCreatorScope.geoPath(
    geometry: Iterable<Geometry>,
    block: PathBuilder.() -> Unit = {}
) {
    geoPath(DataColumn.createValueColumn("geometry", geometry.asList(), typeOf<Geometry>(), Infer.Type), block)
}

public fun LayerCreatorScope.geoPath(
    lineString: LineString,
    block: PathBuilder.() -> Unit = {}
) {
    geoPath(listOf(lineString), block)
}

public fun LayerCreatorScope.geoPath(
    multiLineString: MultiLineString,
    block: PathBuilder.() -> Unit = {}
) {
    geoPath(listOf(multiLineString), block)
}
