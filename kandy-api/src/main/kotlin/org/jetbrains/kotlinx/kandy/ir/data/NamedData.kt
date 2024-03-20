/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.data

import org.jetbrains.kotlinx.dataframe.DataFrame

/**
 * A wrapper for a "simple" dataframe, which is a two-dimensional table of data
 * with named columns potentially of different data types.
 * In essence, it represents data in a format akin to a spreadsheet, SQL table, or a dictionary of objects.
 *
 * @property dataFrame the underlying [dataframe][DataFrame] being wrapped.
 */
public data class NamedData(public val dataFrame: DataFrame<*>) : TableData
