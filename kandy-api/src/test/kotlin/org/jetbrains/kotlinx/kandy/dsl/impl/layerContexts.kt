/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.impl

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.util.color.Color

internal interface WithX : BindingContext {
    fun <T> x(
        column: ColumnReference<T>,
        parameters: CommonPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return addPositionalMapping(
            X,
            column.name(),
            CommonPositionalMappingParametersContinuous<T>().apply(parameters)
        )
    }
}

internal interface WithY : BindingContext {
    fun <T> y(
        column: ColumnReference<T>,
        parameters: CommonPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return addPositionalMapping(
            Y,
            column.name(),
            CommonPositionalMappingParametersContinuous<T>().apply(parameters)
        )
    }
}

internal interface WithColor : BindingContext {
    var color: Color?
        get() = null
        set(value) {
            bindingCollector.settings[COLOR] = NonPositionalSetting(COLOR, value)
        }

    fun <T> color(
        column: ColumnReference<T>,
        parameters: CommonNonPositionalMappingParametersContinuous<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return addNonPositionalMapping(
            COLOR,
            column.name(),
            CommonNonPositionalMappingParametersContinuous<T, Color>().apply(parameters)
        )
    }
}

internal interface WithSize : BindingContext {
    var size: Double?
        get() = null
        set(value) {
            bindingCollector.settings[SIZE] = NonPositionalSetting(SIZE, value)
        }

    fun <T> size(
        column: ColumnReference<T>,
        parameters: CommonNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return addNonPositionalMapping(
            SIZE,
            column.name(),
            CommonNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }
}

internal interface WithWidth : BindingContext {
    var width: Double?
        get() = null
        set(value) {
            bindingCollector.settings[WIDTH] = NonPositionalSetting(WIDTH, value)
        }
}

internal class PointsContext(parent: LayerCollectorContext) : LayerContext(parent), WithColor, WithSize, WithX, WithY {
    override val requiredAes: Set<Aes> = setOf()
    override val geom: Geom
        get() = POINT
}

internal class LineContext(parent: LayerCollectorContext) : LayerContext(parent), WithWidth, WithColor, WithX, WithY {
    override val requiredAes: Set<Aes> = setOf()
    override val geom: Geom
        get() = LINE
}

internal class BarsContext(parent: LayerCollectorContext) : LayerContext(parent), WithWidth, WithColor, WithX, WithY {
    override val requiredAes: Set<Aes> = setOf()
    override val geom: Geom
        get() = BAR
}