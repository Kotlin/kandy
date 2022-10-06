/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.NamedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.WithGroupingBindingContext
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.data.CountedGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface

// todo internal?
public fun <T : Any> ColumnReference<T>.toColRef(): ColumnPointer<T> {
    return ColumnPointer(name())
}


internal fun DataFrame<*>.toNamedData(): Map<String, List<Any>> {
    return flatten().toMap().map { it.key to it.value.map { it!! /*TODO*/ } }.toMap()
}

public class LazyGroupedDataFrame(
    override val keys: List<String>,
    override val origin: DataFrameWrapper,
) : LazyGroupedDataInterface {
    override fun count(): GroupedByWrapper<Any?, Any?> = GroupedByWrapper(origin.df.groupBy(cols = keys.toTypedArray() ))
}

public class GroupedByWrapper<T, G>(public val groupBy: GroupBy<T, G>): CountedGroupedDataInterface {
    override val keys: DataFrameWrapper = DataFrameWrapper(groupBy.keys)
    override val groups: List<DataFrameWrapper> = groupBy.groups.map { DataFrameWrapper(it) }.toList()

    override fun toLazy(): LazyGroupedDataFrame = LazyGroupedDataFrame(
        keys.df.columnNames(),
        DataFrameWrapper(groupBy.groups.concat())
    )
}


public class GroupedDataFrameContext<T, G>(public val groupBy: GroupBy<T, G>) {
    public val groupKey: DataFrame<T> = groupBy.keys
    public val column: DataFrame<G> = groupBy.groups.first() // TODO

    public fun toLazy(): LazyGroupedDataFrame = LazyGroupedDataFrame(
        groupKey.columns().map { it.name() },
        DataFrameWrapper(groupBy.groups.concat())
    )
}


public class DataFrameWrapper(public val df: DataFrame<*>) : NamedDataInterface {
    override val map: Map<String, List<Any>> = df.toNamedData()
    override fun groupBy(vararg columnPointers: ColumnPointer<*>): LazyGroupedDataFrame {
        return LazyGroupedDataFrame(columnPointers.map { it.id }, this)
    }

    public fun groupBy(vararg columnReferences: ColumnReference<*>): LazyGroupedDataFrame {
        return LazyGroupedDataFrame(columnReferences.map { it.name() }, this)
    }

    public fun groupBy(
        columnReferences: List<ColumnReference<*>>,
        columnPointers: List<ColumnPointer<*>> = listOf()
    ): LazyGroupedDataFrame {
        return LazyGroupedDataFrame(columnReferences.map { it.name() } + columnPointers.map { it.id }, this)
    }
}

public inline fun NamedDataPlotContext<DataFrameWrapper>.groupBy(
    vararg columnReferences: ColumnReference<*>,
    block: WithGroupingBindingContext.() -> Unit
) {
    WithGroupingBindingContext(
        data.groupBy(*columnReferences),
        layers,
        bindingCollector
    ).apply(block)
}

public inline fun NamedDataPlotContext<DataFrameWrapper>.groupBy(
    columnReferences: List<ColumnReference<*>>,
    columnPointers: List<ColumnPointer<*>> = listOf(),
    block: WithGroupingBindingContext.() -> Unit
) {
    WithGroupingBindingContext(
        data.groupBy(columnReferences, columnPointers),
        layers,
        bindingCollector
    ).apply(block)
}
