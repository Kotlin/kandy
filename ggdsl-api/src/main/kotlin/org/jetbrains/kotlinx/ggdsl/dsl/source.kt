package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import kotlin.reflect.KProperty

/**
 * Returns a new [ColumnPointer].
 *
 * @param T the type of source
 * @param id the name of source in [NamedDataInterface]
 */
inline fun <reified T : Any> columnPointer(id: String): ColumnPointer<T> =
    ColumnPointer(id)

// TODO
class UnnamedColumnPointer<T : Any> {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): ColumnPointer<T> {
        return ColumnPointer(property.name)
    }
}

// todo
inline operator fun <reified T : Any> String.invoke(): ColumnPointer<T> =
    ColumnPointer(this)

// todo
inline fun <reified T : Any> columnPointer(): UnnamedColumnPointer<T> =
    UnnamedColumnPointer()
