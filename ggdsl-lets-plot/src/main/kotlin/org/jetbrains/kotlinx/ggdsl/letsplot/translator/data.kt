package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import kotlinx.datetime.*
import org.jetbrains.kotlinx.ggdsl.ir.data.CountedGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.MERGED_GROUPS
import kotlin.reflect.typeOf

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
    internal fun postProcess(data: NamedDataInterface): Map<String, List<*>> {
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
        is NamedDataInterface -> DateTimeMaster.postProcess(this)
        is LazyGroupedDataInterface -> DateTimeMaster.postProcess(origin) + (MERGED_GROUPS to mergedKeys())
        is CountedGroupedDataInterface -> toLazy().wrap()
        else -> TODO()
    })
}
