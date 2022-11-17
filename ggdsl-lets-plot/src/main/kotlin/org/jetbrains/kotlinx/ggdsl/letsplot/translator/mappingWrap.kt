package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.bindings.Mapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonScalablePositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledMapping
import org.jetbrains.kotlinx.ggdsl.ir.scale.CategoricalScale
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.PositionalParameters
import org.jetbrains.letsPlot.asDiscrete

internal fun Mapping.wrap(): Pair<String, Any> {
    return when (this) {
        is NonScalablePositionalMapping<*> -> aes.name to source.id
        is ScaledMapping<*> -> when (this.columnScaled.scale) {
            is CategoricalScale -> aes.name to asDiscrete(
                columnScaled.source.id,
                order = (scaleParameters as? PositionalParameters<*>)?.orderBy?.order,
                orderBy = (scaleParameters as? PositionalParameters<*>)?.orderBy?.name
            )

            else -> aes.name to columnScaled.source.id
        }
    }
}
