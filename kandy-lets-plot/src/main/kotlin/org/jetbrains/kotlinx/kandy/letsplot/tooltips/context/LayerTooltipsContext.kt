/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.tooltips.context

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.tooltips
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.value

/**
 * Context created by [LayerContext.tooltips] methods.
 */
public class LayerTooltipsContext(private val layerContext: LayerContext) {
    // todo hide
    internal val lineBuffer = mutableListOf<String>()

    /**
     * Adds solid line to tooltips with given string value.
     *
     * @param string text of the line
     * @see value
     */
    public fun line(string: String) {
        lineBuffer.add(string)
    }

    /**
     * Adds two-sided line to tooltips with given string values.
     *
     * @param leftSide text of the left side of line
     * @param rightSide text of the right side of line
     * @see value
     */
    public fun line(leftSide: String? = null, rightSide: String? = null) {
        lineBuffer.add("${leftSide ?: ""}|${rightSide ?: ""}")
    }

    /**
     * Adds standard line for given column.
     * (name of the column on the left side and the corresponding value on the right side).
     *
     * @param column column whose value will be displayed.
     */
    public fun line(column: ColumnReference<*>) {
        lineBuffer.add("@|@${layerContext.datasetHandler.takeColumn(column.name())}")
    }

    /**
     * Adds standard line for given column.
     * (name of the column on the left side and the corresponding value on the right side).
     *
     * @param column column whose value will be displayed.
     */
    public fun line(column: DataColumn<*>) {
        lineBuffer.add("@|@${layerContext.datasetHandler.addColumn(column)}")
    }

    /**
     * Adds standard line for given column.
     * (name of the column on the left side and the corresponding value on the right side).
     *
     * @param columnName name of column whose values will be displayed.
     */
    public fun varLine(columnName: String) {
        lineBuffer.add("@|@${layerContext.datasetHandler.takeColumn(columnName)}")
    }

    /**
     * Adds standard line for given column.
     * (name of the column on the left side and the corresponding value on the right side).
     *
     * @param column column whose value will be displayed.
     */
    public fun varLine(column: ColumnReference<*>) {
        line(column)
    }

    /**
     * Adds standard line for given column.
     * (name of the column on the left side and the corresponding value on the right side).
     *
     * @param column column whose value will be displayed.
     */
    public fun varLine(column: DataColumn<*>) {
        line(column)
    }

}