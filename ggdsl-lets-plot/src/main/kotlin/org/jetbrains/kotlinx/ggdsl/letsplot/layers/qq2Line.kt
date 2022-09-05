package org.jetbrains.kotlinx.ggdsl.letsplot.layers


import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.ScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.QQ2Stat
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.toDataSource
import kotlin.reflect.typeOf

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


    // todo speed flow


    @PublishedApi
    internal val quantiles = QuantilesAes(this)

    object Statistics {
        val X = QQ2Stat.X
        val Y = QQ2Stat.Y
    }

    val Stat = Statistics


    inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
        stat: QQ2Stat<DomainType>
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
        stat: QQ2Stat<DomainType>
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


