@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package org.jetbrains.kotlinx.kandy.letsplot.geo.dsl

import org.geotools.api.referencing.crs.CoordinateReferenceSystem
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.locationtech.jts.geom.Geometry

@PublishedApi
internal fun GeoDataScope.geometry(): DataColumn<Geometry> {
    with((this as LayerCreatorScope).datasetBuilder as GeoDataBuilder) {
        takeColumn("geometry")
        return buffer[column<Geometry>("geometry")]
    }
}

@PublishedApi
internal fun GeoDataScope.crs(): CoordinateReferenceSystem? {
    return ((this as LayerCreatorScope).datasetBuilder as GeoDataBuilder).crs
}
