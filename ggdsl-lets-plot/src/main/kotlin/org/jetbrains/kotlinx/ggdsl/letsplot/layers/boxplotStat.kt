package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.*
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
class BoxplotStatContext(
    override var data: MutableNamedData,
    varWidth: Boolean?,
) : LayerContext() {
    init {
        varWidth?.let {
            varWidth(it)
        }
    }
    @PublishedApi
    internal val x = XAes(this)
    @PublishedApi
    internal val y = YAes(this)

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

    /*
    @PublishedApi
    internal inline fun <reified T : Any> Stat<T>.toDataSource(): DataSource<T> {
        return DataSource(name, typeOf<T>())
    }

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

    inline operator fun <reified DomainType : Any, RangeType : Any>
            MappableNonPositionalAes<RangeType>.invoke(
        stat: Stat<DomainType>
    ): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
        val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
            this.name,
            stat.toDataSource().scaled(),
            typeOf<DomainType>()
        )
        context.bindingCollector.mappings[this.name] = mapping
        return mapping
    }

     */

}

inline fun <reified T : Any, reified R : Any> PlotContext.boxplot(
    sourceX: DataSource<T>,
    sourceY: DataSource<R>,
    varWidth: Boolean? = null,
    block: BoxplotStatContext.() -> Unit
) {
    layers.add(
        BoxplotStatContext(data, varWidth)
            .apply {
                copyFrom(this@boxplot)
                x(sourceX)
                y(sourceY)
            }
            .apply(block)
            .toLayer(BOXPLOT_STAT)
    )
}

inline fun <reified T : Any, reified R : Any> PlotContext.boxplot(
    sourceX: Iterable<T>,
    sourceY: Iterable<R>,
    varWidth: Boolean? = null,
    block: BoxplotStatContext.() -> Unit
) {
    layers.add(
        BoxplotStatContext(data, varWidth)
            .apply {
                copyFrom(this@boxplot)
                x(sourceX)
                y(sourceY)
            }
            .apply(block)
            .toLayer(BOXPLOT_STAT)
    )
}

