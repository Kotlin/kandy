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

    //@Serializable
    public data class Sequential(override val palette: SequentialPalette? = null) : BrewerType {
        override val name: String = "seq"
    }

    //@Serializable
    public data class Diverging(override val palette: DivergingPalette? = null) : BrewerType {
        override val name: String = "div"
    }

    //@Serializable
    public data class Qualitative(override val palette: QualitativePalette? = null) : BrewerType {
        override val name: String = "qual"
    }
}

public sealed interface ScaleColorBrewer<DomainType> {
    public val limits: List<DomainType?>?
    public val type: BrewerType?

    // todo direction
    public val transform: Transformation?
}

//@Serializable
public data class ScaleContinuousColorBrewer<DomainType>(
    override val limits: List<DomainType?>?,
    override val type: BrewerType?,
    // todo direction
    override val nullValue: Color?,
    override val transform: Transformation?,
) : ContinuousScale<Color>, CustomNonPositionalScale<DomainType, Color>, ScaleColorBrewer<DomainType>

//@Serializable
public data class ScaleCategoricalColorBrewer<DomainType>(
    override val limits: List<DomainType>?,
    override val type: BrewerType?,
    //override val nullValue: TypedValue?,
    // todo direction
) : CategoricalScale, CustomNonPositionalScale<DomainType, Color>, ScaleColorBrewer<DomainType> {
    override val transform: Transformation? = null
}


public inline fun <reified DomainType : Comparable<DomainType>> continuousColorBrewer(
    type: BrewerType?,
    domain: ClosedRange<DomainType>,
    nullValue: Color? = null,
    transform: Transformation? = null
): ScaleContinuousColorBrewer<DomainType> = ScaleContinuousColorBrewer(
    domain.let {
        listOf(it.start, it.endInclusive)
    }, type, nullValue, transform
)

public inline fun <reified DomainType : Comparable<DomainType>> continuousColorBrewer(
    type: BrewerType?,
    domainMin: DomainType? = null,
    domainMax: DomainType? = null,
    nullValue: Color? = null,
    transform: Transformation? = null
): ScaleContinuousColorBrewer<DomainType> = ScaleContinuousColorBrewer(
    listOf(domainMin, domainMax), type, nullValue, transform
)

public inline fun <reified DomainType> categoricalColorBrewer(
    type: BrewerType?,
    domainCategories: List<DomainType>? = null,
    //nullValue: Color? = null,
): ScaleCategoricalColorBrewer<DomainType> = ScaleCategoricalColorBrewer(
    domainCategories, type, //nullValue?
)
