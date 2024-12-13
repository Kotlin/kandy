/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.translator

import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.Reversed
import org.jetbrains.kotlinx.kandy.letsplot.internal.GROUP
import org.jetbrains.kotlinx.kandy.letsplot.internal.MERGED_GROUPS
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.feature.LayerTooltips
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.WithSpatialParameters
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.sampling.samplingNone
import org.jetbrains.letsPlot.spatial.SpatialDataset

internal class LayerWrapper internal constructor(
    private val layer: Layer, addGroups: Boolean,
    dataset: Map<String, List<*>>?,
    mappings: Map<Aes, Mapping>,
    private val settings: Map<Aes, Setting>,
    groupKeys: List<String>?,
    override val map: SpatialDataset? = null,
    override val mapJoin: Pair<Any, Any>? = null,
    override val useCRS: String? = null,
) :
    org.jetbrains.letsPlot.intern.Layer(
        data = dataset,
        // TODO(https://github.com/Kotlin/kandy/issues/420)
        mapping = Options(mappings.map { (_, mapping) -> mapping.wrap(groupKeys) }.toMap().toMutableMap().apply {
            if (addGroups) {
                this[GROUP.name] = MERGED_GROUPS
            }
        }),
        geom = layer.geom.wrap(),
        stat = Stat.identity,
        tooltips = (layer.features[LayerTooltips.FEATURE_NAME] as? LayerTooltips)?.wrap(),
        position = (layer.features[Position.FEATURE_NAME] as? Position)?.wrap() ?: positionIdentity,
        showLegend = true,
        orientation = (layer.features[Reversed.FEATURE_NAME] as? Reversed)?.wrap(),
        sampling = samplingNone
    ), WithSpatialParameters {
    override fun seal() = Options(
        settings.map { (_, setting) ->
            setting.wrap()
        }.toMap()
    )
}
