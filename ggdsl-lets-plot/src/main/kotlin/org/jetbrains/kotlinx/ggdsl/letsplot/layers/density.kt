/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.letsplot.*


public val DENSITY: LetsPlotGeom = LetsPlotGeom("density")
/*
// todo stats
@PlotDslMarker
class DensityContext(
    parent: LayerCollectorContext,
=======
public val DENSITY: LetsPlotGeom = LetsPlotGeom("density")

// todo stats
@PlotDslMarker
public class DensityContext(
    override var data: MutableNamedData,
>>>>>>> main
    kernel: Kernel?,
    bandWidth: BandWidth?,
    pointsSampled: Int?,
    trim: Boolean?,
    adjust: Double?,
    fullScanMax: Int?,
) : LayerContext(parent) {

    @PublishedApi
    internal val _x: XAes = XAes(this)

    @PublishedApi
    internal val _y: YAes = YAes(this)

    public val x: XDummyAes = XDummyAes(this)
    public val y: YDummyAes = YDummyAes(this)


    public val alpha: AlphaAes = AlphaAes(this)
    public val fillColor: FillAes = FillAes(this)
    public val borderLineWidth: SizeAes = SizeAes(this)
    public val borderLineColor: ColorAes = ColorAes(this)
    public val borderLineType: LineTypeAes = LineTypeAes(this)

    // todo weight

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
        public val X: DensityStat.X = DensityStat.X
        public val COUNT: DensityStat.Count = DensityStat.Count
        public val DENSITY: DensityStat.Density = DensityStat.Density
        public val SCALED: DensityStat.Scaled = DensityStat.Scaled
    }

    public val Stat: Statistics = Statistics


    public inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
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


    public inline operator fun <reified DomainType : Any, RangeType : Any>
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

<<<<<<< HEAD
inline fun <reified T : Any> PlotContext.density(
    source: ColumnPointer<T>,
=======
public inline fun <reified T : Any> PlotContext.density(
    source: DataSource<T>,
>>>>>>> main
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

public inline fun <reified T : Any> PlotContext.density(
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