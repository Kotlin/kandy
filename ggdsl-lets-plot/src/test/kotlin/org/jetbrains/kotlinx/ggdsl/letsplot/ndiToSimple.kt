package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData

fun NamedData.toSimple(): Map<String, List<*>> {
    return nameToValues.map {
        it.key to it.value.values
    }.toMap()
}