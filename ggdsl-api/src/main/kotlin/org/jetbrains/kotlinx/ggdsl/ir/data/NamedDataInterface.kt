package org.jetbrains.kotlinx.ggdsl.ir.data

/**
 * Dataframe-like data, i.e. a set of named columns.
 *
 * @property nameToValues [Map] of columns;
 * keys are the columns names, values are [TypedList] with columns values.
 * @property groupBy groups this dataframe.
 */
public interface NamedDataInterface : TableData {
    public val nameToValues: Map<String, TypedList>

    /**
     * Groups this dataframe by given columns.
     *
     * @param columnPointers pointers to columns to group them by.
     */
    public fun groupBy(vararg columnPointers: ColumnPointer<*>): LazyGroupedDataInterface
}
