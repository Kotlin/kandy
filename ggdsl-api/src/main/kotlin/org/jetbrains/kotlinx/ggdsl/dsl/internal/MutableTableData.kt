package org.jetbrains.kotlinx.ggdsl.dsl.internal

import org.jetbrains.kotlinx.ggdsl.dsl.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.ir.data.TypedList

public interface MutableTableData {
    public val map: MutableMap<String, TypedList>

    public fun toTableData(): TableData
}

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
