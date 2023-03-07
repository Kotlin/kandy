package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.bindings.Mapping
import org.jetbrains.kotlinx.ggdsl.ir.scale.CategoricalScale
import org.jetbrains.letsPlot.asDiscrete

internal fun Mapping.wrap(isGroupKey: Boolean): Pair<String, Any> {
    return if (isGroupKey || this.parameters?.scale is CategoricalScale) {
        aes.name to asDiscrete(columnID) //todo orderBy
    } else {
        aes.name to columnID
    }
}
