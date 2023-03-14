package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.letsplot.util.SimpleValueWrapper
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.Symbol
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.color.StandardColor

internal fun Color.wrap(): String {
    return when(this) {
        is StandardColor.Hex -> hexString
        is StandardColor.Named -> name
        is StandardColor.RGB -> hexString
        is StandardColor.RGBA -> TODO()
        else -> TODO()
    }
}

// TODO
internal fun wrapValue(value: Any?): Any? {
    if (value is Enum<*>) {
        return value.toString()
    }
    if (value is SimpleValueWrapper) {
        return value.value
    }
    if (value is Color) {
        return value.wrap()
    }
    if (value is Symbol) {
        return value.shape
    }
    if (value is LineType) {
        return value.description
    }
    return value
}

// TODO
internal fun Pair<*, *>?.wrap() = this?.let { (it.first as Number) to (it.second as Number) }
