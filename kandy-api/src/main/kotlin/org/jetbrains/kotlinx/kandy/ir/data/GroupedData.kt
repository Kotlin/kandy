package org.jetbrains.kotlinx.kandy.ir.data

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.concat
import org.jetbrains.kotlinx.dataframe.api.groupBy


/**
 * Grouped dataframe.
 *
 * @property groupBy underlying [GroupBy].
 * @property keys grouping keys.
 * @property origin original [NamedData]
 * @constructor default constructor from [GroupBy]
 */
public data class GroupedData(
    public val groupBy: GroupBy<*, *>
) : TableData {
    public val keys: List<String> = groupBy.keys.columnNames()
    public val origin: NamedData = NamedData(groupBy.groups.concat())

    /**
     * @param origin original [NamedData].
     * @param keys grouping keys.
     */
    public constructor(origin: NamedData, keys: List<String>) :
            this(origin.dataFrame.groupBy(*keys.toTypedArray()))

    /**
     * @param dataFrame original [DataFrame].
     * @param keys grouping keys.
     */
    public constructor(dataFrame: DataFrame<*>, keys: List<String>) :
            this(dataFrame.groupBy(*keys.toTypedArray()))
}
