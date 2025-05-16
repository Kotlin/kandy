package org.jetbrains.kotlinx.kandy.echarts.translator.option.series

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.marks.EchartsMarkArea
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.marks.EchartsMarkLine
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.marks.EchartsMarkPoint

@OptIn(ExperimentalSerializationApi::class)
@Serializable
internal sealed class Series {
    //    abstract val type: String
    abstract val id: String?
    abstract val name: String?
    abstract val colorBy: String? // TODO (need groupBy)
    abstract val coordinateSystem: String? // TODO (другая система для Pie)
    abstract val legendHoverLink: Boolean?
    abstract val itemStyle: ItemStyle?
    abstract val emphasis: Emphasis?
    abstract val blur: Blur?
    abstract val select: Select?
    abstract val selectedMode: String?
    abstract val dimensions: List<Dimension>?
    abstract val encode: Encode?
    abstract val dataGroupId: String?
    abstract val data: List<Any?>? // TODO!!! Data
    abstract val markPoint: EchartsMarkPoint?
    abstract val markLine: EchartsMarkLine?
    abstract val markArea: EchartsMarkArea?
    abstract val zlevel: Int?
    abstract val z: Int?
    abstract val silent: Boolean?
    abstract val animationDuration: Int?
    abstract val animationEasing: String?
    abstract val animationDelay: Int?
    abstract val universalTransition: UniversalTransition?
    abstract val tooltip: EchartsTooltip?
}