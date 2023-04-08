/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.Infer
import org.jetbrains.kotlinx.dataframe.api.add
import org.jetbrains.kotlinx.dataframe.api.copy
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.data.TableData


public class DatasetHandler(
    public val initialDataset: TableData,
    private val dcAsRefOnly: Boolean = false,
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
                initialNamedData = initialDataset.origin
                isGrouped = true
                groupKeys = initialDataset.keys
                groupKeys.forEach { takeColumn(it) }
            }
        }
    }

    public fun rowsCount(): Int = buffer.rowsCount()
    public fun data(): TableData {
        return if (isGrouped) {
            GroupedData(buffer, groupKeys!!)
        } else {
            NamedData(buffer)
        }
    }

    public fun takeColumn(name: String): String {
        return referredColumns[name] ?: run {
            val columnId = internalAddColumn(initialNamedData.dataFrame.getColumnOrNull(name) ?: error("invalid column id"))
            referredColumns[name] = columnId
            name
        }
        // TODO
    }

    public fun addColumn(column: DataColumn<*>): String {
        // TODO
        if (dcAsRefOnly) {
            return takeColumn(column.name())
        }
        return internalAddColumn(column)
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

    public fun addColumn(values: List<*>, name: String): String {
        val columnId = internalAddColumn(DataColumn.createValueColumn(name, values, Infer.Type))
        referredColumns[name] = columnId
        return internalAddColumn(DataColumn.createValueColumn(name, values, Infer.Type))
    }

}
