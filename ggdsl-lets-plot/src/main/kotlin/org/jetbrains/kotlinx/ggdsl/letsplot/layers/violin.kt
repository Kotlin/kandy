/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.ScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.ViolinScale
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.ViolinStat
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.toDataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.BandWidth
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.Kernel
import kotlin.reflect.typeOf


public val VIOLIN: LetsPlotGeom = LetsPlotGeom("violin")

// todo stats
@PlotDslMarker
public class ViolinContext(
    override var data: MutableNamedData,
    drawQuantiles: List<Double>?,
    scale: ViolinScale?,
    kernel: Kernel?,
    bandWidth: BandWidth?,
    pointsSampled: Int?,
    trim: Boolean?,
    adjust: Double?,
    fullScanMax: Int?,
) : LayerContext() {

    @PublishedApi
    internal val _x: XAes = XAes(this)

    @PublishedApi
    internal val _y: YAes = YAes(this)

    public val x: XDummyAes = XDummyAes(this)
    public val y: YDummyAes = YDummyAes(this)


    public val alpha: AlphaAes = AlphaAes(this)
    public val fillColor: FillAes = FillAes(this)
    public val width: WidthAes = WidthAes(this)
    public val violinWidth: ViolinWidthAes = ViolinWidthAes(this)
    public val borderLineWidth: SizeAes = SizeAes(this)
    public val borderLineColor: ColorAes = ColorAes(this)
    public val borderLineType: LineTypeAes = LineTypeAes(this)

    // todo weight

    @PublishedApi
    internal val drawQuantiles: DrawQuantilesAes = DrawQuantilesAes(this)

    @PublishedApi
    internal val scale: ViolinScaleAes = ViolinScaleAes(this)

    @PublishedApi
    internal val kernel: KernelAes = KernelAes(this)

    @PublishedApi
    internal val bw: BWAes = BWAes(this)

    @PublishedApi
    internal val pointsSampled: NumberAes = NumberAes(this)

    @PublishedApi
    internal val trim: TrimAes = TrimAes(this)

    @PublishedApi
    internal val adjust: AdjustAes = AdjustAes(this)

    @PublishedApi
    internal val fullScanMax: FullScanMaxAes = FullScanMaxAes(this)

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

    public inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
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

    public inline operator fun <reified DomainType : Any, RangeType : Any>
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

public inline fun <reified T : Any, reified R : Any> PlotContext.violin(
    sourceX: DataSource<T>,
    sourceY: DataSource<R>,
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

public inline fun <reified T : Any> PlotContext.violin(
    sourceY: DataSource<T>,
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

public inline fun <reified T : Any, reified R : Any> PlotContext.violin(
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
