package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.data.DataFrameWrapper
import org.jetbrains.kotlinx.ggdsl.dsl.internal.GroupedDataSubContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.NamedDataPlotContext

/**
 * Performs grouping of this plot dataset by given columns.
 * Creates [GroupedDataSubContextImmutable].
 *
 * @param columnReferences references to grouping keys columns.
 */
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
