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
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Bins
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.ContourStat
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.WithBinsContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.toDataSource
import kotlin.reflect.typeOf

/* TODO
@PublishedApi

 */
public val CONTOUR_FILLED: LetsPlotGeom = LetsPlotGeom("contour_filled")


@PlotDslMarker
public class ContourFilledContext(
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
    public val fillColor: FillAes = FillAes(this)
    public val lineColor: ColorAes = ColorAes(this)
    public val lineType: LineTypeAes = LineTypeAes(this)
    public val lineWidth: WidthAes = WidthAes(this)

    // todo speed, flow

    public object Statistics {
        public val X: ContourStat.X = ContourStat.X
        public val Y: ContourStat.Y = ContourStat.Y
        public val LEVEL: ContourStat.Level = ContourStat.Level
        public val GROUP: ContourStat.Group = ContourStat.Group
    }

    public val Stat: Statistics = Statistics

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
}

public inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: DataSource<TX>,
    sourceY: DataSource<TY>,
    sourceZ: DataSource<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
) {
    layers.add(
        ContourFilledContext(data, bins)
            .apply {
                copyFrom(this@contourFilled)
                _x(sourceX)
                _y(sourceY)
                _z(sourceZ)
            }
            .apply(block)
            .toLayer(CONTOUR_FILLED)
    )
}

public inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: Iterable<TX>,
    sourceY: Iterable<TY>,
    sourceZ: Iterable<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
) {
    layers.add(
        ContourFilledContext(data, bins)
            .apply {
                copyFrom(this@contourFilled)
                _x(sourceX)
                _y(sourceY)
                _z(sourceZ)
            }
            .apply(block)
            .toLayer(CONTOUR_FILLED)
    )
}


