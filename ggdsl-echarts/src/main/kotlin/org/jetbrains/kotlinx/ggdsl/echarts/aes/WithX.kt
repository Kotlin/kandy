/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.dataframe.impl.asList
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.echarts.scale.EchartsPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalSetting

public interface WithX : BindingContext {
    public fun <T> x(value: T): PositionalSetting<T> {
        return addPositionalSetting(X, value)
    }

    public fun <T> x(
        column: ColumnReference<T>, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
    ): PositionalMapping<T> =
        addPositionalMapping(X, column.name(), EchartsPositionalMappingParameters<T>().apply(params))

    public fun <T> x(
        values: Iterable<T>, name: String? = null, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
    ): PositionalMapping<T> =
        addPositionalMapping(X, values.asList(), name, EchartsPositionalMappingParameters<T>().apply(params))

    public fun <T> x(
        values: DataColumn<T>, params: EchartsPositionalMappingParameters<T>.() -> Unit = {}
    ): PositionalMapping<T> = addPositionalMapping(X, values, EchartsPositionalMappingParameters<T>().apply(params))
}