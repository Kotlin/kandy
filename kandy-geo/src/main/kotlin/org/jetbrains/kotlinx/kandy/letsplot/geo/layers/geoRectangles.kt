@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package org.jetbrains.kotlinx.kandy.letsplot.geo.layers

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.Infer
import org.jetbrains.kotlinx.dataframe.api.map
import org.jetbrains.kotlinx.dataframe.geo.GeoDataFrame
import org.jetbrains.kotlinx.dataframe.impl.asList
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.GeoDataScope
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.RectanglesBuilder
import org.jetbrains.kotlinx.kandy.letsplot.layers.rectangles
import org.locationtech.jts.geom.*
import kotlin.reflect.typeOf

/**
 * Adds a new `rectangles` layer to the plot.
 *
 * Uses bounds of geometries from `geometry`
 * column of [GeoDataFrame] to build rectangles.
 *
 * It is similar to [rectangles] but `x` and `y` bindings are created
 * automatically from geometries.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Rectangle Aesthetics
 * * **`fillColor`** - The fill color of the rectangle.
 * * **`alpha`** - The transparency of the rectangle.
 * * **`borderLine.color`** - Color of the rectangle's borderline.
 * * **`borderLine.width`** - Width of the rectangle's borderline.
 * * **`borderLine.type`** - Type of the rectangle's borderline, such as dashed or dotted.
 *
 * ## Example Usage
 *
 * ```kotlin
 *
 * geoDF.plot {
 *     geoRectangles {
 *         // Set the fill color based on the `event` column
 *         fillColor(event) {
 *             // Additional scale parameters can be set here if needed
 *         }
 *
 *         // Set transparency and border properties for the rectangles
 *         alpha = 0.7
 *         borderLine {
 *             color = Color.GREY
 *             width = 1.0
 *         }
 *     }
 * }
 * ```
 */
public fun GeoDataScope.geoRectangles(
    block: RectanglesBuilder.() -> Unit = {}
) {
    (this as LayerCreatorScope).rectangles {
        xMin.constant(null)
        yMin.constant(null)
        xMax.constant(null)
        yMax.constant(null)
        block()
    }
}

// TODO add ColumnAccessor & String api
@JvmName("geoRectanglesGeometry")
/**
 * Adds a new `rectangles` layer to the plot.
 *
 * Uses bounds of provided [Geometry] values to build rectangles.
 *
 * It is similar to [rectangles] but `x` and `y` bindings are created
 * automatically from geometries.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Rectangle Aesthetics
 * * **`fillColor`** - The fill color of the rectangle.
 * * **`alpha`** - The transparency of the rectangle.
 * * **`borderLine.color`** - Color of the rectangle's borderline.
 * * **`borderLine.width`** - Width of the rectangle's borderline.
 * * **`borderLine.type`** - Type of the rectangle's borderline, such as dashed or dotted.
 *
 * ## Example Usage
 *
 * ```kotlin
 *
 * df.plot {
 *     geoRectangles(geometriesColumn) {
 *         // Set the fill color based on the `event` column
 *         fillColor("event") {
 *             // Additional scale parameters can be set here if needed
 *         }
 *
 *         // Set transparency and border properties for the rectangles
 *         alpha = 0.7
 *         borderLine {
 *             color = Color.GREY
 *             width = 1.0
 *         }
 *     }
 * }
 * ```
 */
public fun LayerCreatorScope.geoRectangles(
    geometry: DataColumn<Geometry>,
    block: RectanglesBuilder.() -> Unit = {}
) {
    geoLayer(geometry, { geoRectangles(it) }, block)
}

