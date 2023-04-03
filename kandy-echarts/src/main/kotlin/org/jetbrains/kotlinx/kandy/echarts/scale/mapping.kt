package org.jetbrains.kotlinx.kandy.echarts.scale

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.dataframe.impl.asList
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping


internal fun <T> BindingContext.posMapping(
    aesName: AesName, column: ColumnReference<T>, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
): PositionalMapping<T> =
    addPositionalMapping(aesName, column.name(), EchartsPositionalMappingParameters<T>().apply(params))

internal fun <T> BindingContext.posMapping(
    aesName: AesName, values: Iterable<T>,
    name: String? = null, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
): PositionalMapping<T> =
    addPositionalMapping(aesName, values.asList(), name, EchartsPositionalMappingParameters<T>().apply(params))

internal fun <T> BindingContext.posMapping(
    aesName: AesName, values: DataColumn<T>, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
): PositionalMapping<T> = addPositionalMapping(aesName, values, EchartsPositionalMappingParameters<T>().apply(params))

internal fun BindingContext.posMapping(
    aesName: AesName, column: String,
    parameters: EchartsPositionalMappingParameters<*>.() -> Unit = {}
): PositionalMapping<*> =
    addPositionalMapping(aesName, column, EchartsPositionalMappingParameters<Any?>().apply(parameters))

internal fun <T> BindingContext.posFreeScale(
    aesName: AesName, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
): Unit = addPositionalFreeScale(aesName, EchartsPositionalMappingParameters<T>().apply(params))

internal fun <T, D> BindingContext.nonPosMapping(
    aesName: AesName, column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, D>.() -> Unit = {}
): NonPositionalMapping<T, D> = this.addNonPositionalMapping(
    aesName, column.name(), EchartsNonPositionalMappingParameters<T, D>().apply(params)
)

internal fun <T, D> BindingContext.nonPosMapping(
    aesName: AesName, values: Iterable<T>,
    name: String? = null, params: NonPositionalMappingParameters<T, D>.() -> Unit = {}
): NonPositionalMapping<T, D> = this.addNonPositionalMapping(
    aesName, values.asList(), name, EchartsNonPositionalMappingParameters<T, D>().apply(params)
)

internal fun <T, D> BindingContext.nonPosMapping(
    aesName: AesName, values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, D>.() -> Unit
): NonPositionalMapping<T, D> =
    this.addNonPositionalMapping(aesName, values, EchartsNonPositionalMappingParameters<T, D>().apply(params))

internal fun <D> BindingContext.nonPosMapping(
    aesName: AesName, column: String, parameters: EchartsNonPositionalMappingParameters<*, D>.() -> Unit = {}
): NonPositionalMapping<*, D> =
    addNonPositionalMapping(aesName, column, EchartsNonPositionalMappingParameters<Any?, D>().apply(parameters))