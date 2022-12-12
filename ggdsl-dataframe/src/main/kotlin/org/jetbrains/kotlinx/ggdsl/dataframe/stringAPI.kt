package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference

public inline operator fun <reified T : Any> String.invoke(): ColumnReference<T> =
    column(this)