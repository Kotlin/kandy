/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.GroupedData
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.NamedData
import org.jetbrains.kotlinx.kandy.ir.data.TableData


/**
 * Responsible for managing datasets within a plot, this handler provides facilities to manipulate,
 * access, and organize datasets in a manner suitable for plotting.
 *
 * It is capable of managing both named data and grouped data, and provides methods for adding
 * and retrieving columns, among other operations.
 */
public interface DatasetBuilder {
    /**
     * Gets the count of rows in the current buffer.
     *
     * @return Number of rows in the buffer.
     */
    public fun rowsCount(): Int

    /**
     * Retrieves the dataset as [TableData].
     * If the dataset is grouped, it returns a [GroupedData], otherwise, a [NamedData] is returned.
     *
     * @return The dataset represented as [TableData].
     */
    public fun build(): TableData

    /**
     * Attempts to fetch a column by its name.
     * If not present, the column is retrieved from the initial dataset and added to the internal buffer.
     *
     * @param name Name of the column.
     * @return Name of the retrieved column.
     */
    public fun takeColumn(name: String): String

    /**
     * Directly adds a column with specified values and name to the dataset.
     * Useful for adding custom columns not present in the initial dataset.
     *
     * @param values A list of values for the column.
     * @param name Name for the column.
     * @return The name of the added column.
     */
    public fun addColumn(values: List<*>, name: String): String

}
