package org.jetbrains.kotlinx.ggdsl.dsl.internal

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.Infer
import org.jetbrains.kotlinx.dataframe.api.add
import org.jetbrains.kotlinx.ggdsl.ir.data.GroupedData
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData


// todo value column

public class DatasetHandler(public val initialDataset: NamedData) {
    public constructor(initialDataset: GroupedData): this(initialDataset.origin) {
        isGrouped = true
        groupKeys = initialDataset.keys
        groupKeys!!.forEach { takeColumn(it) }
    }
    private var isGrouped = false
    private var groupKeys: List<String>? = null
    private var buffer: DataFrame<*> = DataFrame.Empty
    public fun rowsCount(): Int = buffer.rowsCount()
    public fun data(): TableData {
        return if (isGrouped) {
            GroupedData(buffer, groupKeys!!)
        } else {
            NamedData(buffer)
        }
    }
    private val referredColumns: MutableMap<String, String> = mutableMapOf()
    public fun takeColumn(columnId: String): String {
        return referredColumns[columnId] ?: run {
            val name =  internalAddColumn(initialDataset.dataFrame.getColumnOrNull(columnId) ?: error("invalid column id"))
            referredColumns[columnId] = name
            name
        }
        // TODO
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
        if (isGrouped) {
            return takeColumn(name)
        }
        return internalAddColumn(DataColumn.createValueColumn(name, values, Infer.Type))
    }

}