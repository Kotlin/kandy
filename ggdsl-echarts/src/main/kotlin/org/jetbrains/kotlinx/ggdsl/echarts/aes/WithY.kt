/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.echarts.scale.EchartsPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.echarts.scale.posMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalSetting
import kotlin.reflect.KProperty

public interface WithY : BindingContext {
    public fun <T> y(value: T): PositionalSetting<T> {
        return addPositionalSetting(X, value)
    }

    public fun <T> y(
        values: DataColumn<T>, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
    ): PositionalMapping<T> = posMapping(Y, values, params)

    public fun <T> y(
        values: KProperty<T>, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
    ): PositionalMapping<T> = posMapping(Y, values, params)

    public fun <T> y(
        values: Iterable<T>, name: String? = null, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
    ): PositionalMapping<T> = posMapping(Y, values, name, params)

    public fun y(column: String, params: EchartsPositionalMappingParameters<*>.() -> Unit = {}): PositionalMapping<*> =
        posMapping(Y, column, params)

    public fun <T> y(
        column: ColumnReference<T>, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
    ): PositionalMapping<T> = posMapping(Y, column, params)
}