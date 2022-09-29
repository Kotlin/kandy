package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.GroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface

/**
 * Returns a new plot.
 *
 * Creates a [PlotContext]. In this context, functions for creating new layers are defined.
 *
 * TODO
 * Also [x][PlotContext.x] and [y][PlotContext.y] aesthetic attributes are defined in a [PlotContext].
 * You can create global mappings on them, which will be inherited in following layers in this context.
 *
 * In a [PlotContext], the dataset can be overridden with an assignment to the [PlotContext.data]
 * (for example, to use one dataset for some layers and another for others).
 *
 *```
 * plot {
 *   data = datasetA
 *   x(srcX1)
 *   y(srcY1)
 *   points {
 *      x(srcX2)
 *   }
 *   points {
 *      data = datasetB
 *   }
 *   data = datasetC
 *   y(srcY2)
 *   bars { }
 *   line { }
 * }
 * ```
 *
 * @param dataset the initial plot dataset
 * @return new [Plot]
 * @see [BaseBindingContext]
 */
inline fun <T: NamedDataInterface> plot(dataset: T, block: NamedDataPlotContext<T>.() -> Unit): Plot {
    return NamedDataPlotContext(dataset).apply(block).toPlot()
}

inline fun plot(dataset: GroupedDataInterface, block: GroupedDataPlotContext.() -> Unit): Plot {
    return GroupedDataPlotContext(dataset).apply(block).toPlot()
}

/*
inline fun plot(block: PlotContext.() -> Unit): Plot {
    return PlotContext().apply {
        data.putAll(dataset) // TODO
        block()
    }.toPlot()
}

 */
