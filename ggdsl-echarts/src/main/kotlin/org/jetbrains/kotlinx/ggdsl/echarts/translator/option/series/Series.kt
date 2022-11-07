package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.*
import org.jetbrains.kotlinx.ggdsl.echarts.translator.serializers.SeriesSerializer

@Serializable(with = SeriesSerializer::class)
public sealed class Series {
    public abstract val type: String
    public abstract val id: String?
    public abstract val name: String?
    public abstract val colorBy: String? // TODO (need groupBy)
    public abstract val coordinateSystem: CoordinateSystem? // TODO (другая система для Pie)
    public abstract val legendHoverLink: Boolean?
    public abstract val itemStyle: ItemStyle?
    public abstract val emphasis: Emphasis?
    public abstract val blur: Blur?
    public abstract val select: Select?
    public abstract val selectedMode: String?
    public abstract val dimensions: List<Dimension>?
    public abstract val encode: Encode?
    public abstract val dataGroupId: String?
    public abstract val data: List<List<String>>? // TODO!!! Data
    public abstract val markPoint: MarkPoint?
    public abstract val markLine: MarkLine?
    public abstract val markArea: MarkArea?
    public abstract val zlevel: Int?
    public abstract val z: Int?
    public abstract val silent: Boolean?
    public abstract val animationDuration: Int?
    public abstract val animationEasing: String?
    public abstract val animationDelay: Int?
    public abstract val universalTransition: UniversalTransition?
    public abstract val tooltip: Tooltip?
}