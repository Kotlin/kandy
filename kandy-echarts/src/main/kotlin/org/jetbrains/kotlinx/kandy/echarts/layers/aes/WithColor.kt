/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.layers.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.echarts.scale.EchartsNonPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.echarts.scale.nonPosMappingCont
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.reflect.KProperty

public interface WithColor : BindingContext {
    public var color: Color?
        get() = null
        set(value) {
            addNonPositionalSetting(COLOR, value)
        }

    public fun <T> color(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParametersContinuous<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = nonPosMappingCont(COLOR, column, params)

    public fun <T> color(
        column: KProperty<T>, params: EchartsNonPositionalMappingParametersContinuous<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = nonPosMappingCont(COLOR, column, params)

    public fun <T> color(
        values: Iterable<T>, name: String? = null, params: EchartsNonPositionalMappingParametersContinuous<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = nonPosMappingCont(COLOR, values, name, params)

    public fun color(
        column: String, params: EchartsNonPositionalMappingParametersContinuous<*, Color>.() -> Unit = {}
    ): NonPositionalMapping<*, Color> = nonPosMappingCont(COLOR, column, params)

    public fun <T> color(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParametersContinuous<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = nonPosMappingCont(COLOR, values, params)
}