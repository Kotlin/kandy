/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.echarts.scale.EchartsNonPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.echarts.scale.nonPosMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMappingParameters

public interface WithAlpha : BindingContext {
    public var alpha: Double?
        get() = null
        set(value) {
            addNonPositionalSetting(ALPHA, value)
        }

    public fun <T> alpha(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = nonPosMapping(ALPHA, column, params)

    public fun <T> alpha(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = nonPosMapping(ALPHA, values, name, params)

    public fun alpha(
        column: String, params: EchartsNonPositionalMappingParameters<*, Double>.() -> Unit = {}
    ): NonPositionalMapping<*, Double> = nonPosMapping(ALPHA, column, params)

    public fun <T> alpha(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = nonPosMapping(ALPHA, values, params)
}