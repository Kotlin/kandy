package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.letsplot.*


val DENSITY = LetsPlotGeom("density")
/*
// todo stats
@PlotDslMarker
class DensityContext(
    parent: LayerCollectorContext,
    kernel: Kernel?,
    bandWidth: BandWidth?,
    pointsSampled: Int?,
    trim: Boolean?,
    adjust: Double?,
    fullScanMax: Int?,
) : LayerContext(parent) {

    @PublishedApi
    internal val _x = XAes(this)
    @PublishedApi
    internal val _y = YAes(this)

    val x = XDummyAes(this)
    val y = YDummyAes(this)


    val alpha = AlphaAes(this)
    val fillColor = FillAes(this)
    val borderLineWidth = SizeAes(this)
    val borderLineColor = ColorAes(this)
    val borderLineType = LineTypeAes(this)

    // todo weight

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
    object Statistics {
        val X = DensityStat.X
        val COUNT = DensityStat.Count
        val DENSITY = DensityStat.Density
        val SCALED = DensityStat.Scaled
    }

    val Stat = Statistics


    inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
        stat: DensityStat<DomainType>
    ): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
        val mapping = ScaledUnspecifiedDefaultPositionalMapping(
            this.name,
            stat.toDataSource().scaled(),
            typeOf<DomainType>()
        )
        context.bindingCollector.mappings[this.name] = mapping
        return mapping
    }



    inline operator fun <reified DomainType : Any, RangeType : Any>
            MappableNonPositionalAes<RangeType>.invoke(
        stat: DensityStat<DomainType>
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

inline fun <reified T : Any> PlotContext.density(
    source: ColumnPointer<T>,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: DensityContext.() -> Unit,
) {
    layers.add(
        DensityContext(data, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax)
            .apply {
                copyFrom(this@density)
                _x(source)
            }
            .apply(block)
            .toLayer(DENSITY)
    )
}

inline fun <reified T : Any> PlotContext.density(
    source: Iterable<T>,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: DensityContext.() -> Unit
) {
    layers.add(
        DensityContext(data, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax)
            .apply {
                copyFrom(this@density)
                _x(source)
            }
            .apply(block)
            .toLayer(DENSITY)
    )
}


 */