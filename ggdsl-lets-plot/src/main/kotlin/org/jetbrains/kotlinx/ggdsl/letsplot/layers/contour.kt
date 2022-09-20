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
public val CONTOUR: LetsPlotGeom = LetsPlotGeom("contour")


@PlotDslMarker
// todo move x/y?
public class ContourContext(
    override var data: MutableNamedData,
    bins: Bins?
) : WithBinsContext(bins) {
    @PublishedApi
    internal val _x: XAes = XAes(this)

    @PublishedApi
    internal val _y: YAes = YAes(this)

    @PublishedApi
    internal val _z: ZAes = ZAes(this)

    public val x: XDummyAes = XDummyAes(this)
    public val y: YDummyAes = YDummyAes(this)
    public val z: ZDummyAes = ZDummyAes(this)

    public val alpha: AlphaAes = AlphaAes(this)

    /* TODO */
    public val lineColor: ColorAes = ColorAes(this)
    public val lineType: LineTypeAes = LineTypeAes(this)
    public val lineWidth: WidthAes = WidthAes(this)

    public object Statistics {
        public val X: ContourStat.X = ContourStat.X
        public val Y: ContourStat.Y = ContourStat.Y
        public val LEVEL: ContourStat.Level = ContourStat.Level
        public val GROUP: ContourStat.Group = ContourStat.Group
    }

    public val Stat: Statistics = Statistics


    // todo speed, flow - mi ne znaem chto eto takoe, esli bi mi znali


    public inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
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

    public inline operator fun <reified DomainType : Any, RangeType : Any> MappableNonPositionalAes<RangeType>.invoke(
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

    /* interface BinOption {
         data class ByNumber(val number: Int) : BinOption
         data class ByWidth(val width: Double) : BinOption
     }

     fun byNumber(number: Int) = BinOption.ByNumber(number)
     fun byWidth(width: Double) = BinOption.ByWidth(width)

     private val _bins = BinsAes(this)
     private val binWidth = BinWidthAes(this)

     var bins: BinOption? = null
         set(value) {
             when (value) {
                 is BinOption.ByNumber -> {
                     bindingCollector.settings.remove(BIN_WIDTH)
                     _bins(value.number)
                 }

                 is BinOption.ByWidth -> {
                     bindingCollector.settings.remove(BINS)
                     binWidth(value.width)
                 }

                 else -> {
                     bindingCollector.settings.remove(BINS)
                     bindingCollector.settings.remove(BIN_WIDTH)
                 }
             }
             field = null
         }

     */
}

public inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contour(
    sourceX: DataSource<TX>,
    sourceY: DataSource<TY>,
    sourceZ: DataSource<TZ>,
    bins: Bins? = null,
    block: ContourContext.() -> Unit
) {
    layers.add(
        ContourContext(data, bins)
            .apply {
                copyFrom(this@contour)
                _x(sourceX)
                _y(sourceY)
                _z(sourceZ)
            }
            .apply(block)
            .toLayer(CONTOUR)
    )
}

public inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contour(
    sourceX: Iterable<TX>,
    sourceY: Iterable<TY>,
    sourceZ: Iterable<TZ>,
    bins: Bins? = null,
    block: ContourContext.() -> Unit
) {
    layers.add(
        ContourContext(data, bins)
            .apply {
                copyFrom(this@contour)
                _x(sourceX)
                _y(sourceY)
                _z(sourceZ)
            }
            .apply(block)
            .toLayer(CONTOUR)
    )
}


