package org.jetbrains.kotlinx.ggdsl.letsplot.layers


import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom

/* TODO
@PublishedApi

 */
val FREQPOLY = LetsPlotGeom("freqpoly")
/*

@PlotDslMarker
// todo move x/y?
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
    internal val _x = XAes(this)

    val x = XDummyAes(this)

    val y = YAes(this)

    val alpha = AlphaAes(this)
    val lineColor = ColorAes(this)
    val lineType = LineTypeAes(this)
    val lineWidth = SizeAes(this)

    object Statistics {
        val X = BinStat.X
        val COUNT = BinStat.Count
        val DENSITY = BinStat.Density
    }

    val Stat = Statistics

    // todo weight

    @PublishedApi
    internal val center = CenterAes(this)

    @PublishedApi
    internal val boundary = BoundaryAes(this)


    inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
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

    inline operator fun <reified DomainType : Any, RangeType : Any> MappableNonPositionalAes<RangeType>.invoke(
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

inline fun <reified T : Any> PlotContext.freqPoly(
    source: ColumnPointer<T>,
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

inline fun <reified T : Any> PlotContext.freqPoly(
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

