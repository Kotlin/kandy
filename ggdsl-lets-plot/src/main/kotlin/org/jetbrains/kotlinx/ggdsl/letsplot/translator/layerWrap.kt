package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotPositionalMappingParameters
import org.jetbrains.letsPlot.intern.Feature


internal fun Layer.wrap(featureBuffer: MutableList<Feature>, datasets: List<TableData>) {
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
    featureBuffer.add(LayerWrapper(this, addGroups, dataset?.wrap()))
    freeScales.forEach { (_, freeScale) -> freeScale.wrap(featureBuffer) }
    mappings.forEach { (aes, mapping) ->
        mapping.parameters?.scale?.wrap(aes, )
        if (mapping is ScaledMapping<*>) {
            // TODO!!!
            val type = mapping.domainType
            mapping.columnScaled.scale.wrap(aes, type/* mapping.domainType*/, mapping.scaleParameters, mapping.columnScaled.source.name() in groupKeys)?.let {
                featureBuffer.add(it)
            }
        }
    }
}
