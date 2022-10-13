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
    ): NamedData {
        val ids = (listOf(firstColumn, secondColumn) + columns).map {it.id }
        val mapBuffer = mutableMapOf<String, List<Any>>()
        val keysBuffer = mutableListOf<String>()
        val valuesBuffer = mutableListOf<Any>()
        val multiplier = 2 + columns.size
        map.forEach { (id, list) ->
            if (id in ids) {
                keysBuffer.addAll(List(list.size){id})
                valuesBuffer.addAll(list)
            } else {
                mapBuffer[id] = List(multiplier){list}.flatten()
            }
        }
        mapBuffer[valuesColumnName] = valuesBuffer
        mapBuffer[keysColumnName] = keysBuffer
        return NamedData(mapBuffer)
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
