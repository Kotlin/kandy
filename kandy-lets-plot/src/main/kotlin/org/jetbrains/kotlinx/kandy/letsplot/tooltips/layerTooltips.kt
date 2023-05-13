/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.tooltips

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.context.LayerTooltipsContext
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.feature.LayerTooltips


/**
 * Inserts value of given column into formatted string.
 *
 * @param column column whose value will be inserted into the tooltip
 * @return formatted string
 */
public fun LayerContext.value(column: ColumnReference<*>): String {
    return "@${datasetHandler.takeColumn(column.name())}"
}

/**
 * Inserts value of given column into formatted string.
 *
 * @param column column whose value will be inserted into the tooltip
 * @return formatted string
 */
public fun LayerContext.value(column: DataColumn<*>): String {
    return "@${datasetHandler.addColumn(column)}"
}

/**
 * Inserts value of given column into formatted string.
 *
 * @param columnName name of column whose value will be inserted into the tooltip
 * @return formatted string
 */
public fun LayerContext.value(columnName: String): String {
    return "@${datasetHandler.takeColumn(columnName)}"
}

/**
 * Tooltips fixed position.
 */
public data class Anchor(val value: String) {
    public companion object {
        public val TOP_RIGHT: Anchor = Anchor("top_right")
        public val TOP_CENTER: Anchor = Anchor("top_center")
        public val TOP_LEFT: Anchor = Anchor("top_left")
        public val BOTTOM_RIGHT: Anchor = Anchor("bottom_right")
        public val BOTTOM_CENTER: Anchor = Anchor("bottom_center")
        public val BOTTOM_LEFT: Anchor = Anchor("bottom_left")
        public val MIDDLE_RIGHT: Anchor = Anchor("middle_right")
        public val MIDDLE_CENTER: Anchor = Anchor("middle_center")
        public val MIDDLE_LEFT: Anchor = Anchor("middle_left")
    }
}

/**
 * Defines the tooltips format for this layer.
 *
 * Creates a [LayerTooltipsContext]. In this context, you can configure lines of tooltip
 * by using line(..) methods.
 *
 * @see [LayerTooltipsContext].
 *
 * @param variables list of columns to crete a general multiline tooltip with.
 * Useful for specifying the tooltip content quickly, instead of configuring it via the line(..) methods.
 * @param title the string template to use as a title in the multi-line tooltip.
 * @param anchor the fixed position for the general tooltip.
 * @param minWidth minimum width of a general tooltip in pixels.
 * @param hide flag of tooltips displaying.
 * @param formats map of columns to format string of its value.
 * @see value
 */
public inline fun LayerContext.tooltips(
    variables: List<ColumnReference<*>> = listOf(),
    title: String? = null,
    anchor: Anchor? = null,
    minWidth: Double? = null,
    hide: Boolean = false,
    formats: Map<ColumnReference<*>, String> = mapOf(),
    tooltipsContextAction: LayerTooltipsContext.() -> Unit = {}
) {
    features[LayerTooltips.FEATURE_NAME] = LayerTooltips.fromContext(
        variables.map { datasetHandler.takeColumn(it.name()) },
        title,
        anchor,
        minWidth,
        hide,
        formats.map { it.key.name() to it.value },
        LayerTooltipsContext(this).apply(tooltipsContextAction)
    )
}

/**
 * Defines the tooltips format for this layer.
 *
 * @param variables list of column names to crete a general multiline tooltip with.
 * @see value
 */
public fun LayerContext.tooltips(
    vararg variables: String
) {
    features[LayerTooltips.FEATURE_NAME] = LayerTooltips.fromContext(
        variables.map { datasetHandler.takeColumn(it) },
        null,
        null,
        null,
        false,
        listOf(),
        null
    )
}

/**
 * Defines the tooltips format for this layer.
 *
 * @param variables list of columns to crete a general multiline tooltip with.
 * @see value
 */
public fun LayerContext.tooltips(
    vararg variables: ColumnReference<*>
) {
    tooltips(*variables.map { it.name() }.toTypedArray())
}

/**
 * Defines the tooltips format for this layer.
 *
 * @param variables list of columns to crete a general multiline tooltip with.
 * @see value
 */
public fun LayerContext.tooltips(
    vararg variables: DataColumn<*>
) {
    features[LayerTooltips.FEATURE_NAME] = LayerTooltips.fromContext(
        variables.map { datasetHandler.addColumn(it) },
        null,
        null,
        null,
        false,
        listOf(),
        null
    )
}
