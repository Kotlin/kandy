package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.ViolinScale
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.ViolinStat
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.toDataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.BandWidth
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.Kernel
import kotlin.reflect.typeOf


val VIOLIN = LetsPlotGeom("violin")

// todo stats
@PlotDslMarker
class ViolinContext(
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
    internal val x = XAes(this)

    @PublishedApi
    internal val y = YAes(this)


    val alpha = AlphaAes(this)
    val fillColor = FillAes(this)
    val width = WidthAes(this)
    val violinWidth = ViolinWidthAes(this)
    val borderLineWidth = SizeAes(this)
    val borderLineColor = ColorAes(this)
    val borderLineType = LineTypeAes(this)

    // todo weight

    @PublishedApi
    internal val drawQuantiles = DrawQuantilesAes(this)

    @PublishedApi
    internal val scale = ViolinScaleAes(this)

    @PublishedApi
    internal val kernel = KernelAes(this)

    @PublishedApi
    internal val bw = BWAes(this)

    @PublishedApi
    internal val pointsSampled = NumberAes(this)

    @PublishedApi
    internal val trim = TrimAes(this)

    @PublishedApi
    internal val adjust = AdjustAes(this)

    @PublishedApi
    internal val fullScanMax = FullScanMaxAes(this)

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
    /*
       inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
        stat: Stat<DomainType>
    ): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
        val mapping = ScaledUnspecifiedDefaultPositionalMapping(
            this.name,
            stat.toDataSource().scaled(),
            typeOf<DomainType>()
        )
        context.bindingCollector.mappings[this.name] = mapping
        return mapping
    }
  */

    object Statistics {
        val VIOLIN_WIDTH = ViolinStat.ViolinWidth
        val DENSITY = ViolinStat.Density
        val SCALED = ViolinStat.Scaled
        val COUNT = ViolinStat.Count
    }

    val Stat = Statistics

    inline operator fun <reified DomainType : Any, RangeType : Any>
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

inline fun <reified T : Any, reified R : Any> PlotContext.violin(
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
                x(sourceX)
                y(sourceY)
            }
            .apply(block)
            .toLayer(DENSITY)
    )
}

inline fun <reified T : Any, reified R : Any> PlotContext.violin(
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
                x(sourceX)
                y(sourceY)
            }
            .apply(block)
            .toLayer(DENSITY)
    )
}
