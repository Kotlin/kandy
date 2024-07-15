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

public interface WithX : WithAes {
    public fun <T> x(value: T): PositionalSetting<T> {
        return bindingHandler.addPositionalSetting(X, value)
    }

    public fun <T> x(
        column: ColumnReference<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> = bindingHandler.posMapping(X, column, params)

    public fun <T> x(
        column: KProperty<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> = bindingHandler.posMapping(X, column, params)

    public fun <T> x(
        values: Iterable<T>, name: String? = null, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> = bindingHandler.posMapping(X, values, name, params)

    public fun x(column: String, params: EchartsPositionalMappingParametersContinuous<*>.() -> Unit = {}): PositionalMapping<*> =
        bindingHandler.posMapping(X, column, params)

    public fun <T> x(
        values: DataColumn<T>, params: EchartsPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> = bindingHandler.posMapping(X, values, params)
}