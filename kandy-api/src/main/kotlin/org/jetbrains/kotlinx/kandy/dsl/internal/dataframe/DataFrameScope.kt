package org.jetbrains.kotlinx.kandy.dsl.internal.dataframe

import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.dsl.internal.MultiLayerPlotBuilder

/**
 * Represents a plot builder data scope with grouped dataset
 * created by [DataFramePlotBuilder.withData].
 *
 * @param T The type of the DataFrame.
 */
public class DataFrameScope<T> @PublishedApi internal constructor(
    dataFrame: DataFrame<T>,
    override val plotBuilder: MultiLayerPlotBuilder,
    override val datasetIndex: Int,
) : LayerCreatorScope(), ColumnsContainer<T> by dataFrame {
    override val layersInheritMappings: Boolean = false
}
