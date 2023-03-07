package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.GroupedData
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.letsPlot.intern.Feature


internal fun Layer.wrap(featureBuffer: MutableList<Feature>, plotDataset: TableData?) {
    val addGroups: Boolean = if (dataset == null) {
        plotDataset is GroupedData
    } else {
        dataset is GroupedData
    }
    val groupKeys: List<String> = if (dataset == null) {
        (plotDataset as? GroupedData)?.keys
    } else {
        (dataset as? GroupedData)?.keys
    } ?: listOf()
    featureBuffer.add(LayerWrapper(this, addGroups))
    freeScales.forEach { (_, freeScale) -> freeScale.wrap(featureBuffer) }
    mappings.forEach { (aes, mapping) ->
        if (mapping is ScaledMapping<*>) {
            // TODO!!!
            val type = mapping.domainType
            mapping.columnScaled.scale.wrap(aes, type/* mapping.domainType*/, mapping.scaleParameters, mapping.columnScaled.source.name() in groupKeys)?.let {
                featureBuffer.add(it)
            }
        }
    }
}
