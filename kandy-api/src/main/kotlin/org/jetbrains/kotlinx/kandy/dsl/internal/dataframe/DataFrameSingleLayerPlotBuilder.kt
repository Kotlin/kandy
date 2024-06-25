package org.jetbrains.kotlinx.kandy.dsl.internal.dataframe

import org.jetbrains.kotlinx.kandy.dsl.internal.SingleLayerPlotBuilder
import org.jetbrains.kotlinx.kandy.ir.data.TableData

public abstract class DataFrameSingleLayerPlotBuilder @PublishedApi internal constructor(data: TableData) :
    SingleLayerPlotBuilder() {
    override val datasetBuilder = DatasetBuilderImpl.fromData(data)
}
