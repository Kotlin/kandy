package org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.feature

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.Anchor
import org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.context.LayerTooltipsContext

@Serializable
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