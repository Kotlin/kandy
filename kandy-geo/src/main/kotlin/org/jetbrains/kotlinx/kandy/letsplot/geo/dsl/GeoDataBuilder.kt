@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE", "CANNOT_OVERRIDE_INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.geo.dsl

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.add
import org.jetbrains.kotlinx.dataframe.geo.GeoDataFrame
import org.jetbrains.kotlinx.dataframe.geo.WithGeometry
import org.jetbrains.kotlinx.dataframe.geo.geometry
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.DatasetBuilderImpl
import org.jetbrains.kotlinx.kandy.ir.data.TableData

internal class GeoDataBuilder(
    geoData: GeoData
) : DatasetBuilderImpl(null) {
    constructor(geodf: GeoDataFrame<*>) : this(GeoData(geodf))

    val crs = geoData.geoDataFrame.crs
    override val baseDataFrame: DataFrame<WithGeometry> = geoData.dataFrame

    @Suppress("UNCHECKED_CAST")
    override fun build(): TableData {
        if (!buffer.containsColumn("geometry")) {
            buffer = buffer.add(baseDataFrame.geometry)
        }
        return GeoData(GeoDataFrame(buffer as DataFrame<WithGeometry>, crs))
    }
}