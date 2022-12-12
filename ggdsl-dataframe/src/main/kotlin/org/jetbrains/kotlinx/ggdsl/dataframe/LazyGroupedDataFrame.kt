package org.jetbrains.kotlinx.ggdsl.dataframe

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedDataInterface

@Serializable
public data class LazyGroupedDataFrame(
    override val keys: List<String>,
    override val origin: DataFrameWrapper,
) : LazyGroupedDataInterface {
    override fun count(): GroupedByWrapper<Any?, Any?> = GroupedByWrapper(origin.df.groupBy(cols = keys.toTypedArray() ))
}
