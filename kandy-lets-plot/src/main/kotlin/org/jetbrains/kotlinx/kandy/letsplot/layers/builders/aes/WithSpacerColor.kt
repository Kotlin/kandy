@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.addNonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.letsplot.internal.SPACER_COLOR
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.reflect.KProperty

/**
 * Interface for managing the `spacerColor` aesthetic.
 *
 * Affects the color for spacers between sectors. Spacers are not applied
 * to exploded sectors and to sides of adjacent sectors.
 */
@Suppress("INVISIBLE_MEMBER")
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