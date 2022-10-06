/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers


import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom

/* TODO
@PublishedApi

 */
public val FREQPOLY: LetsPlotGeom = LetsPlotGeom("freqpoly")
/*
=======
public val FREQPOLY: LetsPlotGeom = LetsPlotGeom("freqpoly")

>>>>>>> main

@PlotDslMarker
public// todo move x/y?
class FreqpolyContext(
    parent: LayerCollectorContext,
    bins: Bins?,
    boundary: Double?,
    center: Double?,
) : WithBinsContext(bins) {
    init {
        boundary?.let {
            boundary(it)
        }
        center?.let {
            center(it)
        }
    }

    @PublishedApi
    internal val _x: XAes = XAes(this)

    public val x: XDummyAes = XDummyAes(this)

    public val y: YAes = YAes(this)

    public val alpha: AlphaAes = AlphaAes(this)
    public val lineColor: ColorAes = ColorAes(this)
    public val lineType: LineTypeAes = LineTypeAes(this)
    public val lineWidth: SizeAes = SizeAes(this)

    public object Statistics {
        public val X: BinStat.X = BinStat.X
        public val COUNT: BinStat.Count = BinStat.Count
        public val DENSITY: BinStat.Density = BinStat.Density
    }

    public val Stat: Statistics = Statistics

    // todo weight

    @PublishedApi
    internal val center: CenterAes = CenterAes(this)

    @PublishedApi
    internal val boundary: BoundaryAes = BoundaryAes(this)


    public inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
        stat: BinStat<DomainType>
    ): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
        val mapping = ScaledUnspecifiedDefaultPositionalMapping(
            this.name,
            stat.toDataSource().scaled(),
            typeOf<DomainType>()
        )
        context.bindingCollector.mappings[this.name] = mapping
        return mapping
    }

    public inline operator fun <reified DomainType : Any, RangeType : Any> MappableNonPositionalAes<RangeType>.invoke(
        stat: BinStat<DomainType>
    ): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
        val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
            this.name,
            stat.toDataSource().scaled(),
            typeOf<DomainType>()
        )
        context.bindingCollector.mappings[this.name] = mapping
        return mapping
    }

}

<<<<<<< HEAD
inline fun <reified T : Any> PlotContext.freqPoly(
    source: ColumnPointer<T>,
=======
public inline fun <reified T : Any> PlotContext.freqPoly(
    source: DataSource<T>,
>>>>>>> main
    bins: Bins? = null,
    boundary: Double? = null,
    center: Double? = null,
    block: FreqpolyContext.() -> Unit
) {
    layers.add(
        FreqpolyContext(data, bins, boundary, center)
            .apply {
                copyFrom(this@freqPoly)
                _x(source)
            }
            .apply(block)
            .toLayer(FREQPOLY)
    )
}

public inline fun <reified T : Any> PlotContext.freqPoly(
    source: Iterable<T>,
    bins: Bins? = null,
    boundary: Double? = null,
    center: Double? = null,
    block: FreqpolyContext.() -> Unit
) {
    layers.add(
        FreqpolyContext(data, bins, boundary, center)
            .apply {
                copyFrom(this@freqPoly)
                _x(source)
                boundary?.let {
                    boundary(it)
                }
                center?.let {
                    center(it)
                }
            }
            .apply(block)
            .toLayer(FREQPOLY)
    )
}


 */

