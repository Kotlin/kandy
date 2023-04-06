/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.data

/**
 * Base table data model. Table is a generalized dataframe -  it has a set of columns. Each value is column
 * can be a single value or a nested table.
 */
public sealed interface TableData
