/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference

import org.jetbrains.kotlinx.ggdsl.dsl.internal.GroupedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.NamedDataPlotContext

import org.jetbrains.kotlinx.ggdsl.dsl.plot
import org.jetbrains.kotlinx.ggdsl.ir.Plot


/**
 * Returns a new plot.
 *
 * Creates a scope with this [DataFrame] as a receiver. The [plot] function should be used to create plot with
 * this [DataFrame] as the data source. [ColumnReference] can be used instead of [ColumnReference] for mapping.
 *
 * Scope allows using automatically generated as extension field columns for mapping.
 * ```
 * val dfPlot = df.create {
 *    plot {
 *       x(columnA)
 *       y(columnB)
 *       points {
 *          color(columnC)
 *       }
 *    }
 * }
 * ```
 *
 * In the future, with full support for the context receivers feature
 * this design can be replaced with a single function, for example the following:
 * ```
 * fun <T> DataFrame<T>.plot(block: context(DataFrame<T>, PlotContext).() -> Unit): Plot
 * ```
 *
 * @see <a href="https://kotlin.github.io/dataframe/schemas.html#dataschema-workflow-in-jupyter">Kotlin Dataframe Documentation</a>
 * @return new [Plot]
 */
public inline fun <T> DataFrame<T>.create(block: DataFrame<T>.() -> Plot): Plot {
    return block()
}

/**
 * Creates new [Plot] with this [DataFrame] as a data source.
 *
 * @see [create]
 */

public inline fun DataFrame<*>.plot(block: NamedDataPlotContext.() -> Unit): Plot {
    return plot(DataFrameWrapper(this), block)
}

public inline fun <T, G> GroupBy<T, G>.create(block: GroupedDataFrameContext<T, G>.() -> Plot): Plot {
    return GroupedDataFrameContext(this).block()
}

// TODO
public inline fun <T, G> GroupedDataFrameContext<T, G>.plot(block: GroupedDataPlotContext.() -> Unit): Plot {
    return plot(toLazy(), block)
}
