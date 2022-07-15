package org.jetbrains.kotlinx.ggdsl.letsplot.tooltips

import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.Aes
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

data class LayerTooltips(
    val variables: List<DataSource<*>>,
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
            variables: List<DataSource<*>>,
            title: String? = null,
            anchor: Anchor? = null,
            minWidth: Double? = null,
            hide: Boolean = false,
            context: LayerTooltipsContext
        ): LayerTooltips {
            return LayerTooltips(
                variables,
                context.lineBuffer,
                context.formatBuffer,
                title, anchor, minWidth, hide
            )
        }
    }
}

fun value(aes: Aes): String {
    return "^${aes.name}"
}

fun value(source: DataSource<*>): String {
    return "@${source.id}"
}


class LayerTooltipsContext {
    val lineBuffer = mutableListOf<String>()
    val formatBuffer = mutableListOf<Pair<String, String>>()

    fun line(string: String) {
        lineBuffer.add(string)
    }

    fun line(leftSide: String? = null, rightSide: String? = null) {
        lineBuffer.add("${leftSide ?: ""}|${rightSide ?: ""}")
    }

    fun lineDefault(source: DataSource<*>) {
        lineBuffer.add("@|@${source.id}")
    }

    fun lineDefault(aes: Aes) {
        lineBuffer.add("@|^${aes.name}")
    }

    // todo type????
    fun format(source: DataSource<*>, template: String) {
        formatBuffer.add(source.id to template)
    }

    fun format(aes: Aes, template: String) {
        formatBuffer.add("^${aes.name}" to template)
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

inline fun LayerContext.tooltips(
    vararg variables: DataSource<*>,
    title: String? = null,
    anchor: Anchor? = null,
    minWidth: Double? = null,
    hide: Boolean = false,
    tooltipsContextAction: LayerTooltipsContext.() -> Unit
) {
    features[LayerTooltips.FEATURE_NAME] = LayerTooltips.fromContext(
        variables.toList(),
        title,
        anchor,
        minWidth,
        hide,
        LayerTooltipsContext().apply(tooltipsContextAction)
    )
}