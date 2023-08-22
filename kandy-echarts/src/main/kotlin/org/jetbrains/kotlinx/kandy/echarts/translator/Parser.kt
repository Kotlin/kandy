/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator

import kotlinx.datetime.*
import org.jetbrains.kotlinx.dataframe.api.fillNA
import org.jetbrains.kotlinx.dataframe.api.map
import org.jetbrains.kotlinx.dataframe.api.with
import org.jetbrains.kotlinx.kandy.echarts.layers.*
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.NAME
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.X
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.Y
import org.jetbrains.kotlinx.kandy.echarts.scale.EchartsPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.echarts.translator.option.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.Encode
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.*
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalContinuousScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalCategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalDefaultScale
import kotlin.reflect.KType
import kotlin.reflect.typeOf

@Suppress("UNCHECKED_CAST")
internal fun <T> Map<Aes, Setting>.getNPSValue(key: Aes): T? {
    return (this[key] as? NonPositionalSetting<*>)?.value as? T
}

internal class Parser(plot: Plot) {
    private val datasets: List<TableData> = plot.datasets
    private val globalMappings = plot.globalMappings
    private val layers = plot.layers
    private val features = plot.features
    private val freeScales = plot.freeScales

    private var xAxis: Axis? = null
    private var yAxis: Axis? = null


    internal fun parse(): Option {
        var df = (datasets[0] as NamedData).dataFrame
        val polar: Polar? = null
        val radiusAxis: RadiusAxis? = null
        val angleAxis: AngleAxis? = null
        val radar: Radar? = null
        val visualMaps = mutableListOf<VisualMap>()

        val layout = (features[EChartsLayout.FEATURE_NAME] as? EChartsLayout)
        val title = layout?.title?.toEchartsTitle()
        val legend = layout?.legend?.toEchartsLegend()
        val grid = layout?.grid?.toEchartsGrid()
        val tooltip = layout?.tooltip?.toEchartsTooltip()
        val textStyle = layout?.textStyle?.toTextStyle()
        val animation = layout?.animation?.toAnimationPlotFeature()

        globalMappings.forEach { (aes: Aes, mapping: Mapping) ->
            if (mapping is PositionalMapping<*>) {
                when (aes) {
                    X -> {
                        xAxis = mapping.toAxis(df[mapping.columnID].type())
                        mapping.getNA()?.let { naValue ->
                            df = df.fillNA(mapping.columnID).with { naValue }
                        }
                    }

                    Y -> {
                        yAxis = mapping.toAxis(df[mapping.columnID].type())
                        mapping.getNA()?.let { naValue ->
                            df = df.fillNA(mapping.columnID).with { naValue }
                        }
                        println("NA!!!")
                        println(df)
                    }
                }
            }
        }

        val series = layers.mapIndexed { index, layer -> // TODO(layout???)
            layer.mappings.forEach { (aes, mapping) ->
                if (mapping is PositionalMapping<*>) {
                    when {
                        (xAxis == null && aes == X) -> {
                            xAxis = mapping.toAxis(df[mapping.columnID].type())
                            mapping.getNA()?.let { naValue -> df = df.fillNA(mapping.columnID).with { naValue } }
                        }

                        (yAxis == null && aes == Y) -> {
                            yAxis = mapping.toAxis(df[mapping.columnID].type())
                            mapping.getNA()?.let { naValue -> df = df.fillNA(mapping.columnID).with { naValue } }
                        }
                    }
                } else if (mapping is NonPositionalMapping<*, *>) {
                    mapping.getNA()?.let { naValue -> df = df.fillNA(mapping.columnID).with { naValue } }
                    visualMaps.add(
                        mapping.parameters?.scale!!.toVisualMap(
                            aes, mapping.columnID, index,
                            df[mapping.columnID].toList(), visualMaps.size, df[mapping.columnID].type()
                        )
                    )
                }
            }
            layer.toSeries()
        }

        val source = listOf(df.columnNames()) + df.map { it.values().map { l -> l?.toString() } }
        val dataset = Dataset(source = source).takeIf { it.isNotEmpty() }

        return Option(
            title,
            legend,
            grid,
            xAxis,
            yAxis,
            polar,
            radiusAxis,
            angleAxis,
            radar,
            visualMaps.ifEmpty { null },
            tooltip,
            dataset,
            series.ifEmpty { null },
            textStyle,
            animation?.enable,
            animation?.threshold,
            animation?.duration,
            animation?.easing,
            animation?.delay,
            plotSize = layout?.size ?: (800 to 600)
        )
    }

