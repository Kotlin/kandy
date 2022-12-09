package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.GroupedDataSubContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.NamedDataPlotContext

public inline fun NamedDataPlotContext.groupBy(
    vararg columnReferences: ColumnReference<*>,
    block: GroupedDataSubContextImmutable.() -> Unit
) {
    GroupedDataSubContextImmutable(
        (data as DataFrameWrapper).groupBy(*columnReferences),
        layers,
        this
    ).apply(block)
}
