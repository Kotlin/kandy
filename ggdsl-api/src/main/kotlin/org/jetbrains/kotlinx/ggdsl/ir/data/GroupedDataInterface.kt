package org.jetbrains.kotlinx.ggdsl.ir.data

sealed interface GroupedDataInterface : TableData

interface LazyGroupedDataInterface : GroupedDataInterface {
    val keys: List<String>
    val origin: NamedDataInterface

    fun count(): CountedGroupedDataInterface
}

interface CountedGroupedDataInterface: GroupedDataInterface{
    val keys: NamedDataInterface
    val groups: List<NamedDataInterface>

    fun toLazy(): LazyGroupedDataInterface
}


