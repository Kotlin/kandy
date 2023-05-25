package org.jetbrains.kotlinx.kandy.letsplot.util

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

internal fun <K, T: Any> Map<K, T>.extendedBy(other: Map<K, T>, join: (T, T) -> T): Map<K, T> {
    val ours = this
    return buildMap {
        for ((k, v) in ours) {
            val otherV = other[k]
            val finalV = if (otherV == null) v else join(v, otherV)
            put(k, finalV)
        }
        for ((k, v) in other) {
            if (k !in ours) {
                put(k, v)
            }
        }
    }
}


internal infix fun Map<String, JsonElement>.extendedByJson(other: Map<String, JsonElement>): JsonObject {
    val map = this.extendedBy(other) { a, b ->
        // This logic might be enhanced
        when {
            a is JsonPrimitive && a.isString -> {
                when {
                    b is JsonPrimitive && b.isString -> JsonPrimitive(a.content + b.content)
                    else -> a
                }
            }
            else -> a
        }
    }
    return JsonObject(map)
}
