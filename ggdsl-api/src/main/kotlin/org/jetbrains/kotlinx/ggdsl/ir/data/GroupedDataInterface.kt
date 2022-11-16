package org.jetbrains.kotlinx.ggdsl.ir.data

/**
 * Basic interface for data in the form of a grouped dataframe.
 */
public sealed interface GroupedDataInterface : TableData

/**
 * Lazy grouped data, i.e. data with information for grouping without actual grouping.
 *
 * @property keys grouping keys.
 * @property origin original data
 *
 * @property count transformation into [CountedGroupedDataInterface]
 */
public interface LazyGroupedDataInterface : GroupedDataInterface {
    public val keys: List<String>
    public val origin: NamedDataInterface

    public fun count(): CountedGroupedDataInterface
}

/**
 * Actually grouped data.
 *
 * @property keys grouping keys.
 * @property groups [List] of groups.
 *
 * @property toLazy transformation into [LazyGroupedDataInterface]
 */
public interface CountedGroupedDataInterface: GroupedDataInterface{
    public val keys: NamedDataInterface
    public val groups: List<NamedDataInterface>

    public fun toLazy(): LazyGroupedDataInterface
}
