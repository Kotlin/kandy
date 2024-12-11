@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package org.jetbrains.kotlinx.kandy.letsplot.geo.layers

import org.geotools.api.referencing.crs.CoordinateReferenceSystem
import org.geotools.referencing.CRS
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.Infer
import org.jetbrains.kotlinx.dataframe.geo.GeoDataFrame
import org.jetbrains.kotlinx.dataframe.impl.asList
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.feature.CoordinatesTransformation
import org.jetbrains.kotlinx.kandy.letsplot.feature.coordinatesTransformation
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.GeoDataScope
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.crs
import org.jetbrains.kotlinx.kandy.letsplot.geo.mercator
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.PolygonBuilder
import org.jetbrains.kotlinx.kandy.letsplot.layers.polygon
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.MultiPolygon
import org.locationtech.jts.geom.Polygon
import kotlin.reflect.typeOf

// TODO add ColumnAccessor & String api

@PublishedApi
internal fun CoordinateReferenceSystem.isWGS84(): Boolean {
    return CRS.equalsIgnoreMetadata(this, GeoDataFrame.DEFAULT_CRS)
}

/**
 * Adds a new `polygon` layer to the plot and applies coordinates transformation
 * corresponding to [GeoDataFrame] coordinate reference system.
 * Now, only WGS84 is supported.
 *
 * Uses [Polygon] and [MultiPolygon] values from `geometry`
 * column of [GeoDataFrame] to build polygons.
 *
 * It is similar to [polygon] but `x` and `y` bindings as well as coordinates transformation,
 * are created automatically from geometries.
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
 *     geoMap {
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
public inline fun GeoDataScope.geoMap(
    block: PolygonBuilder.() -> Unit = {}
) {
    val crs = crs()
    if (crs == null || crs.isWGS84() == true) {
        (this as LayerCreatorScope).plotBuilder.coordinatesTransformation = CoordinatesTransformation.mercator()
    } else {
        //TODO handling other CRS
    }
    geoPolygon(block)
}

/**
 * Adds a new `polygon` layer to the plot and applies coordinates transformation
 * corresponding to [GeoDataFrame] coordinate reference system.
 * Now, only WGS84 is supported.
 *
 * Uses provided [Polygon] and [MultiPolygon] to build polygons.
 *
 * It is similar to [polygon] but `x` and `y` bindings as well as coordinates transformation,
 * are created automatically from geometries.
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
 *     geoMap(columnWithGeometries) {
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
public fun LayerCreatorScope.geoMap(
    geometry: DataColumn<Geometry>,
    block: PolygonBuilder.() -> Unit = {}
) {
    plotBuilder.coordinatesTransformation = CoordinatesTransformation.mercator()
    geoLayer(geometry, { geoMap(it) }, block)
}

/**
 * Adds a new `polygon` layer to the plot and applies coordinates transformation
 * corresponding to [GeoDataFrame] coordinate reference system.
 * Now, only WGS84 is supported.
 *
 * Uses provided [Polygon] and [MultiPolygon] to build polygons.
 *
 * It is similar to [polygon] but `x` and `y` bindings as well as coordinates transformation,
 * are created automatically from geometries.
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
 *     geoMap(listOf(polygon1, polygon2, polygon3)) {
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
public fun LayerCreatorScope.geoMap(
    geometry: Iterable<Geometry>,
    block: PolygonBuilder.() -> Unit = {}
) {
    geoMap(DataColumn.createValueColumn("geometry", geometry.asList(), typeOf<Geometry>(), Infer.Type), block)
}

/**
 * Adds a new `polygon` layer to the plot and applies coordinates transformation
 * corresponding to [GeoDataFrame] coordinate reference system.
 * Now, only WGS84 is supported.
 *
 * Uses provided [Polygon] to build polygons.
 *
 * It is similar to [polygon] but `x` and `y` bindings as well as coordinates transformation,
 * are created automatically from geometry.
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
 *     geoMap(myPolygon) {
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
public fun LayerCreatorScope.geoMap(
    polygon: Polygon,
    block: PolygonBuilder.() -> Unit = {}
) {
    geoMap(listOf(polygon), block)
}

/**
 * Adds a new `polygon` layer to the plot and applies coordinates transformation
 * corresponding to [GeoDataFrame] coordinate reference system.
 * Now, only WGS84 is supported.
 *
 * Uses provided [MultiPolygon] to build polygons.
 *
 * It is similar to [polygon] but `x` and `y` bindings as well as coordinates transformation,
 * are created automatically from geometry.
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
 *     geoMap(myMultiPolygon) {
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
public fun LayerCreatorScope.geoMap(
    multiPolygon: MultiPolygon,
    block: PolygonBuilder.() -> Unit = {}
) {
    geoMap(listOf(multiPolygon), block)
}
