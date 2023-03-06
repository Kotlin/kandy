package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotNonPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.WIDTH

public interface WithWidth : BindingContext {
    public var width: Double?
        get() = null
        set(value) {
            bindingCollector.settings[WIDTH] = NonPositionalSetting(WIDTH, value)
        }
    public fun <T> width(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping<T, Double>(
            WIDTH,
            column.name(),
            LetsPlotNonPositionalMappingParameters<T, Double>().apply(parameters)
        )
    }
}