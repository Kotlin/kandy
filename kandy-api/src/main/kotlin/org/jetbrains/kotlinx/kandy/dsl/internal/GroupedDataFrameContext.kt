/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

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
