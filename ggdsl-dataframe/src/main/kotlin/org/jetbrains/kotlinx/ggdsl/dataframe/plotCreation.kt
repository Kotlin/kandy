package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource


/**
 * Returns a new plot.
 *
 * Creates a scope with this [DataFrame] as a receiver. The [plot] function should be used to create plot with
 * this [DataFrame] as the data source. [ColumnReference] can be used instead of [DataSource] for mapping.
 *
 * Scope allows to use automatically generated as extension fields columns for mapping.
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
inline fun <T> DataFrame<T>.create(block: DataFrame<T>.() -> Plot): Plot {
    return block()
}

/**
 * Creates new [Plot] with this [DataFrame] as data source.
 *
 * @see [create]
 */
inline fun<T> DataFrame<T>.plot(block: PlotContext.() -> Unit): Plot {
    return plot(toNamedData(), block)
}
