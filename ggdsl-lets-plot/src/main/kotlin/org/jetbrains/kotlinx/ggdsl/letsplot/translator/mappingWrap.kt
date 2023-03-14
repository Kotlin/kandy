package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.bindings.Mapping
import org.jetbrains.kotlinx.ggdsl.ir.scale.CategoricalScale

internal fun Mapping.wrap(groupKeys: List<String>?): Pair<String, Any> {
    return if (groupKeys?.contains(columnID) == true || this.parameters?.scale is CategoricalScale) {
        //aes.name to asDiscrete(columnID) //todo orderBy
        aes.name to columnID
    } else {
        aes.name to columnID
    }
}
