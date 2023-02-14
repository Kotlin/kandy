package org.jetbrains.kotlinx.ggdsl.dsl.internal
/*
import org.jetbrains.kotlinx.ggdsl.ir.data.TypedList
import org.jetbrains.kotlinx.ggdsl.util.serialization.TypedValue
import kotlin.reflect.KType
import kotlin.reflect.typeOf

/**
 * Wraps a value into [TypedValue].
 *
 * @return [TypedValue] with the given value and obtained [KType].
 */
public inline fun<reified T> T: TypedValue{
    return TypedValue(typeOf<T>(), this)
}

/**
 * Wraps a [Pair] of values into [Pair] of [TypedValue].
 *
 * @return [Pair] of [TypedValue] with the given values and obtained [KType].
 */
public inline fun<reified T> Pair<T, T>.typedPair(): Pair<TypedValue, TypedValue>{
    return TypedValue(typeOf<T>(), this.first) to TypedValue(typeOf<T>(), this.second)
}

/**
 * Wraps a [List] into [TypedList].
 *
 * @return [TypedList] with the given values and obtained [KType].
 */
public inline fun<reified T> List<T>: TypedList{
    return TypedList(typeOf<T>(), this)
}

 */
