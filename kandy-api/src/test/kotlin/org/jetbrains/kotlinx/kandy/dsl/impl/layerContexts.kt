/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.impl

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingHandler
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilderImpl
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.util.color.Color

internal interface WithX : WithAes {
    fun <T> x(
        column: ColumnReference<T>,
        parameters: CommonPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping(
            X,
            column.name(),
            CommonPositionalMappingParametersContinuous<T>().apply(parameters)
        )
    }
}

internal interface WithY : WithAes {
    fun <T> y(
        column: ColumnReference<T>,
        parameters: CommonPositionalMappingParametersContinuous<T>.() -> Unit = {}
    ): PositionalMapping<T> {
        return bindingHandler.addPositionalMapping(
            Y,
            column.name(),
            CommonPositionalMappingParametersContinuous<T>().apply(parameters)
        )
    }
}

internal interface WithColor : WithAes {
    var color: Color?
        get() = null
        set(value) {
            bindingHandler.addNonPositionalSetting(COLOR, value)
        }

    fun <T> color(
        column: ColumnReference<T>,
        parameters: CommonNonPositionalMappingParametersContinuous<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> {
        return bindingHandler.addNonPositionalMapping(
            COLOR,
            column.name(),
            CommonNonPositionalMappingParametersContinuous<T, Color>().apply(parameters)
        )
    }
}

internal interface WithSize : WithAes {
    var size: Double?
        get() = null
        set(value) {
            bindingHandler.addNonPositionalSetting(SIZE, value)
        }

    fun <T> size(
        column: ColumnReference<T>,
        parameters: CommonNonPositionalMappingParametersContinuous<T, Double>.() -> Unit = {}
    ): NonPositionalMapping<T, Double> {
        return bindingHandler.addNonPositionalMapping(
            SIZE,
            column.name(),
            CommonNonPositionalMappingParametersContinuous<T, Double>().apply(parameters)
        )
    }
}

internal interface WithWidth : WithAes {
    var width: Double?
        get() = null
        set(value) {
            bindingHandler.addNonPositionalSetting(WIDTH, value)
        }
}

internal class PointsHandler(parent: LayerCreatorScope) : LayerBuilderImpl(parent), WithColor, WithSize, WithX, WithY {
    override val requiredAes: Set<Aes> = setOf()
    override val geom: Geom
        get() = POINT
}

internal class LineHandler(parent: LayerCreatorScope) : LayerBuilderImpl(parent), WithWidth, WithColor, WithX, WithY {
    override val requiredAes: Set<Aes> = setOf()
    override val geom: Geom
        get() = LINE
}

internal class BarsHandler(parent: LayerCreatorScope) : LayerBuilderImpl(parent), WithWidth, WithColor, WithX, WithY {
    override val requiredAes: Set<Aes> = setOf()
    override val geom: Geom
        get() = BAR
}