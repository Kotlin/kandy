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
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.PathBuilder
import org.jetbrains.kotlinx.kandy.letsplot.layers.path
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.LineString
import org.locationtech.jts.geom.Lineal
import org.locationtech.jts.geom.MultiLineString
import kotlin.reflect.typeOf

/**
 * Adds a new `path` layer to the plot.
 *
 * Uses [LineString] and [MultiLineString] values from `geometry`
 * column of [GeoDataFrame] to build lines.
 *
 * It is similar to [path] but `x` and `y` bindings
 * are created automatically from geometries.
 *
 * This function optionally creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Path Aesthetics
 * * **`color`** - The color of the path.
 * * **`type`** - The type of the line, such as dashed or dotted.
 * * **`width`** - The width of the path.
 * * **`alpha`** - The transparency of the path.
 *
 * ## Example
 *
 * ```kotlin
 * geoDF.plot {
 *     geoPath {
 *         // Non-positional settings
 *         width = 2.0 // Sets the width of the path
 *
 *         // Non-positional mapping
 *         color(typeColumn) {
 *         //      Inside this block, we would define how `typeColumn` is mapped to color,
 *         //      e.g., providing a color scale if `typeColumn` is a categorical column
 *         }
 *
 *         // Here, we specify the line type and alpha if needed
 *         type = LineType.DOTDASH // Example setting, if your DSL allows direct assignments like this
 *         alpha = 0.6 // Setting the transparency of the path
 *     }
 * }
 * ```
 */
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

// TODO add ColumnAccessor & String api
/**
 * Adds a new `path` layer to the plot.
 *
 * Uses provided [LineString] and [MultiLineString] to build lines.
 *
 * It is similar to [path] but `x` and `y` bindings
 * are created automatically from geometries.
 *
 * This function optionally creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Path Aesthetics
 * * **`color`** - The color of the path.
 * * **`type`** - The type of the line, such as dashed or dotted.
 * * **`width`** - The width of the path.
 * * **`alpha`** - The transparency of the path.
 *
 * ## Example
 *
 * ```kotlin
 * df.plot {
 *     geoPath(lineStingColumn) {
 *         // Non-positional settings
 *         width = 2.0 // Sets the width of the path
 *
 *         // Non-positional mapping
 *         color(typeColumn) {
 *         //      Inside this block, we would define how `typeColumn` is mapped to color,
 *         //      e.g., providing a color scale if `typeColumn` is a categorical variable
 *         }
 *
 *         // Here, we specify the line type and alpha if needed
 *         type = LineType.DOTDASH // Example setting, if your DSL allows direct assignments like this
 *         alpha = 0.6 // Setting the transparency of the path
 *     }
 * }
 * ```
 */
public fun LayerCreatorScope.geoPath(
    geometry: DataColumn<Geometry>,
    block: PathBuilder.() -> Unit = {}
) {
    geoLayer(geometry, { geoPath(it) }, block)
}

/**
 * Adds a new `path` layer to the plot.
 *
 * Uses provided [LineString] and [MultiLineString] to build lines.
 *
 * It is similar to [path] but `x` and `y` bindings
 * are created automatically from geometries.
 *
 * This function optionally creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Path Aesthetics
 * * **`color`** - The color of the path.
 * * **`type`** - The type of the line, such as dashed or dotted.
 * * **`width`** - The width of the path.
 * * **`alpha`** - The transparency of the path.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     geoPath(listOf(lineString1, lineString2, lineString3)) {
 *         // Non-positional settings
 *         width = 2.0 // Sets the width of the path
 *
 *         // Non-positional mapping
 *         color(listOf("A", "B", "A")) {
 *         //      Inside this block, we would define how given strings is mapped to color,
 *         //      e.g., providing a color categorical scale
 *         }
 *
 *         // Here, we specify the line type and alpha if needed
 *         type = LineType.DOTDASH // Example setting, if your DSL allows direct assignments like this
 *         alpha = 0.6 // Setting the transparency of the path
 *     }
 * }
 * ```
 */
public fun LayerCreatorScope.geoPath(
    geometry: Iterable<Geometry>,
    block: PathBuilder.() -> Unit = {}
) {
    geoPath(DataColumn.createValueColumn("geometry", geometry.asList(), typeOf<Geometry>(), Infer.Type), block)
}

/**
 * Adds a new `path` layer to the plot.
 *
 * Uses provided [LineString] to build line.
 *
 * It is similar to [path] but `x` and `y` bindings
 * are created automatically from geometry.
 *
 * This function optionally creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Path Aesthetics
 * * **`color`** - The color of the path.
 * * **`type`** - The type of the line, such as dashed or dotted.
 * * **`width`** - The width of the path.
 * * **`alpha`** - The transparency of the path.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     geoPath(lineString) {
 *         // Non-positional settings
 *         width = 2.0 // Sets the width of the path
 *
 *         // Here, we specify the line type and alpha if needed
 *         type = LineType.DOTDASH // Example setting, if your DSL allows direct assignments like this
 *         alpha = 0.6 // Setting the transparency of the path
 *     }
 * }
 * ```
 */
public fun LayerCreatorScope.geoPath(
    lineString: LineString,
    block: PathBuilder.() -> Unit = {}
) {
    geoPath(listOf(lineString), block)
}

/**
 * Adds a new `path` layer to the plot.
 *
 * Uses provided [MultiLineString] to build lines.
 *
 * It is similar to [path] but `x` and `y` bindings
 * are created automatically from geometry.
 *
 * This function optionally creates a context where you can set aesthetic mappings (`aes`) or aesthetic constants.
 * - Mappings are specified by calling methods that correspond to aesthetic names (`aes`).
 * - Constants are directly assigned using properties with the names corresponding to aesthetics.
 *
 * ## Path Aesthetics
 * * **`color`** - The color of the path.
 * * **`type`** - The type of the line, such as dashed or dotted.
 * * **`width`** - The width of the path.
 * * **`alpha`** - The transparency of the path.
 *
 * ## Example
 *
 * ```kotlin
 * plot {
 *     geoPath(multiLineString) {
 *         // Non-positional settings
 *         width = 2.0 // Sets the width of the path
 *
 *         // Here, we specify the line type and alpha if needed
 *         type = LineType.DOTDASH // Example setting, if your DSL allows direct assignments like this
 *         alpha = 0.6 // Setting the transparency of the path
 *     }
 * }
 * ```
 */
public fun LayerCreatorScope.geoPath(
    multiLineString: MultiLineString,
    block: PathBuilder.() -> Unit = {}
) {
    geoPath(listOf(multiLineString), block)
}
