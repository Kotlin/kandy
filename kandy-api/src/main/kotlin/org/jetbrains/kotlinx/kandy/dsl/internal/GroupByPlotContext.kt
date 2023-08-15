/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.concat
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

/**
 * Plotting context with [GroupBy] as an initial dataset.
 *
 * @param groupBy initial grouped dataset.
 */
public class GroupByPlotContext<T>(
    private val groupBy: GroupBy<T, T>
) : LayerPlotContext, ColumnsContainer<T> by groupBy.concat() {
    override val bindingCollector: BindingCollector = BindingCollector()
    override val layers: MutableList<Layer> = mutableListOf()
    override val plotContext: PlotContext = this
    override val datasetIndex: Int = 0
    override val datasetHandlers: MutableList<DatasetHandler> = mutableListOf(
        DatasetHandler(GroupedData(groupBy))
    )
    override val plotFeatures: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
    override val layersInheritMappings: Boolean = true
}
