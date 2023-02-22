package org.jetbrains.kotlinx.ggdsl.ir.data

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference

/**
 * Dataframe-like data, i.e. a set of named columns.
 *
 * @property nameToValues [Map] of columns;
 * keys are the columns names, values are [TypedList] with columns values.
 * @property groupBy performs this dataframe lazy grouping by given columns.
 */
public data class NamedData(public val dataFrame: DataFrame<*>): TableData {

    /**
     * Performs grouping of this dataframe by given columns.
     *
     * @param columnReference pointers to grouping keys columns.
     */
    public fun groupBy(vararg columnReference: ColumnReference<*>): GroupedData {
        return GroupedData(dataFrame.groupBy(*columnReference))
    }
}
