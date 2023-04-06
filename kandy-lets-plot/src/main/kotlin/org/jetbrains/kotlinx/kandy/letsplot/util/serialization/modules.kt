/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.util.serialization
/*
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.modules.*
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.ir.scale.ScaleParameters
import org.jetbrains.kotlinx.kandy.ir.scale.Transform
import org.jetbrains.kotlinx.kandy.letsplot.feature.CoordFlip
import org.jetbrains.kotlinx.kandy.letsplot.feature.Layout
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.kandy.letsplot.feature.Reversed
import org.jetbrains.kotlinx.kandy.letsplot.facet.feature.FacetGridFeature
import org.jetbrains.kotlinx.kandy.letsplot.facet.feature.FacetWrapFeature
import org.jetbrains.kotlinx.kandy.letsplot.position.Position
import org.jetbrains.kotlinx.kandy.letsplot.scales.NonPositionalParameters
import org.jetbrains.kotlinx.kandy.letsplot.scales.PositionalParameters
import org.jetbrains.kotlinx.kandy.letsplot.scales.Transformation
import org.jetbrains.kotlinx.kandy.letsplot.series.GatheringList
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.feature.LayerTooltips

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


 */