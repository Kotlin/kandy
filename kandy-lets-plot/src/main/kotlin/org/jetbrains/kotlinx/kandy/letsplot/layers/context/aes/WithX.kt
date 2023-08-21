/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalFreeScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model.AxisParametersWithSetter
import kotlin.reflect.KProperty

public interface WithX : BindingContext {
    /*public fun <T> x(value: T): PositionalSetting<T> {
        return addPositionalSetting(X, value)
    }*/

    public fun <T> x(
        column: ColumnReference<T>,
        parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            X,
            column.name(),
            LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
        )
    }

    public fun <T> x(
        column: KProperty<T>,
        parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            X,
            column.name,
            LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
        )
    }

    public fun x(
        column: String,
        parameters: LetsPlotPositionalMappingParametersContinuous<Any?>.() -> Unit = {}
    ): PositionalMapping<Any?> {
        return addPositionalMapping<Any?>(
            X,
            column,
            LetsPlotPositionalMappingParametersContinuous<Any?>().apply(parameters)
        )
    }

    public fun <T> x(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            X,
            values.toList(),
            name,
            LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
        )
    }

    public fun <T> x(
        values: DataColumn<T>,
        //name: String? = null,
        parameters: LetsPlotPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            X,
            values,
            LetsPlotPositionalMappingParametersContinuous<T>().apply(parameters)
        )
    }

    @Suppress("UNCHECKED_CAST")
    public val x: AxisParametersWithSetter
        get() {
            return AxisParametersWithSetter(bindingCollector.freeScales.getOrPut(X) {
                PositionalFreeScale(X, LetsPlotPositionalMappingParametersContinuous<Any?>())
            }.parameters as LetsPlotPositionalMappingParametersContinuous<Any?>, X, this)
        }

    public fun x(
        parameters: AxisParametersWithSetter.() -> Unit = {}
    ) {
        x.apply(parameters)
    }
}