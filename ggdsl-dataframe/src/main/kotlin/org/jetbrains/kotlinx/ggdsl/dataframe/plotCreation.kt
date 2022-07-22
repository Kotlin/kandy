package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

fun DataFrame<*>.plot(block: PlotContext.() -> Unit): Plot {
    return plot(toNamedData(), block)
}

inline fun <T> DataFrame<T>.create(block: DataFrame<T>.() -> Plot): Plot {
    return block()
}
