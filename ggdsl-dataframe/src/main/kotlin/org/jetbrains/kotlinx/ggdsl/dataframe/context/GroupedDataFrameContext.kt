package org.jetbrains.kotlinx.ggdsl.dataframe.context

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.concat
import org.jetbrains.kotlinx.dataframe.api.first
import org.jetbrains.kotlinx.ggdsl.dataframe.data.DataFrameWrapper
import org.jetbrains.kotlinx.ggdsl.dataframe.data.LazyGroupedDataFrame

/**
 * Wrapper of [GroupBy] that allows to access grouping keys and dataframe columns.
 *
 * @property groupKey grouping keys accessor.
 * @property column columns accessor.
 */
public class GroupedDataFrameContext<T, G>(private val groupBy: GroupBy<T, G>)  {
    public val groupKey: DataFrame<T> = groupBy.keys
    public val column: DataFrame<G> = groupBy.groups.first() // TODO

    @PublishedApi
    internal fun toLazy(): LazyGroupedDataFrame = LazyGroupedDataFrame(
        groupKey.columns().map { it.name() },
        DataFrameWrapper(groupBy.groups.concat())
    )
}
