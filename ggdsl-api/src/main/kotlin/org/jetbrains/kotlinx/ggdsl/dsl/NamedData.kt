package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.data.GroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface

data class NamedData(override val map: Map<String, List<Any>>): NamedDataInterface {
    override fun groupBy(vararg columnPointers: ColumnPointer<*>): GroupedDataInterface {
        TODO("Not yet implemented")
    }
}
