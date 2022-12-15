package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import kotlinx.datetime.*
import org.jetbrains.kotlinx.ggdsl.ir.data.CountedGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.MERGED_GROUPS
import kotlin.reflect.typeOf
import kotlin.time.Duration.Companion.nanoseconds

internal fun LazyGroupedDataInterface.mergedKeys(): List<String> = buildList {
    val map = this@mergedKeys.origin.nameToValues
    val size = map.values.first().values.size
    for (i in 0 until size) {
        add(keys.joinToString("$") {
            map[it]!!.values[i].toString()
        })
    }
}

internal object DateTimeMaster {
    internal fun postProcess(data: NamedDataInterface): Map<String, List<Any>> {
        return data.nameToValues.map { (key, tList)  ->
            val type = tList.kType
            val values = tList.values
            // TODO!!!
            key to when(type) {
                typeOf<LocalDate>() -> values.map {
                    (it as LocalDate).atStartOfDayIn((TimeZone.currentSystemDefault()))
                }
                typeOf<LocalDateTime>() -> values.map {
                    (it as LocalDateTime).toInstant(TimeZone.currentSystemDefault())
                }
                typeOf<LocalTime>() -> values.map {
                    Clock.System.now().toLocalDateTime(
                        TimeZone.currentSystemDefault()
                    ).date.atStartOfDayIn(TimeZone.currentSystemDefault()).plus(
                        (it as LocalTime).toNanosecondOfDay().nanoseconds
                    )
                }
                else -> values
            }
        }.toMap()
    }
}
/*
internal fun TableData.columnTypes(): Map<String, KType> {
    return (when (this) {
        is NamedDataInterface -> nameToValues.map { it.key to it.value.kType }.toMap()
        is LazyGroupedDataInterface -> origin.nameToValues.map { it.key to it.value.kType }.toMap() + (MERGED_GROUPS to typeOf<String>())
        is CountedGroupedDataInterface -> toLazy().origin.nameToValues.map { it.key to it.value.kType }.toMap()
        else -> TODO()
    })
}

 */


internal fun TableData.wrap(): Map<String, List<Any>> {
    return (when (this) {
        is NamedDataInterface -> DateTimeMaster.postProcess(this)
        is LazyGroupedDataInterface -> DateTimeMaster.postProcess(origin) + (MERGED_GROUPS to mergedKeys())
        is CountedGroupedDataInterface -> toLazy().wrap()
        else -> TODO()
    })
}
