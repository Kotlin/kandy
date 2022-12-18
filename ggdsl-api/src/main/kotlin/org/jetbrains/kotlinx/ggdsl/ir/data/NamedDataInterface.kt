package org.jetbrains.kotlinx.ggdsl.ir.data

/**
 * Dataframe-like data, i.e. a set of named columns.
 *
 * @property nameToValues [Map] of columns;
 * keys are the columns names, values are [TypedList] with columns values.
 * @property groupBy performs this dataframe lazy grouping by given columns.
 */
public interface NamedDataInterface : TableData {
    public val nameToValues: Map<String, TypedList>

    /**
     * Performs grouping of this dataframe by given columns.
     *
     * @param columnPointers pointers to grouping keys columns.
     */
    public fun groupBy(vararg columnPointers: ColumnPointer<*>): LazyGroupedDataInterface
}
