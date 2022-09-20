package org.jetbrains.kotlinx.ggdsl.letsplot.layers


import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.ScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.SmoothMethod
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.SmoothStat
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.toDataSource
import kotlin.reflect.typeOf

/* TODO
@PublishedApi

 */
public val SMOOTH: LetsPlotGeom = LetsPlotGeom("smooth")


@PlotDslMarker
public class SmoothContext(
    override var data: MutableNamedData,
    smoothMethod: SmoothMethod?,
    pointsNumber: Int?,
    se: Boolean?,
    level: Double?,
) : LayerContext() {

    // todo min/max?

    @PublishedApi
    internal val _x: XAes = XAes(this)

    @PublishedApi
    internal val _y: YAes = YAes(this)

    public val x: XDummyAes = XDummyAes(this)
    public val y: YDummyAes = YDummyAes(this)

    public val alpha: AlphaAes = AlphaAes(this)
    public val fillColor: FillAes = FillAes(this)
    public val lineColor: ColorAes = ColorAes(this)
    public val lineWidth: SizeAes = SizeAes(this)
    public val lineType: LineTypeAes = LineTypeAes(this)

    @PublishedApi
    internal val method: MethodAes = MethodAes(this)

    @PublishedApi
    internal val pointsNumber: NumberAes = NumberAes(this)

    @PublishedApi
    internal val se: SEAes = SEAes(this)

    @PublishedApi
    internal val level: LevelAes = LevelAes(this)

    @PublishedApi
    internal val span: SpanAes = SpanAes(this)

    @PublishedApi
    internal val deg: DegAes = DegAes(this)

    @PublishedApi
    internal val seed: SeedAes = SeedAes(this)

    @PublishedApi
    internal val maxN: MaxNAes = MaxNAes(this)

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
        when (smoothMethod) {
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

            null -> {}
        }
    }

    public object Statistics {
        public val X: SmoothStat.X = SmoothStat.X
        public val Y: SmoothStat.Y = SmoothStat.Y
        public val Y_MAX: SmoothStat.YMax = SmoothStat.YMax
        public val Y_MIN: SmoothStat.YMin = SmoothStat.YMin
        public val SE: SmoothStat.SE = SmoothStat.SE
    }

    public val Stat: Statistics = Statistics


    public inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
        stat: SmoothStat<DomainType>
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
        stat: SmoothStat<DomainType>
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

public inline fun <reified T : Any, reified R : Any> PlotContext.smooth(
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

public inline fun <reified T : Any, reified R : Any> PlotContext.smooth(
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


