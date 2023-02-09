package org.jetbrains.kotlinx.ggdsl.dataframe.context

import org.jetbrains.kotlinx.dataframe.*
import org.jetbrains.kotlinx.dataframe.columns.ColumnPath
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.data.DataFrameWrapper
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingCollector
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

public class DataFramePlotContext<T>(
    private val dataFrame: DataFrame<T>
) : LayerPlotContext, LayerCollectorContextImmutable, ColumnsContainer<T> {
    override val bindingCollector: BindingCollector = BindingCollector()
    override val layers: MutableList<Layer> = mutableListOf()
    override val data: TableData = DataFrameWrapper(dataFrame)
    override val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()


    override fun columns(): List<AnyCol> {
        return dataFrame.columns()
    }

    override fun columnsCount(): Int {
        return dataFrame.columnsCount()
    }

    override fun containsColumn(name: String): Boolean {
        return dataFrame.containsColumn(name)
    }

    override fun containsColumn(path: ColumnPath): Boolean {
        return dataFrame.containsColumn(path)
    }

    override fun <C> get(columns: ColumnsSelector<T, C>): List<DataColumn<C>> {
        return dataFrame[columns]
    }

    override fun getColumnIndex(name: String): Int {
        return dataFrame.getColumnIndex(name)
    }

    override fun getColumnOrNull(index: Int): AnyCol? {
        return dataFrame.getColumnOrNull(index)
    }

    override fun getColumnOrNull(name: String): AnyCol? {
        return dataFrame.getColumnOrNull(name)
    }

    override fun getColumnOrNull(path: ColumnPath): AnyCol? {
        return dataFrame.getColumnOrNull(path)
    }

    override fun <R> getColumnOrNull(column: ColumnReference<R>): DataColumn<R>? {
        return dataFrame.getColumnOrNull(column)
    }

    override fun <R> getColumnOrNull(column: ColumnSelector<T, R>): DataColumn<R>? {
        return dataFrame.getColumnOrNull(column)
    }

}