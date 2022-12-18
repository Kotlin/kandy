/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.settings

/**
 * End points of the line.
 *
 * @property BUTT the ends of lines are squared off at the endpoints.
 * @property ROUND the ends of the lines are rounded.
 * @property SQUARE the ends of lines are squared off by adding a box with an equal width and half the height of the line's thickness.
 */
public enum class Cap(internal val type: String) {
    BUTT("butt"), ROUND("round"), SQUARE("square")
}