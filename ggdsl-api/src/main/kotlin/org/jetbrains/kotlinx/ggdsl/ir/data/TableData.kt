package org.jetbrains.kotlinx.ggdsl.ir.data

/**
 * TableData :=
 *    Table t, Ind i:
 *    t[i] := Value | TableData
 */

public sealed interface TableData
