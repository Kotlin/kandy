/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.marks

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.features.marks.MarkAreaFeature
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Blur
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Emphasis
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.ItemStyle
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Label
import org.jetbrains.kotlinx.ggdsl.echarts.translator.serializers.DataMarkAreaSerializer
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

internal fun Map<FeatureName, LayerFeature>.getEchartsMarkArea(): EchartsMarkArea? {
    val dataMarkLines = (this[MarkAreaFeature.FEATURE_NAME] as? MarkAreaFeature)?.areas?.map {
        DataMarkArea(listOf(it.point1.toDataMarkPoint(it.name), it.point2.toDataMarkPoint()))
    }

    return dataMarkLines?.let { EchartsMarkArea(data = dataMarkLines) }
}

@Serializable(with = DataMarkAreaSerializer::class)
internal data class DataMarkArea(
    val listOfPoints: List<DataMarkPoint>? = null,
)

@Serializable
internal data class EchartsMarkArea(
    val silent: Boolean? = null,
    val label: Label? = null,
    val itemStyle: ItemStyle? = null,
    val emphasis: Emphasis? = null,
    val blur: Blur? = null,
    val data: List<DataMarkArea>? = null,
    val animation: Boolean? = null,
    val animationThreshold: Int? = null,
    val animationDuration: Int? = null,
    val animationEasing: String? = null,
    val animationDelay: Int? = null,
    val animationDurationUpdate: Int? = null,
    val animationEasingUpdate: String? = null,
    val animationDelayUpdate: Int? = null
)