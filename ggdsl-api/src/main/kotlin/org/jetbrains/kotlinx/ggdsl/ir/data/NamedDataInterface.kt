package org.jetbrains.kotlinx.ggdsl.ir.data

interface NamedDataInterface : TableData {
    val map: Map<String, List<Any>>

    fun groupBy(vararg columnPointers: ColumnPointer<*>): GroupedDataInterface
}
