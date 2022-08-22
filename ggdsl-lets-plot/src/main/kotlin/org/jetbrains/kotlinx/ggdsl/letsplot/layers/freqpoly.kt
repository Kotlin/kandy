package org.jetbrains.kotlinx.ggdsl.letsplot.layers


import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.ScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.BinStat
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Bins
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.WithBinsContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.toDataSource
import kotlin.reflect.typeOf

/* TODO
@PublishedApi

 */
val FREQPOLY = LetsPlotGeom("freqpoly")


@PlotDslMarker
// todo move x/y?
class FreqpolyContext(
    override var data: MutableNamedData,
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
    internal val x = XAes(this)
    val y = YAes(this)

    val alpha = AlphaAes(this)
    val lineColor = ColorAes(this)
    val lineType = LineTypeAes(this)
    val lineWidth = SizeAes(this)

    object Statistics {
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
    source: DataSource<T>,
    bins: Bins? = null,
    boundary: Double? = null,
    center: Double? = null,
    block: FreqpolyContext.() -> Unit
) {
    layers.add(
        FreqpolyContext(data, bins, boundary, center)
            .apply {
                copyFrom(this@freqPoly)
                x(source)
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
                x(source)
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


