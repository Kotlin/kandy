package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import jetbrains.letsPlot.Pos
import jetbrains.letsPlot.Stat
import jetbrains.letsPlot.intern.Options
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.ggdsl.letsplot.position.POSITION_FEATURE_NAME

class LayerWrapper internal constructor(private val layer: Layer) :
    jetbrains.letsPlot.intern.layer.LayerBase(
        data = layer.data,
        mapping = Options(layer.mappings.map { (_, mapping) -> mapping.wrap(layer.geom) }.toMap()),
        // todo handle with shapes
        geom = layer.geom.toLPGeom(/*!(layer.settings.containsKey(SYMBOL) || layer.mappings.containsKey(SYMBOL))*/
        ),
        stat = Stat.identity,
        position = layer.features[POSITION_FEATURE_NAME]?.wrap() ?: Pos.identity, // TODO(Ok?)
        showLegend = true,
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
