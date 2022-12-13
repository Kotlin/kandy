/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl.column

import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
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
 * [ColumnPointer] delegate. It stores a type of the column. TODO
 */
public class UnnamedColumnPointer<T : Any> @PublishedApi internal constructor(){
    public operator fun getValue(thisRef: Any?, property: KProperty<*>): ColumnPointer<T> {
        return ColumnPointer(property.name)
    }
}

/**
 * Returns a new typed [UnnamedColumnPointer] with the given type.
 * TODO
 *
 * @param T the type of the column
 */
public inline fun <reified T : Any> columnPointer(): UnnamedColumnPointer<T> =
    UnnamedColumnPointer()

@PublishedApi
internal fun<T: Any> String.toColumnPointer(): ColumnPointer<T> = ColumnPointer(this)
