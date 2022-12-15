/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features.marks

/**
 * Mark type. Used in [mark point][MarkPoint] and [mark line][MarkLine].
 *
 * @property MAX maximum value
 * @property MIN minimum value
 * @property MAX average value
 *
 *
 * @see MarkPoint
 * @see MarkLine
 */
public enum class MarkType(public val type: String) {
    MAX("max"), MIN("min"), AVERAGE("average")
}