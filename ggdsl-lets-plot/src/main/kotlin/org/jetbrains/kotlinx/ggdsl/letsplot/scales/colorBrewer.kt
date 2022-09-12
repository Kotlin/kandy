/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.scales

import org.jetbrains.kotlinx.ggdsl.ir.scale.CategoricalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.ContinuousScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.CustomNonPositionalScale
import org.jetbrains.kotlinx.ggdsl.util.color.Color

sealed interface Palette {
    val name: String
}

enum class SequentialPalette: Palette {
    Blues, BuGn, BuPu, GnBu, Greens, Greys, Oranges, OrRd, PuBu, PuBuGn,
    PuRd, Purples, RdPu, Reds, YlGn, YlGnBu, YlOrBr, YlOrRd
}

enum class DivergingPalette: Palette {
    BrBG, PiYG, PRGn, PuOr, RdBu, RdGy, RdYlBu, RdYlGn, Spectral
}

enum class QualitativePalette: Palette {
    Accent, Dark2, Paired, Pastel1, Pastel2, Set1, Set2, Set3
}

sealed interface BrewerType{
    val palette: Palette?
    val name: String
    data class Sequential(override val palette: SequentialPalette? = null): BrewerType {
        override val name: String = "seq"
    }
    data class Diverging(override val palette: DivergingPalette? = null): BrewerType {
        override val name: String = "div"
    }
    data class Qualitative(override val palette: QualitativePalette? = null): BrewerType {
        override val name: String = "qual"
    }
}

sealed interface ScaleColorBrewer<DomainType: Any>{
    val limits: List<DomainType>?
    val type: BrewerType?
    // todo direction
    val transform: Transformation?
}

data class ScaleContinuousColorBrewer<DomainType: Any>(
    override val limits: List<DomainType>?,
    override val type: BrewerType?,
    // todo direction
    override val transform: Transformation?,
) : ContinuousScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorBrewer<DomainType>

data class ScaleCategoricalColorBrewer<DomainType: Any>(
    override val limits: List<DomainType>?,
    override val type: BrewerType?,
    // todo direction
) : CategoricalScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorBrewer<DomainType> {
    override val transform: Transformation? = null
}


fun<DomainType: Any> continuousColorBrewer(
    type: BrewerType?,
    domainLimits: Pair<DomainType, DomainType>? = null,
    transform: Transformation? = null
) = ScaleContinuousColorBrewer(
    domainLimits?.toList(), type, transform
)

fun<DomainType: Any> categoricalColorBrewer(
    type: BrewerType?,
    domainCategories: List<DomainType>? = null,
) = ScaleCategoricalColorBrewer(
    domainCategories, type
)
