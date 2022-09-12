/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toDataSource
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.HistogramContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.histogram
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Bins

inline fun <reified T : Any> PlotContext.histogram(
    source: ColumnReference<T>,
    bins: Bins? = null,
    boundary: Double? = null,
    center: Double? = null,
    block: HistogramContext.() -> Unit
) = histogram(source.toDataSource(), bins, boundary, center, block)
