/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.impl

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.util.color.Color

interface WithX : BindingContext {
    fun <T> x(value: T): PositionalSetting<T> {
        return addPositionalSetting(X, value)
    }

    fun <T> x(
        column: ColumnReference<T>,
        parameters: CommonPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(X, column.name(), CommonPositionalMappingParametersContinuous<T>().apply(parameters))
    }
}

interface WithY : BindingContext {
    fun <T> y(value: T): PositionalSetting<T> {
        return addPositionalSetting(Y, value)
    }

    fun <T> y(
        column: ColumnReference<T>,
        parameters: CommonPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(Y, column.name(), CommonPositionalMappingParametersContinuous<T>().apply(parameters))
    }
}

interface WithColor : BindingContext {
    var color: Color?
        get() = null
        set(value) {
            bindingCollector.settings[COLOR] = NonPositionalSetting(COLOR, value)
        }
    fun <T> color(
        column: ColumnReference<T>,
        parameters: CommonNonPositionalMappingParametersContinuous<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return addNonPositionalMapping<T, Color>(
            COLOR,
            column.name(),
            CommonNonPositionalMappingParametersContinuous<T, Color>().apply(parameters)
        )
    }
}

interface WithSize : BindingContext {
    var size: Double?
        get() = null
        set(value) {
            bindingCollector.settings[SIZE] = NonPositionalSetting(SIZE, value)
        }
    fun <T> size(
        column: ColumnReference<T>,
        parameters: CommonNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping<T, Double>(
            SIZE,
            column.name(),
            CommonNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }
}

interface WithWidth : BindingContext {
    var width: Double?
        get() = null
        set(value) {
            bindingCollector.settings[WIDTH] = NonPositionalSetting(WIDTH, value)
        }
    fun <T> width(
        column: ColumnReference<T>,
        parameters: CommonNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping<T, Double>(
            WIDTH,
            column.name(),
            CommonNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }
}

class PointsContext(parent: LayerCollectorContext) : LayerContext(parent), WithColor, WithSize, WithX, WithY {
    override val requiredAes: Set<AesName> = setOf()
}

class LineContext(parent: LayerCollectorContext) : LayerContext(parent), WithWidth, WithColor, WithX, WithY {
    override val requiredAes: Set<AesName> = setOf()
}

class BarsContext(parent: LayerCollectorContext) : LayerContext(parent), WithWidth, WithColor, WithX, WithY {
    override val requiredAes: Set<AesName> = setOf()
}