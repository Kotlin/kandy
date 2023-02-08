/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.letsplot.feature.Reversed
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.GROUP
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.MERGED_GROUPS
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.feature.LayerTooltips
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.pos.positionIdentity
import org.jetbrains.letsPlot.sampling.samplingNone

internal class LayerWrapper internal constructor(private val layer: Layer, addGroups: Boolean) :
    LayerBase(
        data = layer.dataset?.wrap(),
        mapping = Options(layer.mappings.map { (_, mapping) -> mapping.wrap() }.toMap().toMutableMap().apply {
            if (addGroups) {
                this[GROUP.name] = MERGED_GROUPS
            }
        } ),
        geom = layer.geom.wrap(),
        stat = Stat.identity,
        tooltips = (layer.features[LayerTooltips.FEATURE_NAME] as? LayerTooltips)?.wrap(),
        position = (layer.features[Position.FEATURE_NAME] as? Position)?.wrap() ?: positionIdentity,
        showLegend = true,
        orientation = (layer.features[Reversed.FEATURE_NAME] as? Reversed)?.wrap(),
        sampling = samplingNone
    ) {
    // TODO
    override fun seal() = Options(
        layer.settings.map { (_, setting) ->
            setting.wrap()
        }.toMap()
    )
}
