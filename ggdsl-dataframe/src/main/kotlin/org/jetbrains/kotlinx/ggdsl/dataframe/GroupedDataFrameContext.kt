package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.concat
import org.jetbrains.kotlinx.dataframe.api.first

public class GroupedDataFrameContext<T, G>(public val groupBy: GroupBy<T, G>)  {
    public val groupKey: DataFrame<T> = groupBy.keys
    public val column: DataFrame<G> = groupBy.groups.first() // TODO

    public fun toLazy(): LazyGroupedDataFrame = LazyGroupedDataFrame(
        groupKey.columns().map { it.name() },
        DataFrameWrapper(groupBy.groups.concat())
    )
}
