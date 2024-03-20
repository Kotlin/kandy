/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.HOLE

/**
 * Interface for configuring the `hole` aesthetic specifically used for pie charts.
 * This interface provides a property for setting the hole size in the middle of the pie chart.
 *
 * Implementing this interface allows the hole size to be set as a [Double].
 * The value should be between 0.0 and 1.0,
 * where 0.0 means no hole and 1.0 would essentially remove the pie, making it invisible.
 */
public interface WithHole : BindingContext {

    /**
     * Sets the size of the hole in the middle of the pie chart.
     *
     * @property hole A [Double] value indicating the size of the hole.
     */
    public var hole: Double?
        get() = null
        set(value) {
            addNonPositionalSetting(HOLE, value)
        }
}
