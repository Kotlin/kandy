package org.jetbrains.kotlinx.kandy.letsplot.util

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

internal fun <K, V: Any> Map<K, V>.extendedBy(other: Map<K, V>, join: (V, V) -> V): Map<K, V> {
    return (this + other).mapValues { (k, v) ->
        if (k in this && k in other)
            join(this[k]!!, other[k]!!)
        else
            v
    }
}

@OptIn(ExperimentalContracts::class)
private inline fun <reified T> bothOfType(a: Any, b: Any): Boolean {
    contract { returns(true) implies (a is T && b is T) }
    return a is T && b is T
}

@OptIn(ExperimentalContracts::class)
private inline fun <reified T> bothOfTypeAnd(a: Any, b: Any, condition: (T) -> Boolean): Boolean {
    contract { returns(true) implies (a is T && b is T) }
    return a is T && b is T && condition(a) && condition(b)
}

internal infix fun Map<String, JsonElement>.extendedByJson(other: Map<String, JsonElement>): JsonObject {
    val map = this.extendedBy(other) { a, b ->
        // This logic might be enhanced
        when {
            bothOfTypeAnd<JsonPrimitive>(a, b) { it.isString } -> JsonPrimitive(a.content + b.content)
            bothOfType<JsonArray>(a, b) -> JsonArray(a + b)
            bothOfType<JsonObject>(a, b) -> JsonObject(a + b)
            else -> a
        }
    }
    return JsonObject(map)
}
