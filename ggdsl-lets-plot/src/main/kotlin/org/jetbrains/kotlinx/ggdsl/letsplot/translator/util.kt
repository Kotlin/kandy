package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.letsplot.util.SimpleValueWrapper
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.Symbol
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.color.StandardColor
import org.jetbrains.kotlinx.ggdsl.util.serialization.TypedValue

internal fun Color.wrap(): String {
    return when(this) {
        is StandardColor.Hex -> hexString
        is StandardColor.Named -> name
        is StandardColor.RGB -> hexString
        is StandardColor.RGBA -> TODO()
        else -> TODO()
    }
}

internal fun TypedValue.wrap(): Any = wrapValue(value!!)

// TODO
internal fun wrapValue(value: Any): Any {
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
internal fun  Pair<TypedValue, TypedValue>?.wrap() = this?.let { (it.first.value as Number) to (it.second.value as Number) }