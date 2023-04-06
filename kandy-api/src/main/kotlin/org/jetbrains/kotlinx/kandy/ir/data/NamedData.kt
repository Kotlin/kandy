/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.data

import org.jetbrains.kotlinx.dataframe.DataFrame

/**
 * "Simple dataframe": a set of named typed columns. Types of columns are simple,
 * i.e. not columns or dataframes.
 *
 * @property dataFrame underlying [DataFrame].
 */
public data class NamedData(public val dataFrame: DataFrame<*>): TableData
