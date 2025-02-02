package org.jetbrains.kotlinx.kandy.dsl.internal.dataframe

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.MultiLayerPlotBuilder
import org.jetbrains.kotlinx.kandy.ir.data.TableData

public abstract class MultiLayerPlotBuilderImpl : MultiLayerPlotBuilder() {
    @PublishedApi
    override fun addDataset(dataset: TableData, initialBuilder: DatasetBuilder?): Int {
        datasetBuilders.add(DatasetBuilderImpl(dataset, initialBuilder as DatasetBuilderImpl?))
        return datasetBuilders.lastIndex
    }

    override fun addEmptyDataset(): Int {
        return addDataset(NamedData(DataFrame.Empty))
    }
}