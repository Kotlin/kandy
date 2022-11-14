package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.SMOOTH
import org.jetbrains.kotlinx.ggdsl.echarts.SYMBOL
import org.jetbrains.kotlinx.ggdsl.echarts.features.Stack
import org.jetbrains.kotlinx.ggdsl.echarts.features.markPoint.MarkLineFeature
import org.jetbrains.kotlinx.ggdsl.echarts.features.markPoint.MarkPointFeature
import org.jetbrains.kotlinx.ggdsl.echarts.translator.getNPSValue
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.Symbol
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.*
import org.jetbrains.kotlinx.ggdsl.ir.Layer

internal fun Layer.toLineSeries(name: String?, encode: Encode?): LineSeries {
    val symbol = settings.getNPSValue<Symbol>(SYMBOL)
    val smooth = settings.getNPSValue<Boolean>(SMOOTH)
    val stack = (features[Stack.FEATURE_NAME] as? Stack)?.name
    val dataMarkPoints = (features[MarkPointFeature.FEATURE_NAME] as? MarkPointFeature)?.points?.map {
        DataMarkPoint(it.name, it.type, it.valueMP, it.coord?.toList(), it.x, it.y)
    }
    val dataMarkLines = (features[MarkLineFeature.FEATURE_NAME] as? MarkLineFeature)?.lines?.map {
        DataMarkLine(
            it.nameML,
            it.typeML,
            it.xAxis,
            it.yAxis,
            if (it.point1 != null)
                listOf(
                    DataMarkPoint(
                        it.nameML,
                        it.point1?.type,
                        it.point1?.valueMP,
                        it.point1?.coord?.toList(),
                        it.point1?.x,
                        it.point1?.y
                    ),
                    DataMarkPoint(
                        null,
                        it.point2?.type,
                        it.point2?.valueMP,
                        it.point2?.coord?.toList(),
                        it.point2?.x,
                        it.point2?.y
                    )
                ) else null
        )
    }

    return LineSeries(
        name = name,
        symbol = symbol?.name,
        symbolSize = symbol?.size,
        symbolRotate = symbol?.rotate,
        showSymbol = symbol != null,
        stack = stack,
        smooth = smooth,
        encode = encode,
        markPoint = EchartsMarkPoint(data = dataMarkPoints),
        markLine = EchartsMarkLine(data = dataMarkLines)
    )
}

@Serializable
public class LineSeries(
    public override val type: String = "line",
    public override val id: String? = null,
    public override val name: String? = null,
    public override val colorBy: String? = null, // TODO (need groupBy)
    public override val coordinateSystem: CoordinateSystem? = null,
    public val xAxisIndex: Int? = null,
    public val yAxisIndex: Int? = null,
    public val polarIndex: Int? = null,
    public val symbol: String? = null,
    public val symbolSize: Int? = null,
    public val symbolRotate: Int? = null,
    public val symbolKeepAspect: Boolean? = null,
    public val symbolOffset: Pair<String, String>? = null,
    public val showSymbol: Boolean? = null,
    public val showAllSymbol: String? = null, // auto true false,
    public override val legendHoverLink: Boolean? = null,
    public val stack: String? = null, // TODO
    public val stackStrategy: StackStrategy? = null,
    public val cursor: String? = null,
    public val connectNulls: Boolean? = null, // TODO
    public val clip: Boolean? = null,
    public val triggerLineEvent: Boolean? = null,
    public val step: String? = null,
    public val label: Label? = null,
    public val endLabel: Label? = null,
    public val labelLine: LabelLine? = null,
    public val labelLayout: LabelLayout? = null,
    public override val itemStyle: ItemStyle? = null,
    public val lineStyle: LineStyle? = null,
    public val areaStyle: AreaStyle? = null,
    public override val emphasis: Emphasis? = null,
    public override val blur: Blur? = null,
    public override val select: Select? = null,
    public override val selectedMode: String? = null, // string boolean
    public val smooth: String? = null, // boolean number
    public val smoothMonotone: String? = null,
    public val sampling: String? = null,
    public override val dimensions: List<Dimension>? = null,
    public override val encode: Encode? = null,
    public val seriesLayoutBy: String? = null,
    public val datasetIndex: Int? = null,
    public override val dataGroupId: String? = null,
    public override val data: List<List<String>>? = null, // TODO!!! Data
    public override val markPoint: MarkPoint? = null,
    public override val markLine: MarkLine? = null,
    public override val markArea: MarkArea? = null,
    public override val zlevel: Int? = null,
    public override val z: Int? = null,
    public override val silent: Boolean? = null,
    public val animation: Boolean? = null,
    public val animationThreshold: Int? = null,
    public override val animationDuration: Int? = null,
    public override val animationEasing: String? = null,
    public override val animationDelay: Int? = null,
    public val animationDurationUpdate: Int? = null,
    public val animationDelayUpdate: Int? = null,
    public override val universalTransition: UniversalTransition? = null,
    public override val tooltip: Tooltip? = null
) : Series()