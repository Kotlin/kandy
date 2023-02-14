/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.data

import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.concat
import org.jetbrains.kotlinx.dataframe.api.map
import org.jetbrains.kotlinx.ggdsl.ir.data.CountedGroupedData

/**
 * Wrapper for a [GroupBy] implementing [CountedGroupedData].
 */
// TODO @Serializable
public data class GroupedByWrapper<T, G>(public val groupBy: GroupBy<T, G>): CountedGroupedData {
    override val keys: DataFrameWrapper
        get ()= DataFrameWrapper(groupBy.keys)
    override val groups: List<DataFrameWrapper>
        get() = groupBy.groups.map { DataFrameWrapper(it) }.toList()

    override fun toLazy(): LazyGroupedDataFrame = LazyGroupedDataFrame(
        keys.df.columnNames(),
        DataFrameWrapper(groupBy.groups.concat())
    )
}
