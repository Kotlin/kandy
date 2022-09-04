package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledPositionalUnspecifiedMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.BoxplotStat
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.OrderBy
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.PositionalParameters
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.Axis
import org.jetbrains.kotlinx.ggdsl.util.context.SelfInvocationContext

val BOXPLOT_STAT = LetsPlotGeom("boxplot_stat")


@PlotDslMarker
class OutlierSubContext(parentContext: BindingContext) : SubContext(parentContext), SelfInvocationContext {
    override var data: MutableNamedData = mutableMapOf()
    val color = OutlierColorAes(parentContext)
    val fillColor = OutlierFillAes(parentContext)
    val symbol = OutlierShapeAes(parentContext)
    val size = OutlierSizeAes(parentContext)
}

// TODO Stats
@PlotDslMarker
// todo move x/y?
class BoxplotStatContext<T>(
    override var data: MutableNamedData,
    varWidth: Boolean?,
) : LayerContext() {
    init {
        varWidth?.let {
            varWidth(it)
        }
    }

    @PublishedApi
    internal val _x = XAes(this)

    @PublishedApi
    internal val _y = YAes(this)

    val x = XDummyAes(this)
    val y = YDummyAes(this)

    val alpha = AlphaAes(this)
    val fillColor = FillAes(this)

    val fatten = FattenAes(this)

    val width = WidthAes(this)

    /*  TODO*/
    val borderLineColor = ColorAes(this)
    val borderLineWidth = SizeAes(this)
    val borderLineType = LineTypeAes(this)

    val outlier = OutlierSubContext(this)

    @PublishedApi
    internal val varWidth = VarWidthAes(this)

    object Statistics {
        val X = BoxplotStat.X<String>() // TODO!!!
        val MIDDLE = BoxplotStat.Middle
        val Y_MIN = BoxplotStat.YMin
        val Y_MAX = BoxplotStat.YMax
        val LOWER = BoxplotStat.Lower
        val UPPER = BoxplotStat.Upper
    }

    val Stat = Statistics

    fun orderBy(stat: BoxplotStat<*>, descending: Boolean = false) {
        val mapping = bindingCollector.mappings[X] as ScaledPositionalUnspecifiedMapping<*>
        if (mapping.scaleParameters == null) {
            mapping.scaleParameters = PositionalParameters(Axis()).apply {
                orderBy = OrderBy(
                    stat.name, if (descending) {
                        -1
                    } else 1
                )
            }
        }
    }

}

inline fun <reified T : Any, reified R : Any> PlotContext.boxplot(
    sourceX: DataSource<T>,
    sourceY: DataSource<R>,
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

inline fun <reified T : Any, reified R : Any> PlotContext.boxplot(
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

