package org.jetbrains.kotlinx.ggdsl.dsl.internal

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.jetbrains.kotlinx.ggdsl.ir.data.TypedList
import kotlin.reflect.typeOf

/**
 * Casts a default data map to a typed one.
 *
 * @return [Map] with the same keys but with [TypedList] instead of untyped lists values.
 */
public fun Map<String, List<Any>>.toTyped(): Map<String, TypedList> {
    return map { (key, list)-> key to list.toTypedList() }.toMap()
}

/**
 * Casts an untyped lists to a typed one.
 *
 * @return [TypedList] with the same values (with dynamic type inference).
 */
public fun List<Any>.toTypedList(): TypedList {
    val type = when {
        all { it is Int } -> typeOf<Int>()
        all { it is Long } -> typeOf<Long>()
        all { it is Double } -> typeOf<Double>()
        all { it is Float } -> typeOf<Float>()
        all { it is Char } -> typeOf<Char>()
        all { it is Boolean } -> typeOf<Boolean>()
        all { it is String } -> typeOf<String>()
        all { it is Instant } -> typeOf<Instant>()
        all { it is LocalDateTime } -> typeOf<LocalDateTime>()
        all { it is LocalDate } -> typeOf<LocalDate>()
        else -> typeOf<Any>() // todo
    }
    return TypedList(type, this)
}
