package org.jetbrains.kotlinx.kandy.letsplot.tooltips.context

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext

/**
 * Context created by [LayerContext.tooltips] method.
 */
///*@PlotDslMarker*/
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
     * Adds standard line for given [ColumnReference]
     * (name of the column on the left side and the corresponding value on the right side).
     *
     * @param source [ColumnReference]
     */

    public fun line(column: ColumnReference<*>) {
        lineBuffer.add("@|@${layerContext.datasetHandler.takeColumn(column.name())}")
    }

    /**
     * Adds standard line for given aesthetic attribute
     * (name of the column mapped oh this aes on the left side and the corresponding value on the right side).
     *
     * @param aes aesthetic attribute
     */
    /* TODO
    public fun line(aes: Aes) {
        lineBuffer.add("@|^${aes.name.name}")
    }

     */

    /* TODO
    /**
     * Adds standard line for given statistics
     * (name of the source mapped oh this aes on the left side and the corresponding value on the right side).
     *
     * @param stat a statistic to display
     */
    public fun line(stat: Statistic<*>) {
        lineBuffer.add("${stat.id.drop(2).dropLast(2)}|@${stat.id}")
    }


     */
}