    private fun Mapping.getNA(): Any? = when (val scale = this.parameters?.scale) {
        is PositionalContinuousScale<*> -> scale.nullValue
        is NonPositionalContinuousScale<*, *> -> scale.nullValue
        else -> null
    }

    private fun PositionalMapping<*>.toAxis(ktype: KType): Axis {
        val params = this.parameters as EchartsPositionalMappingParameters
        val axis = params.axis
        val axisScale = params.scale
        var min: String? = null
        var max: String? = null
        val type = when (axisScale) {
            is PositionalCategoricalScale<*> -> AxisType.CATEGORY
            is PositionalContinuousScale<*> -> {
                min = axisScale.min?.toString()
                max = axisScale.max?.toString()
                AxisType.VALUE
            }

            is PositionalDefaultScale -> {
                when (ktype) {
                    typeOf<String>(), typeOf<String?>(), typeOf<Char>(), typeOf<Char?>() -> AxisType.CATEGORY
                    typeOf<Number>(), typeOf<Number?>() -> AxisType.VALUE
                    typeOf<LocalDateTime>(), typeOf<LocalDateTime?>(),
                    typeOf<java.time.LocalDateTime>(), typeOf<java.time.LocalDateTime?>() -> AxisType.TIME

                    typeOf<LocalDate>(), typeOf<LocalDate?>(),
                    typeOf<java.time.LocalDate>(), typeOf<java.time.LocalDate?>() -> AxisType.TIME

                    typeOf<LocalTime>(), typeOf<LocalTime?>(),
                    typeOf<Month>(), typeOf<Month?>(),
                    typeOf<DayOfWeek>(), typeOf<DayOfWeek?>(),
                    typeOf<java.time.LocalTime>(), typeOf<java.time.LocalTime?>(),
                    typeOf<java.time.Month>(), typeOf<java.time.Month?>(),
                    typeOf<java.time.DayOfWeek>(), typeOf<java.time.DayOfWeek?>()
                    -> AxisType.CATEGORY

                    else -> AxisType.VALUE
                }
            }

            else -> AxisType.VALUE
        }
        return Axis(name = axis.name, show = axis.show, type = type.value, min = min, max = max)
    }

    private fun Layer.toSeries(): Series {
        val x = mappings[X]?.columnID ?: globalMappings[X]?.columnID
        val y = mappings[Y]?.columnID ?: globalMappings[Y]?.columnID
        val encode = Encode(x, y).takeIf { it.isNotEmpty() }
        val name = settings.getNPSValue(NAME)
            ?: if (xAxis?.name == null && x == null && yAxis?.name == null && y == null)
                null
            else
                "${xAxis?.name ?: x} ${yAxis?.name ?: y}".trim()

        return when (geom) {
            LINE -> this.toLineSeries(name, encode)
            AREA -> this.toAreaSeries(name, encode)
            BAR -> this.toBarSeries(name, encode)
            PIE -> this.toPieSeries(name, encode)
            POINT -> this.toPointSeries(name, encode)
            CANDLESTICK -> this.toCandlestickSeries(name, encode)
            BOXPLOT -> this.toBoxplotSeries(name, encode)
            else -> TODO("exception?")
        }
    }
}
