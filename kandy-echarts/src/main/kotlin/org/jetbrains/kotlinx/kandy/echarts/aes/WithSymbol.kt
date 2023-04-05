/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.echarts.scale.EchartsNonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.echarts.scale.nonPosMapping
import org.jetbrains.kotlinx.kandy.echarts.settings.Symbol
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import kotlin.reflect.KProperty

public interface WithSymbol : BindingContext {
    public var symbol: Symbol?
        get() = null
        set(value) {
            addNonPositionalSetting(SYMBOL, value)
        }

    public fun <T> symbol(
        column: ColumnReference<T>, parameters: EchartsNonPositionalMappingParameters<T, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<T, Symbol> = nonPosMapping(SYMBOL, column, parameters)

    public fun <T> symbol(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<T, Symbol> = nonPosMapping(SYMBOL, column, params)

    public fun <T> symbol(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<T, Symbol> = nonPosMapping(SYMBOL, values, name, params)

    public fun symbol(
        column: String, params: EchartsNonPositionalMappingParameters<*, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<*, Symbol> = nonPosMapping(SYMBOL, column, params)

    public fun <T> symbol(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<T, Symbol> = nonPosMapping(SYMBOL, values, params)
}