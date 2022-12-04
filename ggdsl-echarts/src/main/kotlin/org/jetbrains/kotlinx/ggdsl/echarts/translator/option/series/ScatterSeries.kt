package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.aes.SIZE
import org.jetbrains.kotlinx.ggdsl.echarts.aes.SYMBOL
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.AnimationLineFeature
import org.jetbrains.kotlinx.ggdsl.echarts.settings.Symbol
import org.jetbrains.kotlinx.ggdsl.echarts.translator.getNPSValue
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.*
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.marks.*
import org.jetbrains.kotlinx.ggdsl.ir.Layer

internal fun Layer.toPointSeries(name: String?, encode: Encode?): ScatterSeries {
    val symbol = settings.getNPSValue<Symbol>(SYMBOL)
    val animation = (features[AnimationLineFeature.FEATURE_NAME] as? AnimationLineFeature)
    val size = settings.getNPSValue(SIZE) ?: symbol?.size

    return ScatterSeries(
        name = name,
        symbol = symbol?.name,
        symbolSize = size,
        symbolRotate = symbol?.rotate,
        itemStyle = settings.getItemStyle(),
        encode = encode,
        markPoint = features.getEchartsMarkPoint(),
        markLine = features.getEchartsMarkLine(),
        animation = animation?.enable,
        animationThreshold = animation?.threshold,
        animationDuration = animation?.duration,
        animationEasing = animation?.easing?.name,
        animationDelay = animation?.delay,
    )
}

@Serializable
public class ScatterSeries(
    public override val type: String = "scatter",
    public override val id: String? = null,
    public override val name: String? = null,
    public override val colorBy: String? = null,
    public override val coordinateSystem: CoordinateSystem? = null,
    public val xAxisIndex: Int? = null,
    public val yAxisIndex: Int? = null,
    public val polarIndex: Int? = null,
    public val geoIndex: Int? = null,
    public val calendarIndex: Int? = null,
    public override val legendHoverLink: Boolean? = null,
    public val symbol: String? = null,
    public val symbolSize: Double? = null,
    public val symbolRotate: Int? = null,
    public val symbolKeepAspect: Boolean? = null,
    public val symbolOffset: Int? = null,
    public val large: Boolean? = null,
    public val largeThreshold: Int? = null,
    public val cursor: String? = null,
    public val label: Label? = null,
    public val labelLine: LabelLine? = null,
    public val labelLayout: LabelLayout? = null,
    public override val itemStyle: ItemStyle? = null,
    public override val emphasis: Emphasis? = null,
    public override val blur: Blur? = null,
    public override val select: Select? = null,
    public override val selectedMode: String? = null,
    public val progressive: Int? = null,
    public val progressiveThreshold: Int? = null,
    public override val dimensions: List<Dimension>? = null,
    public override val encode: Encode? = null,
    public val seriesLayoutBy: String? = null,
    public val datasetIndex: Int? = null,
    public override val dataGroupId: String? = null,
    public override val data: List<List<String>>? = null,
    public override val markPoint: EchartsMarkPoint? = null,
    public override val markLine: EchartsMarkLine? = null,
    public override val markArea: MarkArea? = null,
    public val clip: Boolean? = null,
    public override val zlevel: Int? = null,
    public override val z: Int? = null,
    public override val silent: Boolean? = null,
    public val animation: Boolean? = null,
    public val animationThreshold: Int? = null,
    public override val animationDuration: Int? = null,
    public override val animationEasing: String? = null,
    public override val animationDelay: Int? = null,
    public val animationDurationUpdate: Int? = null,
    public val animationEasingUpdate: String? = null,
    public val animationDelayUpdate: Int? = null,
    public override val universalTransition: UniversalTransition? = null,
    public override val tooltip: Tooltip? = null
) : Series()