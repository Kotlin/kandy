/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.concat
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

/**
 * Represents a plotting context where the data is initially grouped using [GroupBy].
 * This context facilitates the creation and management of visualizations with data that's grouped by certain criteria.
 * It also provides column access via delegation to the concatenated representation of [groupBy].
 *
 * @param groupBy The initial dataset that is grouped using [GroupBy].
 *
 * @property _plotContext The actual plotting context for this instance, set to this class itself.
 * @property datasetHandlers A list that manages datasets within the plot. Initialized with the grouped data from [groupBy].
 * @property plotFeatures A mutable map to store and manage features associated with the plot.
 */
@PlotDslMarker
public class GroupByPlotContext<T>(
    private val groupBy: GroupBy<T, T>
) : LayerPlotContext(), ColumnsContainer<T> by groupBy.concat() {
    override val _plotContext: PlotContext = this
    override val datasetHandlers: MutableList<DatasetHandler> = mutableListOf(
        DatasetHandler(GroupedData(groupBy))
    )
    override val plotFeatures: MutableMap<FeatureName, PlotFeature> = mutableMapOf()
}
