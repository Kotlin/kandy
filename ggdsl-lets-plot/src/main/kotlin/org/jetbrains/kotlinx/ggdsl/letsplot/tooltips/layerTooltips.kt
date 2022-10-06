/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.tooltips

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.Aes
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Stat

public data class LayerTooltips internal constructor(
    val variables: List<String>,
    val lines: List<String>,
    val formats: List<Pair<String, String>>,
    val title: String? = null,
    val anchor: Anchor? = null,
    val minWidth: Double? = null,
    val hide: Boolean = false,
) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("layer_tooltips")


        public fun fromContext(
            variables: List<String>,
            title: String?,
            anchor: Anchor?,
            minWidth: Double?,
            hide: Boolean,
            valueFormats: List<Pair<String, String>>,
            context: LayerTooltipsContext
        ): LayerTooltips {
            return LayerTooltips(
                variables,
                context.lineBuffer,
                valueFormats,
                title, anchor, minWidth, hide
            )
        }
    }
}


/**
 * Inserts value of given [ColumnPointer] into format string.
 *
 * @param source [ColumnPointer] whose value will be inserted into the tooltip
 * @return format string
 */

public fun value(source: ColumnPointer<*>): String {
    return "@${source.id}"
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
public fun value(stat: Stat<*>): String {
    return "@${stat.name}"
}


/**
 * Context created by [LayerContext.tooltips] method.
 */
//@PlotDslMarker
public class LayerTooltipsContext {
    // todo hide
    internal val lineBuffer = mutableListOf<String>()

    /**
     * Adds solid line to tooltips with given string value.
     *
     * @param string text of the line
     * @see value
     */
    public fun line(string: String) {
        lineBuffer.add(string)
    }

    /**
     * Adds two-sided line to tooltips with given string values.
     *
     * @param leftSide text of the left side of line
     * @param rightSide text of the right side of line
     * @see value
     */
    public fun line(leftSide: String? = null, rightSide: String? = null) {
        lineBuffer.add("${leftSide ?: ""}|${rightSide ?: ""}")
    }

    /**
<<<<<<< HEAD
     * Adds standard line for given [ColumnPointer]
     * (name of source on the left side and corresponding value on the right side).
=======
     * Adds standard line for given [DataSource]
     * (name of the source on the left side and corresponding value on the right side).
>>>>>>> main
     *
     * @param source [ColumnPointer]
     */

    public fun line(source: ColumnPointer<*>) {

        lineBuffer.add("@|@${source.id}")
    }

    /**
     * Adds standard line for given aesthetic attribute
     * (name of a source mapped oh this aes on the left side and corresponding value on the right side).
     *
     * @param aes aesthetic attribute
     */
    public fun line(aes: Aes) {
        lineBuffer.add("@|^${aes.name.name}")
    }

    /**
     * Adds standard line for given statistics
     * (name of a source mapped oh this aes on the left side and corresponding value on the right side).
     *
     * @param stat statistics
     */
    public fun line(stat: Stat<*>) {
        lineBuffer.add("${stat.name.drop(2).dropLast(2)}|@${stat.name}")
    }

}

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
 * @param variables vararg of [ColumnPointer] to crete a general multiline tooltip with.
 * Useful for specifying the tooltip content quickly, instead of configuring it via the line(..) methods.
 * @param title the string template to use as a title in the multi-line tooltip.
 * @param anchor the fixed position for the general tooltip.
 * @param minWidth minimum width of a general tooltip in pixels.
 * @param hide flag of tooltips displaying.
 * @param valueFormats map of [ColumnPointer] to format string of its value.
 * @param aesFormats map of [Aes] to format string of its value.
 * The format will be applied to the mapped value in the default tooltip or to the corresponding
 * value specified in the line template.
 * @see value
 */

public inline fun LayerContext.tooltips(
    variables: List<ColumnPointer<*>>,
    title: String? = null,
    anchor: Anchor? = null,
    minWidth: Double? = null,
    hide: Boolean = false,
    valueFormats: Map<ColumnPointer<*>, String> = mapOf(),
    aesFormats: Map<Aes, String> = mapOf(),
    statFormats: Map<Stat<*>, String> = mapOf(),
    tooltipsContextAction: LayerTooltipsContext.() -> Unit
) {
    features[LayerTooltips.FEATURE_NAME] = LayerTooltips.fromContext(
        variables.map { it.id },
        title,
        anchor,
        minWidth,
        hide,
        valueFormats.map { it.key.id to it.value }
            + aesFormats.map { "^" + it.key.name.name to it.value }
            + statFormats.map { it.key.name to it.value },
        LayerTooltipsContext().apply(tooltipsContextAction)
    )
}
