/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers


import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotGeom

/* TODO
@PublishedApi

 */
public val SMOOTH: LetsPlotGeom = LetsPlotGeom("smooth")

/*
@PlotDslMarker
<<<<<<< HEAD
class SmoothContext(
    parent: LayerCollectorContext,
=======
public class SmoothContext(
    override var data: MutableNamedData,
>>>>>>> main
    smoothMethod: SmoothMethod?,
    pointsNumber: Int?,
    se: Boolean?,
    level: Double?,
) : LayerContext(parent) {

    // todo min/max?

    @PublishedApi
    internal val _x: XAes get() = XAes(this)

    @PublishedApi
    internal val _y: YAes get() = YAes(this)

    public val x: XDummyAes get() = XDummyAes(this)
    public val y: YDummyAes get() = YDummyAes(this)

    public val alpha: AlphaAes get() = AlphaAes(this)
    public val fillColor: FillAes get() = FillAes(this)
    public val lineColor: ColorAes get() = ColorAes(this)
    public val lineWidth: SizeAes get() = SizeAes(this)
    public val lineType: LineTypeAes get() = LineTypeAes(this)

    @PublishedApi
    internal val method: MethodAes get() = MethodAes(this)

    @PublishedApi
    internal val pointsNumber: NumberAes get() = NumberAes(this)

    @PublishedApi
    internal val se: SEAes get() = SEAes(this)

    @PublishedApi
    internal val level: LevelAes get() = LevelAes(this)

    @PublishedApi
    internal val span: SpanAes get() = SpanAes(this)

    @PublishedApi
    internal val deg: DegAes get() = DegAes(this)

    @PublishedApi
    internal val seed: SeedAes get() = SeedAes(this)

    @PublishedApi
    internal val maxN: MaxNAes get() = MaxNAes(this)

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


    public inline operator fun <reified DomainType> ScalablePositionalAes.invoke(
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

    public inline operator fun <reified DomainType, RangeType> MappableNonPositionalAes<RangeType>.invoke(
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

<<<<<<< HEAD
inline fun <reified T, reified R> PlotContext.smooth(
    sourceX: ColumnPointer<T>,
    sourceY: ColumnPointer<R>,
=======
public inline fun <reified T, reified R> PlotContext.smooth(
    sourceX: DataSource<T>,
    sourceY: DataSource<R>,
>>>>>>> main
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

public inline fun <reified T, reified R> PlotContext.smooth(
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



 */
