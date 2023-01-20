package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.data.TypedList
import kotlin.reflect.typeOf

/**
 * Builder for typed data.
 *
 * Infix fun `String to List<T>` is overloaded inside this context for adding new typed columns.
 */
public class NamedDataBuilder {
    @PublishedApi
    internal val buffer: MutableMap<String, TypedList> = mutableMapOf()

    /**
     * Adds a new column keeping reified type.
     *
     * @receiver a new column name.
     * @param list a new column values.
     */
    public inline infix fun <reified T> String.to(list: List<T>) {
        buffer[this] = TypedList(typeOf<T>(), list)
    }
    // TODO iterable and arrays
}

/**
 * Creates a new dataframe ([NamedData]). Opens a [NamedDataBuilder] context with method
 * [NamedDataBuilder.to], that adds a new column with a receiver [String] as its name and the given
 * [List] as its values.
 *
 * @return a new dataset in form of [NamedData].
 */
public inline fun dataOf(builderAction: NamedDataBuilder.() -> Unit): NamedData {
    return NamedData(NamedDataBuilder().apply(builderAction).buffer.toMap())
}
