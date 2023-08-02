/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.scale

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.dataframe.impl.asList
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import kotlin.reflect.KProperty


internal fun <T> BindingContext.posMapping(
    aesName: AesName, column: ColumnReference<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> =
    addPositionalMapping(aesName, column.name(), EchartsPositionalMappingParametersContinuous<T>().apply(params))

internal fun <T> BindingContext.posMapping(
    aesName: AesName, column: KProperty<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> =
    addPositionalMapping(aesName, column.name, EchartsPositionalMappingParametersContinuous<T>().apply(params))

internal fun <T> BindingContext.posMapping(
    aesName: AesName, values: Iterable<T>,
    name: String? = null, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> =
    addPositionalMapping(aesName, values.asList(), name, EchartsPositionalMappingParametersContinuous<T>().apply(params))

internal fun <T> BindingContext.posMapping(
    aesName: AesName, values: DataColumn<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> = addPositionalMapping(aesName, values, EchartsPositionalMappingParametersContinuous<T>().apply(params))

internal fun BindingContext.posMapping(
    aesName: AesName, column: String,
    parameters: EchartsPositionalMappingParametersContinuous<*>.() -> Unit = {}
): PositionalMapping<*> =
    addPositionalMapping(aesName, column, EchartsPositionalMappingParametersContinuous<Any?>().apply(parameters))

internal fun <T> BindingContext.posFreeScale(
    aesName: AesName, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
): Unit = addPositionalFreeScale(aesName, EchartsPositionalMappingParametersContinuous<T>().apply(params))

internal fun <T, D> BindingContext.nonPosMappingCont(
    aesName: AesName, column: ColumnReference<T>, params: EchartsNonPositionalMappingParametersContinuous<T, D>.() -> Unit = {}
): NonPositionalMapping<T, D> = this.addNonPositionalMapping(
    aesName, column.name(), EchartsNonPositionalMappingParametersContinuous<T, D>().apply(params)
)

internal fun <T, D> BindingContext.nonPosMappingCont(
    aesName: AesName, column: KProperty<T>, params: EchartsNonPositionalMappingParametersContinuous<T, D>.() -> Unit = {}
): NonPositionalMapping<T, D> = this.addNonPositionalMapping(
    aesName, column.name, EchartsNonPositionalMappingParametersContinuous<T, D>().apply(params)
)

internal fun <T, D> BindingContext.nonPosMappingCont(
    aesName: AesName, values: Iterable<T>,
    name: String? = null, params: EchartsNonPositionalMappingParametersContinuous<T, D>.() -> Unit = {}
): NonPositionalMapping<T, D> = this.addNonPositionalMapping(
    aesName, values.asList(), name, EchartsNonPositionalMappingParametersContinuous<T, D>().apply(params)
)

internal fun <T, D> BindingContext.nonPosMappingCont(
    aesName: AesName, values: DataColumn<T>, params: EchartsNonPositionalMappingParametersContinuous<T, D>.() -> Unit
): NonPositionalMapping<T, D> =
    this.addNonPositionalMapping(aesName, values, EchartsNonPositionalMappingParametersContinuous<T, D>().apply(params))

internal fun <D> BindingContext.nonPosMappingCont(
    aesName: AesName, column: String, parameters: EchartsNonPositionalMappingParametersContinuous<*, D>.() -> Unit = {}
): NonPositionalMapping<*, D> =
    addNonPositionalMapping(aesName, column, EchartsNonPositionalMappingParametersContinuous<Any?, D>().apply(parameters))

internal fun <T, D> BindingContext.nonPosMappingCat(
    aesName: AesName, column: ColumnReference<T>, params: EchartsNonPositionalMappingParametersCategorical<T, D>.() -> Unit = {}
): NonPositionalMapping<T, D> = this.addNonPositionalMapping(
    aesName, column.name(), EchartsNonPositionalMappingParametersCategorical<T, D>().apply(params)
)

internal fun <T, D> BindingContext.nonPosMappingCat(
    aesName: AesName, column: KProperty<T>, params: EchartsNonPositionalMappingParametersCategorical<T, D>.() -> Unit = {}
): NonPositionalMapping<T, D> = this.addNonPositionalMapping(
    aesName, column.name, EchartsNonPositionalMappingParametersCategorical<T, D>().apply(params)
)

internal fun <T, D> BindingContext.nonPosMappingCat(
    aesName: AesName, values: Iterable<T>,
    name: String? = null, params: EchartsNonPositionalMappingParametersCategorical<T, D>.() -> Unit = {}
): NonPositionalMapping<T, D> = this.addNonPositionalMapping(
    aesName, values.asList(), name, EchartsNonPositionalMappingParametersCategorical<T, D>().apply(params)
)

internal fun <T, D> BindingContext.nonPosMappingCat(
    aesName: AesName, values: DataColumn<T>, params: EchartsNonPositionalMappingParametersCategorical<T, D>.() -> Unit
): NonPositionalMapping<T, D> =
    this.addNonPositionalMapping(aesName, values, EchartsNonPositionalMappingParametersCategorical<T, D>().apply(params))

internal fun <D> BindingContext.nonPosMappingCat(
    aesName: AesName, column: String, parameters: EchartsNonPositionalMappingParametersCategorical<*, D>.() -> Unit = {}
): NonPositionalMapping<*, D> =
    addNonPositionalMapping(aesName, column, EchartsNonPositionalMappingParametersCategorical<Any?, D>().apply(parameters))
