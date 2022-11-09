package org.jetbrains.kotlinx.ggdsl.ir.data

/**
 * Dataframe-like data.
 */
public interface NamedDataInterface : TableData {
    public val map: Map<String, List<Any>>

    public fun groupBy(vararg columnPointers: ColumnPointer<*>): LazyGroupedDataInterface

    public fun <T: Any> gather(
        valuesColumnName: String,
        keysColumnName: String,
        firstColumn: ColumnPointer<T>,
        secondColumn: ColumnPointer<T>,
        vararg columns: ColumnPointer<T>,
    ): NamedDataInterface

}
