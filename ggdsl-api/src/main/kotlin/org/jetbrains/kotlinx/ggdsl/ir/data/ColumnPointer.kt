package org.jetbrains.kotlinx.ggdsl.ir.data

// todo nullable?
/**
 * A source of data, i.e. a reference to a column with the corresponding name.
 *
 * @param T a type of data
 * @property id the name of source in a dataset
 * @property type reified type of data
 */

data class ColumnPointer<T : Any>(val id: String, /*val type: KType*/)

