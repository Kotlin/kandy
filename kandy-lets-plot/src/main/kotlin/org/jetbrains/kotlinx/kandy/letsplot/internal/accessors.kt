@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package org.jetbrains.kotlinx.kandy.letsplot.internal

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.DatasetBuilderImpl
import org.jetbrains.kotlinx.kandy.dsl.internal.datasetBuilder

@Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
@PublishedApi
internal val LayerBuilder.datasetBuilderImpl: DatasetBuilderImpl
        get() = datasetBuilder as DatasetBuilderImpl

@Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
@PublishedApi
internal val PlotBuilder.datasetBuilderImpl: DatasetBuilderImpl
    get() = datasetBuilder as DatasetBuilderImpl
