/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.tooltips

import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.Aes
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Stat

data class LayerTooltips internal constructor(
    val variables: List<String>,
    val lines: List<String>,
    val formats: List<Pair<String, String>>,
    val title: String? = null,
    val anchor: Anchor? = null,
    val minWidth: Double? = null,
    val hide: Boolean = false,
) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    companion object {
        val FEATURE_NAME = FeatureName("layer_tooltips")


        fun fromContext(
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
 * Inserts value of given [DataSource] into format string.
 *
 * @param source [DataSource] whose value will be inserted into the tooltip
 * @return format string
 */
fun value(source: DataSource<*>): String {
    return "@${source.id}"
}

/**
 * Inserts value of given aesthetic attribute into format string.
 *
 * @param aes aesthetic attribute whose value will be inserted into the tooltip
 * @return format string
 */
fun value(aes: Aes): String {
    return "^${aes.name.name}"
}

/**
 * Inserts value of given statistics into format string.
 *
 * @param stat statistics whose value will be inserted into the tooltip
 * @return format string
 */
fun value(stat: Stat<*>): String {
    return "@${stat.name}"
}


/**
 * Context created by [LayerContext.tooltips] method.
 */
//@PlotDslMarker
class LayerTooltipsContext {
    // todo hide
    internal val lineBuffer = mutableListOf<String>()

    /**
     * Adds solid line to tooltips with given string value.
     *
     * @param string text of the line
     * @see value
     */
    fun line(string: String) {
        lineBuffer.add(string)
    }

    /**
     * Adds two-sided line to tooltips with given string values.
     *
     * @param leftSide text of the left side of line
     * @param rightSide text of the right side of line
     * @see value
     */
    fun line(leftSide: String? = null, rightSide: String? = null) {
        lineBuffer.add("${leftSide ?: ""}|${rightSide ?: ""}")
    }

    /**
     * Adds standard line for given [DataSource]
     * (name of source on the left side and corresponding value on the right side).
     *
     * @param source [DataSource]
     */
    fun line(source: DataSource<*>) {
        lineBuffer.add("@|@${source.id}")
    }

    /**
     * Adds standard line for given aesthetic attribute
     * (name of source mapped oh this aes on the left side and corresponding value on the right side).
     *
     * @param aes aesthetic attribute
     */
    fun line(aes: Aes) {
        lineBuffer.add("@|^${aes.name.name}")
    }

    /**
     * Adds standard line for given statistics
     * (name of source mapped oh this aes on the left side and corresponding value on the right side).
     *
     * @param stat statistics
     */
    fun line(stat: Stat<*>) {
        lineBuffer.add("${stat.name.drop(2).dropLast(2)}|@${stat.name}")
    }

}

data class Anchor(val value: String) {
    companion object {
        val TOP_RIGHT = Anchor("top_right")
        val TOP_CENTER = Anchor("top_center")
        val TOP_LEFT = Anchor("top_left")
        val BOTTOM_RIGHT = Anchor("bottom_right")
        val BOTTOM_CENTER = Anchor("bottom_center")
        val BOTTOM_LEFT = Anchor("bottom_left")
        val MIDDLE_RIGHT = Anchor("middle_right")
        val MIDDLE_CENTER = Anchor("middle_center")
        val MIDDLE_LEFT = Anchor("middle_left")
    }
}

/**
 * Defines the format for displaying values of this layer.
 *
 * Creates a [LayerTooltipsContext]. In this context you can configure lines of tooltip
 * by using line(..) methods.
 *
 * @see [LayerTooltipsContext].
 *
 * @param variables vararg of [DataSource] to crete a general multiline tooltip with.
 * Useful for specifying the tooltip content quickly, instead of configuring it via the line(..) methods.
 * @param title the string template to use as a title in the multi-line tooltip.
 * @param anchor the fixed position for the general tooltip.
 * @param minWidth minimum width of a general tooltip in pixels.
 * @param hide flag of tooltips displaying.
 * @param valueFormats map of [DataSource] to format string of its value.
 * @param aesFormats map of [Aes] to format string of its value.
 * The format will be applied to the mapped value in the default tooltip or to the corresponding
 * value specified in the line template.
 * @see value
 */
inline fun LayerContext.tooltips(
    variables: List<DataSource<*>>,
    title: String? = null,
    anchor: Anchor? = null,
    minWidth: Double? = null,
    hide: Boolean = false,
    valueFormats: Map<DataSource<*>, String> = mapOf(),
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
