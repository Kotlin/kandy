package org.jetbrains.kotlinx.ggdsl.dataframe.api

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.ggdsl.ir.Plot

public fun<T> DataFrame<T>.plot(block: DataFramePlotContext<T>.() -> Unit): Plot{
    return DataFramePlotContext<T>(this).apply(block).toPlot()
}
