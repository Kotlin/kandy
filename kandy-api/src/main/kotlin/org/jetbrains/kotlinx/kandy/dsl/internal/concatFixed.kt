package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*

internal fun GroupBy<*, *>.concatFixed(): DataFrame<*> {
    val keyNames = keys.columnNames()
    
    return mapToFrames {
        val rowsCount = group.rowsCount()
        val keyColumns = keyNames.filter { it !in group.columnNames() }.map {keyName ->
            DataColumn.create(keyName, List(rowsCount) { key[keyName] }, Infer.Type)
         }
        group.addAll(keyColumns)
     }.concat()
}
