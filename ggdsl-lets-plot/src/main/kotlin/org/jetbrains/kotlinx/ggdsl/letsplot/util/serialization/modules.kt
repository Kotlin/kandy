package org.jetbrains.kotlinx.ggdsl.letsplot.util.serialization

import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.modules.*
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.ir.scale.ScaleParameters
import org.jetbrains.kotlinx.ggdsl.ir.scale.Transform
import org.jetbrains.kotlinx.ggdsl.letsplot.CoordFlip
import org.jetbrains.kotlinx.ggdsl.letsplot.Layout
import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.letsplot.Reversed
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.FacetGridFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.FacetWrapFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.NonPositionalParameters
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.PositionalParameters
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.Transformation
import org.jetbrains.kotlinx.ggdsl.letsplot.series.GatheringList
import org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.LayerTooltips

public val letsPlotModules: SerializersModule
    get() = SerializersModule() {
        polymorphic(PlotFeature::class) {
            subclass(FacetGridFeature::class)
            subclass(FacetWrapFeature::class)
            subclass(GatheringList::class)
            subclass(Layout::class)
            subclass(CoordFlip::class)
        }
        polymorphic(LayerFeature::class) {
           // subclass(Position::class)
            subclass(Reversed::class)
            subclass(LayerTooltips::class)
            subclass(Position.Identity::class)
            subclass(Position.Stack::class)
            subclass(Position.Dodge::class)
            subclass(Position.Nudge::class)
            subclass(Position.JitterDodge::class)
            subclass(Position.Jitter::class)
        }
       // polymorphic(Position::class)
        contextual(PositionalParameters::class) {args -> PositionalParameters.serializer(args[0])}
        contextual(NonPositionalParameters::class) {args -> NonPositionalParameters.serializer(args[0], args[1])}
        polymorphic(ScaleParameters::class) {
          //  subclass(LetsPlotScaleParameters::class)
            subclass(PositionalParameters.serializer(PolymorphicSerializer(Any::class)))
            subclass(NonPositionalParameters.serializer(PolymorphicSerializer(Any::class), PolymorphicSerializer(Any::class)))
        }
        polymorphic(Transform::class) {
            subclass(Transformation::class)
        }
        polymorphic(Geom::class) {
            subclass(LetsPlotGeom::class)
        }
    }
