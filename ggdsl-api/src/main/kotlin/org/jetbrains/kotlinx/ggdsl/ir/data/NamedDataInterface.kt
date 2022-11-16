package org.jetbrains.kotlinx.ggdsl.ir.data

/**
 * Dataframe-like data.
 */
public interface NamedDataInterface : TableData {
    public val map: Map<String, List<Any>>

    public fun groupBy(vararg columnPointers: ColumnPointer<*>): LazyGroupedDataInterface
}
