/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.layers.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotContext
import org.jetbrains.kotlinx.kandy.echarts.scale.EchartsPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.echarts.scale.posFreeScale
import org.jetbrains.kotlinx.kandy.echarts.scale.posMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import kotlin.reflect.KProperty

public fun <T> PlotContext.x(
    column: ColumnReference<T>, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
): PositionalMapping<T> = posMapping(X, column, params)

public fun <T> PlotContext.x(
    column: KProperty<T>, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
): PositionalMapping<T> = posMapping(X, column, params)

public fun <T> PlotContext.x(
    values: Iterable<T>, name: String? = null, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
): PositionalMapping<T> = posMapping(X, values, name, params)

public fun <T> PlotContext.x(
    values: DataColumn<T>, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
): PositionalMapping<T> = posMapping(X, values, params)

public fun PlotContext.x(
    column: String, params: EchartsPositionalMappingParameters<*>.() -> Unit = {}
): PositionalMapping<*> = posMapping(X, column, params)

public fun <T> PlotContext.x(params: EchartsPositionalMappingParameters<T>.() -> Unit = {}): Unit =
    posFreeScale(X, params)

public fun <T> PlotContext.y(
    column: ColumnReference<T>, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
): PositionalMapping<T> = posMapping(Y, column, params)

public fun <T> PlotContext.y(
    column: KProperty<T>, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
): PositionalMapping<T> = posMapping(Y, column, params)

public fun <T> PlotContext.y(
    values: Iterable<T>, name: String? = null, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
): PositionalMapping<T> = posMapping(Y, values, name, params)

public fun <T> PlotContext.y(
    values: DataColumn<T>, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
): PositionalMapping<T> = posMapping(Y, values, params)

public fun PlotContext.y(
    column: String, params: EchartsPositionalMappingParameters<*>.() -> Unit = {}
): PositionalMapping<*> = posMapping(Y, column, params)

public fun <T> PlotContext.y(
    params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
): Unit = posFreeScale(Y, params)