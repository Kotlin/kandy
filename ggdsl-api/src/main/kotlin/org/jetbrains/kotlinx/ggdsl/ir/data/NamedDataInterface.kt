package org.jetbrains.kotlinx.ggdsl.ir.data

/**
 * Dataframe-like data.
 */
public interface NamedDataInterface : TableData {
    public val nameToValues: Map<String, TypedList>

    public fun groupBy(vararg columnPointers: ColumnPointer<*>): LazyGroupedDataInterface
}
