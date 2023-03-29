package org.jetbrains.kotlinx.ggdsl.dsl.internal

import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.concat
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.data.GroupedData
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

public class GroupByPlotContext<T>(
    private val groupBy: GroupBy<T, T>
) : LayerPlotContext, ColumnsContainer<T> by groupBy.groups.concat() {
    override val bindingCollector: BindingCollector = BindingCollector()
    override val layers: MutableList<Layer> = mutableListOf()
    override val plotContext: PlotContext = this
    override val datasetIndex: Int = 0
    override val datasetHandlers: MutableList<DatasetHandler> = mutableListOf(
        DatasetHandler(GroupedData(groupBy))
    )
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()

/*
    public fun <C> columns(selector: ColumnsSelector<G, C>): List<DataColumn<C>> = groupBy.groups.concat().get(selector)
    public fun <C> columns(vararg columns: String): List<AnyCol> = groupBy.groups.concat().getColumns(*columns)


 */
}
