package org.jetbrains.kotlinx.ggdsl.dataframe.data

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.flatten
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.dataframe.values
import org.jetbrains.kotlinx.ggdsl.dataframe.util.serialization.DataFrameSerializer
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.data.TypedList

/**
 * Wrapper for a [DataFrame] implementing [NamedData].
 */
@Serializable
public data class DataFrameWrapper(
    @Serializable(with = DataFrameSerializer::class)
    public val df: DataFrame<@Contextual Any?>
) : NamedData {
    override val nameToValues: Map<String, TypedList> = df.toTypedDataMap()
    override fun groupBy(vararg ColumnReferences: ColumnReference<*>): LazyGroupedDataFrame {
        return LazyGroupedDataFrame(ColumnReferences.map { it.name }, this)
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
            ColumnReferences: List<ColumnReference<*>> = listOf()
        ): LazyGroupedDataFrame {
            return LazyGroupedDataFrame(columnReferences.map { it.name() } + ColumnReferences.map { it.name }, this)
        }

     */
}

@Suppress("UNCHECKED_CAST")
internal fun DataFrame<*>.toTypedDataMap(): Map<String, TypedList> {
    // TODO (change convert df to map)
    return flatten().columns().associate {
        it.name() to TypedList(it.type(), it.values.toList() as List<*>)
    }
}