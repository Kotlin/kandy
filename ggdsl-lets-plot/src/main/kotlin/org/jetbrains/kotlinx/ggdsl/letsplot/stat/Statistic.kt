package org.jetbrains.kotlinx.ggdsl.letsplot.stat

import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer

interface Statistic<T> {
    val name: String
}

@PublishedApi
internal inline fun <reified T : Any> Statistic<T>.toDataSource(): ColumnPointer<T> {
    return ColumnPointer(this.name)
}
