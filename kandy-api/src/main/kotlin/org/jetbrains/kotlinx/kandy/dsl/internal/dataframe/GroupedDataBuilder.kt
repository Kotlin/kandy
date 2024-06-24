package org.jetbrains.kotlinx.kandy.dsl.internal.dataframe

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.kandy.ir.data.TableData

internal class GroupedDataBuilder(
    groupedData: GroupedData,
    initialBuilder: DatasetBuilderImpl? = null
) : DatasetBuilderImpl(initialBuilder) {
    override val baseDataFrame: DataFrame<*> = groupedData.dataFrame
    val keys = groupedData.keys.apply {
        forEach { key -> takeColumn(key) }
    }

    override fun build(): TableData {
        return GroupedData(buffer, keys)
    }

    constructor(groupBy: GroupBy<*, *>, initialBuilder: DatasetBuilderImpl? = null)
            : this(GroupedData(groupBy), initialBuilder)
}
