package org.jetbrains.kotlinx.kandy.dsl.internal

/*
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
    override val data: TableData = GroupedData(groupBy)
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
}

 */
