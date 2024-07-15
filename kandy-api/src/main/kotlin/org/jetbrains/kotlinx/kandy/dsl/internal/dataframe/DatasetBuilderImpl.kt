/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.internal.dataframe

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.columns.ColumnAccessor
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetBuilder
import org.jetbrains.kotlinx.kandy.ir.data.TableData

/**
 * [DatasetBuilder] implementation with DataFrame
 */
@PublishedApi
internal abstract class DatasetBuilderImpl(
    initialBuilder: DatasetBuilderImpl? = null
) : DatasetBuilder {
    @PublishedApi
    internal abstract val baseDataFrame: DataFrame<*>
    private val referredColumns: MutableMap<String, String> = mutableMapOf()

    @PublishedApi
    internal var buffer: DataFrame<*> = initialBuilder?.buffer?.copy() ?: DataFrame.Empty

    override fun rowsCount(): Int = if (baseDataFrame.isEmpty()) {
        buffer.rowsCount()
    } else baseDataFrame.rowsCount()

    override fun takeColumn(name: String): String {
        return referredColumns[name] ?: run {
            val columnId = addColumn(
                baseDataFrame.getColumnOrNull(name) ?: error("invalid column id: $name")
            )
            referredColumns[name] = columnId
            name
        }
    }

    fun takeColumn(column: ColumnAccessor<*>): String {
        return takeColumn(column.name())
    }

    fun addColumn(column: ColumnReference<*>): String {
        return when (column) {
            is ColumnAccessor<*> -> takeColumn(column)
            is DataColumn<*> -> addColumn(column)
            else -> error("Unexpected column reference type: ${column::class}")
        }
    }

    fun addColumn(column: DataColumn<*>): String {
        return if (buffer.containsColumn(column.name())) {
            if (buffer[column.name()] == column) {
                column.name()
            } else {
                addColumn(column.rename(column.name() + "*"))
            }
        } else {
            buffer = buffer.add(column)
            column.name()
        }
    }

    override fun addColumn(values: List<*>, name: String): String {
        val columnId = addColumn(DataColumn.createValueColumn(name, values, Infer.Type))
        referredColumns[name] = columnId
        return columnId
    }
}

internal fun DatasetBuilderImpl(dataFrame: DataFrame<*>, initialBuilder: DatasetBuilderImpl? = null): NamedDataBuilder {
    return NamedDataBuilder(dataFrame, initialBuilder)
}

internal fun DatasetBuilderImpl(
    groupBy: GroupBy<*, *>,
    initialBuilder: DatasetBuilderImpl? = null
): GroupedDataBuilder {
    return GroupedDataBuilder(groupBy, initialBuilder)
}

internal fun DatasetBuilderImpl(dataset: TableData, initialBuilder: DatasetBuilderImpl? = null): DatasetBuilderImpl {
    return when (dataset) {
        is NamedData -> NamedDataBuilder(dataset, initialBuilder)
        is GroupedData -> GroupedDataBuilder(dataset, initialBuilder)
        else -> error("Unexpected dataset type: ${dataset::class}")
    }
}
