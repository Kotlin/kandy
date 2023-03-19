package org.jetbrains.kotlinx.ggdsl.dsl.internal

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.Infer
import org.jetbrains.kotlinx.dataframe.api.add
import org.jetbrains.kotlinx.ggdsl.ir.data.GroupedData
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData


// todo value column

public class DatasetHandler(public val initialDataset: TableData) {
    private val initialNamedData: NamedData
    private val isGrouped: Boolean
    private val groupKeys: List<String>?
    private val referredColumns: MutableMap<String, String> = mutableMapOf()
    private var buffer: DataFrame<*> = DataFrame.Empty

    init {
        when(initialDataset) {
            is NamedData -> {
                initialNamedData = initialDataset
                isGrouped = false
                groupKeys =  null
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
    public fun takeColumn(columnId: String): String {
        return referredColumns[columnId] ?: run {
            val name = addColumn(initialNamedData.dataFrame.getColumnOrNull(columnId) ?: error("invalid column id"))
            referredColumns[columnId] = name
            name
        }
        // TODO
    }

    public fun addColumn(column: DataColumn<*>): String {
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

    public fun addColumn(values: List<*>, name: String): String {
        if (isGrouped) {
            return takeColumn(name)
        }
        return addColumn(DataColumn.createValueColumn(name, values, Infer.Type))
    }

}