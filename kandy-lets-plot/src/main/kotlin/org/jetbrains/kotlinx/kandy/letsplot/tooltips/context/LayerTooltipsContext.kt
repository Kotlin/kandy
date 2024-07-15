/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.tooltips.context

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.datasetBuilder
import org.jetbrains.kotlinx.kandy.letsplot.internal.datasetBuilderImpl
import kotlin.reflect.KProperty

/**
 * Context created by [org.jetbrains.kotlinx.kandy.letsplot.tooltips.tooltips] methods.
 */
public class LayerTooltipsContext(private val LayerBuilder: LayerBuilder) {
    internal val lineBuffer = mutableListOf<String>()
    internal val formatsBuffer = mutableMapOf<String, String>()

    /**
     * Adds solid line to tooltips with given string value.
     *
     * @param string text of the line.
     */
    public fun line(string: String) {
        lineBuffer.add(string)
    }

    /**
     * Inserts value of given column into formatted string.
     *
     * @receiver property with a name of column whose value will be inserted into the tooltip.
     * @param format value format.
     * @return formatted string.
     */
    public fun KProperty<*>.tooltipValue(format: String? = null): String {
        @Suppress("INVISIBLE_MEMBER")
        val colID = LayerBuilder.datasetBuilder.takeColumn(this.name)
        addFormat(colID, format)
        return "@$colID"
    }

    /**
     * Inserts value of given column into formatted string.
     *
     * @receiver name of column whose value will be inserted into the tooltip.
     * @param format value format.
     * @return formatted string.
     */
    public fun String.tooltipValue(format: String? = null): String {
        @Suppress("INVISIBLE_MEMBER")
        val colID = (LayerBuilder.datasetBuilder).takeColumn(this)
        addFormat(colID, format)
        return "@$colID"
    }

    /**
     * Inserts value of given column into formatted string.
     *
     * @receiver column whose value will be inserted into the tooltip.
     * @param format value format.
     * @return formatted string.
     */
    public fun ColumnReference<*>.tooltipValue(format: String? = null): String {
        @Suppress("INVISIBLE_MEMBER")
        val colID = LayerBuilder.datasetBuilderImpl.addColumn(this)
        addFormat(colID, format)
        return "@$colID"
    }

    /**
     * Adds two-sided line to tooltips with given string values.
     *
     * @param leftSide text of the left side of line
     * @param rightSide text of the right side of line
     */
    public fun line(leftSide: String? = null, rightSide: String? = null) {
        lineBuffer.add("${leftSide ?: ""}|${rightSide ?: ""}")
    }

    private fun addVarLine(columnName: String) {
        lineBuffer.add("@|@{$columnName}")
    }

    private fun addFormat(columnName: String, format: String?) {
        format?.let {
            formatsBuffer[columnName] = it
        }
    }

    /**
     * Adds standard line for the given column
     * (name of the column on the left side and the corresponding value on the right side).
     *
     * @param column column whose value will be displayed.
     */
    public fun line(column: ColumnReference<*>, format: String? = null) {
        @Suppress("INVISIBLE_MEMBER")
        addVarLine(LayerBuilder.datasetBuilderImpl.addColumn(column).also {
            addFormat(it, format)
        })
    }

    /**
     * Adds standard line for given column.
     * (Name of the column on the left side and the corresponding value on the right side).
     *
     * @param property property with the name of column whose value will be displayed.
     */
    public fun line(property: KProperty<*>, format: String? = null) {
        @Suppress("INVISIBLE_MEMBER")
        addVarLine(LayerBuilder.datasetBuilder.takeColumn(property.name).also {
            addFormat(it, format)
        })
    }

    /**
     * Adds standard line for the given column
     * (name of the column on the left side and the corresponding value on the right side).
     *
     * @param columnName name of column whose value will be displayed.
     */
    public fun varLine(columnName: String, format: String? = null) {
        @Suppress("INVISIBLE_MEMBER")
        addVarLine(LayerBuilder.datasetBuilder.takeColumn(columnName).also {
            addFormat(it, format)
        })
    }

    /**
     * Adds standard line for the given column
     * (name of the column on the left side and the corresponding value on the right side).
     *
     * @param property property with the name of column whose value will be displayed.
     */
    public fun varLine(property: KProperty<*>, format: String? = null) {
        line(property, format)
    }

    /**
     * Adds standard line for the given column
     * (name of the column on the left side and the corresponding value on the right side).
     *
     * @param column column whose value will be displayed.
     */
    public fun varLine(column: ColumnReference<*>, format: String? = null) {
        line(column, format)
    }

}