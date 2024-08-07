@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.kandy.letsplot.internal.SPACER_COLOR
import org.jetbrains.kotlinx.kandy.util.color.Color

/**
 * Interface for managing the `spacerColor` aesthetic.
 *
 * Affects the color for spacers between sectors. Spacers are not applied
 * to exploded sectors and to sides of adjacent sectors.
 */
public interface WithSpacerColor : WithAes {

    /**
     *
     * @property stroke a numeric value that represents the spacer color.
     */
    public var spacerColor: Color?
        get() = null
        set(value) {
            bindingHandler.addNonPositionalSetting(SPACER_COLOR, value)
        }

}