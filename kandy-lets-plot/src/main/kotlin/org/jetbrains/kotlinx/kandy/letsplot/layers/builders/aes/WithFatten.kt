/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.kandy.letsplot.internal.FATTEN

/**
 * Interface for managing the `fatten` aesthetic, which is particularly useful for controlling
 * the size of elements in specific types of plots like box plots, crossbars, and inner points.
 *
 * Implementing this interface allows the aesthetic to be directly set as a numeric value.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithFatten : WithAes {

    /**
     * Sets the `fatten` aesthetic, determining the thickness or size factor for specific elements.
     * This aesthetic is specifically applicable in plot types like box plots, crossbars, and inner points.
     *
     * @property fatten a numeric value that represents the factor by which the elements in those specific plot types should be _fattened_.
     */
    public var fatten: Double?
        get() = null
        set(value) {
            bindingHandler.addNonPositionalSetting(FATTEN, value)
        }
}
