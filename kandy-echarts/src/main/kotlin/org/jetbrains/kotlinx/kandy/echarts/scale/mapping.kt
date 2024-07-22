/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package org.jetbrains.kotlinx.kandy.echarts.scale

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.dataframe.impl.asList
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingHandler
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.*
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import kotlin.reflect.KProperty


internal fun <T> BindingHandler.posMapping(
    aes: Aes, column: ColumnReference<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> =
    addPositionalMapping(aes, column.name(), EchartsPositionalMappingParametersContinuous<T>().apply(params))

internal fun <T> BindingHandler.posMapping(
    aes: Aes, column: KProperty<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> =
    addPositionalMapping(aes, column.name, EchartsPositionalMappingParametersContinuous<T>().apply(params))

internal fun <T> BindingHandler.posMapping(
    aes: Aes, values: Iterable<T>,
    name: String? = null, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> =
    addPositionalMapping(aes, values.asList(), name, EchartsPositionalMappingParametersContinuous<T>().apply(params))

internal fun <T> BindingHandler.posMapping(
    aes: Aes, values: DataColumn<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> = addPositionalMapping(aes, values, EchartsPositionalMappingParametersContinuous<T>().apply(params))

internal fun BindingHandler.posMapping(
    aes: Aes, column: String,
    parameters: EchartsPositionalMappingParametersContinuous<*>.() -> Unit = {}
): PositionalMapping<*> =
    addPositionalMapping(aes, column, EchartsPositionalMappingParametersContinuous<Any?>().apply(parameters))

internal fun <T> BindingHandler.posFreeScale(
    aes: Aes, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): Unit = addPositionalFreeScale(aes, EchartsPositionalMappingParametersContinuous<T>().apply(params))

internal fun <T, D> BindingHandler.nonPosMappingCont(
    aes: Aes, column: ColumnReference<T>, params: EchartsNonPositionalMappingParametersContinuous<T, D>.() -> Unit = {}
): NonPositionalMapping<T, D> = this.addNonPositionalMapping(
    aes, column.name(), EchartsNonPositionalMappingParametersContinuous<T, D>().apply(params)
)

internal fun <T, D> BindingHandler.nonPosMappingCont(
    aes: Aes, column: KProperty<T>, params: EchartsNonPositionalMappingParametersContinuous<T, D>.() -> Unit = {}
): NonPositionalMapping<T, D> = this.addNonPositionalMapping(
    aes, column.name, EchartsNonPositionalMappingParametersContinuous<T, D>().apply(params)
)

internal fun <T, D> BindingHandler.nonPosMappingCont(
    aes: Aes, values: Iterable<T>,
    name: String? = null, params: EchartsNonPositionalMappingParametersContinuous<T, D>.() -> Unit = {}
): NonPositionalMapping<T, D> = this.addNonPositionalMapping(
    aes, values.asList(), name, EchartsNonPositionalMappingParametersContinuous<T, D>().apply(params)
)

internal fun <T, D> BindingHandler.nonPosMappingCont(
    aes: Aes, values: DataColumn<T>, params: EchartsNonPositionalMappingParametersContinuous<T, D>.() -> Unit
): NonPositionalMapping<T, D> =
    this.addNonPositionalMapping(aes, values, EchartsNonPositionalMappingParametersContinuous<T, D>().apply(params))

internal fun <D> BindingHandler.nonPosMappingCont(
    aes: Aes, column: String, parameters: EchartsNonPositionalMappingParametersContinuous<*, D>.() -> Unit = {}
): NonPositionalMapping<*, D> =
    addNonPositionalMapping(aes, column, EchartsNonPositionalMappingParametersContinuous<Any?, D>().apply(parameters))

internal fun <T, D> BindingHandler.nonPosMappingCat(
    aes: Aes, column: ColumnReference<T>, params: EchartsNonPositionalMappingParametersCategorical<T, D>.() -> Unit = {}
): NonPositionalMapping<T, D> = this.addNonPositionalMapping(
    aes, column.name(), EchartsNonPositionalMappingParametersCategorical<T, D>().apply(params)
)

internal fun <T, D> BindingHandler.nonPosMappingCat(
    aes: Aes, column: KProperty<T>, params: EchartsNonPositionalMappingParametersCategorical<T, D>.() -> Unit = {}
): NonPositionalMapping<T, D> = this.addNonPositionalMapping(
    aes, column.name, EchartsNonPositionalMappingParametersCategorical<T, D>().apply(params)
)

internal fun <T, D> BindingHandler.nonPosMappingCat(
    aes: Aes, values: Iterable<T>,
    name: String? = null, params: EchartsNonPositionalMappingParametersCategorical<T, D>.() -> Unit = {}
): NonPositionalMapping<T, D> = this.addNonPositionalMapping(
    aes, values.asList(), name, EchartsNonPositionalMappingParametersCategorical<T, D>().apply(params)
)

internal fun <T, D> BindingHandler.nonPosMappingCat(
    aes: Aes, values: DataColumn<T>, params: EchartsNonPositionalMappingParametersCategorical<T, D>.() -> Unit
): NonPositionalMapping<T, D> =
    this.addNonPositionalMapping(aes, values, EchartsNonPositionalMappingParametersCategorical<T, D>().apply(params))

internal fun <D> BindingHandler.nonPosMappingCat(
    aes: Aes, column: String, parameters: EchartsNonPositionalMappingParametersCategorical<*, D>.() -> Unit = {}
): NonPositionalMapping<*, D> =
    addNonPositionalMapping(aes, column, EchartsNonPositionalMappingParametersCategorical<Any?, D>().apply(parameters))
