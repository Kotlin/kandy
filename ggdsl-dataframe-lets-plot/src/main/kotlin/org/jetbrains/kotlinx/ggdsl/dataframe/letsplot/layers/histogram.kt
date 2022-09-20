package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toDataSource
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.HistogramContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.histogram
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Bins

public inline fun <reified T : Any> PlotContext.histogram(
    source: ColumnReference<T>,
    bins: Bins? = null,
    boundary: Double? = null,
    center: Double? = null,
    block: HistogramContext.() -> Unit
): Unit = histogram(source.toDataSource(), bins, boundary, center, block)
