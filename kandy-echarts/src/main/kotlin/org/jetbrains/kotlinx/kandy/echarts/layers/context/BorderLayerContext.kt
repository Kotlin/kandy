/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.echarts.layers.context

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingHandler
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.BORDER_COLOR
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.BORDER_TYPE
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.BORDER_WIDTH
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.bindingHandler
import org.jetbrains.kotlinx.kandy.echarts.scale.EchartsNonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.echarts.scale.nonPosMappingCont
import org.jetbrains.kotlinx.kandy.echarts.settings.LineType
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.reflect.KProperty

/**
 * Adds [border][BorderLayerBuilder] settings to [bars][org.jetbrains.kotlinx.kandy.echarts.layers.bars].
 */
public fun BarHandler.border(block: BorderLayerBuilder.() -> Unit) {
    BorderLayerBuilder(bindingHandler).apply(block)
}

/**
 * Adds [border][BorderLayerBuilder] settings to [points][org.jetbrains.kotlinx.kandy.echarts.layers.points].
 */
public fun PointHandler.border(block: BorderLayerBuilder.() -> Unit) {
    BorderLayerBuilder(bindingHandler).apply(block)
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
public class BorderLayerBuilder @PublishedApi internal constructor(private val context: BindingHandler) {
    public var color: Color? = null
        set(value) {
            context.addNonPositionalSetting(BORDER_COLOR, value)
        }

    public fun <T> color(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMappingCont(BORDER_COLOR, column, params)

    public fun <T> color(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMappingCont(BORDER_COLOR, column, params)

    public fun color(
        column: String, params: EchartsNonPositionalMappingParameters<*, Color>.() -> Unit = {}
    ): NonPositionalMapping<*, Color> = context.nonPosMappingCont(BORDER_COLOR, column, params)

    public fun <T> color(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMappingCont(BORDER_COLOR, values, name, params)

    public fun <T> color(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Color>.() -> Unit = {}
    ): NonPositionalMapping<T, Color> = context.nonPosMappingCont(BORDER_COLOR, values, params)


    public var width: Number? = null
        set(value) {
            context.addNonPositionalSetting(BORDER_WIDTH, value)
        }

    public fun <T> width(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMappingCont(BORDER_WIDTH, column, params)

    public fun <T> width(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMappingCont(BORDER_WIDTH, column, params)

    public fun width(
        column: String, params: EchartsNonPositionalMappingParameters<*, Number>.() -> Unit = {}
    ): NonPositionalMapping<*, Number> = context.nonPosMappingCont(BORDER_WIDTH, column, params)

    public fun <T> width(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMappingCont(BORDER_WIDTH, values, name, params)

    public fun <T> width(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMappingCont(BORDER_WIDTH, values, params)


    public var type: LineType? = null
        set(value) {
            context.addNonPositionalSetting(BORDER_TYPE, value)
        }

    public fun <T> type(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> = context.nonPosMappingCont(BORDER_TYPE, column, params)

    public fun <T> type(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> = context.nonPosMappingCont(BORDER_TYPE, column, params)

    public fun type(
        column: String, params: EchartsNonPositionalMappingParameters<*, LineType>.() -> Unit = {}
    ): NonPositionalMapping<*, LineType> = context.nonPosMappingCont(BORDER_TYPE, column, params)

    public fun <T> type(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> = context.nonPosMappingCont(BORDER_TYPE, values, name, params)

    public fun <T> type(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, LineType>.() -> Unit = {}
    ): NonPositionalMapping<T, LineType> = context.nonPosMappingCont(BORDER_TYPE, values, params)

    public var radius: LineType? = null
        set(value) {
            context.addNonPositionalSetting(BORDER_TYPE, value)
        }

    public fun <T> radius(
        column: ColumnReference<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMappingCont(BORDER_TYPE, column, params)

    public fun <T> radius(
        column: KProperty<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMappingCont(BORDER_TYPE, column, params)

    public fun radius(
        column: String, params: EchartsNonPositionalMappingParameters<*, Number>.() -> Unit = {}
    ): NonPositionalMapping<*, Number> = context.nonPosMappingCont(BORDER_TYPE, column, params)

    public fun <T> radius(
        values: Iterable<T>, name: String? = null, params: NonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMappingCont(BORDER_TYPE, values, name, params)

    public fun <T> radius(
        values: DataColumn<T>, params: EchartsNonPositionalMappingParameters<T, Number>.() -> Unit = {}
    ): NonPositionalMapping<T, Number> = context.nonPosMappingCont(BORDER_TYPE, values, params)
}
