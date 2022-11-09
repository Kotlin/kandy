/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.GROUP
import org.jetbrains.kotlinx.ggdsl.letsplot.MERGED_GROUPS
import org.jetbrains.kotlinx.ggdsl.letsplot.Reversed
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.LayerTooltips
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.pos.positionIdentity

internal class LayerWrapper internal constructor(private val layer: Layer) :
    LayerBase(
        data = layer.dataset?.wrap(),
        mapping = Options(layer.mappings.map { (_, mapping) -> mapping.wrap() }.toMap().toMutableMap().apply {
            if (layer.dataset is LazyGroupedDataInterface) {
                this[GROUP.name] = MERGED_GROUPS
            }
        } ),
        geom = layer.geom.toLPGeom(),
        stat = layer.geom.toStat(),
        tooltips = (layer.features[LayerTooltips.FEATURE_NAME] as? LayerTooltips)?.wrap(),
        position = (layer.features[Position.FEATURE_NAME] as? Position)?.wrap() ?: positionIdentity,
        showLegend = true,
        orientation = (layer.features[Reversed.FEATURE_NAME] as? Reversed)?.wrap()
    ) {
    // TODO
    override fun seal() = Options(
        layer.settings.map { (_, setting) ->
            setting.wrap()
        }.toMap()
    )
}

