@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package org.jetbrains.kotlinx.kandy.letsplot.geo.layers

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.Infer
import org.jetbrains.kotlinx.dataframe.api.forEach
import org.jetbrains.kotlinx.dataframe.geo.GeoDataFrame
import org.jetbrains.kotlinx.dataframe.impl.asList
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.GeoDataScope
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.geometry
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.PolygonBuilder
import org.jetbrains.kotlinx.kandy.letsplot.layers.polygon
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.MultiPolygon
import org.locationtech.jts.geom.Polygon
import org.locationtech.jts.geom.Polygonal
import kotlin.reflect.typeOf


/**
 * Adds a new `polygon` layer to the plot.
 *
 * Uses [Polygon] and [MultiPolygon] values from `geometry`
 * column of [GeoDataFrame] to build polygons.
 *
 * It is similar to [polygon] but `x` and `y` bindings are created
 * automatically from geometries.
 *
 * This function optionally creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Polygon Aesthetics
 * * **`fillColor`** - The fill color of the polygon.
 * * **`alpha`** - The transparency of the polygon.
 * * **`borderLine.color`** - Color of the polygon borderline.
 * * **`borderLine.width`** - Width of the polygon borderline.
 * * **`borderLine.type`** - Type of the polygon borderline, such as dashed or dotted.
 *
 * ## Example
 *
 * ```kotlin
 * geoDF.plot {
 *     geoPolygon {
 *         // Non-positional mapping
 *         fillColor(someValueColumn)
 *
 *         // Non-positional settings
 *         alpha = 0.8
 *
 *         // BorderLine settings
 *         borderLine.width = .5
 *
 *     }
 * }
 * ```
 */
public inline fun GeoDataScope.geoPolygon(
    block: PolygonBuilder.() -> Unit = {}
) {
    geometry().forEach { check(it is Polygonal) { "Not a polygon geometry: $it" } }
    (this as LayerCreatorScope).polygon {
        x.constant(null)
        y.constant(null)
        block()
    }
}

/**
 * Adds a new `polygon` layer to the plot.
 *
 * Uses provided [Polygon] and [MultiPolygon] to build polygons.
 * It is similar to [polygon] but `x` and `y` bindings are created
 * automatically from geometries.
 *
 * This function optionally creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Polygon Aesthetics
 * * **`fillColor`** - The fill color of the polygon.
 * * **`alpha`** - The transparency of the polygon.
 * * **`borderLine.color`** - Color of the polygon borderline.
 * * **`borderLine.width`** - Width of the polygon borderline.
 * * **`borderLine.type`** - Type of the polygon borderline, such as dashed or dotted.
 *
 * ## Example
 *
 * ```kotlin
 * df.plot {
 *     geoPolygon(columnWithGeometries) {
 *         // Non-positional mapping
 *         fillColor(someValueColumn)
 *
 *         // Non-positional settings
 *         alpha = 0.8
 *
 *         // BorderLine settings
 *         borderLine.width = .5
 *     }
 * }
 * ```
 *
 * @param geometry [DataColumn] of geometries to be plotted.
 */
public fun LayerCreatorScope.geoPolygon(
    geometry: DataColumn<Geometry>,
    block: PolygonBuilder.() -> Unit = {}
) {
    geoLayer(geometry, { geoPolygon(it) }, block)
}

/**
 * Adds a new `polygon` layer to the plot.
 *
 * Uses provided [Polygon] and [MultiPolygon] to build polygons.
 *
 * It is similar to [polygon] but `x` and `y` bindings are created
 * automatically from geometries.
 *
 * This function optionally creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Polygon Aesthetics
 * * **`fillColor`** - The fill color of the polygon.
 * * **`alpha`** - The transparency of the polygon.
 * * **`borderLine.color`** - Color of the polygon borderline.
 * * **`borderLine.width`** - Width of the polygon borderline.
 * * **`borderLine.type`** - Type of the polygon borderline, such as dashed or dotted.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     geoPolygon(listOf(polygon1, polygon2, polygon3)) {
 *         // Non-positional mapping
 *         fillColor(listOf("PolyA", "PolyB", "PolyC"))
 *
 *         // Non-positional settings
 *         alpha = 0.8
 *
 *         // BorderLine settings
 *         borderLine.width = .5
 *     }
 * }
 * ```
 *
 * @param geometry [Iterable] of geometries to be plotted.
 */
public fun LayerCreatorScope.geoPolygon(
    geometry: Iterable<Geometry>,
    block: PolygonBuilder.() -> Unit = {}
) {
    geoPolygon(DataColumn.createValueColumn("geometry", geometry.asList(), typeOf<Geometry>(), Infer.Type), block)
}

/**
 * Adds a new `polygon` layer to the plot.
 *
 * Uses provided [Polygon] to build polygons.
 *
 * It is similar to [polygon] but `x` and `y` bindings are created
 * automatically from geometry.
 *
 * This function optionally creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Polygon Aesthetics
 * * **`fillColor`** - The fill color of the polygon.
 * * **`alpha`** - The transparency of the polygon.
 * * **`borderLine.color`** - Color of the polygon borderline.
 * * **`borderLine.width`** - Width of the polygon borderline.
 * * **`borderLine.type`** - Type of the polygon borderline, such as dashed or dotted.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     geoPolygon(myPolygon) {
 *         // Non-positional settings
 *         alpha = 0.8
 *
 *         // BorderLine settings
 *         borderLine.width = .5
 *     }
 * }
 * ```
 *
 * @param polygon [Polygon] to be plotted.
 */
public fun LayerCreatorScope.geoPolygon(
    polygon: Polygon,
    block: PolygonBuilder.() -> Unit = {}
) {
    geoPolygon(listOf(polygon), block)
}

/**
 * Adds a new `polygon` layer to the plot.
 *
 * Uses provided [MultiPolygon] to build polygons.
 *
 * It is similar to [polygon] but `x` and `y` bindings are created
 * automatically from geometry.
 *
 * This function optionally creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Polygon Aesthetics
 * * **`fillColor`** - The fill color of the polygon.
 * * **`alpha`** - The transparency of the polygon.
 * * **`borderLine.color`** - Color of the polygon borderline.
 * * **`borderLine.width`** - Width of the polygon borderline.
 * * **`borderLine.type`** - Type of the polygon borderline, such as dashed or dotted.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     geoPolygon(myMultiPolygon) {
 *         // Non-positional settings
 *         alpha = 0.8
 *
 *         // BorderLine settings
 *         borderLine.width = .5
 *     }
 * }
 * ```
 *
 * @param multiPolygon [MultiPolygon] to be plotted.
 */
public fun LayerCreatorScope.geoPolygon(
    multiPolygon: MultiPolygon,
    block: PolygonBuilder.() -> Unit = {}
) {
    geoPolygon(listOf(multiPolygon), block)
}
