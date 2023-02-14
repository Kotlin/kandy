package org.jetbrains.kotlinx.ggdsl.ir.data

import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.groupBy

/**
 * Basic interface for data in the form of a grouped dataframe.
 */
public sealed interface GroupedDataInterface : TableData

/**
 * Lazy grouped data, i.e. data with information for grouping without actual grouping.
 *
 * @property keys grouping keys.
 * @property origin original data
 *
 * @property count transformation into [CountedGroupedData]
 */
public data class LazyGroupedData(
    public val keys: List<String>,
    public val origin: NamedData
) : GroupedDataInterface {
    public fun count(): CountedGroupedData {
        return CountedGroupedData(origin.dataFrame.groupBy(*keys.toTypedArray()))
    }
}

/**
 * Actually grouped data.
 *
 * @property keys grouping keys.
 * @property groups [List] of groups.
 *
 * @property toLazy transformation into [LazyGroupedData]
 */
public data class CountedGroupedData(
    public val groupBy: GroupBy<*, *>
): GroupedDataInterface{
    public fun toLazy(): LazyGroupedData {
        return LazyGroupedData(
            groupBy.keys.columnNames(),
            NamedData(groupBy.toDataFrame())
        )
    }
}
