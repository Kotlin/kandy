/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.kandy.letsplot.internal.STROKE

/**
 * Interface for managing the `stroke` aesthetic.
 *
 * The `stroke` aesthetic is primarily used in pie plots to control the thickness of the outline around each slice.
 * It can be set to numerical values to indicate the width of the stroke.
 *
 * Implementing this interface allows you to either set a constant stroke thickness or map the
 * `stroke` aesthetic to a data column for dynamic control over the pie slice outlines.
 */
@Suppress("INVISIBLE_MEMBER")
public interface WithStroke : WithAes {

    /**
     * Sets a constant stroke width for the outline around each slice in a pie plot.
     *
     * @property stroke a numeric value that represents the width of the stroke.
     */
    public var stroke: Double?
        get() = null
        set(value) {
            bindingHandler.addNonPositionalSetting(STROKE, value)
        }
}
