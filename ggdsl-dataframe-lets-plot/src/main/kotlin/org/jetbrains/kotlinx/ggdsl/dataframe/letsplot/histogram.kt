package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.invoke
import org.jetbrains.kotlinx.ggdsl.dsl.toLayer
import org.jetbrains.kotlinx.ggdsl.dataframe.*
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.*

inline fun <reified T : Any> PlotContext.histogram(source: ColumnReference<T>, block: HistogramContext.() -> Unit) {
    layers.add(
        HistogramContext(data)
            .apply {
                copyFrom(this@histogram)
                x(source)
            }
            .apply(block)
            .toLayer(HISTOGRAM) // TODO
    )
}

inline fun <reified T : Any> PlotContext.density(source: ColumnReference<T>, block: DensityContext.() -> Unit) {
    layers.add(
        DensityContext(data)
            .apply {
                copyFrom(this@density)
                x(source)
            }
            .apply(block)
            .toLayer(DENSITY)
    )
}

inline fun <reified T : Any, reified R : Any> PlotContext.boxplot(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
    block: BoxplotStatContext.() -> Unit
) {
    layers.add(
        BoxplotStatContext(data)
            .apply {
                copyFrom(this@boxplot)
                x(sourceX)
                y(sourceY)
            }
            .apply(block)
            .toLayer(BOXPLOT_STAT)
    )
}

inline fun <reified T : Any, reified R : Any> PlotContext.boxplot(
    sourceX: DataSource<T>,
    sourceY: ColumnReference<R>,
    block: BoxplotStatContext.() -> Unit
) {
    layers.add(
        BoxplotStatContext(data)
            .apply {
                copyFrom(this@boxplot)
                x(sourceX)
                y(sourceY)
            }
            .apply(block)
            .toLayer(BOXPLOT_STAT)
    )
}

inline fun <reified T : Any, reified R : Any> PlotContext.boxplot(
    sourceX: ColumnReference<T>,
    sourceY: DataSource<R>,
    block: BoxplotStatContext.() -> Unit
) {
    layers.add(
        BoxplotStatContext(data)
            .apply {
                copyFrom(this@boxplot)
                x(sourceX)
                y(sourceY)
            }
            .apply(block)
            .toLayer(BOXPLOT_STAT)
    )
}
