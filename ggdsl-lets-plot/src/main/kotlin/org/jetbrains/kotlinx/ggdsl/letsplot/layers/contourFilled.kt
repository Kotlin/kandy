package org.jetbrains.kotlinx.ggdsl.letsplot.layers


import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.ScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Bins
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.ContourStat
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.WithBinsContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.toDataSource
import kotlin.reflect.typeOf

/* TODO
@PublishedApi

 */
val CONTOUR_FILLED = LetsPlotGeom("contour_filled")



@PlotDslMarker
class ContourFilledContext(
    override var data: MutableNamedData,
    bins: Bins?
) : WithBinsContext(bins) {
    @PublishedApi
    internal val _x = XAes(this)
    @PublishedApi
    internal val _y = YAes(this)
    @PublishedApi
    internal val _z = ZAes(this)

    val x = XDummyAes(this)
    val y = YDummyAes(this)
    val z = ZDummyAes(this)

    val alpha = AlphaAes(this)
    val fillColor = FillAes(this)
    val lineColor = ColorAes(this)
    val lineType = LineTypeAes(this)
    val lineWidth = WidthAes(this)

   // todo speed, flow

    object Statistics {
        val LEVEL = ContourStat.Level
    }

    val Stat = Statistics

    inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
        stat: ContourStat<DomainType>
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
        stat: ContourStat<DomainType>
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

inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: DataSource<TX>,
    sourceY: DataSource<TY>,
    sourceZ: DataSource<TZ>,
    bins: Bins?,
    block: ContourFilledContext.() -> Unit
) {
    layers.add(
        ContourFilledContext(data, bins)
            .apply {
                copyFrom(this@contourFilled)
                _x(sourceX)
                _y(sourceY)
                _z(sourceZ)
            }
            .apply(block)
            .toLayer(CONTOUR_FILLED)
    )
}

inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: Iterable<TX>,
    sourceY: Iterable<TY>,
    sourceZ: Iterable<TZ>,
    bins: Bins?,
    block: ContourFilledContext.() -> Unit
) {
    layers.add(
        ContourFilledContext(data, bins)
            .apply {
                copyFrom(this@contourFilled)
                _x(sourceX)
                _y(sourceY)
                _z(sourceZ)
            }
            .apply(block)
            .toLayer(CONTOUR_FILLED)
    )
}


