package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.letsPlot.Pos
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.intern.Options
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.ggdsl.letsplot.Reversed
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.LayerTooltips
import org.jetbrains.letsPlot.intern.layer.LayerBase
import org.jetbrains.letsPlot.pos.positionIdentity
/*
internal class LayerWrapper internal constructor(private val layer: Layer) :
    LayerBase(
        data = layer.data,
        mapping = Options(layer.mappings.map { (_, mapping) -> mapping.wrap(layer.geom) }.toMap()),
        // todo handle with shapes
        geom = layer.geom.toLPGeom(),
        stat = Stat.identity,
        tooltips = (layer.features[LayerTooltips.FEATURE_NAME] as? LayerTooltips)?.wrap(),
        position = (layer.features[Position.FEATURE_NAME] as? Position)?.wrap() ?: positionIdentity,
        showLegend = true,
        orientation = (layer.features[Reversed.FEATURE_NAME] as? Reversed)?.wrap()
    ) {
    // TODO
    override fun seal() = Options(
        layer.settings.map {
            // TODO(Other settings?)
                (_, setting) ->
            (setting as NonPositionalSetting<*>).wrap(layer.geom)
        }.toMap()
    )
}

 */
