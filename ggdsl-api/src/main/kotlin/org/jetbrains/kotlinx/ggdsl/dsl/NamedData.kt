package org.jetbrains.kotlinx.ggdsl.dsl
/*
import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.toTyped
import org.jetbrains.kotlinx.ggdsl.dsl.internal.validate
import org.jetbrains.kotlinx.ggdsl.ir.data.*

/**
 * Standard [NamedData] implementation.
 */
//@Serializable
public data class NamedData(override val nameToValues: Map<String, TypedList>) :
    org.jetbrains.kotlinx.ggdsl.ir.data.NamedData {
    init {
        validate()
    }
    override fun groupBy(vararg ColumnReferences: ColumnReference<*>): LazyGroupedData {
        return LazyGroupedData(ColumnReferences.map { it.name }, this)
    }
    public companion object {
        @PublishedApi
        internal fun fromUntyped(untypedMap: Map<String, List<*>>): NamedData = NamedData(untypedMap.toTyped())
    }
}

/**
 * Standard [LazyGroupedData] implementation.
 */
//@Serializable
public data class LazyGroupedData(
    override val keys: List<String>,
    override val origin: NamedData
) : org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedData {
    init {
        validate()
    }
    override fun count(): CountedGroupedData {
        TODO("Not yet implemented")
    }
}

 */
