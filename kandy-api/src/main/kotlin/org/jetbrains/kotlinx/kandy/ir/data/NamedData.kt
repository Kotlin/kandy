package org.jetbrains.kotlinx.kandy.ir.data

import org.jetbrains.kotlinx.dataframe.DataFrame

/**
 * "Simple dataframe": a set of named typed columns. Types of columns are simple,
 * i.e. not columns or dataframes.
 *
 * @property dataFrame underlying [DataFrame].
 */
public data class NamedData(public val dataFrame: DataFrame<*>): TableData
