@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.kandy.letsplot.internal.COLOR
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
public interface WithStrokeColor : WithAes {

    /**
     * Sets a constant color for the outline or stroke around visual elements.
     *
     * @property strokeColor a [Color] value that represents the color of the stroke.
     */
    public var strokeColor: Color?
        get() = null
        set(value) {
            bindingHandler.addNonPositionalSetting(COLOR, value)
        }
}
