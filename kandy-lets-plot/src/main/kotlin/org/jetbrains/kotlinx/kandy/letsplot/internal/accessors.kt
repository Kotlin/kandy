@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.internal

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.DatasetBuilderImpl
import org.jetbrains.kotlinx.kandy.dsl.internal.datasetBuilder

@PublishedApi
internal val LayerBuilder.datasetBuilderImpl: DatasetBuilderImpl
    get() = datasetBuilder as DatasetBuilderImpl

@PublishedApi
internal val PlotBuilder.datasetBuilderImpl: DatasetBuilderImpl
    get() = datasetBuilder as DatasetBuilderImpl
