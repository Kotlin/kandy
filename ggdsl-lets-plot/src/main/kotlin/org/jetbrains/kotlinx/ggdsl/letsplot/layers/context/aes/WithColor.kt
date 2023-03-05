package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.COLOR
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotNonPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.util.color.Color

public interface WithColor : BindingContext {
    public var color: Color?
        get() = null
        set(value) {
            bindingCollector.settings[COLOR] = NonPositionalSetting(COLOR, value)
        }
    public fun <T> color(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return addNonPositionalMapping<T, Color>(
            COLOR,
            column.name(),
            LetsPlotNonPositionalMappingParameters<T, Color>().apply(parameters)
        )
    }

    public fun <T> color(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return addNonPositionalMapping<T, Color>(
            COLOR,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParameters<T, Color>().apply(parameters)
        )
    }
}