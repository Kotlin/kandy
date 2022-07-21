package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.Plot

fun DataFrame<*>.plot(block: PlotContext.() -> Unit): Plot {
    return plot(toNamedData(), block)
}

fun DataFrame<*>.create(block: DataFrame<*>.() -> Plot): Plot {
    return with(this) {
        block()
    }
}
