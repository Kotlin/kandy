/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalFreeScale
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model.AxisParametersWithSetter

public interface WithY : BindingContext {
    /*public fun <T> y(value: T): PositionalSetting<T> {
        return addPositionalSetting(Y, value)
    }*/

    public fun <T> y(
        column: ColumnReference<T>,
        parameters: LetsPlotPositionalMappingParameters<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y, column.name(), LetsPlotPositionalMappingParameters<T>().apply(parameters))
    }

    public fun y(
        column: String,
        parameters: LetsPlotPositionalMappingParameters<Any?>.() -> Unit = {}
    ): PositionalMapping<Any?> {
        return addPositionalMapping<Any?>(Y, column, LetsPlotPositionalMappingParameters<Any?>().apply(parameters))
    }

    public fun <T> y(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotPositionalMappingParameters<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            Y,
            values.toList(),
            name,
            LetsPlotPositionalMappingParameters<T>().apply(parameters)
        )
    }

    public fun <T> y(
        values: DataColumn<T>,
        //name: String? = null,
        parameters: LetsPlotPositionalMappingParameters<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            Y,
            values,
            LetsPlotPositionalMappingParameters<T>().apply(parameters)
        )
    }

    @Suppress("UNCHECKED_CAST")
    public val y: AxisParametersWithSetter
        get() {
            return AxisParametersWithSetter(bindingCollector.freeScales.getOrPut(Y) {
                PositionalFreeScale(Y, LetsPlotPositionalMappingParameters<Any?>())
            }.parameters as LetsPlotPositionalMappingParameters<Any?>, Y, this)
        }

    public fun y(
        parameters: AxisParametersWithSetter.() -> Unit = {}
    ) {
        y.apply(parameters)
    }
}