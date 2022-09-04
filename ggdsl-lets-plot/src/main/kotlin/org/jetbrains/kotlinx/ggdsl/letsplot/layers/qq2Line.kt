package org.jetbrains.kotlinx.ggdsl.letsplot.layers


import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.*

/* TODO
@PublishedApi

 */
val QQ2_LINE = LetsPlotGeom("qq2Line")


@PlotDslMarker
// todo move x/y?
class QQ2LineContext(
    override var data: MutableNamedData,
    quantiles: Pair<Double, Double>?,
) : LayerContext() {
    init {
        quantiles?.let {
            quantiles(it)
        }
    }
    @PublishedApi
    internal val _x = XAes(this)
    @PublishedApi
    internal val _y = YAes(this)

    val x = XDummyAes(this)
    val y = YDummyAes(this)

    val alpha = AlphaAes(this)
    val color = ColorAes(this)
    val width = SizeAes(this)
    val type = LineTypeAes(this)

    object Statistics {
        // TODO
    }

    val Stat = Statistics

    // todo speed flow


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

inline fun <reified T : Any, reified R: Any> PlotContext.qq2Line(
    sourceX: DataSource<T>,
    sourceY: DataSource<R>,
    quantiles: Pair<Double, Double>? = null,
    block: QQ2LineContext.() -> Unit
) {
    layers.add(
        QQ2LineContext(data, quantiles)
            .apply {
                copyFrom(this@qq2Line)
                _x(sourceX)
                _y(sourceY)
            }
            .apply(block)
            .toLayer(QQ2_LINE)
    )
}

inline fun <reified T : Any, reified R: Any> PlotContext.qq2Line(
    sourceX: Iterable<T>,
    sourceY: Iterable<R>,
    quantiles: Pair<Double, Double>? = null,
    block: QQ2LineContext.() -> Unit
) {
    layers.add(
        QQ2LineContext(data, quantiles)
            .apply {
                copyFrom(this@qq2Line)
                _x(sourceX)
                _y(sourceY)
            }
            .apply(block)
            .toLayer(QQ2_LINE)
    )
}


