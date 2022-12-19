package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.data.DataFrameWrapper
import org.jetbrains.kotlinx.ggdsl.dsl.LazyGroupedData
import org.jetbrains.kotlinx.ggdsl.dsl.NamedData
import org.jetbrains.kotlinx.ggdsl.dsl.internal.GroupedDataSubContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.NamedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer

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
        (data as? DataFrameWrapper)?.groupBy(*columnReferences) ?: (data as NamedData).groupBy(*columnReferences), // TODO!!!
        layers,
        this
    ).apply(block)
}

/**
 * Performs grouping of this [NamedData] by given column references.
 *
 * @param columnReferences references to grouping keys columns.
 */
public fun NamedData.groupBy(vararg columnReferences: ColumnReference<*>): LazyGroupedData {
    return LazyGroupedData(columnReferences.map { it.name() }, this)
}
