package org.jetbrains.kotlinx.ggdsl.dsl.internal

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.jetbrains.kotlinx.ggdsl.dsl.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.data.TypedList
import kotlin.reflect.typeOf

public fun Map<String, List<Any>>.toTyped(): Map<String, TypedList> {
    return map { (key, list)-> key to list.toTypedList() }.toMap()
}

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
