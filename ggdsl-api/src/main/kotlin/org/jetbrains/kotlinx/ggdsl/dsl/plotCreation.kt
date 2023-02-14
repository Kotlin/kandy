/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.dsl.internal.GroupedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.NamedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotContextMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.validate
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.GroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData

/**
 * Returns a new [Plot].
 *
 * Creates a plotting context [NamedDataPlotContext], in which you can configure a plot.
 * Possible configuration parameters depend on the engine.
 *
 * @param dataset plot dataset.
 */
public inline fun plot(dataset: NamedData, block: NamedDataPlotContext.() -> Unit): Plot {
    return NamedDataPlotContext(dataset).apply(block).toPlot().also {
        it.validate()
    }
}

/**
 * Returns a new [Plot].
 *
 * Creates a plotting context [NamedDataPlotContext], in which you can configure a plot.
 * Possible configuration parameters depend on the engine.
 *
 * @param dataset plot dataset.
 */
public inline fun plot(dataset: Map<String, List<*>>, block: NamedDataPlotContext.() -> Unit): Plot {
    return NamedDataPlotContext(NamedData.fromUntyped(dataset)).apply(block).toPlot().also {
        it.validate()
    }
}

/**
 * Returns a new [Plot].
 *
 * Creates a plotting with grouping context [GroupedDataPlotContext], in which you can configure a plot.
 * Possible configuration parameters depend on the engine.
 *
 * @param dataset plot dataset.
 */
public inline fun plot(dataset: GroupedDataInterface, block: GroupedDataPlotContext.() -> Unit): Plot {
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