@JvmName("geoRectanglesEnvelope")
/**
 * Adds a new `rectangles` layer to the plot.
 *
 * Uses provided [Envelope] values to build rectangles.
 *
 * It is similar to [rectangles] but `x` and `y` bindings are created
 * automatically from envelopes.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Rectangle Aesthetics
 * * **`fillColor`** - The fill color of the rectangle.
 * * **`alpha`** - The transparency of the rectangle.
 * * **`borderLine.color`** - Color of the rectangle's borderline.
 * * **`borderLine.width`** - Width of the rectangle's borderline.
 * * **`borderLine.type`** - Type of the rectangle's borderline, such as dashed or dotted.
 *
 * ## Example Usage
 *
 * ```kotlin
 *
 * df.plot {
 *     geoRectangles(envelopesColumn) {
 *         // Set the fill color based on the `event` column
 *         fillColor("event") {
 *             // Additional scale parameters can be set here if needed
 *         }
 *
 *         // Set transparency and border properties for the rectangles
 *         alpha = 0.7
 *         borderLine {
 *             color = Color.GREY
 *             width = 1.0
 *         }
 *     }
 * }
 * ```
 */
public fun LayerCreatorScope.geoRectangles(
    geometry: DataColumn<Envelope>,
    block: RectanglesBuilder.() -> Unit = {}
) {
    val factory = GeometryFactory()
    geoLayer(geometry.map { factory.toGeometry(it) }, { geoRectangles(it) }, block)
}

/**
 * Adds a new `rectangles` layer to the plot.
 *
 * Uses bounds of provided [Geometry] values to build rectangles.
 *
 * It is similar to [rectangles] but `x` and `y` bindings are created
 * automatically from geometries.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Rectangle Aesthetics
 * * **`fillColor`** - The fill color of the rectangle.
 * * **`alpha`** - The transparency of the rectangle.
 * * **`borderLine.color`** - Color of the rectangle's borderline.
 * * **`borderLine.width`** - Width of the rectangle's borderline.
 * * **`borderLine.type`** - Type of the rectangle's borderline, such as dashed or dotted.
 *
 * ## Example Usage
 *
 * ```kotlin
 *
 * df.plot {
 *     geoRectangles(listOf(polygon1, point2, multiLineString3)) {
 *         // Set the fill color based on the `event` column
 *         fillColor(listOf("A", "B", "A")) {
 *             // Additional scale parameters can be set here if needed
 *         }
 *
 *         // Set transparency and border properties for the rectangles
 *         alpha = 0.7
 *         borderLine {
 *             color = Color.GREY
 *             width = 1.0
 *         }
 *     }
 * }
 * ```
 */
@JvmName("geoRectanglesGeometry")
public fun LayerCreatorScope.geoRectangles(
    geometry: Iterable<Geometry>,
    block: RectanglesBuilder.() -> Unit = {}
) {
    geoRectangles(DataColumn.createValueColumn("geometry", geometry.asList(), typeOf<Geometry>(), Infer.Type), block)
}

/**
 * Adds a new `rectangles` layer to the plot.
 *
 * Uses provided [Envelope] values to build rectangles.
 *
 * It is similar to [rectangles] but `x` and `y` bindings are created
 * automatically from envelopes.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Rectangle Aesthetics
 * * **`fillColor`** - The fill color of the rectangle.
 * * **`alpha`** - The transparency of the rectangle.
 * * **`borderLine.color`** - Color of the rectangle's borderline.
 * * **`borderLine.width`** - Width of the rectangle's borderline.
 * * **`borderLine.type`** - Type of the rectangle's borderline, such as dashed or dotted.
 *
 * ## Example Usage
 *
 * ```kotlin
 *
 * df.plot {
 *     geoRectangles(listOf(envelope1, envelope2, envelope3)) {
 *         // Set the fill color based on the `event` column
 *         fillColor(listOf("A", "B", "A")) {
 *             // Additional scale parameters can be set here if needed
 *         }
 *
 *         // Set transparency and border properties for the rectangles
 *         alpha = 0.7
 *         borderLine {
 *             color = Color.GREY
 *             width = 1.0
 *         }
 *     }
 * }
 * ```
 */
