/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.layers.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.MultiLayerPlotBuilder
import org.jetbrains.kotlinx.kandy.echarts.scale.EchartsPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.echarts.scale.posFreeScale
import org.jetbrains.kotlinx.kandy.echarts.scale.posMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import kotlin.reflect.KProperty

public fun <T> MultiLayerPlotBuilder.x(
    column: ColumnReference<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> = posMapping(X, column, params)

public fun <T> MultiLayerPlotBuilder.x(
    column: KProperty<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> = posMapping(X, column, params)

public fun <T> MultiLayerPlotBuilder.x(
    values: Iterable<T>, name: String? = null, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> = posMapping(X, values, name, params)

public fun <T> MultiLayerPlotBuilder.x(
    values: DataColumn<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> = posMapping(X, values, params)

public fun MultiLayerPlotBuilder.x(
    column: String, params: EchartsPositionalMappingParametersContinuous<*>.() -> Unit = {}
): PositionalMapping<*> = posMapping(X, column, params)

public fun <T> MultiLayerPlotBuilder.x(params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}): Unit =
    posFreeScale(X, params)

public fun <T> MultiLayerPlotBuilder.y(
    column: ColumnReference<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> = posMapping(Y, column, params)

public fun <T> MultiLayerPlotBuilder.y(
    column: KProperty<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> = posMapping(Y, column, params)

public fun <T> MultiLayerPlotBuilder.y(
    values: Iterable<T>, name: String? = null, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> = posMapping(Y, values, name, params)

public fun <T> MultiLayerPlotBuilder.y(
    values: DataColumn<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> = posMapping(Y, values, params)

public fun MultiLayerPlotBuilder.y(
    column: String, params: EchartsPositionalMappingParametersContinuous<*>.() -> Unit = {}
): PositionalMapping<*> = posMapping(Y, column, params)

public fun <T> MultiLayerPlotBuilder.y(
    params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): Unit = posFreeScale(Y, params)
