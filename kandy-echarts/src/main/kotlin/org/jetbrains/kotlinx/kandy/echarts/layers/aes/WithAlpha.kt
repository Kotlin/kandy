/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.layers.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.echarts.scale.EchartsNonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.echarts.scale.EchartsNonPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.echarts.scale.nonPosMappingCont
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import kotlin.reflect.KProperty

public interface WithAlpha : BindingContext {
    public var alpha: Double?
        get() = null
        set(value) {
            addNonPositionalSetting(ALPHA, value)
        }

    public fun <T> alpha(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = nonPosMappingCont(ALPHA, column, params)

    public fun <T> alpha(
        column: KProperty<T>, params: EchartsNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = nonPosMappingCont(ALPHA, column, params)

    public fun <T> alpha(
        values: Iterable<T>, name: String? = null, params: EchartsNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = nonPosMappingCont(ALPHA, values, name, params)

    public fun alpha(
        column: String, params: EchartsNonPositionalMappingParametersContinuous<*, Double>.() -> Unit = {}
    ): NonPositionalMapping<*, Double> = nonPosMappingCont(ALPHA, column, params)

    public fun <T> alpha(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = nonPosMappingCont(ALPHA, values, params)
}