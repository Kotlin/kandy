/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers


/* TODO
@PublishedApi

 */
//public val QQ: LetsPlotGeom = LetsPlotGeom("qq")

/*
@PlotDslMarker
// todo move x/y?
<<<<<<< HEAD
class QQContext(
    parent: LayerCollectorContext,
=======
public class QQContext(
    override var data: MutableNamedData,
>>>>>>> main
    distribution: Distribution?,
) : LayerContext(parent) {
    init {
        distribution?.let {
            distribution(it.name)
            dParams(it.toList())
        }
    }

    @PublishedApi
    internal val _sample: SampleAes get() = SampleAes(this)

    public val sample: SampleDummyAes get() = SampleDummyAes(this)

    public val alpha: AlphaAes get() = AlphaAes(this)
    public val fillColor: FillAes get() = FillAes(this)
    public val color: ColorAes get() = ColorAes(this)
    public val size: SizeAes get() = SizeAes(this)
    public val symbol: ShapeAes get() = ShapeAes(this)

    // todo weight

    @PublishedApi
    internal val distribution: DistributionAes get() = DistributionAes(this)

    @PublishedApi
    internal val dParams: DParamsAes get() = DParamsAes(this)

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

<<<<<<< HEAD
inline fun <reified T : Any> PlotContext.qq(
    source: ColumnPointer<T>,
=======
public inline fun <reified T : Any> PlotContext.qq(
    source: DataSource<T>,
>>>>>>> main
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

public inline fun <reified T : Any> PlotContext.qq(
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

 */


