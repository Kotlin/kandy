/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.FATTEN
import org.jetbrains.kotlinx.kandy.letsplot.internal.WHISKER_WIDTH

/**
 * Interface for managing the `whiskerWidth` aesthetic, which is responsible for the length of boxplor whiskers.
 *
 * Implementing this interface allows the aesthetic to be directly set as a numeric value.
 */
public interface WithWhiskerWidth : BindingContext {

    /**
     * Sets the `whiskerWidth` aesthetic, determining the ratio of the length of box ends to the width of the box.
     * This aesthetic is specifically applicable in boxes-based plots (boxplot, candlestick, etc.).
     *
     * @property whiskerWidth a numeric value that represents the ratio of the length of box ends to the width of the box.
     */
    public var whiskerWidth: Double?
        get() = null
        set(value) {
            addNonPositionalSetting(WHISKER_WIDTH, value)
        }
}
