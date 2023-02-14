/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.tooltips

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.dsl.Aes
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.Statistic
import org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.context.LayerTooltipsContext
import org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.feature.LayerTooltips


/**
 * Inserts value of given [ColumnReference] into format string.
 *
 * @param source [ColumnReference] whose value will be inserted into the tooltip
 * @return format string
 */

public fun value(source: ColumnReference<*>): String {
    return "@${source.name}"
}

/**
 * Inserts value of given aesthetic attribute into format string.
 *
 * @param aes aesthetic attribute whose value will be inserted into the tooltip
 * @return format string
 */
public fun value(aes: Aes): String {
    return "^${aes.name.name}"
}

/**
 * Inserts value of given statistics into format string.
 *
 * @param stat statistics whose value will be inserted into the tooltip
 * @return format string
 */
public fun value(stat: Statistic<*>): String {
    return "@${stat.id}"
}

/**
 * Tooltips fixed position.
 */
//@Serializable
public data class Anchor(val value: String) {
    public companion object {
        public val TOP_RIGHT: Anchor = Anchor("top_right")
        public val TOP_CENTER: Anchor = Anchor("top_center")
        public val TOP_LEFT: Anchor = Anchor("top_left")
        public val BOTTOM_RIGHT: Anchor = Anchor("bottom_right")
        public val BOTTOM_CENTER: Anchor = Anchor("bottom_center")
        public val BOTTOM_LEFT: Anchor = Anchor("bottom_left")
        public val MIDDLE_RIGHT: Anchor = Anchor("middle_right")
        public val MIDDLE_CENTER: Anchor = Anchor("middle_center")
        public val MIDDLE_LEFT: Anchor = Anchor("middle_left")
    }
}

/**
 * Defines the format for displaying values of this layer.
 *
 * Creates a [LayerTooltipsContext]. In this context, you can configure lines of tooltip
 * by using line(..) methods.
 *
 * @see [LayerTooltipsContext].
 *
 * @param variables list of [ColumnReference] to crete a general multiline tooltip with.
 * Useful for specifying the tooltip content quickly, instead of configuring it via the line(..) methods.
 * @param title the string template to use as a title in the multi-line tooltip.
 * @param anchor the fixed position for the general tooltip.
 * @param minWidth minimum width of a general tooltip in pixels.
 * @param hide flag of tooltips displaying.
 * @param valueFormats map of [ColumnReference] to format string of its value.
 * @param aesFormats map of [Aes] to format string of its value.
 * The format will be applied to the mapped value in the default tooltip or to the corresponding
 * value specified in the line template.
 * @see value
 */
public inline fun LayerContextInterface.tooltips(
    variables: List<ColumnReference<*>>,
    title: String? = null,
    anchor: Anchor? = null,
    minWidth: Double? = null,
    hide: Boolean = false,
    valueFormats: Map<ColumnReference<*>, String> = mapOf(),
    aesFormats: Map<Aes, String> = mapOf(),
    statFormats: Map<Statistic<*>, String> = mapOf(),
    tooltipsContextAction: LayerTooltipsContext.() -> Unit
) {
    features[LayerTooltips.FEATURE_NAME] = LayerTooltips.fromContext(
        variables.map { it.name },
        title,
        anchor,
        minWidth,
        hide,
        valueFormats.map { it.key.name to it.value }
            + aesFormats.map { "^" + it.key.name.name to it.value }
            + statFormats.map { it.key.id to it.value },
        LayerTooltipsContext().apply(tooltipsContextAction)
    )
}
