package org.jetbrains.kotlinx.ggdsl.dsl.internal

import org.jetbrains.kotlinx.ggdsl.ir.data.TypedList
import org.jetbrains.kotlinx.ggdsl.util.serialization.TypedValue
import kotlin.reflect.typeOf

public inline fun<reified T: Any> T.typed(): TypedValue{
    return TypedValue(typeOf<T>(), this)
}

//todo
public inline fun<reified T: Any> Pair<T, T>.typedPair(): Pair<TypedValue, TypedValue>{
    return TypedValue(typeOf<T>(), this.first) to TypedValue(typeOf<T>(), this.second)
}

public inline fun<reified T: Any> List<T>.typedList(): TypedList{
    return TypedList(typeOf<T>(), this)
}
