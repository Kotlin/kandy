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
val HISTOGRAM = LetsPlotGeom("histogram")


@PlotDslMarker
// todo move x/y?
class HistogramContext(
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
    internal val _x = XAes(this)
    val x = XDummyAes(this)

    val y = YAes(this)

    val alpha = AlphaAes(this)
    val fillColor = FillAes(this)
    val borderLineColor = ColorAes(this)
    val borderLineWidth = SizeAes(this)

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

inline fun <reified T : Any> PlotContext.histogram(
    source: DataSource<T>,
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

inline fun <reified T : Any> PlotContext.histogram(
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


