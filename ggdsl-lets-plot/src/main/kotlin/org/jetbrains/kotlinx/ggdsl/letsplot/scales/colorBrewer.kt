/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.scales

import org.jetbrains.kotlinx.ggdsl.ir.scale.CategoricalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.ContinuousScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.CustomNonPositionalScale
import org.jetbrains.kotlinx.ggdsl.util.color.Color

public sealed interface Palette {
    public val name: String
}

public enum class SequentialPalette : Palette {
    Blues, BuGn, BuPu, GnBu, Greens, Greys, Oranges, OrRd, PuBu, PuBuGn,
    PuRd, Purples, RdPu, Reds, YlGn, YlGnBu, YlOrBr, YlOrRd
}

public enum class DivergingPalette : Palette {
    BrBG, PiYG, PRGn, PuOr, RdBu, RdGy, RdYlBu, RdYlGn, Spectral
}

public enum class QualitativePalette : Palette {
    Accent, Dark2, Paired, Pastel1, Pastel2, Set1, Set2, Set3
}

public sealed interface BrewerType {
    public val palette: Palette?
    public val name: String

    public data class Sequential(override val palette: SequentialPalette? = null) : BrewerType {
        override val name: String = "seq"
    }

    public data class Diverging(override val palette: DivergingPalette? = null) : BrewerType {
        override val name: String = "div"
    }

    public data class Qualitative(override val palette: QualitativePalette? = null) : BrewerType {
        override val name: String = "qual"
    }
}

public sealed interface ScaleColorBrewer<DomainType : Any> {
    public val limits: List<DomainType>?
    public val type: BrewerType?

    // todo direction
    public val transform: Transformation?
}

public data class ScaleContinuousColorBrewer<DomainType : Any>(
    override val limits: List<DomainType>?,
    override val type: BrewerType?,
    // todo direction
    override val transform: Transformation?,
) : ContinuousScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorBrewer<DomainType>

public data class ScaleCategoricalColorBrewer<DomainType : Any>(
    override val limits: List<DomainType>?,
    override val type: BrewerType?,
    // todo direction
) : CategoricalScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorBrewer<DomainType> {
    override val transform: Transformation? = null
}


public fun <DomainType : Any> continuousColorBrewer(
    type: BrewerType?,
    domainLimits: Pair<DomainType, DomainType>? = null,
    transform: Transformation? = null
): ScaleContinuousColorBrewer<DomainType> = ScaleContinuousColorBrewer(
    domainLimits?.toList(), type, transform
)

public fun <DomainType : Any> categoricalColorBrewer(
    type: BrewerType?,
    domainCategories: List<DomainType>? = null,
): ScaleCategoricalColorBrewer<DomainType> = ScaleCategoricalColorBrewer(
    domainCategories, type
)
