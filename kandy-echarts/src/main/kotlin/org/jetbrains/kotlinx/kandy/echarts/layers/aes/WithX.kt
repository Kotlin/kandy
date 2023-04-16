/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.layers.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.echarts.scale.EchartsPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.echarts.scale.posMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import kotlin.reflect.KProperty

public interface WithX : BindingContext {
    public fun <T> x(value: T): PositionalSetting<T> {
        return addPositionalSetting(X, value)
    }

    public fun <T> x(
        column: ColumnReference<T>, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
    ): PositionalMapping<T> = posMapping(X, column, params)

    public fun <T> x(
        column: KProperty<T>, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
    ): PositionalMapping<T> = posMapping(X, column, params)

    public fun <T> x(
        values: Iterable<T>, name: String? = null, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
    ): PositionalMapping<T> = posMapping(X, values, name, params)

    public fun x(column: String, params: EchartsPositionalMappingParameters<*>.() -> Unit = {}): PositionalMapping<*> =
        posMapping(X, column, params)

    public fun <T> x(
        values: DataColumn<T>, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
    ): PositionalMapping<T> = posMapping(X, values, params)
}