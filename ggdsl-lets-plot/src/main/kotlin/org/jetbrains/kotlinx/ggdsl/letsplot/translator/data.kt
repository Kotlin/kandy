package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import kotlinx.datetime.*
import org.jetbrains.kotlinx.ggdsl.ir.data.CountedGroupedData
import org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedData
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.MERGED_GROUPS
import kotlin.reflect.typeOf

internal fun LazyGroupedData.mergedKeys(): List<String> = buildList {
    val map = this@mergedKeys.origin.nameToValues
    val size = map.values.first().values.size
    for (i in 0 until size) {
        add(keys.joinToString("$") {
            map[it]!!.values[i].toString()
        })
    }
}

internal object DateTimeMaster {
    internal fun postProcess(data: NamedData): Map<String, List<*>> {
        return data.nameToValues.map { (key, tList) ->
            val type = tList.kType
            val values = tList.values
            // TODO!!!
            key to (when (type) {
                typeOf<LocalDate>(), typeOf<LocalDate?>() -> values.map {
                    (it as? LocalDate)?.atStartOfDayIn((TimeZone.currentSystemDefault()))
                }
                typeOf<LocalDateTime>(), typeOf<LocalDateTime?>() -> values.map { dateTime ->
                    (dateTime as? LocalDateTime)?.toInstant(TimeZone.currentSystemDefault())
                }
                typeOf<LocalTime>(), typeOf<LocalTime?>() -> values.map { time ->
                    (time as? LocalTime)?.toMillisecondOfDay()
                }
                else -> values
            })
        }.toMap()
    }
}

internal fun TableData.wrap(): Map<String, List<*>> {
    return (when (this) {
        is NamedData -> DateTimeMaster.postProcess(this)
        is LazyGroupedData -> DateTimeMaster.postProcess(origin) + (MERGED_GROUPS to mergedKeys())
        is CountedGroupedData -> toLazy().wrap()
        else -> TODO()
    })
}
