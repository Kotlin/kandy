package org.jetbrains.kotlinx.ggdsl.ir.data

public sealed interface GroupedDataInterface : TableData

public interface LazyGroupedDataInterface : GroupedDataInterface {
    public val keys: List<String>
    public val origin: NamedDataInterface

    public fun count(): CountedGroupedDataInterface
}

public interface CountedGroupedDataInterface: GroupedDataInterface{
    public val keys: NamedDataInterface
    public val groups: List<NamedDataInterface>

    public fun toLazy(): LazyGroupedDataInterface
}


