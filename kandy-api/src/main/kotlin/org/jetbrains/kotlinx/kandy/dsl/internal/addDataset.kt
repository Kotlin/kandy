package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
import org.jetbrains.kotlinx.kandy.ir.data.NamedData

internal fun LayerCollectorContext.addDataset(df: DataFrame<*>): Int {
    val datasetHandlers = plotContext.datasetHandlers
    datasetHandlers.add(DatasetHandler(NamedData(df)))
    return datasetHandlers.lastIndex
}

internal fun LayerCollectorContext.addDataset(gb: GroupBy<*, *>, initialBuffer: DataFrame<*>): Int {
    val datasetHandlers = plotContext.datasetHandlers
    datasetHandlers.add(DatasetHandler(GroupedData(gb), initialBuffer))
    return datasetHandlers.lastIndex
}
