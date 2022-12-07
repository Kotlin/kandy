package org.jetbrains.kotlinx.ggdsl.echarts.translator.option

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.Series
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.TextStyle
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Tooltip

@Serializable
internal data class Option(
    val title: Title? = null,
    val legend: Legend? = null,
    val grid: Grid? = null,
    val xAxis: Axis? = null,
    val yAxis: Axis? = null,
    val polar: Polar? = null,
    val radiusAxis: RadiusAxis? = null,
    val angleAxis: AngleAxis? = null,
    val radar: Radar? = null,
//    val dataZoom: List<DataZoom>,
    val visualMap: List<VisualMap>? = null,
    val tooltip: Tooltip? = null,
//    val axisPointer: AxisPointer,
//    val toolbox: Toolbox,
//    val brush: Brush,
//    val geo: Geo,
//    val parallel: Parallel,
//    val parallelAxis: ParallelAxis,
//    val timeline: Timeline,
//    val graphic: Graphic,
//    val calendar: Calendar,
    val dataset: Dataset? = null,
//    val aria: Aria,
    val series: List<Series>? = null,
//    val darkMode: Boolean,
//    val color: Color,
//    val backgroundColor: Color,
    val textStyle: TextStyle? = null,
    val animation: Boolean? = null,
    val animationThreshold: Int? = null,
    val animationDuration: Int? = null,
    val animationEasing: String? = null,
    val animationDelay: Int? = null,
    @Transient val plotSize: Pair<Int, Int> = 800 to 600
//    val animationDurationUpdate: Int,
//    val animationEasingUpdate: String,
//    val animationDelayUpdate: 0,
//    val stateAnimation: StateAnimation,
//    val blendMode: String,
//    val hoverLayerThreshold: Int,
//    val useUtc: Boolean,
//    val options: List<ECUnitOption>,
//    val media: List<Media>
)