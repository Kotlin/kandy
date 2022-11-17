package org.jetbrains.kotlinx.ggdsl.ir.data

// todo nullable?
/**
 * Pointer to the data source - a column in the table with the corresponding name.
 *
 * @param T a type of column
 * @property id the name of column in the table
 * @property type reified type of data TODO: was removed
 */

public data class ColumnPointer<T : Any>(val id: String, /* TODO val type: KType*/)
