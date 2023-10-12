/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.translator

import kotlinx.datetime.*
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import org.jetbrains.kotlinx.kandy.letsplot.internal.MERGED_GROUPS
import kotlin.reflect.typeOf

internal fun GroupedData.mergedKeys(): List<String> = buildList {
    val size = dataFrame.rowsCount()
    for (i in 0 until size) {
        add(keys.joinToString("$") {
            dataFrame[it][i].toString()
        })
    }
}

internal fun process(data: DataFrame<*>): Map<String, List<*>> {
    return data.columns().map {
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


internal fun TableData.wrap(): Map<String, List<*>> {
    return when (this) {
        is NamedData -> process(dataFrame)
        is GroupedData -> process(dataFrame) + (MERGED_GROUPS to mergedKeys())
        else -> error("Unexpected data format")
    }
}
