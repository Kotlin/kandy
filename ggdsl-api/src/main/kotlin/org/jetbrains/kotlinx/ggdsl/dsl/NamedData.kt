package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.data.CountedGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface

public data class NamedData(override val map: Map<String, List<Any>>) : NamedDataInterface {
    override fun groupBy(vararg columnPointers: ColumnPointer<*>): LazyGroupedData {
        return LazyGroupedData(columnPointers.map { it.id }, this)
    }

    override fun <T : Any> gather(
        valuesColumnName: String,
        keysColumnName: String,
        firstColumn: ColumnPointer<T>,
        secondColumn: ColumnPointer<T>,
        vararg columns: ColumnPointer<T>
    ): NamedDataInterface {
        TODO("Not yet implemented")
    }

}

public data class LazyGroupedData(
    override val keys: List<String>,
    override val origin: NamedData
) : LazyGroupedDataInterface {
    override fun count(): CountedGroupedDataInterface {
        TODO("Not yet implemented")
    }
}
