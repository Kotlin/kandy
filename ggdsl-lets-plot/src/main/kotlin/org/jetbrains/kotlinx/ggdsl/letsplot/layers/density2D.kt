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
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.ContourStat
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.toDataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.BandWidth
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.Kernel
import kotlin.reflect.typeOf


public val DENSITY_2D: LetsPlotGeom = LetsPlotGeom("density_2D")


// todo stats
@PlotDslMarker
// todo move x/y?
public class Density2DContext(
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
    internal val _x: XAes = XAes(this)

    @PublishedApi
    internal val _y: YAes = YAes(this)

    public val x: XDummyAes = XDummyAes(this)
    public val y: YDummyAes = YDummyAes(this)

    public object Statistics {
        public val X: ContourStat.X = ContourStat.X
        public val Y: ContourStat.Y = ContourStat.Y
        public val LEVEL: ContourStat.Level = ContourStat.Level
        public val GROUP: ContourStat.Group = ContourStat.Group
    }

    public val Stat: Statistics = Statistics


    public val alpha: AlphaAes = AlphaAes(this)

    // val fillColor = FillAes(this)
    public val borderLineWidth: SizeAes = SizeAes(this)
    public val borderLineColor: ColorAes = ColorAes(this)
    public val borderLineType: LineTypeAes = LineTypeAes(this)

    // todo weight
// TODO params

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


    public inline operator fun <reified DomainType : Any, RangeType : Any>
        MappableNonPositionalAes<RangeType>.invoke(
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

public inline fun <reified T : Any, reified R : Any> PlotContext.density2D(
    sourceX: DataSource<T>,
    sourceY: DataSource<R>,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: Density2DContext.() -> Unit
) {
    layers.add(
        Density2DContext(data, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax)
            .apply {
                copyFrom(this@density2D)
                _x(sourceX)
                _y(sourceY)
            }
            .apply(block)
            .toLayer(DENSITY_2D)
    )
}

public inline fun <reified T : Any, reified R : Any> PlotContext.density2D(
    sourceX: Iterable<T>,
    sourceY: Iterable<R>,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: Density2DContext.() -> Unit
) {
    layers.add(
        Density2DContext(data, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax)
            .apply {
                copyFrom(this@density2D)
                _x(sourceX)
                _y(sourceY)
            }
            .apply(block)
            .toLayer(DENSITY_2D)
    )
}
