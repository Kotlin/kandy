package org.jetbrains.kotlinx.kandy.dsl.internal.dataframe

@PublishedApi
internal val GroupedDataScope<*, *>.datasetBuilder: DatasetBuilderImpl
    get() = when (this) {
        is GroupByScope -> datasetBuilder as DatasetBuilderImpl
        is GroupByPlotBuilder -> datasetBuilder as DatasetBuilderImpl
    }
