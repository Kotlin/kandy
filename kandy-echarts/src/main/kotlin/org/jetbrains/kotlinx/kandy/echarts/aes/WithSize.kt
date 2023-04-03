/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.echarts.scale.EchartsNonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.echarts.scale.nonPosMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters

public interface WithSize : BindingContext {
    public var size: Double?
        get() = null
        set(value) {
            addNonPositionalSetting(SIZE, value)
        }

    public fun <T> size(
        column: ColumnReference<T>, parameters: EchartsNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = nonPosMapping(SIZE, column, parameters)

    public fun <T> size(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = nonPosMapping(SIZE, values, name, params)

    public fun size(
        column: String, params: EchartsNonPositionalMappingParameters<*, Double>.() -> Unit = {}
    ): NonPositionalMapping<*, Double> = nonPosMapping(SIZE, column, params)

    public fun <T> size(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = nonPosMapping(SIZE, values, params)
}