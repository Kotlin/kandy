package org.jetbrains.kotlinx.ggdsl.ir.data

import org.jetbrains.kotlinx.dataframe.api.GroupBy


/**
 * Actually grouped data.
 *
 * @property keys grouping keys.
 * @property groups [List] of groups.
 *
 * @property toLazy transformation into [LazyGroupedData]
 */
public data class GroupedData(
    public val groupBy: GroupBy<*, *>
): TableData {
    public val keys: List<String> = groupBy.keys.columnNames()
    public val origin: NamedData = NamedData(groupBy.toDataFrame())
}
