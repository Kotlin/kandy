package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot

/*
inline fun <reified T : Any> PlotContext.histogram(column: ColumnReference<T>, block: HistogramContext.() -> Unit) {
   /* layers.add(
        HistogramContext(data)
            .apply {
                copyFrom(this@histogram)
                x(source)
            }
            .apply(block)
            .toLayer(HISTOGRAM) // TODO
    )*/
    return histogram(column.toDataSource(), block = block)
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

 */
