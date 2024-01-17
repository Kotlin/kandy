/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.FATTEN
import org.jetbrains.kotlinx.kandy.letsplot.internal.WHISKER_WIDTH

/**
 * Interface for managing the `fatten` aesthetic, which is particularly useful for controlling
 * the size of elements in specific types of plots like box plots, crossbars, and inner points.
 *
 * Implementing this interface allows the aesthetic to be directly set as a numeric value.
 */
public interface WithWhiskerWidth : BindingContext {

    /**
     * Sets the `fatten` aesthetic, determining the thickness or size factor for specific elements.
     * This aesthetic is specifically applicable in plot types like box plots, crossbars, and inner points.
     *
     * @property fatten a numeric value that represents the factor by which the elements in those specific plot types should be _fattened_.
     */
    public var whiskerWidth: Double?
        get() = null
        set(value) {
            addNonPositionalSetting(WHISKER_WIDTH, value)
        }
}
