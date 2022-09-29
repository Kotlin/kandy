package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData

fun TableData.wrap(): Map<String, List<Any>> {
    return when(this){
        is NamedDataInterface -> map
        is LazyGroupedDataInterface -> origin.map
        else -> TODO()
    }
}