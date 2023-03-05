package org.jetbrains.kotlinx.ggdsl.dsl.internal

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.Infer
import org.jetbrains.kotlinx.dataframe.api.add
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData


// todo value column

public class DatasetHandler(public val initialDataset: NamedData) {
    // TODO public val isMutable: Boolean = true
    public var buffer: DataFrame<*> = DataFrame.Empty
    public val referredColumns: MutableMap<String, String> = mutableMapOf()
    public fun takeColumn(columnId: String): String {
        return referredColumns[columnId] ?: run {
            val name =  addColumn(initialDataset.dataFrame.getColumnOrNull(columnId) ?: error("invalid column id"))
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
            return column.name()
        }
    }

    public fun addColumn(values: List<*>, name: String): String {
        return addColumn(DataColumn.createValueColumn(name, values, Infer.Type))
    }
}