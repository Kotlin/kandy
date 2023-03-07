package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotNonPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.SHAPE
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.Symbol

public interface WithSymbol : BindingContext {
    public var symbol: Symbol?
        get() = null
        set(value) {
            addNonPositionalSetting(SHAPE, value)
        }
    public fun <T> symbol(
        column: ColumnReference<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<T, Symbol> {
        return addNonPositionalMapping<T, Symbol>(
            SHAPE,
            column.name(),
            LetsPlotNonPositionalMappingParameters<T, Symbol>().apply(parameters)
        )
    }
}