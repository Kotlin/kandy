/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.option.series

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.echarts.features.StackFeature
import org.jetbrains.kotlinx.kandy.echarts.features.animation.AnimationLayerFeature
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.marks.*
import org.jetbrains.kotlinx.kandy.ir.Layer

internal fun Layer.toBarSeries(name: String?, encode: Encode?): BarSeries {
    val stack = (features[StackFeature.FEATURE_NAME] as? StackFeature)?.name
    val animation = (features[AnimationLayerFeature.FEATURE_NAME] as? AnimationLayerFeature)
    val backgroundStyle = settings.getBackgroundStyle()


    return BarSeries(
        name = name,
        stack = stack,
        showBackground = if (backgroundStyle != null) true else null,
        backgroundStyle = backgroundStyle,
        label = features.getLabel(),
        itemStyle = settings.getItemStyle(),
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
internal class BarSeries(
    override val type: String = "bar",
    override val id: String? = null,
    override val name: String? = null,
    override val colorBy: String? = null,
    override val legendHoverLink: Boolean? = null,
    override val coordinateSystem: String? = null,
    val xAxisIndex: Int? = null,
    val yAxisIndex: Int? = null,
    val polarIndex: Int? = null,
    val roundCap: Boolean? = null,
    val realtimeSort: Boolean? = null,
    val showBackground: Boolean? = null,
    val backgroundStyle: BackgroundStyle? = null,
    val label: Label? = null,
    val labelLine: LabelLine? = null,
    override val itemStyle: ItemStyle? = null,
    val labelLayout: LabelLayout? = null,
    override val emphasis: Emphasis? = null,
    override val blur: Blur? = null,
    override val select: Select? = null,
    override val selectedMode: String? = null,
    val stack: String? = null,
    val stackStrategy: String? = null,
    val sampling: String? = null,
    val cursor: String? = null,
    val barWidth: String? = null,
    val barMaxWidth: String? = null,
    val barMinWidth: String? = null,
    val barMinHeight: String? = null,
    val barMinAngle: String? = null,
    val barGap: String? = null,
    val barCategoryGap: String? = null,
    val large: Boolean? = null,
    val largeThreshold: Int? = null,
    val progressive: Int? = null,
    val progressiveThreshold: Int? = null,
    val progressiveChunkMode: String? = null,
    override val dimensions: List<Dimension>? = null,
    override val encode: Encode? = null,
    val seriesLayoutBy: String? = null,
    val datasetIndex: Int? = null,
    override val dataGroupId: String? = null,
    override val data: List<List<String>>? = null,
    val clip: Boolean? = null,
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
    val animationEasingUpdate: String? = null,
    val animationDelayUpdate: Int? = null,
    override val universalTransition: UniversalTransition? = null,
    override val tooltip: EchartsTooltip? = null
) : Series()