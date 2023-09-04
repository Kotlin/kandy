package org.jetbrains.kotlinx.kandy.letsplot.stat

import org.jetbrains.kotlinx.dataframe.ColumnSelector
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.BarsContextInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.BorderLineContext
import org.jetbrains.kotlinx.kandy.letsplot.position.Position
import org.jetbrains.kotlinx.kandy.letsplot.position.position
import org.jetbrains.kotlinx.kandy.letsplot.stat.bin.BinStatContext
import org.jetbrains.kotlinx.kandy.letsplot.stat.bin.BinXPos
import org.jetbrains.kotlinx.kandy.letsplot.stat.bin.Bins
import org.jetbrains.kotlinx.kandy.letsplot.stat.bin.countBinsImpl

public class StatBin(data: NamedData,
                     column: ColumnReference<*>,
                     bins: Bins,
                     binXPos: BinXPos
) {
    public val data: NamedData = countBinsImpl(data, column, bins, binXPos)
}

public fun Iterable<Number>.statBin(bins: Bins = Bins.byNumber(10),
                                    binXPos: BinXPos = BinXPos.center(0.0)): StatBin {
    val column = this.toColumn("__values")
    val df = dataFrameOf(column)
    return StatBin(NamedData(df), column, bins, binXPos)
}

public fun DataColumn<Number>.statBin(bins: Bins = Bins.byNumber(10),
                                    binXPos: BinXPos = BinXPos.center(0.0)): StatBin {
    val df = dataFrameOf(this)
    return StatBin(NamedData(df), this, bins, binXPos)
}

public fun <T, C : Number> DataFrame<T>.statBin(
    bins: Bins = Bins.byNumber(10),
    binXPos: BinXPos = BinXPos.center(0.0),
    column: ColumnSelector<T, C>
): StatBin {
    return StatBin(NamedData(this), get(column), bins, binXPos)
}

public fun <T> DataFrame<T>.statBin(column: String,
                                    bins: Bins = Bins.byNumber(10),
                                    binXPos: BinXPos = BinXPos.center(0.0),): StatBin {
    return StatBin(NamedData(this), get(column), bins, binXPos)
}

public inline fun StatBin.plot(block: StatBinPlotContext.() -> Unit): Plot {
    return StatBinPlotContext(this).apply(block).toPlot()
}

public class StatBinPlotContext(statBin: StatBin) : LayerPlotContext(), BinStatContext {
    override val _plotContext: PlotContext = this
    override val datasetHandlers: MutableList<DatasetHandler> = mutableListOf(
        DatasetHandler(statBin.data)
    )
    override val plotFeatures: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
}

public class HistogramPlotContext(statBin: StatBin) : SingleLayerPlotContext, BarsContextInterface, BinStatContext {
    override val bindingCollector: BindingCollector = BindingCollector()
    override val borderLine: BorderLineContext = BorderLineContext(this)
    override val layerFeatures: MutableMap<FeatureName, LayerFeature> = mutableMapOf()

    private val datasetIndex: Int = 0
    override val layer: Layer
        get() = toLayer(true)
    override val datasetHandlers: MutableList<DatasetHandler> = mutableListOf(
        DatasetHandler(statBin.data)
    )
    override val plotFeatures: MutableMap<FeatureName, PlotFeature> = mutableMapOf()

    override fun toLayer(layersInheritMappings: Boolean): Layer {
        return Layer(
            datasetIndex,
            geom,
            bindingCollector.mappings,
            bindingCollector.settings,
            layerFeatures,
            bindingCollector.freeScales,
            layersInheritMappings
        )
    }
}

public fun Iterable<Number>.histogram(bins: Bins = Bins.byNumber(10),
                                    binXPos: BinXPos = BinXPos.center(0.0),
                                      plotAction: HistogramPlotContext.() -> Unit = {}): Plot {
    return HistogramPlotContext(statBin(bins, binXPos)).apply {
        x(Stat.BINS)
        y(Stat.COUNT)
        position = Position.Dodge()
        plotAction()
    }.toPlot()
}

public fun DataFrame<*>.histogram(column: String,
    bins: Bins = Bins.byNumber(10),
                                      binXPos: BinXPos = BinXPos.center(0.0),
                                      plotAction: HistogramPlotContext.() -> Unit = {}): Plot {
    return HistogramPlotContext(statBin(column, bins, binXPos)).apply {
        x(Stat.BINS)
        y(Stat.COUNT)
        position = Position.Dodge()
        plotAction()
    }.toPlot()
}

public fun DataColumn<Number>.histogram(bins: Bins = Bins.byNumber(10),
                                       binXPos: BinXPos = BinXPos.center(0.0),
                                       plotAction: HistogramPlotContext.() -> Unit = {}): Plot {
    return HistogramPlotContext(statBin(bins, binXPos)).apply {
        x(Stat.BINS)
        y(Stat.COUNT)
        position = Position.Dodge()
        plotAction()
    }.toPlot()
}
