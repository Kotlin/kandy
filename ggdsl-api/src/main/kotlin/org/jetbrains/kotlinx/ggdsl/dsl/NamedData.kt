package org.jetbrains.kotlinx.ggdsl.dsl

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.toTyped
import org.jetbrains.kotlinx.ggdsl.dsl.internal.validate
import org.jetbrains.kotlinx.ggdsl.ir.data.*

/**
 * Standard [NamedDataInterface] implementation.
 */
@Serializable
public data class NamedData(override val nameToValues: Map<String, TypedList>) : NamedDataInterface {
    init {
        validate()
    }
    override fun groupBy(vararg columnPointers: ColumnPointer<*>): LazyGroupedData {
        return LazyGroupedData(columnPointers.map { it.name }, this)
    }
    public companion object {
        @PublishedApi
        internal fun fromUntyped(untypedMap: Map<String, List<Any>>): NamedData = NamedData(untypedMap.toTyped())
    }
}

/**
 * Standard [LazyGroupedDataInterface] implementation.
 */
@Serializable
public data class LazyGroupedData(
    override val keys: List<String>,
    override val origin: NamedData
) : LazyGroupedDataInterface {
    init {
        validate()
    }
    override fun count(): CountedGroupedDataInterface {
        TODO("Not yet implemented")
    }
}