@JvmName("geoRectanglesEnvelope")
public fun LayerCreatorScope.geoRectangles(
    geometry: Iterable<Envelope>,
    block: RectanglesBuilder.() -> Unit = {}
) {
    geoRectangles(DataColumn.createValueColumn("geometry", geometry.asList(), typeOf<Envelope>(), Infer.Type), block)
}

/**
 * Adds a new `rectangles` layer to the plot.
 *
 * Uses provided [Envelope] value to build rectangle.
 *
 * It is similar to [rectangles] but `x` and `y` bindings are created
 * automatically from envelopes.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Rectangle Aesthetics
 * * **`fillColor`** - The fill color of the rectangle.
 * * **`alpha`** - The transparency of the rectangle.
 * * **`borderLine.color`** - Color of the rectangle's borderline.
 * * **`borderLine.width`** - Width of the rectangle's borderline.
 * * **`borderLine.type`** - Type of the rectangle's borderline, such as dashed or dotted.
 *
 * ## Example Usage
 *
 * ```kotlin
 *
 * df.plot {
 *     geoRectangles(envelope) {
 *         // Set transparency and border properties for the rectangles
 *         alpha = 0.7
 *         borderLine {
 *             color = Color.GREY
 *             width = 1.0
 *         }
 *     }
 * }
 * ```
 */
public fun LayerCreatorScope.geoRectangles(
    envelope: Envelope,
    block: RectanglesBuilder.() -> Unit = {}
) {
    geoRectangles(listOf(envelope), block)
}

/**
 * Adds a new `rectangles` layer to the plot.
 *
 * Uses bounds of provided [Polygon] value to build rectangle.
 *
 * It is similar to [rectangles] but `x` and `y` bindings are created
 * automatically from geometry.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Rectangle Aesthetics
 * * **`fillColor`** - The fill color of the rectangle.
 * * **`alpha`** - The transparency of the rectangle.
 * * **`borderLine.color`** - Color of the rectangle's borderline.
 * * **`borderLine.width`** - Width of the rectangle's borderline.
 * * **`borderLine.type`** - Type of the rectangle's borderline, such as dashed or dotted.
 *
 * ## Example Usage
 *
 * ```kotlin
 *
 * df.plot {
 *     geoRectangles(polygon) {
 *         // Set transparency and border properties for the rectangles
 *         alpha = 0.7
 *         borderLine {
 *             color = Color.GREY
 *             width = 1.0
 *         }
 *     }
 * }
 * ```
 */
public fun LayerCreatorScope.geoRectangles(
    polygon: Polygon,
    block: RectanglesBuilder.() -> Unit = {}
) {
    geoRectangles(listOf(polygon), block)
}

/**
 * Adds a new `rectangles` layer to the plot.
 *
 * Uses bounds of provided [MultiPolygon] value to build rectangle.
 *
 * It is similar to [rectangles] but `x` and `y` bindings are created
 * automatically from geometry.
 *
 * This function creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Rectangle Aesthetics
 * * **`fillColor`** - The fill color of the rectangle.
 * * **`alpha`** - The transparency of the rectangle.
 * * **`borderLine.color`** - Color of the rectangle's borderline.
 * * **`borderLine.width`** - Width of the rectangle's borderline.
 * * **`borderLine.type`** - Type of the rectangle's borderline, such as dashed or dotted.
 *
 * ## Example Usage
 *
 * ```kotlin
 *
 * df.plot {
 *     geoRectangles(multiPolygon) {
 *         // Set transparency and border properties for the rectangles
 *         alpha = 0.7
 *         borderLine {
 *             color = Color.GREY
 *             width = 1.0
 *         }
 *     }
 * }
 * ```
 */
public fun LayerCreatorScope.geoRectangles(
    multiPolygon: MultiPolygon,
    block: RectanglesBuilder.() -> Unit = {}
) {
    geoRectangles(listOf(multiPolygon), block)
}
