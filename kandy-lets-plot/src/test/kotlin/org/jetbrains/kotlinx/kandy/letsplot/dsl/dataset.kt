package org.jetbrains.kotlinx.kandy.letsplot.dsl

import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf

internal val dataset = dataFrameOf(
    "time" to listOf(1, 2),
    "type" to listOf("a", "b")
)

internal val time = column<Int>("time")
internal val type = column<String>("type")
