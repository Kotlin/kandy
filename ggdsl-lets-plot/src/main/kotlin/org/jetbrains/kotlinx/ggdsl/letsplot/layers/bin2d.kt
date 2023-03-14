/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

/* TODO
@PublishedApi

 */
//public val BIN_2D: LetsPlotGeom = LetsPlotGeom("bin_2D")

/*
/*@PlotDslMarker*/
<<<<<<< HEAD
class Bin2DContext(
    parent: LayerCollectorContext,
=======
public class Bin2DContext(
    override var data: MutableNamedData,
>>>>>>> main
    bins: Bins2D?,
    drop: Boolean?,
) : WithBins2DContext(bins) {
    init {
        drop?.let {
            drop(it)
        }
    }

    @PublishedApi
    internal val _x: XAes get() = XAes(this)

    @PublishedApi
    internal val _y: YAes get() = YAes(this)

    public val x: XDummyAes get() = XDummyAes(this)
    public val y: YDummyAes get() = YDummyAes(this)

    public val width: WidthPosAes get() = WidthPosAes(this)
    public val height: HeightPosAes get() = HeightPosAes(this)

    public val alpha: AlphaAes get() = AlphaAes(this)
    public val fillColor: FillAes get() = FillAes(this)

    // TODO move?
    public val borderLineColor: ColorAes get() = ColorAes(this)
    public val borderLineTypeAes: LineTypeAes get() = LineTypeAes(this)
    public val borderLineWidth: WidthAes get() = WidthAes(this)

    // todo weight

    @PublishedApi
    internal val drop: DropAes get() = DropAes(this)

    public object Statistics {
        public val X: Bin2DStat.X = Bin2DStat.X
        public val Y: Bin2DStat.Y = Bin2DStat.Y
        public val DENSITY: Bin2DStat.Density = Bin2DStat.Density
    }

    public val Stat: Statistics = Statistics

    public inline operator fun <reified DomainType> ScalablePositionalAes.invoke(
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

    public inline operator fun <reified DomainType, RangeType> MappableNonPositionalAes<RangeType>.invoke(
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

        private val _bins get() = Bins2DAes(this)
        private val binWidth get() = BinWidth2DAes(this)

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

<<<<<<< HEAD
inline fun <reified T, reified R: Any> PlotContext.bin2D(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
=======
public inline fun <reified T, reified R> PlotContext.bin2D(
    sourceX: DataSource<T>,
    sourceY: DataSource<R>,
>>>>>>> main
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

public inline fun <reified T, reified R> PlotContext.bin2D(
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