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
public val QQ2: LetsPlotGeom = LetsPlotGeom("qq2")


@PlotDslMarker
// todo move x/y?
public class QQ2Context(
    override var data: MutableNamedData,
) : LayerContext() {
    @PublishedApi
    internal val _x: XAes = XAes(this)

    @PublishedApi
    internal val _y: YAes = YAes(this)

    public val x: XDummyAes = XDummyAes(this)
    public val y: YDummyAes = YDummyAes(this)

    public val alpha: AlphaAes = AlphaAes(this)
    public val fillColor: FillAes = FillAes(this)
    public val color: ColorAes = ColorAes(this)
    public val size: SizeAes = SizeAes(this)
    public val symbol: ShapeAes = ShapeAes(this)
    // todo stroke

    public object Statistics {
        public val X: QQ2Stat.X = QQ2Stat.X
        public val Y: QQ2Stat.Y = QQ2Stat.Y
    }

    public val Stat: Statistics = Statistics


    public inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
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

    public inline operator fun <reified DomainType : Any, RangeType : Any> MappableNonPositionalAes<RangeType>.invoke(
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

public inline fun <reified T : Any, reified R : Any> PlotContext.qq2(
    sourceX: DataSource<T>,
    sourceY: DataSource<R>,
    block: QQ2Context.() -> Unit
) {
    layers.add(
        QQ2Context(data)
            .apply {
                copyFrom(this@qq2)
                _x(sourceX)
                _y(sourceY)
            }
            .apply(block)
            .toLayer(QQ2)
    )
}

public inline fun <reified T : Any, reified R : Any> PlotContext.qq2(
    sourceX: Iterable<T>,
    sourceY: Iterable<R>,
    block: QQ2Context.() -> Unit
) {
    layers.add(
        QQ2Context(data)
            .apply {
                copyFrom(this@qq2)
                _x(sourceX)
                _y(sourceY)
            }
            .apply(block)
            .toLayer(QQ2)
    )
}


