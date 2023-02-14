package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.util.serialization.allModules
import org.jetbrains.kotlinx.ggdsl.ir.Plot
/*
// TODO improve
public fun Plot.toJson(prettyPrint: Boolean = false): String {
    val json = Json {
        this.prettyPrint = prettyPrint
        allowStructuredMapKeys = true
        serializersModule = allModules
    }
    return json.encodeToString(this)
}

private val jsonDefault = Json {
    allowStructuredMapKeys = true
    serializersModule = allModules
}

public fun Plot.Companion.fromJson(json: String): Plot {
    return jsonDefault.decodeFromString(json)
}

 */
