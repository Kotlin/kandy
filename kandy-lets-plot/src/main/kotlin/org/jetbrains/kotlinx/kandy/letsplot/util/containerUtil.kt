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
private fun isStringLiteral(element: JsonElement): Boolean {
    contract { returns(true) implies (element is JsonPrimitive) }
    return element is JsonPrimitive && element.isString
}

internal infix fun Map<String, JsonElement>.extendedByJson(other: Map<String, JsonElement>): JsonObject {
    val map = this.extendedBy(other) { a, b ->
        // This logic might be enhanced
        when {
            isStringLiteral(a) && isStringLiteral(b) -> JsonPrimitive(a.content + b.content)
            a is JsonArray && b is JsonArray -> JsonArray(a + b)
            a is JsonObject && b is JsonObject -> JsonObject(a + b)
            else -> a
        }
    }
    return JsonObject(map)
}
