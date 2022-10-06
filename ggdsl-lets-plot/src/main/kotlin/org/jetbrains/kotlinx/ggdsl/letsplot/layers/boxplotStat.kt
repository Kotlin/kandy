/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom

public val BOXPLOT_STAT: LetsPlotGeom = LetsPlotGeom("boxplot_stat")

/*
@PlotDslMarker
<<<<<<< HEAD
class OutlierSubContext(parentContext: BindingContext) : SubContext(parentContext), SelfInvocationContext {
    parent: LayerCollectorContext = mutableMapOf()
    val color = OutlierColorAes(parentContext)
    val fillColor = OutlierFillAes(parentContext)
    val symbol = OutlierShapeAes(parentContext)
    val size = OutlierSizeAes(parentContext)
=======
public class OutlierSubContext(parentContext: BindingContext) : SubContext(parentContext), SelfInvocationContext {
    override var data: MutableNamedData = mutableMapOf()
    public val color: OutlierColorAes = OutlierColorAes(parentContext)
    public val fillColor: OutlierFillAes = OutlierFillAes(parentContext)
    public val symbol: OutlierShapeAes = OutlierShapeAes(parentContext)
    public val size: OutlierSizeAes = OutlierSizeAes(parentContext)
>>>>>>> main
}

// TODO Stats
@PlotDslMarker
// todo move x/y?
<<<<<<< HEAD
class BoxplotStatContext<T>(
    parent: LayerCollectorContext,
=======
public class BoxplotStatContext<T>(
    override var data: MutableNamedData,
>>>>>>> main
    varWidth: Boolean?,
) : LayerContext(parent) {
    init {
        varWidth?.let {
            varWidth(it)
        }
    }

    @PublishedApi
    internal val _x: XAes = XAes(this)

    @PublishedApi
    internal val _y: YAes = YAes(this)

    public val x: XDummyAes = XDummyAes(this)
    public val y: YDummyAes = YDummyAes(this)

    public val alpha: AlphaAes = AlphaAes(this)
    public val fillColor: FillAes = FillAes(this)

    public val fatten: FattenAes = FattenAes(this)

    public val width: WidthAes = WidthAes(this)

    /*  TODO*/
    public val borderLineColor: ColorAes = ColorAes(this)
    public val borderLineWidth: SizeAes = SizeAes(this)
    public val borderLineType: LineTypeAes = LineTypeAes(this)

    public val outlier: OutlierSubContext = OutlierSubContext(this)

    @PublishedApi
    internal val varWidth: VarWidthAes = VarWidthAes(this)

    public object Statistics {
        public val X: BoxplotStat.X<String> = BoxplotStat.X<String>() // TODO!!!
        public val MIDDLE: BoxplotStat.Middle = BoxplotStat.Middle
        public val Y_MIN: BoxplotStat.YMin = BoxplotStat.YMin
        public val Y_MAX: BoxplotStat.YMax = BoxplotStat.YMax
        public val LOWER: BoxplotStat.Lower = BoxplotStat.Lower
        public val UPPER: BoxplotStat.Upper = BoxplotStat.Upper
    }

    public val Stat: Statistics = Statistics

    public fun orderBy(stat: BoxplotStat<*>, descending: Boolean = false) {
        val mapping = bindingCollector.mappings[X] as ScaledPositionalUnspecifiedMapping<*>
        if (mapping.scaleParameters == null) {
            mapping.scaleParameters = PositionalParameters(Axis()).apply {
                orderBy = OrderBy(
                    if (stat is BoxplotStat.X) {
                        null
                    } else stat.name,
                    if (descending) {
                        -1
                    } else 1
                )
            }
        }
    }

}

<<<<<<< HEAD
inline fun <reified T : Any, reified R : Any> PlotContext.boxplot(
    sourceX: ColumnPointer<T>,
    sourceY: ColumnPointer<R>,
=======
public inline fun <reified T : Any, reified R : Any> PlotContext.boxplot(
    sourceX: DataSource<T>,
    sourceY: DataSource<R>,
>>>>>>> main
    varWidth: Boolean? = null,
    block: BoxplotStatContext<T>.() -> Unit
) {
    layers.add(
        BoxplotStatContext<T>(data, varWidth)
            .apply {
                copyFrom(this@boxplot)
                _x(sourceX.scaled(categoricalPos()))
                _y(sourceY)
            }
            .apply(block)
            .toLayer(BOXPLOT_STAT)
    )
}

public inline fun <reified T : Any, reified R : Any> PlotContext.boxplot(
    sourceX: Iterable<T>,
    sourceY: Iterable<R>,
    varWidth: Boolean? = null,
    block: BoxplotStatContext<T>.() -> Unit
) {
    layers.add(
        BoxplotStatContext<T>(data, varWidth)
            .apply {
                copyFrom(this@boxplot)
                _x(sourceX.scaled(categoricalPos()))
                _y(sourceY)
            }
            .apply(block)
            .toLayer(BOXPLOT_STAT)
    )
}

 */

