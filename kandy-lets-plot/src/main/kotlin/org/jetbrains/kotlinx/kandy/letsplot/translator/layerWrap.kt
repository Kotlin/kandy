/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.translator

import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import org.jetbrains.letsPlot.intern.Feature


internal fun Layer.wrap(
    featureBuffer: MutableList<Feature>,
    datasets: List<TableData>,
    globalMappings: Map<AesName, Mapping>,
    globalSettings: Map<AesName, Setting>,
) {
    val dataset = if (datasetIndex == 0) {
        null
    } else {
        datasets[datasetIndex]
    }
    val addGroups = datasets[datasetIndex] is GroupedData
    val groupKeys = (datasets[datasetIndex] as? GroupedData)?.keys
    //todo
    /*val mappings = if (datasetIndex == 0) {
        globalMappings + mappings
    } else {
        mappings
    }*/
    val mappings = globalMappings + mappings
    val settings = if (datasetIndex == 0) {
        globalSettings + settings
    } else {
        settings
    }
    val df = (datasets[datasetIndex]).dataFrame()
    featureBuffer.add(LayerWrapper(this, addGroups, dataset?.wrap(), mappings, settings, groupKeys))
    freeScales.forEach { (_, freeScale) -> freeScale.wrap(featureBuffer) }
    mappings.forEach { (_, mapping) ->
        //todo group
        mapping.wrapScale(df[mapping.columnID].type(), groupKeys)?.let { featureBuffer.add(it) }
    }
}
