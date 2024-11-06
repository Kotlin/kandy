/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.translator

import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.GroupedData
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import org.jetbrains.kotlinx.kandy.letsplot.data.GeoSpatialData
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.spatial.CRSCode


internal fun Layer.wrap(
    featureBuffer: MutableList<Feature>,
    datasets: List<TableData>,
    globalMappings: Map<Aes, Mapping>,
    globalSettings: Map<Aes, Setting>,
) {
    val dataset = if (datasetIndex == 0) {
        val dataset = datasets[datasetIndex]
        // can't provide geodata as main dataset
        if (dataset is GeoSpatialData) {
            dataset
        } else {
            null
        }
    } else {
        datasets[datasetIndex]
    }
    val addGroups = datasets[datasetIndex] is GroupedData
    val groupKeys = (datasets[datasetIndex] as? GroupedData)?.keys
    val mappings = if (inheritsBindings) {
        globalMappings + mappings
    } else {
        mappings
    }
    val settings = if (inheritsBindings) {
        globalSettings + settings
    } else {
        settings
    }
    val map = if (dataset is GeoSpatialData) {
        dataset.toSpatialDataset()
    } else null
    val crsProvided = map?.let {
        val crs = it.crs
        if ((crs != null) && !CRSCode.isWGS84Code(crs)) {
            "provided"
        } else null
    }
    featureBuffer.add(LayerWrapper(
        this, addGroups, dataset?.wrap(), mappings, settings, groupKeys, map, null, crsProvided))
    freeScales.forEach { (_, freeScale) -> freeScale.wrap(featureBuffer) }
    val df = (datasets[datasetIndex]).dataFrame()
    mappings.forEach { (_, mapping) ->
        mapping.wrapScale(df[mapping.columnID].type(), groupKeys)?.let { featureBuffer.add(it) }
    }
}
