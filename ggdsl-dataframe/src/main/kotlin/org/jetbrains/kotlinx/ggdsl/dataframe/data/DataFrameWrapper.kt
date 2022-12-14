package org.jetbrains.kotlinx.ggdsl.dataframe.data

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.dropNulls
import org.jetbrains.kotlinx.dataframe.api.flatten
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.dataframe.values
import org.jetbrains.kotlinx.ggdsl.dataframe.util.serialization.DataFrameSerializer
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.TypedList

/**
 * Wrapper for a [DataFrame] implementing [NamedDataInterface].
 */
@Serializable
public data class DataFrameWrapper(
    @Serializable(with = DataFrameSerializer::class) public val df: DataFrame<@Contextual Any?>
) : NamedDataInterface {
    override val nameToValues: Map<String, TypedList> = df.toTypedDataMap()
    override fun groupBy(vararg columnPointers: ColumnPointer<*>): LazyGroupedDataFrame {
        return LazyGroupedDataFrame(columnPointers.map { it.name }, this)
    }

    /**
     * Performs grouping of this dataframe by given columns.
     *
     * @param columnReferences pointers to grouping keys columns.
     */
    public fun groupBy(vararg columnReferences: ColumnReference<*>): LazyGroupedDataFrame {
        return LazyGroupedDataFrame(columnReferences.map { it.name() }, this)
    }
    /*
        public fun groupBy(
            columnReferences: List<ColumnReference<*>>,
            columnPointers: List<ColumnPointer<*>> = listOf()
        ): LazyGroupedDataFrame {
            return LazyGroupedDataFrame(columnReferences.map { it.name() } + columnPointers.map { it.name }, this)
        }

     */
}

@Suppress("UNCHECKED_CAST")
internal fun DataFrame<*>.toTypedDataMap(): Map<String, TypedList> {
    // TODO (change convert df to map)
    return dropNulls().flatten().columns().associate {
        it.name() to TypedList(it.type(), it.values.toList() as List<Any>)
    }
}