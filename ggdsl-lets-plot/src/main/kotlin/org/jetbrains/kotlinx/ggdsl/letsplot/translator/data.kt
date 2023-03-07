package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import kotlinx.datetime.*
import org.jetbrains.kotlinx.ggdsl.ir.data.GroupedData
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.MERGED_GROUPS
import kotlin.reflect.typeOf

internal fun GroupedData.mergedKeys(): List<String> = buildList {
    val dataFrame = origin.dataFrame
    val size = dataFrame.rowsCount()
    for (i in 0 until size) {
        add(keys.joinToString("$") {
            dataFrame[it][i].toString()
        })
    }
}

internal object DateTimeMaster {
    internal fun postProcess(data: NamedData): Map<String, List<*>> {
        return data.dataFrame.columns().map {
            val type = it.type()
            val values = it.values()
            // TODO!!!
            it.name() to (when (type) {
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
            }).toList()
        }.toMap()
    }
}

internal fun TableData.wrap(): Map<String, List<*>> {
    return (when (this) {
        is NamedData -> DateTimeMaster.postProcess(this)
        is GroupedData -> DateTimeMaster.postProcess(origin) + (MERGED_GROUPS to mergedKeys())
        else -> TODO()
    })
}
