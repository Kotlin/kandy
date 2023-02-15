package org.jetbrains.kotlinx.ggdsl.ir.data

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.concat
import org.jetbrains.kotlinx.dataframe.api.groupBy


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
) : TableData {
    public val keys: List<String> = groupBy.keys.columnNames()
    public val origin: NamedData = NamedData(groupBy.groups.concat())

    public constructor(origin: NamedData, keys: List<String>) :
            this(origin.dataFrame.groupBy(*keys.toTypedArray()))

    public constructor(dataFrame: DataFrame<*>, keys: List<String>) :
            this(dataFrame.groupBy(*keys.toTypedArray()))
}
