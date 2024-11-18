@file:Suppress("INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.geo.dsl

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
