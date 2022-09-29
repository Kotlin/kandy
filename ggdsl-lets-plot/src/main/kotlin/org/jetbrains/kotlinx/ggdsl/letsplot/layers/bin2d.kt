package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom

/* TODO
@PublishedApi

 */
val BIN_2D = LetsPlotGeom("bin_2D")

/*
@PlotDslMarker
class Bin2DContext(
    parent: LayerCollectorContext,
    bins: Bins2D?,
    drop: Boolean?,
) : WithBins2DContext(bins) {
    init {
        drop?.let {
            drop(it)
        }
    }
    @PublishedApi
    internal val _x = XAes(this)
    @PublishedApi
    internal val _y = YAes(this)

    val x = XDummyAes(this)
    val y = YDummyAes(this)

    val width = WidthPosAes(this)
    val height =  HeightPosAes(this)

    val alpha = AlphaAes(this)
    val fillColor = FillAes(this)

    // TODO move?
    val borderLineColor = ColorAes(this)
    val borderLineTypeAes = LineTypeAes(this)
    val borderLineWidth = WidthAes(this)

    // todo weight

    @PublishedApi
    internal val drop = DropAes(this)

    object Statistics {
        val X = Bin2DStat.X
        val Y = Bin2DStat.Y
        val DENSITY = Bin2DStat.Density
    }

    val Stat = Statistics

    inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
        stat: Bin2DStat<DomainType>
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
        stat: Bin2DStat<DomainType>
    ): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
        val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
            this.name,
            stat.toDataSource().scaled(),
            typeOf<DomainType>()
        )
        context.bindingCollector.mappings[this.name] = mapping
        return mapping
    }

/*
    interface BinOption {
        data class ByNumber(val numberX: Int, val numberY: Int) : BinOption
        data class ByWidth(val widthX: Double, val widthY: Double) : BinOption
    }

    fun byNumber(numberX: Int, numberY: Int) = BinOption.ByNumber(numberX, numberY)
    fun byWidth(widthX: Double, widthY: Double) = BinOption.ByWidth(widthX, widthY)

    private val _bins = Bins2DAes(this)
    private val binWidth = BinWidth2DAes(this)

    var bins: BinOption? = null
        set(value) {
            when (value) {
                is BinOption.ByNumber -> {
                    bindingCollector.settings.remove(BIN_WIDTH)
                    _bins(value.numberX to value.numberY)
                }

                is BinOption.ByWidth -> {
                    bindingCollector.settings.remove(BINS)
                    binWidth(value.widthX to value.widthY)
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

inline fun <reified T : Any, reified R: Any> PlotContext.bin2D(
    sourceX: ColumnPointer<T>,
    sourceY: ColumnPointer<R>,
    bins: Bins2D? = null,
    drop: Boolean? = null,
    block: Bin2DContext.() -> Unit
) {
    layers.add(
        Bin2DContext(data, bins, drop)
            .apply {
                copyFrom(this@bin2D)
                _x(sourceX)
                _y(sourceY)
            }
            .apply(block)
            .toLayer(BIN_2D)
    )
}

inline fun <reified T : Any, reified R: Any> PlotContext.bin2D(
    sourceX: Iterable<T>,
    sourceY: Iterable<R>,
    bins: Bins2D? = null,
    drop: Boolean? = null,
    block: Bin2DContext.() -> Unit
) {
    layers.add(
        Bin2DContext(data, bins, drop)
            .apply {
                copyFrom(this@bin2D)
                _x(sourceX)
                _y(sourceY)
            }
            .apply(block)
            .toLayer(BIN_2D)
    )
}


 */