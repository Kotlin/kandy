package org.jetbrains.kotlinx.kandy.letsplot.geo.util

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.map
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.MultiPolygon
import org.locationtech.jts.operation.union.CascadedPolygonUnion


public fun DataColumn<Geometry>.mergePolygons(): MultiPolygon {
    val geometryFactory = GeometryFactory()
    val cleanedPolygons = map { geometry ->
        val cleanedGeometry = geometry.buffer(0.0)
        when (cleanedGeometry) {
            is MultiPolygon -> cleanedGeometry
            is org.locationtech.jts.geom.Polygon -> geometryFactory.createMultiPolygon(arrayOf(cleanedGeometry))
            else -> throw IllegalArgumentException("Unsupported geometry type: ${geometry::class.simpleName}")
        }
    }
    val union = CascadedPolygonUnion.union(cleanedPolygons.toList())

    return union as MultiPolygon
}
