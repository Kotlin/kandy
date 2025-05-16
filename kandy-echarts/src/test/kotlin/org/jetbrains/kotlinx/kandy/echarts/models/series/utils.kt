package org.jetbrains.kotlinx.kandy.echarts.models.series

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
internal val json = Json {
    prettyPrint = true
    isLenient = true
    encodeDefaults = true
    explicitNulls = false
    allowSpecialFloatingPointValues = true
}