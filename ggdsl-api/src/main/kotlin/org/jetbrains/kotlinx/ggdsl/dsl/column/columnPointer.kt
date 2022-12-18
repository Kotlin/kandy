/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl.column

import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import kotlin.reflect.KProperty

/**
 * Returns a new typed [ColumnPointer] to the column with the given name and type.
 * The pointer name and type must be exactly the same as the name and type of the
 * column in the dataset (of type [TableData]).
 *
 * @param T the type of the column
 * @param id the name of the column
 */
public  fun <T : Any> columnPointer(id: String): ColumnPointer<T> =
    ColumnPointer(id)

/**
 * Returns a new typed [ColumnPointer] to the column with the receiver [String] as a name and given type.
 * The pointer name and type must be exactly the same as the name and type of the
 * column in the dataset (of type [TableData]).
 *
 * @param T the type of the column
 * @receiver the name of the column
 */
public inline operator fun <reified T : Any> String.invoke(): ColumnPointer<T> =
    ColumnPointer(this)


/**
 * [ColumnPointer] delegate. It stores a type of the column.
 *
 * Can be delegated to create a [ColumnPointer] with the same type and the name of the variable as a
 * column name:
 *
 * ```
 * // equals to
 * // val timeVar = columnPointer<Int>("timeVar")
 * val timeVar by columnPointer<Int>()
 * ```
 *
 * @property T the type of the column.
 */
public class UnnamedColumnPointer<T : Any> @PublishedApi internal constructor(){
    /**
     * Creates a [ColumnPointer] with `T` type and property name as a column name.
     */
    public operator fun getValue(thisRef: Any?, property: KProperty<*>): ColumnPointer<T> {
        return ColumnPointer(property.name)
    }
}

/**
 * Returns a new typed [UnnamedColumnPointer] with the given type.
 *
 * Can be delegated to create a [ColumnPointer] with the same type and the name of the variable as a
 * column name:
 *
 * ```
 * // equals to
 * // val timeVar = columnPointer<Int>("timeVar")
 * val timeVar by columnPointer<Int>()
 *
 * @param T the type of the column
 */
public inline fun <reified T : Any> columnPointer(): UnnamedColumnPointer<T> =
    UnnamedColumnPointer()

@PublishedApi
internal fun<T: Any> String.toColumnPointer(): ColumnPointer<T> = ColumnPointer(this)
