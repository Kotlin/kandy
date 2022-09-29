package org.jetbrains.kotlinx.ggdsl.dataframe

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.first
import org.jetbrains.kotlinx.dataframe.api.flatten
import org.jetbrains.kotlinx.dataframe.api.toMap
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.GroupedBindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.NamedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface

// todo internal?
fun <T : Any> ColumnReference<T>.toColRef(): ColumnPointer<T> {
    return ColumnPointer(name())
}


internal fun DataFrame<*>.toNamedData(): Map<String, List<Any>> {
    return flatten().toMap().map { it.key to it.value.map { it!! /*TODO*/ } }.toMap()
}

class LazyGroupedDataFrame(
    override val keys: List<String>,
    override val origin: DataFrameWrapper,
) : LazyGroupedDataInterface


class GroupedDataFrame<T, G>(val groupBy: GroupBy<T, G>) {
    public val groupKey = groupBy.keys
    public val column: DataFrame<G> = groupBy.groups.first() // TODO

    fun toLazy() = LazyGroupedDataFrame(
        groupKey.columns().map { it.name() },
        DataFrameWrapper(column)
    )
}


class DataFrameWrapper(df: DataFrame<*>) : NamedDataInterface {
    override val map: Map<String, List<Any>> = df.toNamedData()
    override fun groupBy(vararg columnPointers: ColumnPointer<*>): LazyGroupedDataFrame {
        return LazyGroupedDataFrame(columnPointers.map { it.id }, this)
    }

    fun groupBy(vararg columnReferences: ColumnReference<*>): LazyGroupedDataFrame {
        return LazyGroupedDataFrame(columnReferences.map { it.name() }, this)
    }

    fun groupBy(
        columnReferences: List<ColumnReference<*>>,
        columnPointers: List<ColumnPointer<*>> = listOf()
    ): LazyGroupedDataFrame {
        return LazyGroupedDataFrame(columnReferences.map { it.name() } + columnPointers.map { it.id }, this)
    }
}

inline fun NamedDataPlotContext<DataFrameWrapper>.groupBy(
    vararg columnReferences: ColumnReference<*>,
    block: GroupedBindingContext.() -> Unit
) {
    GroupedBindingContext(
        data.groupBy(*columnReferences),
        layers,
        bindingCollector
    ).apply(block)
}

inline fun NamedDataPlotContext<DataFrameWrapper>.groupBy(
    columnReferences: List<ColumnReference<*>>,
    columnPointers: List<ColumnPointer<*>> = listOf(),
    block: GroupedBindingContext.() -> Unit
) {
    GroupedBindingContext(
        data.groupBy(columnReferences, columnPointers),
        layers,
        bindingCollector
    ).apply(block)
}
