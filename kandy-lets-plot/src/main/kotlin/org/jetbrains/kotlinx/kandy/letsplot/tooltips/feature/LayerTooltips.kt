/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.tooltips.feature

import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.Anchor
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.context.LayerTooltipsContext

public data class LayerTooltips internal constructor(
    val variables: List<String>,
    val lines: List<String>?,
    val formats: List<Pair<String, String>>,
    val title: String?,
    val anchor: Anchor?,
    val minWidth: Double?,
    val hide: Boolean,
) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("layer_tooltips")


        @PublishedApi
        internal fun fromContext(
            variables: List<String>,
            title: String?,
            anchor: Anchor?,
            minWidth: Double?,
            hide: Boolean,
            valueFormats: List<Pair<String, String>>,
            context: LayerTooltipsContext?
        ): LayerTooltips {
            return LayerTooltips(
                variables,
                context?.lineBuffer,
                valueFormats,
                title, anchor, minWidth, hide
            )
        }
    }
}