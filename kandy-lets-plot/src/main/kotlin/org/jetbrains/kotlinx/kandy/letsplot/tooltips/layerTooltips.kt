/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package org.jetbrains.kotlinx.kandy.letsplot.tooltips

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.datasetBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.layerFeatures
import org.jetbrains.kotlinx.kandy.letsplot.internal.datasetBuilderImpl
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.context.LayerTooltipsContext
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.feature.LayerTooltips
import kotlin.reflect.KProperty


/**
 * Inserts value of given column into formatted string.
 *
 * @param column column whose value will be inserted into the tooltip
 * @return formatted string
 */
public fun LayerBuilder.value(column: ColumnReference<*>): String {
    @Suppress("INVISIBLE_MEMBER")
    return "@${datasetBuilderImpl.addColumn(column)}"
}

/**
 * Inserts value of given column into formatted string.
 *
 * @param columnName name of column whose value will be inserted into the tooltip
 * @return formatted string
 */
public fun LayerBuilder.value(columnName: String): String {
    @Suppress("INVISIBLE_MEMBER")
    return "@${datasetBuilder.takeColumn(columnName)}"
}

/**
 * Inserts value of given column into formatted string.
 *
 * @param column name of column whose value will be inserted into the tooltip
 * @return formatted string
 */
public fun LayerBuilder.value(column: KProperty<*>): String {
    @Suppress("INVISIBLE_MEMBER")
    return "@${datasetBuilder.takeColumn(column.name)}"
}

/**
 * Hides tooltips. Applies to all kinds of tooltips: axis, side and general tooltips.
 *
 * @param enable If `true` (by default), tooltips are displayed. If `false`, tooltips are not displayed.
 */
public fun LayerBuilder.tooltips(
    enable: Boolean = true,
) {
    @Suppress("INVISIBLE_MEMBER")
    layerFeatures[LayerTooltips.FEATURE_NAME] = LayerTooltips(
        listOf(),
        null,
        listOf(),
        null, null, null,
        enable,
    )
}

/**
 * Defines the tooltips format for this layer.
 *
 * Creates a [LayerTooltipsContext]. In this context, you can configure lines of tooltip
 * by using line(…) methods.
 *
 * @see [LayerTooltipsContext].
 *
 * @param variables list of columns to create a general multiline tooltip with.
 * Useful for specifying the tooltip content quickly, instead of configuring it via the line(…) methods.
 * @param title the string template to use as a title in the multi-line tooltip.
 * @param anchor the fixed position for the general tooltip.
 * @param minWidth minimum width of a general tooltip in pixels.
 * @param formats map of columns to format string of its value.
 * @see value
 */
public inline fun LayerBuilder.tooltips(
    vararg variables: ColumnReference<*>,
    formats: Map<ColumnReference<*>, String> = mapOf(),
    title: String? = null,
    anchor: Anchor? = null,
    minWidth: Double? = null,
    tooltipsContextAction: LayerTooltipsContext.() -> Unit
) {
    @Suppress("INVISIBLE_MEMBER")
    layerFeatures[LayerTooltips.FEATURE_NAME] = LayerTooltips.fromContext(
        variables.map { datasetBuilderImpl.addColumn(it) },
        title,
        anchor,
        minWidth,
        enable = true,
        formats.map { (column, format) -> datasetBuilder.takeColumn(column.name()) to format },
        LayerTooltipsContext(this).apply(tooltipsContextAction)
    )
}

/**
 * Defines the tooltips format for this layer.
 *
 * @param variables list of column names to create a general multiline tooltip with.
 * @param formats map of columns to format string of its value.
 * @param title the string template to use as a title in the multi-line tooltip.
 * @param anchor the fixed position for the general tooltip.
 * @param minWidth minimum width of a general tooltip in pixels.
 * @see value
 */
public fun LayerBuilder.tooltips(
    variable: String,
    vararg variables: String,
    formats: Map<String, String> = mapOf(),
    title: String? = null,
    anchor: Anchor? = null,
    minWidth: Double? = null,
) {
    @Suppress("INVISIBLE_MEMBER")
    layerFeatures[LayerTooltips.FEATURE_NAME] = LayerTooltips(
        (listOf(variable) + variables.toList()).map { datasetBuilder.takeColumn(it) },
        null,
        formats.toList(),
        title, anchor, minWidth, enable = true
    )
}

/**
 * Defines the tooltips format for this layer.
 *
 * @param variables list of columns to create a general multiline tooltip with.
 * @param formats map of columns to format string of its value.
 * @param title the string template to use as a title in the multi-line tooltip.
 * @param anchor the fixed position for the general tooltip.
 * @param minWidth minimum width of a general tooltip in pixels.
 * @see value
 */
public fun LayerBuilder.tooltips(
    variable: ColumnReference<*>,
    vararg variables: ColumnReference<*>,
    formats: Map<ColumnReference<*>, String> = mapOf(),
    title: String? = null,
    anchor: Anchor? = null,
    minWidth: Double? = null,
) {
    @Suppress("INVISIBLE_MEMBER")
    layerFeatures[LayerTooltips.FEATURE_NAME] = LayerTooltips(
        (listOf(variable) + variables.toList()).map { datasetBuilderImpl.addColumn(it) },
        null,
        formats.map { (column, format) -> datasetBuilder.takeColumn(column.name()) to format },
        title,
        anchor,
        minWidth,
        enable = true
    )
}


/**
 * Defines the tooltips format for this layer.
 *
 * @param variables list of column names to create a general multiline tooltip with.
 * @param formats map of columns to format string of its value.
 * @param title the string template to use as a title in the multi-line tooltip.
 * @param anchor the fixed position for the general tooltip.
 * @param minWidth minimum width of a general tooltip in pixels.
 * @see value
 */
public fun LayerBuilder.tooltips(
    variable:KProperty<*>,
    vararg variables: KProperty<*>,
    formats: Map<KProperty<*>, String> = mapOf(),
    title: String? = null,
    anchor: Anchor? = null,
    minWidth: Double? = null,
) {
    @Suppress("INVISIBLE_MEMBER")
    layerFeatures[LayerTooltips.FEATURE_NAME] = LayerTooltips(
        (listOf(variable) + variables.toList()).map { datasetBuilder.takeColumn(it.name) },
        null,
        formats.map { (property, format) ->  datasetBuilder.takeColumn(property.name) to format },
        title, anchor, minWidth, enable = true
    )
}
