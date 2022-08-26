package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Density2DStat
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.toDataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.BandWidth
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.Kernel
import kotlin.reflect.typeOf


val DENSITY_2D_FILLED = LetsPlotGeom("density_2D_filled")


// todo stats
@PlotDslMarker
// todo move x/y?
class Density2DFilledContext(
    override var data: MutableNamedData,
    kernel: Kernel?,
    bandWidth: BandWidth?,
    pointsSampled: Int?,
    trim: Boolean?,
    adjust: Double?,
    fullScanMax: Int?,
) : LayerContext() {
    // todo internal
    @PublishedApi
    internal val _x = XAes(this)

    @PublishedApi
    internal val _y = YAes(this)

    val x = XDummyAes(this)
    val y = YDummyAes(this)

     object Statistics {
        val LEVEL = Density2DStat.Level
     }

     val Stat = Statistics


    val alpha = AlphaAes(this)
    val fillColor = FillAes(this)
    val borderLineWidth = SizeAes(this)
    val borderLineColor = ColorAes(this)
    val borderLineType = LineTypeAes(this)

    // todo weight
// TODO params

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
        stat: Density2DStat<DomainType>
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

    inline operator fun <reified DomainType : Any, RangeType : Any>
            MappableNonPositionalAes<RangeType>.invoke(
        stat: Density2DStat<DomainType>
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

inline fun <reified T : Any, reified R: Any> PlotContext.density2DFilled(
    sourceX: DataSource<T>,
    sourceY: DataSource<R>,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: Density2DFilledContext.() -> Unit
) {
    layers.add(
        Density2DFilledContext(data, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax)
            .apply {
                copyFrom(this@density2DFilled)
                _x(sourceX)
                _y(sourceY)
            }
            .apply(block)
            .toLayer(DENSITY_2D_FILLED)
    )
}

inline fun <reified T : Any, reified R: Any> PlotContext.density2DFilled(
    sourceX: Iterable<T>,
    sourceY: Iterable<R>,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: Density2DFilledContext.() -> Unit
) {
    layers.add(
        Density2DFilledContext(data, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax)
            .apply {
                copyFrom(this@density2DFilled)
                _x(sourceX)
                _y(sourceY)
            }
            .apply(block)
            .toLayer(DENSITY_2D_FILLED)
    )
}