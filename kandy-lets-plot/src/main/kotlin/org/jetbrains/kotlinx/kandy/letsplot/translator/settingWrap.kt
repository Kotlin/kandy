/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

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
