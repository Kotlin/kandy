package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.ALPHA
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotNonPositionalMappingParameters

public interface WithAlpha : BindingContext {
    public var alpha: Double?
        get() = null
        set(value) {
            bindingCollector.settings[ALPHA] = NonPositionalSetting(ALPHA, value)
        }
    public fun <T> alpha(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping<T, Double>(
            ALPHA,
            column.name(),
            LetsPlotNonPositionalMappingParameters<T, Double>().apply(parameters)
        )
    }
}