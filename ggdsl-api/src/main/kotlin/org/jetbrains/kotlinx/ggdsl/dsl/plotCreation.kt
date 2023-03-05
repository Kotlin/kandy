/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.toDataFrame
import org.jetbrains.kotlinx.ggdsl.dsl.internal.*
import org.jetbrains.kotlinx.ggdsl.ir.Plot


/**
 * Returns a new [Plot].
 *
 * Creates a plotting context [NamedDataPlotContext], in which you can configure a plot.
 * Possible configuration parameters depend on the engine.
 *
 * @param dataset plot dataset.
 */
public inline fun plot(dataset: Map<String, List<*>>, block: DataFramePlotContext<*>.() -> Unit): Plot {
    return DataFramePlotContext(dataset.toDataFrame()).apply(block).toPlot()
}
/*
/**
 * Returns a new [Plot].
 *
 * Creates a plotting with grouping context [GroupedDataPlotContext], in which you can configure a plot.
 * Possible configuration parameters depend on the engine.
 *
 * @param dataset plot dataset.
 */
public inline fun plot(dataset: GroupedData, block: GroupedDataPlotContext.() -> Unit): Plot {
    return GroupedDataPlotContext(dataset).apply(block).toPlot().also {
        it.validate()
    }
}

/**
 * Returns a new [Plot].
 *
 * Creates a plotting with mutable data (i.e. with dynamic dataset change - usage
 * iterable instead of prepared columns)
 * context [PlotContextMutable], in which you can configure a plot.
 * Possible configuration parameters depend on the engine.
 */
public inline fun plot(block: PlotContextMutable.() -> Unit): Plot {
    return PlotContextMutable().apply(block).toPlot().also {
        it.validate()
    }
}

 */

public fun <T> DataFrame<T>.plot(block: DataFramePlotContext<T>.() -> Unit): Plot {
    return DataFramePlotContext<T>(this).apply(block).toPlot()
}

/*
public fun <T, G> GroupBy<T, G>.plot(block: GroupedDataFrameContext<T, G>.() -> Unit): Plot {
    return GroupedDataFrameContext(this).apply(block).toPlot()
}


 */