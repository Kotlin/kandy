package org.jetbrains.kotlinx.ggdsl.ir.data

public interface NamedDataInterface : TableData {
    public val map: Map<String, List<Any>>

    public fun groupBy(vararg columnPointers: ColumnPointer<*>): LazyGroupedDataInterface

    public fun gather(
        vararg columnPointers: ColumnPointer<*>,
        valuesColumns: String,
        keysColumns: String
    ): NamedDataInterface
}
