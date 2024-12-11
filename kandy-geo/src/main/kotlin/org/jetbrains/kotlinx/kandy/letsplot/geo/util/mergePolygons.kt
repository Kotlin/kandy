package org.jetbrains.kotlinx.kandy.letsplot.geo.util

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.asIterable
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.MultiPolygon
import org.locationtech.jts.geom.Polygon
import org.locationtech.jts.operation.union.CascadedPolygonUnion

/**
 * Merges a collection of polygons and multi-polygons into a single [MultiPolygon].
 *
 * This function processes an [Iterable] containing geometric shapes
 * of type [Polygon] and [MultiPolygon], and merges them into a combined `MultiPolygon`.
 *
 * @return A `MultiPolygon` that represents the union of all merged polygons.
 */
public fun Iterable<Geometry>.mergePolygons(): MultiPolygon {
    val geometryFactory = GeometryFactory()
    val cleanedPolygons = map { geometry ->
        val cleanedGeometry = geometry.buffer(0.0)
        when (cleanedGeometry) {
            is MultiPolygon -> cleanedGeometry
            is Polygon -> geometryFactory.createMultiPolygon(arrayOf(cleanedGeometry))
            else -> error("Unsupported geometry type: ${geometry::class.simpleName}")
        }
    }
    val union = CascadedPolygonUnion.union(cleanedPolygons.toList())

    return union as MultiPolygon
}

/**
 * Merges the polygons and multi-polygons contained within this [DataColumn] of geometries
 * into a single [MultiPolygon].
 *
 * This function processes an [DataColumn] containing geometric shapes
 * of type [Polygon] and [MultiPolygon], and merges them into a combined `MultiPolygon`.
 *
 * @return A `MultiPolygon` that represents the union of all polygons in the column.
 */
public fun DataColumn<Geometry>.mergePolygons(): MultiPolygon = asIterable().mergePolygons()
