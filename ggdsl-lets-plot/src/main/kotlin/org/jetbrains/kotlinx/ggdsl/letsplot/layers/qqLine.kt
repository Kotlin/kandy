package org.jetbrains.kotlinx.ggdsl.letsplot.layers


import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Distribution
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.toList

/* TODO
@PublishedApi

 */
val QQ_LINE = LetsPlotGeom("qqLine")


@PlotDslMarker
// todo move x/y?
class QQLineContext(
    override var data: MutableNamedData,
    distribution: Distribution?,
    quantiles: Pair<Double, Double>?,
) : LayerContext() {
    init {
        distribution?.let {
            distribution(it.name)
            dParams(it.toList())
        }
        quantiles?.let {
            quantiles(it)
        }
    }
    @PublishedApi
    internal val sample = SampleAes(this)

    val alpha = AlphaAes(this)
    val color = ColorAes(this)
    val width = SizeAes(this)
    val type = LineTypeAes(this)

    object Statistics {
        // TODO
    }

    val Stat = Statistics

    // todo weight

    @PublishedApi
    internal val distribution = DistributionAes(this)

    @PublishedApi
    internal val dParams = DParamsAes(this)

    @PublishedApi
    internal val quantiles = QuantilesAes(this)

    /* TODO
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

     */

}

inline fun <reified T : Any> PlotContext.qqLine(
    source: DataSource<T>,
    distribution: Distribution? = null,
    quantiles: Pair<Double, Double>? = null,
    block: QQLineContext.() -> Unit
) {
    layers.add(
        QQLineContext(data, distribution, quantiles)
            .apply {
                copyFrom(this@qqLine)
                sample(source)
            }
            .apply(block)
            .toLayer(QQ_LINE)
    )
}

inline fun <reified T : Any> PlotContext.qqLine(
    source: Iterable<T>,
    distribution: Distribution? = null,
    quantiles: Pair<Double, Double>? = null,
    block: QQLineContext.() -> Unit
) {
    layers.add(
        QQLineContext(data, distribution, quantiles)
            .apply {
                copyFrom(this@qqLine)
                sample(source)
            }
            .apply(block)
            .toLayer(QQ_LINE)
    )
}


