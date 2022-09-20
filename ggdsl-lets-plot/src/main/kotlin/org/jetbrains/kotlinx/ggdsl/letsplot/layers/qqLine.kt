package org.jetbrains.kotlinx.ggdsl.letsplot.layers


import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.ScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Distribution
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.QQStat
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.toDataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.toList
import kotlin.reflect.typeOf

/* TODO
@PublishedApi

 */
public val QQ_LINE: LetsPlotGeom = LetsPlotGeom("qqLine")


@PlotDslMarker
// todo move x/y?
public class QQLineContext(
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
    internal val _sample: SampleAes = SampleAes(this)

    public val sample: SampleDummyAes = SampleDummyAes(this)

    public val alpha: AlphaAes = AlphaAes(this)
    public val color: ColorAes = ColorAes(this)
    public val width: SizeAes = SizeAes(this)
    public val type: LineTypeAes = LineTypeAes(this)


    // todo weight

    @PublishedApi
    internal val distribution: DistributionAes = DistributionAes(this)

    @PublishedApi
    internal val dParams: DParamsAes = DParamsAes(this)

    @PublishedApi
    internal val quantiles: QuantilesAes = QuantilesAes(this)

    public object Statistics {
        public val SAMPLE: QQStat.Sample = QQStat.Sample
        public val THEORETICAL: QQStat.Theoretical = QQStat.Theoretical
    }

    public val Stat: Statistics = Statistics


    public inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
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

    public inline operator fun <reified DomainType : Any, RangeType : Any> MappableNonPositionalAes<RangeType>.invoke(
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

public inline fun <reified T : Any> PlotContext.qqLine(
    source: DataSource<T>,
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

public inline fun <reified T : Any> PlotContext.qqLine(
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


