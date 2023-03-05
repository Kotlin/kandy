package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.scale.CategoricalScale
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.PositionalParameters
import org.jetbrains.letsPlot.asDiscrete

internal fun Mapping.wrap(): Pair<String, Any> {
    return when (this) {
        is NonScalablePositionalMapping<*> -> aes.name to source.name()
        is NonScalableNonPositionalMapping<*> -> aes.name to source.name()
        is ScaledMapping<*> -> when (this.columnScaled.scale) {
            is CategoricalScale -> aes.name to asDiscrete(
                columnScaled.source.name(),
                order = (scaleParameters as? PositionalParameters<*>)?.orderBy?.order,
                orderBy = (scaleParameters as? PositionalParameters<*>)?.orderBy?.name
            )

            else -> aes.name to columnScaled.source.name()
        }

        is NonPositionalMapping -> TODO()
        is PositionalMapping -> TODO()
    }
}

internal fun Mapping.columnName(): String {
    return when (this) {
        is NonScalablePositionalMapping<*> -> source.name()
        is NonScalableNonPositionalMapping<*> -> source.name()
        is ScaledMapping<*> -> columnScaled.source.name()
    }
}
