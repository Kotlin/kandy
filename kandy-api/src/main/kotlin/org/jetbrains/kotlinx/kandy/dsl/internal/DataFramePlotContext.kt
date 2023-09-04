/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.*
import org.jetbrains.kotlinx.dataframe.api.getColumns
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

/**
 * Represents a standard plotting context initialized with a [DataFrame] as its primary dataset.
 * The class allows the seamless integration of the dataframe's columns into the plotting process.
 * It also provides convenient methods for grouping data and creating grouped contexts.
 *
 * The implemented [ColumnsContainer] interface is delegated to the [dataFrame],
 * enabling the user to leverage the columns of the dataframe directly in the plotting process.
 *
 * @param dataFrame the initial dataframe that this plotting context operates upon.
 *
 * @property _plotContext the underlying plot context, typically set to 'this' instance.
 * @property datasetHandlers a mutable list of handlers for managing datasets.
 * @property plotFeatures a mutable map representing various plot features.
 */
public class DataFramePlotContext<T>(
    private val dataFrame: DataFrame<T>
) : LayerPlotContext(), ColumnsContainer<T> by dataFrame {
    override val _plotContext: PlotContext = this
    override val datasetHandlers: MutableList<DatasetHandler> = mutableListOf(
        DatasetHandler(NamedData(dataFrame))
    )
    override val plotFeatures: MutableMap<FeatureName, PlotFeature> = mutableMapOf()

    /**
     * Fetches the specified columns from the dataframe.
     *
     * @param selector a selector to determine the columns to be fetched.
     * @return a list of selected data columns.
     */
    public fun <C> columns(selector: ColumnsSelector<T, C>): List<DataColumn<C>> = dataFrame.get(selector)

    /**
     * Fetches the specified columns from the dataframe by their names.
     *
     * @param columns names of the desired columns.
     * @return a list of selected data columns.
     */
    public fun <C> columns(vararg columns: String): List<AnyCol> = dataFrame.getColumns(*columns)

    /**
     * Creates and initializes a new context with the dataframe grouped by the specified column names.
     *
     * @param columns the column names to group the dataframe by.
     * @param block a lambda with receiver block that configures the new grouped context.
     */
    public inline fun groupBy(
        columns: Iterable<String>,
        block: GroupedContext.() -> Unit
    ) {
        datasetHandlers.add(
            DatasetHandler(
                GroupedData(
                    datasetHandler.initialDataset as NamedData,
                    columns.toList()
                ),
                initialBuffer = datasetHandler.buffer
            )
        )
        GroupedContext(
            datasetHandlers.size - 1,
            this
        ).apply(block)
    }

    /**
     * Creates and initializes a new context with the dataframe grouped by the specified column names.
     *
     * @param columns the column names to group the dataframe by.
     * @param block a lambda with receiver block that configures the new grouped context.
     */
    public inline fun groupBy(
        vararg columns: String,
        block: GroupedContext.() -> Unit
    ): Unit = groupBy(columns.toList(), block)

    /**
     * Creates and initializes a new context with the dataframe grouped by the given column references.
     *
     * @param columnReferences references to the columns to group by.
     * @param block a lambda with receiver block that configures the new grouped context.
     */
    public inline fun groupBy(
        vararg columnReferences: ColumnReference<*>,
        block: GroupedContext.() -> Unit
    ): Unit = groupBy(columnReferences.map { it.name() }, block)

    /**
     * Creates and initializes a new context with the dataframe grouped by the given column references.
     *
     * @param columnReferences a list of references to the columns to group by.
     * @param block a lambda with receiver block that configures the new grouped context.
     */
    public inline fun groupBy(
        columnReferences: List<ColumnReference<*>>,
        block: GroupedContext.() -> Unit
    ): Unit = groupBy(columnReferences.map { it.name() }, block)
}