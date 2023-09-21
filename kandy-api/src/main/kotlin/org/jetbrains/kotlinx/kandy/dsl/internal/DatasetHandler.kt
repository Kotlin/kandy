/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.Infer
import org.jetbrains.kotlinx.dataframe.api.add
import org.jetbrains.kotlinx.dataframe.api.copy
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.data.TableData


/**
 * Responsible for managing datasets within a plot, this handler provides facilities to manipulate,
 * access, and organize datasets in a manner suitable for plotting.
 *
 * It is capable of managing both named data and grouped data, and provides methods for adding
 * and retrieving columns, among other operations.
 *
 * @param initialDataset The initial dataset that this handler manages, represented as [TableData].
 * @param columnAsRefOnly A flag indicating whether to treat columns strictly as references. When set to true,
 *                        columns are not directly added to the dataset, only references to them are maintained.
 * @param initialBuffer An optional initial buffer, which is a mutable dataframe that can be used for temporary storage
 *                      and operations. If not provided, an empty dataframe is used by default.
 *
 * @property initialNamedData Named data derived from the initial dataset.
 */
public class DatasetHandler(
    public val initialDataset: TableData,
    //private val columnAsRefOnly: Boolean = false,
    initialBuffer: DataFrame<*>? = null
) {
    // todo value column

    public val initialNamedData: NamedData
    private val isGrouped: Boolean
    private val groupKeys: List<String>?
    private val referredColumns: MutableMap<String, String> = mutableMapOf()

    @PublishedApi
    internal var buffer: DataFrame<*> = initialBuffer?.copy() ?: DataFrame.Empty

    init {
        when (initialDataset) {
            is NamedData -> {
                initialNamedData = initialDataset
                isGrouped = false
                groupKeys = null
            }

            is GroupedData -> {
                initialNamedData = NamedData(initialDataset.dataFrame)
                isGrouped = true
                groupKeys = initialDataset.keys
                groupKeys.forEach { takeColumn(it) }
            }
        }
    }

    /**
     * Gets the count of rows in the current buffer.
     *
     * @return Number of rows in the buffer.
     */
    public fun rowsCount(): Int = buffer.rowsCount()

    /**
     * Retrieves the dataset as [TableData].
     * If the dataset is grouped, it returns a [GroupedData], otherwise, a [NamedData] is returned.
     *
     * @return The dataset represented as [TableData].
     */
    public fun data(): TableData {
        return if (isGrouped) {
            GroupedData(buffer, groupKeys!!)
        } else {
            NamedData(buffer)
        }
    }

    /**
     * Attempts to fetch a column by its name.
     * If not present, the column is retrieved from the initial dataset and added to the internal buffer.
     *
     * @param name Name of the column.
     * @return Name of the retrieved column.
     */
    public fun takeColumn(name: String): String {
        return referredColumns[name] ?: run {
            val columnId = internalAddColumn(
                initialNamedData.dataFrame.getColumnOrNull(name) ?: error("invalid column id: $name")
            )
            referredColumns[name] = columnId
            name
        }
    }

    /**
     * Adds a column to the dataset.
     * Depending on the nature of the reference and the `columnAsRefOnly` flag,
     * it either directly adds the column or treats it as a reference.
     *
     * @param column The column to be added, represented as [ColumnReference].
     * @return The name of the added column.
     */
    public fun addColumn(column: ColumnReference<*>): String {
        return if (column is DataColumn<*>/* && !columnAsRefOnly*/) {
            internalAddColumn(column)
        } else takeColumn(column.name())
    }

    private fun internalAddColumn(column: DataColumn<*>): String {
        return if (buffer.containsColumn(column.name())) {
            if (buffer[column.name()] == column) {
                column.name()
            } else {
                internalAddColumn(column.rename(column.name() + "*"))
            }
        } else {
            buffer = buffer.add(column)
            column.name()
        }
    }

    /**
     * Directly adds a column with specified values and name to the dataset.
     * Useful for adding custom columns not present in the initial dataset.
     *
     * @param values A list of values for the column.
     * @param name Name for the column.
     * @return The name of the added column.
     */
    public fun addColumn(values: List<*>, name: String): String {
        val columnId = internalAddColumn(DataColumn.createValueColumn(name, values, Infer.Type))
        referredColumns[name] = columnId
        return internalAddColumn(DataColumn.createValueColumn(name, values, Infer.Type))
    }

}
