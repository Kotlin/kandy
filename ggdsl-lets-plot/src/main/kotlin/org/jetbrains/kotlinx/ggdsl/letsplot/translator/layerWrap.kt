package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Mapping
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.letsPlot.intern.Feature


internal fun Layer.wrap(
    featureBuffer: MutableList<Feature>,
    datasets: List<TableData>,
    globalMappings:  Map<AesName, Mapping>,
) {
    val addGroups: Boolean = false/*if (dataset == null) {
        plotDataset is GroupedData
    } else {
        dataset is GroupedData
    }
    */
    /*
    val groupKeys: List<String> = if (dataset == null) {
        (plotDataset as? GroupedData)?.keys
    } else {
        (dataset as? GroupedData)?.keys
    } ?: listOf()

     */
    val dataset = if (datasetIndex == 0) {
        null
    } else {
        datasets[datasetIndex]
    }
    //todo
    val mappings = if (datasetIndex == 0) {
        globalMappings + mappings
    } else {
        mappings
    }
    val df = (datasets[datasetIndex] as NamedData).dataFrame
    featureBuffer.add(LayerWrapper(this, addGroups, dataset?.wrap(), mappings))
    freeScales.forEach { (_, freeScale) -> freeScale.wrap(featureBuffer) }
    mappings.forEach { (_, mapping) ->
        //todo group
        mapping.wrapScale(df[mapping.columnID].type(), false)?.let { featureBuffer.add(it) }
    }
}
