package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.data.CountedGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.letsplot.MERGED_GROUPS

fun LazyGroupedDataInterface.mergedKeys(): List<String> = buildList {
    val map = this@mergedKeys.origin.map
    val size = map.values.first().size
    for (i in 0 until size) {
        add(keys.joinToString("$") {
            map[it]!![i].toString()
        })
    }
}

fun TableData.wrap(): Map<String, List<Any>> {
    return when(this){
        is NamedDataInterface -> map
        is LazyGroupedDataInterface -> origin.map + (MERGED_GROUPS to mergedKeys())
        is CountedGroupedDataInterface -> toLazy().wrap()
        else -> TODO()
    }
}