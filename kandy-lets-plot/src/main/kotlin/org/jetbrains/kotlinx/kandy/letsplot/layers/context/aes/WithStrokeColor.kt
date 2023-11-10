/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.STROKE_COLOR
import org.jetbrains.kotlinx.kandy.util.color.Color

/**
 * Interface for managing the `strokeColor` aesthetic.
 *
 * The `strokeColor` aesthetic is used to control the color of the outline or stroke around visual elements.
 * It can be set to constant colors or mapped to a data column for more dynamic rendering.
 *
 * Implementing this interface allows you to either set a constant stroke color or map the `strokeColor` aesthetic
 * to a data column for dynamic control over the visual elements.
 */
public interface WithStrokeColor : BindingContext {

    /**
     * Sets a constant color for the outline or stroke around visual elements.
     *
     * @property strokeColor a [Color] value that represents the color of the stroke.
     */
    public var strokeColor: Color?
        get() = null
        set(value) {
            addNonPositionalSetting(STROKE_COLOR, value)
        }
}
