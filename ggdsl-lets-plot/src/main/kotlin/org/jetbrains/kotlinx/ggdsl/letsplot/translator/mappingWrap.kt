package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.bindings.Mapping
import org.jetbrains.kotlinx.ggdsl.ir.scale.CategoricalScale
import org.jetbrains.letsPlot.asDiscrete

internal fun Mapping.wrap(groupKeys: List<String>?): Pair<String, Any> {
    return if (groupKeys?.contains(columnID) == true || this.parameters?.scale is CategoricalScale) {
        aes.name to asDiscrete(columnID) //todo orderBy
    } else {
        aes.name to columnID
    }
}
