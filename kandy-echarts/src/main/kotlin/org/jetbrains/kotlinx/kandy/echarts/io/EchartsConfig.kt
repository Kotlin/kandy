package org.jetbrains.kotlinx.kandy.echarts.io

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
internal object EchartsConfig {
    private const val ECHARTS_MAJOR = 5
    private const val ECHARTS_MINOR = 6
    private const val ECHARTS_PATCH = 0
    private const val VERSION: String = "$ECHARTS_MAJOR.$ECHARTS_MINOR.$ECHARTS_PATCH"

    internal const val ECHARTS_JSDELIVER_PACK =
        "https://cdn.jsdelivr.net/npm/echarts@$VERSION/dist/echarts.min"

    internal const val ECHARTS_JSDELIVER_SRC: String = "$ECHARTS_JSDELIVER_PACK.js"

    internal val json = Json {
        explicitNulls = false
        encodeDefaults = true
        prettyPrint = true
        isLenient = true
        allowSpecialFloatingPointValues = true
    }
}