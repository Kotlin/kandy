package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface

fun NamedDataInterface.toSimple(): Map<String, List<Any>> {
    return nameToValues.map {
        it.key to it.value.values
    }.toMap()
}