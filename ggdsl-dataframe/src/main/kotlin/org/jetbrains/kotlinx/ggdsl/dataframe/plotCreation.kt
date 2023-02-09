/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.ggdsl.dataframe.context.DataFramePlotContext
import org.jetbrains.kotlinx.ggdsl.dataframe.context.GroupedDataFrameContext
import org.jetbrains.kotlinx.ggdsl.ir.Plot

public fun<T> DataFrame<T>.plot(block: DataFramePlotContext<T>.() -> Unit): Plot {
    return DataFramePlotContext<T>(this).apply(block).toPlot()
}

public fun<T, G> GroupBy<T, G>.plot(block:  GroupedDataFrameContext<T, G>.() -> Unit): Plot {
    return GroupedDataFrameContext(this).apply(block).toPlot()
}

// TODO series plots

/*
/**
 * Returns a new [Plot].
 *
 * Creates a context with this [DataFrame] as a receiver and a plot returned. Use [DataFrame.plot]
 * in this context to build a plot with this dataframe as a plot dataset.
 *
 * In addition, in this context, you can use dataframe property-columns generated
 * with the Kotlin Notebook or with the IDEA plugin.
 *
 * ```
 * df.create { plot {
 *   x(col1)
 *   y(col2.scaled(..))
 *   points {
 *      color(col3.scaled(..))
 *   }
 * } }
 * ```
 *
 * @return [Plot].
 */
public inline fun <T> DataFrame<T>.create(block: DataFrame<T>.() -> Plot): Plot {
    return block()
}

/**
 * Creates a new [Plot] with this dataframe as a dataset.
 *
 * @receiver plot dataset.
 * @return [Plot].
 */
public inline fun <T> DataFrame<T>.plot(block: NamedDataPlotContext.() -> Unit): Plot {
    return plot(DataFrameWrapper(this), block)
}

/**
 * Returns a new [Plot].
 *
 * Creates a [GroupedDataFrameContext] over this [GroupBy] and a plot returned. Use [GroupedDataFrameContext.plot]
 * in this context to build a plot with this grouped dataframe as a plot dataset.
 *
 * In addition, in this context, you can access to group keys and dataframe columns
 * (with the Kotlin Notebook or with the IDEA plugin).
 *
 * ```
 * dfGrouped.create { plot {
 *   x(column.col1)
 *   y(groupKey.col2.scaled(..))
 *   points {
 *      color(groupKey.col3.scaled(..))
 *   }
 * } }
 * ```
 *
 * @return [Plot].
 */
public inline fun <T, G> GroupBy<T, G>.create(block: GroupedDataFrameContext<T, G>.() -> Plot): Plot {
    return GroupedDataFrameContext(this).block()
}

/**
 * Creates a new [Plot] with this grouped dataframe as a dataset.
 *
 * @receiver grouped dataframe context.
 * @return [Plot].
 */
public inline fun <T, G> GroupedDataFrameContext<T, G>.plot(block: GroupedDataPlotContext.() -> Unit): Plot {
    return plot(toLazy(), block)
}

 */
