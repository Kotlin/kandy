package org.jetbrains.kotlinx.ggdsl.letsplot.stat

import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference

public interface Statistic<T> {
    public val id: String
}

@PublishedApi
internal inline fun <reified T> Statistic<T>.toColumnReference(): ColumnReference<T> {
    return ColumnReference(this.id)
}
