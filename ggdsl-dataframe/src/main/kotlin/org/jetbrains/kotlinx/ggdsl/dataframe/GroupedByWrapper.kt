/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.ggdsl.ir.data.*

// TODO @Serializable
public data class GroupedByWrapper<T, G>(public val groupBy: GroupBy<T, G>): CountedGroupedDataInterface {
    override val keys: DataFrameWrapper = DataFrameWrapper(groupBy.keys)
    override val groups: List<DataFrameWrapper> = groupBy.groups.map { DataFrameWrapper(it) }.toList()

    override fun toLazy(): LazyGroupedDataFrame = LazyGroupedDataFrame(
        keys.df.columnNames(),
        DataFrameWrapper(groupBy.groups.concat())
    )
}
