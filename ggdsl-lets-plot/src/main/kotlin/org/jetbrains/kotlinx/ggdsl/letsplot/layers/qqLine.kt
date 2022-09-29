package org.jetbrains.kotlinx.ggdsl.letsplot.layers


import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom

/* TODO
@PublishedApi

 */
val QQ_LINE = LetsPlotGeom("qqLine")

/*
@PlotDslMarker
// todo move x/y?
class QQLineContext(
    parent: LayerCollectorContext,
    distribution: Distribution?,
    quantiles: Pair<Double, Double>?,
) : LayerContext(parent) {
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
    internal val _sample = SampleAes(this)

    val sample = SampleDummyAes(this)

    val alpha = AlphaAes(this)
    val color = ColorAes(this)
    val width = SizeAes(this)
    val type = LineTypeAes(this)



    // todo weight

    @PublishedApi
    internal val distribution = DistributionAes(this)

    @PublishedApi
    internal val dParams = DParamsAes(this)

    @PublishedApi
    internal val quantiles = QuantilesAes(this)

    object Statistics {
        val SAMPLE = QQStat.Sample
        val THEORETICAL = QQStat.Theoretical
    }

    val Stat = Statistics


    inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
        stat: QQStat<DomainType>
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
        stat: QQStat<DomainType>
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

inline fun <reified T : Any> PlotContext.qqLine(
    source: ColumnPointer<T>,
    distribution: Distribution? = null,
    quantiles: Pair<Double, Double>? = null,
    block: QQLineContext.() -> Unit
) {
    layers.add(
        QQLineContext(data, distribution, quantiles)
            .apply {
                copyFrom(this@qqLine)
                _sample(source)
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
                _sample(source)
            }
            .apply(block)
            .toLayer(QQ_LINE)
    )
}

 */


