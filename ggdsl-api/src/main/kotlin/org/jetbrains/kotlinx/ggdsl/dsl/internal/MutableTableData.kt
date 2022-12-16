package org.jetbrains.kotlinx.ggdsl.dsl.internal

import org.jetbrains.kotlinx.ggdsl.dsl.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.ir.data.TypedList

/**
 * Buffer type for creating a [TableData] dynamically, i.e. with dynamic adding columns.
 *
 * @property map buffer [MutableMap].
 * @property toTableData builds [TableData].
 */
public interface MutableTableData {
    public val map: MutableMap<String, TypedList>

    public fun toTableData(): TableData
}

/**
 * Buffer type for creating a [NamedData] dynamically, i.e. with dynamic adding columns.
 */
public open class MutableNamedData(
    public override val map: MutableMap<String, TypedList> = mutableMapOf<String, TypedList>()
): MutableTableData {
    public override fun toTableData(): NamedData {
        return NamedData(map.toMap())
    }
}
/*
@PublishedApi
internal fun NamedData.toMutableNamedData(): MutableNamedData = MutableNamedData(map.toMutableMap())
 */
