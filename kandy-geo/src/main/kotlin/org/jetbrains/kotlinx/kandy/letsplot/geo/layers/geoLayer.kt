@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package org.jetbrains.kotlinx.kandy.letsplot.geo.layers

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.add
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.named
import org.jetbrains.kotlinx.dataframe.geo.toGeo
import org.jetbrains.kotlinx.dataframe.size
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.DatasetBuilderImpl
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.GeoDataScope
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.geometry
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.withData
import org.locationtech.jts.geom.Geometry

/**
 * Util function for creating geo layers.
 */
@PublishedApi
internal fun <T> LayerCreatorScope.geoLayer(
    geometry: DataColumn<Geometry>,
    layerFun: GeoDataScope.(T.() -> Unit) -> Unit,
    builder: T.() -> Unit
) {
    if (this is GeoDataScope && geometry() == geometry) {
        return layerFun(builder)
    }
    val columnRowsCount = geometry.size
    val datasetBuilderRowsCount = datasetBuilder.rowsCount()
    val geometryColumn = geometry named "geometry"
    val geoDataFrame = if (columnRowsCount != datasetBuilderRowsCount) {
        // override dataset if datasetBuilder size doesn't match
        dataFrameOf(geometryColumn).toGeo(null)
    } else {
        // TODO improve logic if the other geometry column with the same size is used
        // add geometry column otherwise
        (datasetBuilder as DatasetBuilderImpl).baseDataFrame.add(geometryColumn).toGeo()
    }
    plotBuilder.withData(geoDataFrame) {
        layerFun(builder)
    }
}
