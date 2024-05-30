package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*

/**
 *  Temporary solution for https://github.com/Kotlin/dataframe/issues/673.
 */
internal fun<T, R> GroupBy<T, R>.concatFixed(): DataFrame<R> {
    val keyNames = keys.columnNames()
    
    return mapToFrames {
        val rowsCount = group.rowsCount()
        val keyColumns = keyNames.filter { it !in group.columnNames() }.map {keyName ->
            DataColumn.create(keyName, List(rowsCount) { key[keyName] }, Infer.Type)
         }
        group.addAll(keyColumns)
     }.concat()
}
