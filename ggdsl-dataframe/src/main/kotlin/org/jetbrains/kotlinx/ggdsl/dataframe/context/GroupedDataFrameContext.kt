package org.jetbrains.kotlinx.ggdsl.dataframe.context

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.first
import org.jetbrains.kotlinx.ggdsl.dataframe.data.GroupedByWrapper
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingCollector
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

/**
 * Wrapper of [GroupBy] that allows to access grouping keys and dataframe columns.
 *
 * @property groupKey grouping keys accessor.
 * @property column columns accessor.
 */
public class GroupedDataFrameContext<T, G>(
    private val groupBy: GroupBy<T, G>
) : LayerPlotContext, LayerCollectorContextImmutable {
    public val groupKey: DataFrame<T> = groupBy.keys
    public val column: DataFrame<G> = groupBy.groups.first() // TODO
    override val bindingCollector: BindingCollector = BindingCollector()
    override val layers: MutableList<Layer> = mutableListOf()
    override val data: TableData = GroupedByWrapper(groupBy)
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
}
