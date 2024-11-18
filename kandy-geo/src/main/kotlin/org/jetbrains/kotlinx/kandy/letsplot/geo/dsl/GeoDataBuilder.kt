@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE", "CANNOT_OVERRIDE_INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.geo.dsl

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.geo.GeoDataFrame
import org.jetbrains.kotlinx.dataframe.geo.WithGeometry
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.DatasetBuilderImpl
import org.jetbrains.kotlinx.kandy.ir.data.TableData

internal class GeoDataBuilder(
    namedData: GeoData
) : DatasetBuilderImpl(null) {
    constructor(geodf: GeoDataFrame<*>) : this(GeoData(geodf))

    private val crs = namedData.geoDataFrame.crs
    override val baseDataFrame: DataFrame<*> = namedData.dataFrame

    override fun build(): TableData {
        @Suppress("UNCHECKED_CAST")
        return GeoData(GeoDataFrame(buffer as DataFrame<WithGeometry>, crs))
    }
}