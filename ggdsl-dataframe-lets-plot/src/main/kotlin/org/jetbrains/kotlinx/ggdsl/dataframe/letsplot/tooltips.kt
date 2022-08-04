package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.LayerTooltipsContext

/**
 * Inserts value of given column into format string.
 *
 * @param column column whose value will be inserted into the tooltip
 * @return format string
 */
fun value(column: ColumnReference<*>): String {
    return "@${column.name()}"
}

/**
 * Adds standard line for given column
 * (name of this column on the left side and corresponding value on the right side).
 *
 * @param column
 */
fun LayerTooltipsContext.line(column: ColumnReference<*>) {
    lineBuffer.add("@|@${column.name()}")
}

