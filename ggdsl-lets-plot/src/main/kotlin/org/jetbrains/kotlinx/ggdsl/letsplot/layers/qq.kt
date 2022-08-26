package org.jetbrains.kotlinx.ggdsl.letsplot.layers


import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Distribution
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.toList

/* TODO
@PublishedApi

 */
val QQ = LetsPlotGeom("qq")


@PlotDslMarker
// todo move x/y?
class QQContext(
    override var data: MutableNamedData,
    distribution: Distribution?,
) : LayerContext() {
    init {
        distribution?.let {
            distribution(it.name)
            dParams(it.toList())
        }
    }
    @PublishedApi
    internal val _sample = SampleAes(this)

    val sample = SampleDummyAes(this)

    val alpha = AlphaAes(this)
    val fillColor = FillAes(this)
    val color = ColorAes(this)
    val size = SizeAes(this)
    val symbol = ShapeAes(this)

    object Statistics {
        // TODO
    }

    val Stat = Statistics

    // todo weight

    @PublishedApi
    internal val distribution = DistributionAes(this)

    @PublishedApi
    internal val dParams = DParamsAes(this)

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

inline fun <reified T : Any> PlotContext.qq(
    source: DataSource<T>,
    distribution: Distribution? = null,
    block: QQContext.() -> Unit
) {
    layers.add(
        QQContext(data, distribution)
            .apply {
                copyFrom(this@qq)
                _sample(source)
            }
            .apply(block)
            .toLayer(QQ)
    )
}

inline fun <reified T : Any> PlotContext.qq(
    source: Iterable<T>,
    distribution: Distribution? = null,
    block: QQContext.() -> Unit
) {
    layers.add(
        QQContext(data, distribution)
            .apply {
                copyFrom(this@qq)
                _sample(source)
            }
            .apply(block)
            .toLayer(QQ)
    )
}


