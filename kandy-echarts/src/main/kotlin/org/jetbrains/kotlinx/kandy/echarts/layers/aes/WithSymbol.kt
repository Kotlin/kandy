/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.echarts.layers.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.echarts.scale.EchartsNonPositionalMappingParametersCategorical
import org.jetbrains.kotlinx.kandy.echarts.scale.nonPosMappingCat
import org.jetbrains.kotlinx.kandy.echarts.settings.Symbol
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import kotlin.reflect.KProperty

public interface WithSymbol : WithAes {
    public var symbol: Symbol?
        get() = null
        set(value) {
            bindingHandler.addNonPositionalSetting(SYMBOL, value)
        }

    public fun <T> symbol(
        column: ColumnReference<T>,
        parameters: EchartsNonPositionalMappingParametersCategorical<T, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<T, Symbol> = bindingHandler.nonPosMappingCat(SYMBOL, column, parameters)

    public fun <T> symbol(
        column: KProperty<T>, params: EchartsNonPositionalMappingParametersCategorical<T, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<T, Symbol> = bindingHandler.nonPosMappingCat(SYMBOL, column, params)

    public fun <T> symbol(
        values: Iterable<T>,
        name: String? = null,
        params: EchartsNonPositionalMappingParametersCategorical<T, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<T, Symbol> = bindingHandler.nonPosMappingCat(SYMBOL, values, name, params)

    public fun symbol(
        column: String, params: EchartsNonPositionalMappingParametersCategorical<*, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<*, Symbol> = bindingHandler.nonPosMappingCat(SYMBOL, column, params)

    public fun <T> symbol(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParametersCategorical<T, Symbol>.() -> Unit = {}
    ): NonPositionalMapping<T, Symbol> = bindingHandler.nonPosMappingCat(SYMBOL, values, params)
}