package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.dataframe.DataFrame

/**
 * Returns a new typed [ColumnReference] to the column with the receiver [String] as a name and given type.
 * The pointer name and type must be exactly the same as the name and type of the
 * column in the [DataFrame].
 *
 * @param T the type of the column
 * @receiver the name of the column
 */
public inline operator fun <reified T : Any> String.invoke(): ColumnReference<T> =
    column(this)