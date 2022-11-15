package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import kotlinx.datetime.*
import org.jetbrains.kotlinx.ggdsl.ir.data.CountedGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.letsplot.MERGED_GROUPS

internal fun LazyGroupedDataInterface.mergedKeys(): List<String> = buildList {
    val map = this@mergedKeys.origin.map
    val size = map.values.first().size
    for (i in 0 until size) {
        add(keys.joinToString("$") {
            map[it]!![i].toString()
        })
    }
}

internal object DateTimeMaster {
    var hasXDateTime: Boolean = false
    var hasYDateTime: Boolean = false

    internal fun postProcess(data: Map<String, List<Any>>): Map<String, List<Any>> {
        return data.map { (key, value) ->
            when (value.first()) {
                is LocalDate -> key to value.map {
                    (it as LocalDate).atStartOfDayIn((TimeZone.currentSystemDefault()))
                }
                is LocalDateTime -> key to value.map {
                    (it as LocalDateTime).toInstant(TimeZone.currentSystemDefault())
                }
                /*
                is LocalTime -> key to value.map {
                    Clock.System.now().toLocalDateTime(
                        TimeZone.currentSystemDefault()
                    ).date.atStartOfDayIn(TimeZone.currentSystemDefault()).plus(
                        (it as LocalTime).toNanosecondOfDay().nanoseconds
                    )
                }

                 */
                else -> key to value
            }
        }.toMap()
    }
}



internal fun TableData.wrap(): Map<String, List<Any>> {
    return (when (this) {
        is NamedDataInterface -> map
        is LazyGroupedDataInterface -> origin.map + (MERGED_GROUPS to mergedKeys())
        is CountedGroupedDataInterface -> toLazy().wrap()
        else -> TODO()
    }).let { DateTimeMaster.postProcess(it) }
}
