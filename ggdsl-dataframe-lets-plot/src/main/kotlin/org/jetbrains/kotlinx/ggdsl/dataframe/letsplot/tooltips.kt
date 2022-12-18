/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dsl.Aes
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.Statistic
import org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.Anchor
import org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.context.LayerTooltipsContext
import org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.feature.LayerTooltips
import org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.value

/**
 * Inserts value of given column into format string.
 *
 * @param column column whose value will be inserted into the tooltip
 * @return format string
 */
public fun value(column: ColumnReference<*>): String {
    return "@${column.name()}"
}

/**
 * Adds standard line for given column
 * (name of this column on the left side and corresponding value on the right side).
 *
 * @param column
 */
public fun LayerTooltipsContext.line(column: ColumnReference<*>) {
    line("@|@${column.name()}")
}

/**
 * Defines the format for displaying values of this layer.
 *
 * Creates a [LayerTooltipsContext]. In this context, you can configure lines of tooltip
 * by using line(..) methods.
 *
 * @see [LayerTooltipsContext].
 *
 * @param columns list of [ColumnReference] to crete a general multiline tooltip with.
 * Useful for specifying the tooltip content quickly, instead of configuring it via the line(..) methods.
 * @param title the string template to use as a title in the multi-line tooltip.
 * @param anchor the fixed position for the general tooltip.
 * @param minWidth minimum width of a general tooltip in pixels.
 * @param hide flag of tooltips displaying.
 * @param columnsFormats map of [ColumnReference] to format string of its value.
 * @param aesFormats map of [Aes] to format string of its value.
 * The format will be applied to the mapped value in the default tooltip or to the corresponding
 * value specified in the line template.
 * @see value
 */
public inline fun LayerContextInterface.tooltips(
    columns: List<ColumnReference<*>> = listOf(),
  //  variablesDS: List<ColumnPointer<*>> = listOf(),
    title: String? = null,
    anchor: Anchor? = null,
    minWidth: Double? = null,
    hide: Boolean = false,
  //  dsFormats: Map<ColumnPointer<*>, String> = mapOf(),
    columnsFormats: Map<ColumnReference<*>, String> = mapOf(),
    aesFormats: Map<Aes, String> = mapOf(),
    statFormats: Map<Statistic<*>, String> = mapOf(),
    tooltipsContextAction: LayerTooltipsContext.() -> Unit
) {
    features[LayerTooltips.FEATURE_NAME] = LayerTooltips.fromContext(
        columns.map { it.name() } /*+ variablesDS.map { it.name }*/,
        title,
        anchor,
        minWidth,
        hide,
        /*dsFormats.map { it.key.name to it.value }
            +*/ columnsFormats.map { it.key.name() to it.value }
            + aesFormats.map { "^" + it.key.name.name to it.value }
            + statFormats.map { it.key.id to it.value },
        LayerTooltipsContext().apply(tooltipsContextAction)
    )
}
