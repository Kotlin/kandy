/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.translator

import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.scale.CategoricalScale

internal fun Mapping.wrap(groupKeys: List<String>?): Pair<String, Any> {
    return if (groupKeys?.contains(columnID) == true || this.parameters?.scale is CategoricalScale) {
        aes.name to columnID
    } else {
        aes.name to columnID
    }
}
