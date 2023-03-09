package org.jetbrains.kotlinx.ggdsl.dsl.internal

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.concat
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.data.GroupedData
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

public class GroupByPlotContext<T, G>(
    private val groupBy: GroupBy<T, G>
) : LayerPlotContext {
    override val bindingCollector: BindingCollector = BindingCollector()
    override val layers: MutableList<Layer> = mutableListOf()
    override val plotContext: PlotContext = this
    override val datasetIndex: Int = 0
    override val datasetHandlers: MutableList<DatasetHandler> = mutableListOf(
        DatasetHandler(GroupedData(groupBy))
    )
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()

    public val groupKey: DataFrame<T> = groupBy.keys
    public val column: DataFrame<G> = groupBy.groups.concat()

}
