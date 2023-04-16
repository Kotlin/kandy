/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.impl

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting

fun <T> PlotContext.x(value: T): PositionalSetting<T> {
    return addPositionalSetting(X, value)
}

fun <T> PlotContext.x(column: ColumnReference<T>, parameters: CommonPositionalMappingParameters<T>.() -> Unit = {}): PositionalMapping<T> {
    return addPositionalMapping<T>(X, column.name(), CommonPositionalMappingParameters<T>().apply(parameters))
}


fun <T> PlotContext.y(value: T): PositionalSetting<T> {
    return addPositionalSetting(Y, value)
}

fun <T> PlotContext.y(column: ColumnReference<T>, parameters: CommonPositionalMappingParameters<T>.() -> Unit = {}): PositionalMapping<T> {
    return addPositionalMapping<T>(Y, column.name(), CommonPositionalMappingParameters<T>().apply(parameters))
}