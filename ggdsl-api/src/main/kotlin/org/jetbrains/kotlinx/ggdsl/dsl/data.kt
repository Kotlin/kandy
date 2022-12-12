package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.dsl.internal.validate
import org.jetbrains.kotlinx.ggdsl.ir.data.TypedList
import kotlin.reflect.typeOf

public class NamedDataBuilder {
    @PublishedApi
    internal val buffer: MutableMap<String, TypedList> = mutableMapOf()

    public inline infix fun<reified T: Any> String.to(list: List<T>) {
        buffer[this] = TypedList(typeOf<T>(), list)
    }
}

// TODO name
public inline fun dataOf(builderAction: NamedDataBuilder.() -> Unit): NamedData {
    return NamedData(NamedDataBuilder().apply(builderAction).buffer.toMap())
}
