/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.impl

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping

internal fun <T> PlotContext.x(
    column: ColumnReference<T>,
    parameters: CommonPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping(X, column.name(), CommonPositionalMappingParametersContinuous<T>().apply(parameters))
}

internal fun <T> PlotContext.y(
    column: ColumnReference<T>,
    parameters: CommonPositionalMappingParametersContinuous<T>.() -> Unit = {}
): PositionalMapping<T> {
    return addPositionalMapping<T>(Y, column.name(), CommonPositionalMappingParametersContinuous<T>().apply(parameters))
}
