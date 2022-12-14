package org.jetbrains.kotlinx.ggdsl.ir.data

/**
 * Base table data model. Table is a generalized dataframe -  it has a set of columns. Each value is column
 * can be a single value or a nested table.
 */
public sealed interface TableData
