/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers


import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom

/* TODO
@PublishedApi

 */
public val HISTOGRAM: LetsPlotGeom = LetsPlotGeom("histogram")
/*

@PlotDslMarker
// todo move x/y?
class HistogramContext(
    parent: LayerCollectorContext,
=======
public val HISTOGRAM: LetsPlotGeom = LetsPlotGeom("histogram")


@PlotDslMarker
// todo move x/y?
public class HistogramContext(
    override var data: MutableNamedData,
>>>>>>> main
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
    public val fillColor: FillAes = FillAes(this)
    public val borderLineColor: ColorAes = ColorAes(this)
    public val borderLineWidth: SizeAes = SizeAes(this)

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
inline fun <reified T : Any> PlotContext.histogram(
    source: ColumnPointer<T>,
=======
public inline fun <reified T : Any> PlotContext.histogram(
    source: DataSource<T>,
>>>>>>> main
    bins: Bins? = null,
    boundary: Double? = null,
    center: Double? = null,
    block: HistogramContext.() -> Unit
) {
    layers.add(
        HistogramContext(data, bins, boundary, center)
            .apply {
                copyFrom(this@histogram)
                _x(source)
            }
            .apply(block)
            .toLayer(HISTOGRAM)
    )
}

public inline fun <reified T : Any> PlotContext.histogram(
    source: Iterable<T>,
    bins: Bins? = null,
    boundary: Double? = null,
    center: Double? = null,
    block: HistogramContext.() -> Unit
) {
    layers.add(
        HistogramContext(data, bins, boundary, center)
            .apply {
                copyFrom(this@histogram)
                _x(source)
            }
            .apply(block)
            .toLayer(HISTOGRAM)
    )
}


 */

