package org.jetbrains.kotlinx.kandy.letsplot.internal

import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.*

@Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
@PublishedApi
internal val LayerBuilder.datasetBuilderImpl: DatasetBuilderImpl
        get() = datasetBuilder as DatasetBuilderImpl

@Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
@PublishedApi
internal val PlotBuilder.datasetBuilderImpl: DatasetBuilder
    get() = datasetBuilder as DatasetBuilderImpl
