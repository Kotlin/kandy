package org.jetbrains.kotlinx.ggdsl.echarts.translator

import org.jetbrains.kotlinx.ggdsl.echarts.aes.NAME
import org.jetbrains.kotlinx.ggdsl.echarts.aes.X
import org.jetbrains.kotlinx.ggdsl.echarts.aes.Y
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.AnimationPlotFeature
import org.jetbrains.kotlinx.ggdsl.echarts.layers.*
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.*
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.*
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Encode
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.scale.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.reflect.typeOf

@Suppress("UNCHECKED_CAST")
internal fun <T : Any> Map<AesName, Setting>.getNPSValue(key: AesName): T? {
    return (this[key] as? NonPositionalSetting<*>)?.value?.value as? T
}

internal class Parser(plot: Plot) {
    private val data = (plot.dataset as NamedDataInterface).nameToValues
    private val globalMappings = plot.globalMappings
    private val layers = plot.layers
    private val features = plot.features

    private var xAxis: Axis? = null
    private var yAxis: Axis? = null
    private val source = mutableMapOf<String, List<Any>>()


    internal fun parse(): Option {
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
        val animation = (features[AnimationPlotFeature.FEATURE_NAME] as? AnimationPlotFeature)

        globalMappings.forEach { (aes, mapping) ->
            if (mapping is ScaledMapping<*>) {
                when (aes) {
                    X -> {
                        xAxis = mapping.toAxis()
                        source[mapping.getId()] = data[mapping.getId()]!!.values
                    }

                    Y -> {
                        yAxis = mapping.toAxis()
                        source[mapping.getId()] = data[mapping.getId()]!!.values
                    }
                }
            }
        }

        val series = layers.mapIndexed { index, layer -> // TODO(layout???)
            layer.mappings.forEach { (aes, mapping) ->
                if (mapping is ScaledMapping<*>) {
                    when {
                        (xAxis == null && aes == X) -> xAxis = mapping.toAxis()
                        (yAxis == null && aes == Y) -> yAxis = mapping.toAxis()
                        aes != X && aes != Y -> {
                            val sourceId = mapping.getId()
                            visualMaps.add(
                                mapping.columnScaled.scale.toVisualMap(
                                    aes,
                                    sourceId, index,
                                    (layer.dataset as? NamedDataInterface)?.nameToValues?.get(sourceId)?.values
                                        ?: data[sourceId]?.values,
                                    visualMaps.size,
                                    mapping.domainType
                                )
                            )
                        }
                    }
                    source.getOrPut(mapping.getId()) { data[mapping.getId()]!!.values } // TODO(missing columns?)
                }
            }
            layer.toSeries()
        }

        val headersOfData = source.keys.toList()
        val datasetSource = listOf(headersOfData) + List(source.values.firstOrNull()?.size ?: 0) { i ->
            List(headersOfData.size) { j ->
                source[headersOfData[j]]?.get(i).toString()
            }
        }

        val dataset = Dataset(source = datasetSource)

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
            series,
            textStyle,
            animation?.enable,
            animation?.threshold,
            animation?.duration,
            animation?.easing,
            animation?.delay,
            plotSize = layout?.size ?: (800 to 600)
        )
    }

    private fun ScaledMapping<*>.getId(): String = this.columnScaled.source.name

    private fun ScaledMapping<*>.toAxis(): Axis {
        val scaleMap = this.columnScaled.scale
        val show: Boolean = true
        val grid: Int? = null
        val alignTicks: Boolean? = null
        val position: String? = null
        val offset: Int? = null
        var min: String? = null
        var max: String? = null
        val type: AxisType = when (scaleMap) { // TODO match Mapping
            is PositionalCategoricalScale<*> -> AxisType.CATEGORY
            is PositionalContinuousScale<*> -> {
                min = scaleMap.limits?.first?.value?.toString()
                max = scaleMap.limits?.second?.value?.toString()
                AxisType.VALUE
            }

            is PositionalCategoricalUnspecifiedScale -> AxisType.CATEGORY
            is PositionalContinuousUnspecifiedScale -> AxisType.VALUE
            is DefaultUnspecifiedScale, is UnspecifiedScale -> {
                when (this.domainType) {
                    typeOf<String>(), typeOf<Char>() -> AxisType.CATEGORY
                    typeOf<Number>() -> AxisType.VALUE
                    typeOf<LocalDate>(), typeOf<LocalDateTime>(), typeOf<LocalTime>() -> AxisType.TIME // TODO(kotlinx.datetime)
                    else -> AxisType.VALUE
                }
            }

            else -> AxisType.VALUE
        }
        val name: String? = null
        val nameLocation: String? = null
        val nameGap: Int? = null
        val nameRotate: Int? = null
        val inverse: Boolean? = null
        val boundaryGap: Any? = null
        val scale: Boolean? = null
        val splitNumber: Int? = null
        val minInterval: Int? = null
        val interval: Int? = null
        val logBase: Int? = null
        val silent: Boolean? = null
        val triggerEvent: Boolean? = null
        val zlevel: Int? = null
        val z: Int? = null

        return Axis(
            null, show, grid, alignTicks, position, offset, type, name, nameLocation,
            nameGap, nameRotate, inverse, boundaryGap, min, max, scale, splitNumber, minInterval,
            interval, logBase, silent, triggerEvent, null, zlevel, z
        )
    }

    private fun Layer.toSeries(): Series {
        val x = (this.mappings[X] as? ScaledMapping<*>)?.getId()
        val y = (this.mappings[Y] as? ScaledMapping<*>)?.getId()
        val encode = Encode(x, y)
        val name = settings.getNPSValue(NAME) ?: when {
            x != null && y != null -> "$x $y"
            x == null && y != null -> y
            x != null && y == null -> x
            else -> null
        }

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
