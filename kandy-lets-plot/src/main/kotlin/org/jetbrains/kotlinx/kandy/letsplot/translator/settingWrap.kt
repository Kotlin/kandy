package org.jetbrains.kotlinx.kandy.letsplot.translator

import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting

internal fun Setting.wrap(): Pair<String, Any?> {
    return when (this) {
        is NonPositionalSetting<*> -> aes.name to wrapValue(value)
        is PositionalSetting<*> -> aes.name to wrapValue(value)
    }
}
