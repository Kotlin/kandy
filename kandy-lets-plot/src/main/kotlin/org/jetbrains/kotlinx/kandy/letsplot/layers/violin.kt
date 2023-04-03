/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers



//public val VIOLIN: LetsPlotGeom = LetsPlotGeom("violin")
/*
// todo stats
/*@PlotDslMarker*/
class ViolinContext(
    parent: LayerCollectorContext,
=======
public val VIOLIN: LetsPlotGeom = LetsPlotGeom("violin")

// todo stats
/*@PlotDslMarker*/
public class ViolinContext(
    override var data: MutableNamedData,
>>>>>>> main
    drawQuantiles: List<Double>?,
    scale: ViolinScale?,
    kernel: Kernel?,
    bandWidth: BandWidth?,
    pointsSampled: Int?,
    trim: Boolean?,
    adjust: Double?,
    fullScanMax: Int?,
) : LayerContext(parent) {

    @PublishedApi
    internal val _x: XAes get() = XAes(this)

    @PublishedApi
    internal val _y: YAes get() = YAes(this)

    public val x: XDummyAes get() = XDummyAes(this)
    public val y: YDummyAes get() = YDummyAes(this)


    public val alpha: AlphaAes get() = AlphaAes(this)
    public val fillColor: FillAes get() = FillAes(this)
    public val width: WidthAes get() = WidthAes(this)
    public val violinWidth: ViolinWidthAes get() = ViolinWidthAes(this)
    public val borderLineWidth: SizeAes get() = SizeAes(this)
    public val borderLineColor: ColorAes get() = ColorAes(this)
    public val borderLineType: LineTypeAes get() = LineTypeAes(this)

    // todo weight

    @PublishedApi
    internal val drawQuantiles: DrawQuantilesAes get() = DrawQuantilesAes(this)

    @PublishedApi
    internal val scale: ViolinScaleAes get() = ViolinScaleAes(this)

    @PublishedApi
    internal val kernel: KernelAes get() = KernelAes(this)

    @PublishedApi
    internal val bw: BWAes get() = BWAes(this)

    @PublishedApi
    internal val pointsSampled: NumberAes get() = NumberAes(this)

    @PublishedApi
    internal val trim: TrimAes get() = TrimAes(this)

    @PublishedApi
    internal val adjust: AdjustAes get() = AdjustAes(this)

    @PublishedApi
    internal val fullScanMax: FullScanMaxAes get() = FullScanMaxAes(this)

    init {


        drawQuantiles?.let {
            drawQuantiles(it)
        }
        scale?.let {
            scale(it.toString())
        }

        kernel?.let {
            kernel(it)
        }
        bandWidth?.let {
            bw(it)
        }
        pointsSampled?.let {
            pointsSampled(it)
        }
        trim?.let {
            trim(it)
        }
        adjust?.let {
            adjust(it)
        }
        fullScanMax?.let {
            fullScanMax(it)
        }

    }


    public object Statistics {
        public val X: ViolinStat.X = ViolinStat.X
        public val Y: ViolinStat.Y = ViolinStat.Y
        public val VIOLIN_WIDTH: ViolinStat.ViolinWidth = ViolinStat.ViolinWidth
        public val DENSITY: ViolinStat.Density = ViolinStat.Density
        public val SCALED: ViolinStat.Scaled = ViolinStat.Scaled
        public val COUNT: ViolinStat.Count = ViolinStat.Count
    }

    public val Stat: Statistics = Statistics

    public inline operator fun <reified DomainType> ScalablePositionalAes.invoke(
        stat: ViolinStat<DomainType>
    ): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
        val mapping = ScaledUnspecifiedDefaultPositionalMapping(
            this.name,
            stat.toDataSource().scaled(),
            typeOf<DomainType>()
        )
        context.bindingCollector.mappings[this.name] = mapping
        return mapping
    }

    public inline operator fun <reified DomainType, RangeType>
        MappableNonPositionalAes<RangeType>.invoke(
        stat: ViolinStat<DomainType>
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
inline fun <reified T, reified R> PlotContext.violin(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
=======
public inline fun <reified T, reified R> PlotContext.violin(
    sourceX: DataSource<T>,
    sourceY: DataSource<R>,
>>>>>>> main
    drawQuantiles: List<Double>? = null,
    scale: ViolinScale? = null,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: ViolinContext.() -> Unit,
) {
    layers.add(
        ViolinContext(data, drawQuantiles, scale, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax)
            .apply {
                copyFrom(this@violin)
                _x(sourceX)
                _y(sourceY)
            }
            .apply(block)
            .toLayer(VIOLIN)
    )
}

<<<<<<< HEAD
inline fun <reified T> PlotContext.violin(
    sourceY: ColumnReference<T>,
=======
public inline fun <reified T> PlotContext.violin(
    sourceY: DataSource<T>,
>>>>>>> main
    drawQuantiles: List<Double>? = null,
    scale: ViolinScale? = null,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: ViolinContext.() -> Unit,
) {
    layers.add(
        ViolinContext(data, drawQuantiles, scale, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax)
            .apply {
                copyFrom(this@violin)
                _y(sourceY)
            }
            .apply(block)
            .toLayer(VIOLIN)
    )
}

public inline fun <reified T, reified R> PlotContext.violin(
    sourceX: Iterable<T>,
    sourceY: Iterable<R>,
    drawQuantiles: List<Double>? = null,
    scale: ViolinScale? = null,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: ViolinContext.() -> Unit,
) {
    layers.add(
        ViolinContext(data, drawQuantiles, scale, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax)
            .apply {
                copyFrom(this@violin)
                _x(sourceX)
                _y(sourceY)
            }
            .apply(block)
            .toLayer(VIOLIN)
    )
}

 */
