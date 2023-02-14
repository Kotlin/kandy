/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl.column

import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import kotlin.reflect.KProperty

/**
 * Returns a new typed [ColumnReference] to the column with the given name and type.
 * The pointer name and type must be exactly the same as the name and type of the
 * column in the dataset (of type [TableData]).
 *
 * @param T the type of the column
 * @param id the name of the column
 */
public  fun <T> ColumnReference(id: String): ColumnReference<T> =
    ColumnReference(id)

/**
 * Returns a new typed [ColumnReference] to the column with the receiver [String] as a name and given type.
 * The pointer name and type must be exactly the same as the name and type of the
 * column in the dataset (of type [TableData]).
 *
 * @param T the type of the column
 * @receiver the name of the column
 */
public inline operator fun <reified T> String.invoke(): ColumnReference<T> =
    ColumnReference(this)


/**
 * [ColumnReference] delegate. It stores a type of the column.
 *
 * Can be delegated to create a [ColumnReference] with the same type and the name of the variable as a
 * column name:
 *
 * ```
 * // equals to
 * // val timeVar = ColumnReference<Int>("timeVar")
 * val timeVar by ColumnReference<Int>()
 * ```
 *
 * @property T the type of the column.
 */
public class UnnamedColumnReference<T> @PublishedApi internal constructor(){
    /**
     * Creates a [ColumnReference] with `T` type and property name as a column name.
     */
    public operator fun getValue(thisRef: Any?, property: KProperty<*>): ColumnReference<T> {
        return ColumnReference(property.name)
    }
}

/**
 * Returns a new typed [UnnamedColumnReference] with the given type.
 *
 * Can be delegated to create a [ColumnReference] with the same type and the name of the variable as a
 * column name:
 *
 * ```
 * // equals to
 * // val timeVar = ColumnReference<Int>("timeVar")
 * val timeVar by ColumnReference<Int>()
 *
 * @param T the type of the column
 */
public inline fun <reified T> ColumnReference(): UnnamedColumnReference<T> =
    UnnamedColumnReference()

@PublishedApi
internal fun<T> String.toColumnReference(): ColumnReference<T> = ColumnReference(this)
