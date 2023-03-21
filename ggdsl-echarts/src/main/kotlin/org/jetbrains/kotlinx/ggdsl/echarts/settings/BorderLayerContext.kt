/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.settings

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.echarts.aes.BORDER_COLOR
import org.jetbrains.kotlinx.ggdsl.echarts.aes.BORDER_TYPE
import org.jetbrains.kotlinx.ggdsl.echarts.aes.BORDER_WIDTH
import org.jetbrains.kotlinx.ggdsl.echarts.layers.BarContextImmutable
import org.jetbrains.kotlinx.ggdsl.echarts.layers.PointContextImmutable
import org.jetbrains.kotlinx.ggdsl.echarts.scale.EchartsNonPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.echarts.scale.nonPosMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.util.color.Color

/**
 * Adds [border][BorderLayerContext] settings to [bars][org.jetbrains.kotlinx.ggdsl.echarts.layers.bars].
 */
public fun BarContextImmutable.border(block: BorderLayerContext.() -> Unit) {
    BorderLayerContext(this).apply(block)
}

/**
 * Adds [border][BorderLayerContext] settings to [points][org.jetbrains.kotlinx.ggdsl.echarts.layers.points].
 */
public fun PointContextImmutable.border(block: BorderLayerContext.() -> Unit) {
    BorderLayerContext(this).apply(block)
}

/**
 * Border context with aesthetic attribute properties.
 *
 * @property color border [color][Color].
 * @property width border width. By default `0`.
 * @property type border [type][LineType]. By default `solid`.
 * @property radius border radius. By default `0`.
 *
 * @see Color
 * @see LineType
 */
public class BorderLayerContext(private val context: BindingContext) {
    public var color: Color? = null
        set(value) {
            context.addNonPositionalSetting(BORDER_COLOR, value)
        }

    public fun <T> color(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BORDER_COLOR, column, params)

    public fun <T> color(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BORDER_COLOR, values, name, params)

    public fun <T> color(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMapping(BORDER_COLOR, values, params)


    public var width: Number? = null
        set(value) {
            context.addNonPositionalSetting(BORDER_WIDTH, value)
        }

    public fun <T> width(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BORDER_WIDTH, column, params)

    public fun <T> width(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BORDER_WIDTH, values, name, params)

    public fun <T> width(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BORDER_WIDTH, values, params)


    public var type: LineType? = null
        set(value) {
            context.addNonPositionalSetting(BORDER_TYPE, value)
        }

    public fun <T> type(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> = context.nonPosMapping(BORDER_TYPE, column, params)

    public fun <T> type(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> = context.nonPosMapping(BORDER_TYPE, values, name, params)

    public fun <T> type(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> = context.nonPosMapping(BORDER_TYPE, values, params)

    public var radius: LineType? = null
        set(value) {
            context.addNonPositionalSetting(BORDER_TYPE, value)
        }

    public fun <T> radius(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BORDER_TYPE, column, params)

    public fun <T> radius(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BORDER_TYPE, values, name, params)

    public fun <T> radius(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMapping(BORDER_TYPE, values, params)
}
