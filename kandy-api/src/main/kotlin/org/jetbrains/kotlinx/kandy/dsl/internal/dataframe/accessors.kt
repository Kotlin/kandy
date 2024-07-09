package org.jetbrains.kotlinx.kandy.dsl.internal.dataframe

@PublishedApi
internal val GroupedDataScope<*, *>.datasetBuilder: DatasetBuilderImpl
    get() = when (this) {
        is GroupByScope -> this.datasetBuilder as DatasetBuilderImpl
        is GroupByPlotBuilder -> this.datasetBuilder as DatasetBuilderImpl
    }
