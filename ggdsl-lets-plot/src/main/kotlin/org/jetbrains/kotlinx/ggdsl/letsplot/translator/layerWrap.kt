package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.CountedGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.GroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.letsPlot.intern.Feature
import kotlin.reflect.KType

internal fun GroupedDataInterface.groupKeys(): List<String> {
    return when(this) {
        is CountedGroupedDataInterface -> keys.nameToValues.keys.toList()
        is LazyGroupedDataInterface -> keys
    }
}

internal fun Layer.wrap(featureBuffer: MutableList<Feature>, plotDataset: TableData?) {
    val addGroups: Boolean = if (dataset == null) {
        plotDataset is GroupedDataInterface
    } else {
        dataset is GroupedDataInterface
    }
    val groupKeys: List<String> = if (dataset == null) {
        (plotDataset as? GroupedDataInterface)?.groupKeys()
    } else {
        (dataset as? GroupedDataInterface)?.groupKeys()
    } ?: listOf()
    featureBuffer.add(LayerWrapper(this, addGroups))
    freeScales.forEach { (_, freeScale) -> freeScale.wrap(featureBuffer) }
    mappings.forEach { (aes, mapping) ->
        if (mapping is ScaledMapping<*>) {
            // TODO!!!
            val type = mapping.domainType
            mapping.columnScaled.scale.wrap(aes, type/* mapping.domainType*/, mapping.scaleParameters, mapping.columnScaled.source.name in groupKeys)?.let {
                featureBuffer.add(it)
            }
        }
    }
}
