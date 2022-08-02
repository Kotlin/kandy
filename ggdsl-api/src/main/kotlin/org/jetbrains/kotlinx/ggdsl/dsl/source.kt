package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import kotlin.reflect.KProperty
import kotlin.reflect.KType
import kotlin.reflect.typeOf

/**
 * Returns a new [DataSource].
 *
 * @param T the type of source
 * @param id the name of source in [NamedData]
 */
inline fun <reified T : Any> source(id: String): DataSource<T> =
    DataSource(id, typeOf<T>())

data class UnnamedDataSource<T : Any>(val type: KType) {
    operator fun getValue(t: T?, property: KProperty<*>): DataSource<T> {
        return DataSource(property.name, type)
    }
}

// todo
inline fun <reified T : Any> source(): UnnamedDataSource<T> =
    UnnamedDataSource(typeOf<T>())
