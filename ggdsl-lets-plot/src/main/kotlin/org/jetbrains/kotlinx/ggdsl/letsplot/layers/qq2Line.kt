/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers


/* TODO
@PublishedApi

 */
//public val QQ2_LINE: LetsPlotGeom = LetsPlotGeom("qq2Line")

/*
/*@PlotDslMarker*/
// todo move x/y?
<<<<<<< HEAD
class QQ2LineContext(
    parent: LayerCollectorContext,
=======
public class QQ2LineContext(
    override var data: MutableNamedData,
>>>>>>> main
    quantiles: Pair<Double, Double>?,
) : LayerContext(parent) {
    init {
        quantiles?.let {
            quantiles(it)
        }
    }

    @PublishedApi
    internal val _x: XAes get() = XAes(this)

    @PublishedApi
    internal val _y: YAes get() = YAes(this)

    public val x: XDummyAes get() = XDummyAes(this)
    public val y: YDummyAes get() = YDummyAes(this)

    public val alpha: AlphaAes get() = AlphaAes(this)
    public val color: ColorAes get() = ColorAes(this)
    public val width: SizeAes get() = SizeAes(this)
    public val type: LineTypeAes get() = LineTypeAes(this)


    // todo speed flow


    @PublishedApi
    internal val quantiles: QuantilesAes get() = QuantilesAes(this)

    public object Statistics {
        public val X: QQ2Stat.X = QQ2Stat.X
        public val Y: QQ2Stat.Y = QQ2Stat.Y
    }

    public val Stat: Statistics = Statistics


    public inline operator fun <reified DomainType> ScalablePositionalAes.invoke(
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

    public inline operator fun <reified DomainType, RangeType> MappableNonPositionalAes<RangeType>.invoke(
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

<<<<<<< HEAD
inline fun <reified T, reified R: Any> PlotContext.qq2Line(
    sourceX: ColumnPointer<T>,
    sourceY: ColumnPointer<R>,
=======
public inline fun <reified T, reified R> PlotContext.qq2Line(
    sourceX: DataSource<T>,
    sourceY: DataSource<R>,
>>>>>>> main
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

public inline fun <reified T, reified R> PlotContext.qq2Line(
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



 */
