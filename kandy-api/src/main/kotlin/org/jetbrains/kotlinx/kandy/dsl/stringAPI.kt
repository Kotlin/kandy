package org.jetbrains.kotlinx.kandy.dsl

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference

/**
 * Returns a new typed [ColumnReference] to the column with the receiver [String] as a name and given type.
 * The pointer name and type must be exactly the same as the name and type of the
 * column in the [DataFrame].
 *
 * @param T type of the column
 * @receiver name of the column
 */
public inline operator fun <reified T> String.invoke(): ColumnReference<T> =
    column(this)
