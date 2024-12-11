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
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.PointsBuilder
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.MultiPoint
import org.locationtech.jts.geom.Point
import org.locationtech.jts.geom.Puntal
import kotlin.reflect.typeOf

/**
 * Adds a new `points` layer to the plot.
 *
 * Uses [Point] and [MultiPoint] values from `geometry`
 * column of [GeoDataFrame] to build polygons.
 *
 * It is similar to [points] but `x` and `y` bindings are created
 * automatically from geometries.
 *
 * This function provides a context where you can define aesthetic mappings (`aes`) and aesthetic constants for the layer.
 * - Mappings are specified by calling methods that have names corresponding to aesthetic names (`aes`).
 * - Constants are set directly using properties with names that correspond to aesthetics.
 *
 * ## Points Aesthetics
 * * **`color`** - The color of the point.
 * * **`symbol`** - The symbol used to represent the point.
 * * **`size`** - The size of the point.
 * * **`alpha`** - The transparency of the point.
 * * **`fillColor`** - The fill color for symbols that have a fill.
 * * **`stroke`** - width of the shape border. Applied only to the shapes having border.
 *
 * ## Example
 *
 * ```kotlin
 * geoDF.plot {
 *     geoPoints {
 *         // Non-positional settings
 *         color = Color.BLUE // Set a constant color for the points
 *         alpha = 0.7 // Set a constant transparency for the points
 *         fillColor = Color.GREEN // Set a fill color for the points (for filled symbols)
 *         stroke = 3
 *
 *         // Map 'population' to 'size' to represent the population value as the size of the point
 *         size(population) {
 *             // Additional mapping parameters if necessary
 *             // For example, you might want to normalize or scale the sizes
 *         }
 *     }
 * }
 * ```
 */
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

// TODO add ColumnAccessor & String api
/**
 * Adds a new `points` layer to the plot.
 *
 * Uses provided [Point] and [MultiPoint] values to build points.
 *
 * It is similar to [points] but `x` and `y` bindings are created
 * automatically from geometries.
 *
 * This function provides a context where you can define aesthetic mappings (`aes`) and aesthetic constants for the layer.
 * - Mappings are specified by calling methods that have names corresponding to aesthetic names (`aes`).
 * - Constants are set directly using properties with names that correspond to aesthetics.
 *
 * ## Points Aesthetics
 * * **`color`** - The color of the point.
 * * **`symbol`** - The symbol used to represent the point.
 * * **`size`** - The size of the point.
 * * **`alpha`** - The transparency of the point.
 * * **`fillColor`** - The fill color for symbols that have a fill.
 * * **`stroke`** - width of the shape border. Applied only to the shapes having border.
 *
 * ## Example
 *
 * ```kotlin
 * df.plot {
 *     geoPoints(pointsColumn) {
 *         // Non-positional settings
 *         color = Color.BLUE // Set a constant color for the points
 *         alpha = 0.7 // Set a constant transparency for the points
 *         fillColor = Color.GREEN // Set a fill color for the points (for filled symbols)
 *         stroke = 3
 *
 *         // Map 'population' column to 'size' to represent the population value as the size of the point
 *         size(population) {
 *             // Additional mapping parameters if necessary
 *             // For example, you might want to normalize or scale the sizes
 *         }
 *     }
 * }
 * ```
 */
public fun LayerCreatorScope.geoPoints(
    geometry: DataColumn<Geometry>,
    block: PointsBuilder.() -> Unit = {}
) {
    geoLayer(geometry, { geoPoints(it) }, block)
}

