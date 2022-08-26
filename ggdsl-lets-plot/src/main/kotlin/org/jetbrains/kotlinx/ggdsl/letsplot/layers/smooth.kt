package org.jetbrains.kotlinx.ggdsl.letsplot.layers


import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.SmoothMethod

/* TODO
@PublishedApi

 */
val SMOOTH = LetsPlotGeom("smooth")


@PlotDslMarker
class SmoothContext(
    override var data: MutableNamedData,
    smoothMethod: SmoothMethod?,
    pointsNumber: Int?,
    se: Boolean?,
    level: Double?,
) : LayerContext() {

    // todo min/max?

    @PublishedApi
    internal val _x = XAes(this)
    @PublishedApi
    internal val _y = YAes(this)

    val x = XDummyAes(this)
    val y = YDummyAes(this)

    val alpha = AlphaAes(this)
    val fillColor = FillAes(this)
    val lineColor = ColorAes(this)
    val lineWidth = SizeAes(this)
    val lineType = LineTypeAes(this)

    @PublishedApi
    internal val method = MethodAes(this)
    @PublishedApi
    internal val pointsNumber = NumberAes(this)
    @PublishedApi
    internal val se = SEAes(this)
    @PublishedApi
    internal val level = LevelAes(this)
    @PublishedApi
    internal val span = SpanAes(this)
    @PublishedApi
    internal val deg = DegAes(this)
    @PublishedApi
    internal val seed = SeedAes(this)
    @PublishedApi
    internal val maxN = MaxNAes(this)

    init {
        se?.let {
            se(it)
        }
        pointsNumber?.let {
            pointsNumber(it)
        }
        level?.let {
            level(it)
        }
        when(smoothMethod) {
            is SmoothMethod.Linear -> {
                method(smoothMethod.name)
                deg(smoothMethod.degree)
            }
            is SmoothMethod.Loess -> {
                method(smoothMethod.name)
                span(smoothMethod.span)
                maxN(smoothMethod.maxNumber)
                smoothMethod.seed?.let {
                    seed(it)
                }
            }
            null -> TODO()
        }
    }

    object Statistics {
        // TODO
    }

    val Stat = Statistics



  /*

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

inline fun <reified T : Any, reified R : Any> PlotContext.smooth(
    sourceX: DataSource<T>,
    sourceY: DataSource<R>,
    method: SmoothMethod? = null,
    pointsNumber: Int? = null,
    se: Boolean? = null,
    level: Double? = null,
    block: SmoothContext.() -> Unit
) {
    layers.add(
        SmoothContext(data, method, pointsNumber, se, level)
            .apply {
                copyFrom(this@smooth)
                _x(sourceX)
                _y(sourceY)
            }
            .apply(block)
            .toLayer(SMOOTH)
    )
}

inline fun <reified T : Any, reified R : Any> PlotContext.smooth(
    sourceX: Iterable<T>,
    sourceY: Iterable<R>,
    method: SmoothMethod? = null,
    pointsNumber: Int? = null,
    se: Boolean? = null,
    level: Double? = null,
    block: SmoothContext.() -> Unit
) {
    layers.add(
        SmoothContext(data, method, pointsNumber, se, level)
            .apply {
                copyFrom(this@smooth)
                _x(sourceX)
                _y(sourceY)
            }
            .apply(block)
            .toLayer(SMOOTH)
    )
}


