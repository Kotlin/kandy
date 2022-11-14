package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.*

@Serializable
public class CandlestickSeries(
    public override val type: String = "candlestick",
    public override val id: String? = null,
    public override val coordinateSystem: CoordinateSystem? = null,
    public val xAxisIndex: Int? = null,
    public val yAxisIndex: Int? = null,
    public override val name: String? = null,
    public override val colorBy: String? = null,
    public override val legendHoverLink: Boolean? = null,
    public val hoverAnimation: Boolean? = null,
    public val layout: String? = null,
    public val barwidth: Int? = null,
    public val barMinwidth: String? = null,
    public val barMaxWidth: String? = null,
    public override val itemStyle: ItemStyle? = null,
    public override val emphasis: Emphasis? = null,
    public override val blur: Blur? = null,
    public override val select: Select? = null,
    public override val selectedMode: String? = null,
    public val large: Boolean? = null,
    public val largeThreshold: Int? = null,
    public val progressive: Int? = null,
    public val progressiveThreshold: Int? = null,
    public val progressiveChunkMode: String? = null,
    public override val dimensions: List<Dimension>? = null,
    public override val encode: Encode? = null,
    public override val dataGroupId: String? = null,
    public override val data: List<List<String>>? = null,
    public override val markPoint: EchartsMarkPoint? = null,
    public override val markLine: EchartsMarkLine? = null,
    public override val markArea: MarkArea? = null,
    public val clip: Boolean? = null,
    public override val zlevel: Int? = null,
    public override val z: Int? = null,
    public override val silent: Boolean? = null,
    public override val animationDuration: Int? = null,
    public override val animationEasing: String? = null,
    public override val animationDelay: Int? = null,
    public override val universalTransition: UniversalTransition? = null,
    public override val tooltip: Tooltip? = null
) : Series()