/**
 * Adds a new `points` layer to the plot.
 *
 * Uses provided [Point] and [MultiPoint] values to build points.
 *
 * It is similar to [points] but `x` and `y` bindings are created
 * automatically from geometries.
 *
 * This function provides a context where you can define aesthetic mappings (`aes`) and aesthetic constants for the layer.
 * - Mappings are specified by calling methods that have names corresponding to aesthetic names (`aes`).
 * - Constants are set directly using properties with names that correspond to aesthetics.
 *
 * ## Points Aesthetics
 * * **`color`** - The color of the point.
 * * **`symbol`** - The symbol used to represent the point.
 * * **`size`** - The size of the point.
 * * **`alpha`** - The transparency of the point.
 * * **`fillColor`** - The fill color for symbols that have a fill.
 * * **`stroke`** - width of the shape border. Applied only to the shapes having border.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     geoPoints(listOf(point1, multiPoint2, point3)) {
 *         // Non-positional settings
 *         color = Color.BLUE // Set a constant color for the points
 *         alpha = 0.7 // Set a constant transparency for the points
 *         fillColor = Color.GREEN // Set a fill color for the points (for filled symbols)
 *         stroke = 3
 *
 *         // Map list of `Int` to 'size'
 *         size(listOf(5, 12, 7)) {
 *             // Additional mapping parameters if necessary
 *             // For example, you might want to normalize or scale the sizes
 *         }
 *     }
 * }
 * ```
 */
public fun LayerCreatorScope.geoPoints(
    geometry: Iterable<Geometry>,
    block: PointsBuilder.() -> Unit = {}
) {
    geoPoints(DataColumn.createValueColumn("geometry", geometry.asList(), typeOf<Geometry>(), Infer.Type), block)
}

/**
 * Adds a new `points` layer to the plot.
 *
 * Uses provided [Point] value to build point.
 *
 * It is similar to [points] but `x` and `y` bindings are created
 * automatically from geometry.
 *
 * This function provides a context where you can define aesthetic mappings (`aes`) and aesthetic constants for the layer.
 * - Mappings are specified by calling methods that have names corresponding to aesthetic names (`aes`).
 * - Constants are set directly using properties with names that correspond to aesthetics.
 *
 * ## Points Aesthetics
 * * **`color`** - The color of the point.
 * * **`symbol`** - The symbol used to represent the point.
 * * **`size`** - The size of the point.
 * * **`alpha`** - The transparency of the point.
 * * **`fillColor`** - The fill color for symbols that have a fill.
 * * **`stroke`** - width of the shape border. Applied only to the shapes having border.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     geoPoints(point) {
 *         // Non-positional settings
 *         color = Color.BLUE // Set a constant color for the points
 *         alpha = 0.7 // Set a constant transparency for the points
 *         fillColor = Color.GREEN // Set a fill color for the points (for filled symbols)
 *         stroke = 3
 *     }
 * }
 * ```
 */
public fun LayerCreatorScope.geoPoints(
    points: Point,
    block: PointsBuilder.() -> Unit = {}
) {
    geoPoints(listOf(points), block)
}

/**
 * Adds a new `points` layer to the plot.
 *
 * Uses provided [MultiPoint] value to build points.
 *
 * It is similar to [points] but `x` and `y` bindings are created
 * automatically from geometry.
 *
 * This function provides a context where you can define aesthetic mappings (`aes`) and aesthetic constants for the layer.
 * - Mappings are specified by calling methods that have names corresponding to aesthetic names (`aes`).
 * - Constants are set directly using properties with names that correspond to aesthetics.
 *
 * ## Points Aesthetics
 * * **`color`** - The color of the point.
 * * **`symbol`** - The symbol used to represent the point.
 * * **`size`** - The size of the point.
 * * **`alpha`** - The transparency of the point.
 * * **`fillColor`** - The fill color for symbols that have a fill.
 * * **`stroke`** - width of the shape border. Applied only to the shapes having border.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     geoPoints(multiPoint) {
 *         // Non-positional settings
 *         color = Color.BLUE // Set a constant color for the points
 *         alpha = 0.7 // Set a constant transparency for the points
 *         fillColor = Color.GREEN // Set a fill color for the points (for filled symbols)
 *         stroke = 3
 *     }
 * }
 * ```
 */
public fun LayerCreatorScope.geoPoints(
    multiPoints: MultiPoint,
    block: PointsBuilder.() -> Unit = {}
) {
    geoPoints(listOf(multiPoints), block)
}
