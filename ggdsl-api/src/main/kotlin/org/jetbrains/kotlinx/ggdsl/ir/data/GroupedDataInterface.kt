package org.jetbrains.kotlinx.ggdsl.ir.data

interface GroupedDataInterface : TableData

interface LazyGroupedDataInterface : GroupedDataInterface {
    val keys: List<String>
    val origin: NamedDataInterface
}


