/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.option.series

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.echarts.features.StackFeature
import org.jetbrains.kotlinx.kandy.echarts.features.animation.AnimationLayerFeature
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.SMOOTH
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.STEP
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.SYMBOL
import org.jetbrains.kotlinx.kandy.echarts.settings.Step
import org.jetbrains.kotlinx.kandy.echarts.settings.Symbol
import org.jetbrains.kotlinx.kandy.echarts.translator.getNPSValue
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.marks.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.Measurement
import org.jetbrains.kotlinx.kandy.ir.Layer

internal fun Layer.toLineSeries(name: String?, encode: Encode?): LineSeries {
    val symbol = settings.getNPSValue<Symbol>(SYMBOL)
    val smooth = settings.getNPSValue<Boolean>(SMOOTH)
    val step = settings.getNPSValue<Step>(STEP)?.type
    val stack = (features[StackFeature.FEATURE_NAME] as? StackFeature)?.name
    val animation = (features[AnimationLayerFeature.FEATURE_NAME] as? AnimationLayerFeature)

    return LineSeries(
        name = name,
        symbol = symbol?.name,
        symbolSize = symbol?.getSize(),
        symbolRotate = symbol?.rotate,
        showSymbol = symbol != null,
        stack = stack,
        step = step,
        label = features.getLabel(),
        itemStyle = settings.getItemStyle(),
        lineStyle = settings.getLineStyle(),
        smooth = smooth,
        encode = encode,
        markPoint = features.getEchartsMarkPoint(),
        markLine = features.getEchartsMarkLine(),
        markArea = features.getEchartsMarkArea(),
        animation = animation?.enable,
        animationThreshold = animation?.threshold,
        animationDuration = animation?.duration,
        animationEasing = animation?.easing,
        animationDelay = animation?.delay,
    )
}

internal fun Layer.toAreaSeries(name: String?, encode: Encode?): LineSeries {
    val symbol = settings.getNPSValue<Symbol>(SYMBOL)
    val smooth = settings.getNPSValue<Boolean>(SMOOTH)
    val stack = (features[StackFeature.FEATURE_NAME] as? StackFeature)?.name
    val animation = (features[AnimationLayerFeature.FEATURE_NAME] as? AnimationLayerFeature)

    return LineSeries(
        name = name,
        symbol = symbol?.name,
        symbolSize = symbol?.getSize(),
        symbolRotate = symbol?.rotate,
        showSymbol = symbol != null,
        stack = stack,
        label = features.getLabel(),
        itemStyle = settings.getItemStyle(),
        lineStyle = settings.getLineStyle(),
        areaStyle = settings.getAreaStyle(),
        smooth = smooth,
        encode = encode,
        markPoint = features.getEchartsMarkPoint(),
        markLine = features.getEchartsMarkLine(),
        markArea = features.getEchartsMarkArea(),
        animation = animation?.enable,
        animationThreshold = animation?.threshold,
        animationDuration = animation?.duration,
        animationEasing = animation?.easing,
        animationDelay = animation?.delay,
    )
}

@Serializable
internal class LineSeries(
    override val type: String = "line",
    override val id: String? = null,
    override val name: String? = null,
    override val colorBy: String? = null, // TODO (need groupBy)
    override val coordinateSystem: String? = null,
    val xAxisIndex: Int? = null,
    val yAxisIndex: Int? = null,
    val polarIndex: Int? = null,
    val symbol: String? = null,
    val symbolSize: Measurement? = null,
    val symbolRotate: Int? = null,
    val symbolKeepAspect: Boolean? = null,
    val symbolOffset: Pair<String, String>? = null,
    val showSymbol: Boolean? = null,
    val showAllSymbol: String? = null, // auto true false,
    override val legendHoverLink: Boolean? = null,
    val stack: String? = null, // TODO
    val stackStrategy: String? = null,
    val cursor: String? = null,
    val connectNulls: Boolean? = null, // TODO
    val clip: Boolean? = null,
    val triggerLineEvent: Boolean? = null,
    val step: String? = null,
    val label: Label? = null,
    val endLabel: Label? = null,
    val labelLine: LabelLine? = null,
    val labelLayout: LabelLayout? = null,
    override val itemStyle: ItemStyle? = null,
    val lineStyle: LineStyle? = null,
    val areaStyle: AreaStyle? = null,
    override val emphasis: Emphasis? = null,
    override val blur: Blur? = null,
    override val select: Select? = null,
    override val selectedMode: String? = null, // string boolean
    val smooth: Boolean? = null, // boolean number
    val smoothMonotone: String? = null,
    val sampling: String? = null,
    override val dimensions: List<Dimension>? = null,
    override val encode: Encode? = null,
    val seriesLayoutBy: String? = null,
    val datasetIndex: Int? = null,
    override val dataGroupId: String? = null,
    override val data: List<List<String>>? = null, // TODO!!! Data
    override val markPoint: EchartsMarkPoint? = null,
    override val markLine: EchartsMarkLine? = null,
    override val markArea: EchartsMarkArea? = null,
    override val zlevel: Int? = null,
    override val z: Int? = null,
    override val silent: Boolean? = null,
    val animation: Boolean? = null,
    val animationThreshold: Int? = null,
    override val animationDuration: Int? = null,
    override val animationEasing: String? = null,
    override val animationDelay: Int? = null,
    val animationDurationUpdate: Int? = null,
    val animationDelayUpdate: Int? = null,
    override val universalTransition: UniversalTransition? = null,
    override val tooltip: EchartsTooltip? = null
) : Series()