package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.toMap
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.ir.data.*
import kotlin.reflect.typeOf

@PublishedApi
internal inline fun <reified T: Any> ColumnReference<T>.toDataSource(): DataSource<T> {
    return DataSource(name(), typeOf<T>())
}

fun DataFrame<*>.toNamedData(): NamedData {
    return toMap().map { it.key to it.value.map { it!! /*TODO*/ } }.toMap()
}