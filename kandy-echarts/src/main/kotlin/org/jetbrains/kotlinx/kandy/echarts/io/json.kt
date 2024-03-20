package org.jetbrains.kotlinx.kandy.echarts.io

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.kotlinx.kandy.echarts.translator.option.Option
import org.jetbrains.kotlinx.kandy.echarts.translator.toOption
import org.jetbrains.kotlinx.kandy.ir.Plot


public fun Plot.toJson(json: Json = EchartsConfig.json): String = this.toOption().toJSON(json)

internal fun Option.toJSON(json: Json): String = json.encodeToString(this)
