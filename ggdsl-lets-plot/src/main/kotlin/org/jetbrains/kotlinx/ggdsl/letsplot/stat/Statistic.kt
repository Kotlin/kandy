package org.jetbrains.kotlinx.ggdsl.letsplot.stat

import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference

public interface Statistic<T> {
    public val id: String
}

@PublishedApi
internal inline fun <reified T> Statistic<T>.toColumnReference(): ColumnReference<T> {
    return column(this.id)
}
