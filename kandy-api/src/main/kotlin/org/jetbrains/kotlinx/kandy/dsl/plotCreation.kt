/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.toDataFrame
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.DataFramePlotBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.GroupByPlotBuilder
import org.jetbrains.kotlinx.kandy.ir.Plot

/**
 * Returns a new [Plot].
 *
 * Creates a [DataFramePlotBuilder] plotting context, in which you can configure a plot.
 * Possible configuration parameters depend on the engine.
 *
 * @param dataset plot dataset.
 */
public inline fun plot(dataset: Map<String, List<*>> = mapOf(), block: DataFramePlotBuilder<*>.() -> Unit): Plot {
    return plot(dataset.toDataFrame(), block)
}

/**
 * Returns a new [Plot].
 *
 * Creates a [DataFramePlotBuilder] plotting context, in which you can configure a plot.
 * Possible configuration parameters depend on the engine.
 *
 * @receiver plot dataset.
 */
@JvmName("mapPlot")
public inline fun Map<String, List<*>>.plot(block: DataFramePlotBuilder<*>.() -> Unit): Plot {
    return plot(this, block)
}

/**
 * Returns a new [Plot].
 *
 * Creates a [DataFramePlotBuilder] plotting context, in which you can configure a plot.
 * Possible configuration parameters depend on the engine.
 *
 * @receiver plot dataset.
 */
public inline fun <T> DataFrame<T>.plot(block: DataFramePlotBuilder<T>.() -> Unit): Plot {
    return plot(this, block)
}

/**
 * Returns a new [Plot].
 *
 * Creates a [DataFramePlotBuilder] plotting context, in which you can configure a plot.
 * Possible configuration parameters depend on the engine.
 *
 * @param dataframe plot dataset.
 */
@JvmName("plotDataframe")
public inline fun <T> plot(dataframe: DataFrame<T>, block: DataFramePlotBuilder<T>.() -> Unit): Plot {
    return DataFramePlotBuilder(dataframe).apply(block).toPlot()
}

/**
 * Returns a new [Plot].
 *
 * Creates a [GroupByPlotBuilder] plotting context, in which you can configure a plot.
 * Possible configuration parameters depend on the engine.
 *
 * @receiver plot dataset.
 */
public inline fun <T, G> GroupBy<T, G>.plot(block: GroupByPlotBuilder<T, G>.() -> Unit): Plot {
    return GroupByPlotBuilder(this).apply(block).toPlot()
}

/**
 * Returns a new [Plot].
 *
 * Creates a [GroupByPlotBuilder] plotting context, in which you can configure a plot.
 * Possible configuration parameters depend on the engine.
 *
 * @param groupedDataframe plot dataset.
 */
@JvmName("plotGroupBy")
public inline fun <T, G> plot(groupedDataframe: GroupBy<T, G>, block: GroupByPlotBuilder<T, G>.() -> Unit): Plot {
    return GroupByPlotBuilder(groupedDataframe).apply(block).toPlot()
}
