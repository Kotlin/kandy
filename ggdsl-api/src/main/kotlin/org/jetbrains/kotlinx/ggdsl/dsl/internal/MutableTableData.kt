package org.jetbrains.kotlinx.ggdsl.dsl.internal

import org.jetbrains.kotlinx.ggdsl.dsl.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData

public interface MutableTableData {
    public val map: MutableMap<String, List<Any>>

    public fun toTableData(): TableData
}

public open class MutableNamedData(
    public override val map: MutableMap<String, List<Any>> = mutableMapOf<String, List<Any>>()
): MutableTableData {
    public override fun toTableData(): NamedData {
        return NamedData(map.toMap())
    }
}
/*
@PublishedApi
internal fun NamedData.toMutableNamedData(): MutableNamedData = MutableNamedData(map.toMutableMap())
 */
