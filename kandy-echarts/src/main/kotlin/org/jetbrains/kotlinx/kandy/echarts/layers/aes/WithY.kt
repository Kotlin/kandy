/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package org.jetbrains.kotlinx.kandy.echarts.layers.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.echarts.scale.EchartsPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.echarts.scale.posMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import kotlin.reflect.KProperty

public interface WithY : WithAes {
    public fun <T> y(value: T): PositionalSetting<T> {
        return bindingHandler.addPositionalSetting(X, value)
    }

    public fun <T> y(
        values: DataColumn<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> = bindingHandler.posMapping(Y, values, params)

    public fun <T> y(
        values: KProperty<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> = bindingHandler.posMapping(Y, values, params)

    public fun <T> y(
        values: Iterable<T>, name: String? = null, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> = bindingHandler.posMapping(Y, values, name, params)

    public fun y(column: String, params: EchartsPositionalMappingParametersContinuous<*>.() -> Unit = {}): PositionalMapping<*> =
        bindingHandler.posMapping(Y, column, params)

    public fun <T> y(
        column: ColumnReference<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> = bindingHandler.posMapping(Y, column, params)
}