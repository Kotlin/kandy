package org.jetbrains.kotlinx.kandy.dsl.internal.dataframe

import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetBuilder

@PublishedApi
internal val GroupedDataScope<*, *>.datasetBuilder: DatasetBuilder
    get() = when (this) {
        is GroupByScope -> datasetBuilder
        is GroupByPlotBuilder -> datasetBuilder
    }
