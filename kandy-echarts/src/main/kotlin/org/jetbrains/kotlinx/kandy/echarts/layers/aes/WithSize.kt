/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package org.jetbrains.kotlinx.kandy.echarts.layers.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingHandler
import org.jetbrains.kotlinx.kandy.echarts.scale.EchartsNonPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.echarts.scale.nonPosMappingCont
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import kotlin.reflect.KProperty

public interface WithSize : WithAes {
    public var size: Double?
        get() = null
        set(value) {
            bindingHandler.addNonPositionalSetting(SIZE, value)
        }

    public fun <T> size(
        column: ColumnReference<T>, parameters: EchartsNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = bindingHandler.nonPosMappingCont(SIZE, column, parameters)

    public fun <T> size(
        column: KProperty<T>, params: EchartsNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = bindingHandler.nonPosMappingCont(SIZE, column, params)

    public fun <T> size(
        values: Iterable<T>, name: String? = null, params: EchartsNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = bindingHandler.nonPosMappingCont(SIZE, values, name, params)

    public fun size(
        column: String, params: EchartsNonPositionalMappingParametersContinuous<*, Double>.() -> Unit = {}
    ): NonPositionalMapping<*, Double> = bindingHandler.nonPosMappingCont(SIZE, column, params)

    public fun <T> size(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> = bindingHandler.nonPosMappingCont(SIZE, values, params)
}