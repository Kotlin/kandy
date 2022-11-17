package org.jetbrains.kotlinx.ggdsl.echarts.translator

import org.jetbrains.kotlinx.ggdsl.echarts.aes.NAME
import org.jetbrains.kotlinx.ggdsl.echarts.aes.X
import org.jetbrains.kotlinx.ggdsl.echarts.aes.Y
import org.jetbrains.kotlinx.ggdsl.echarts.features.TextStyleFeature
import org.jetbrains.kotlinx.ggdsl.echarts.features.TitleFeature
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.AnimationPlotFeature
import org.jetbrains.kotlinx.ggdsl.echarts.layers.BAR
import org.jetbrains.kotlinx.ggdsl.echarts.layers.EchartsGeom
import org.jetbrains.kotlinx.ggdsl.echarts.layers.LINE
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.*
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.BarSeries
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.Series
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Encode
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.toLineSeries
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.ir.scale.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.reflect.typeOf

internal fun <T : Any> Map<AesName, Setting>.getNPSValue(key: AesName): T? {
    return (this[key] as? NonPositionalSetting<*>)?.value as? T
}

internal class Parser(plot: Plot) {
    private val data = (plot.dataset as NamedDataInterface).map
    private val globalMappings = plot.globalMappings
    private val layers = plot.layers
    private val features = plot.features

    private var xAxis: Axis? = null
    private var yAxis: Axis? = null
    private val source = mutableMapOf<String, List<Any>>()


    internal fun parse(): Option {
        val legend: Legend? = null
        val grid: Grid? = null
        val polar: Polar? = null
        val radiusAxis: RadiusAxis? = null
        val angleAxis: AngleAxis? = null
        val radar: Radar? = null

        val title = (features[TitleFeature.FEATURE_NAME] as? TitleFeature)?.let {
            Title(
                text = it.text,
                textStyle = it.textStyle?.toTextStyle(),
                subtext = it.subtext,
                subtextStyle = it.subtextStyle?.toTextStyle(),
                textAlign = it.align?.align,
                textVerticalAlign = it.verticalAlign?.align,
                backgroundColor = it.backgroundColor?.let { col -> BaseColor(col.hex) },
                borderColor = it.borderColor?.let { col -> BaseColor(col.hex) },
                borderWidth = it.borderWidth
            )
        }

        globalMappings.forEach { (aes, mapping) ->
            if (mapping is ScaledMapping<*>) {
                when (aes) {
                    X -> {
                        xAxis = mapping.toAxis()
                        source[mapping.columnScaled.source.id] = data[mapping.columnScaled.source.id]!!
                    }

                    Y -> {
                        yAxis = mapping.toAxis()
                        source[mapping.columnScaled.source.id] = data[mapping.columnScaled.source.id]!!
                    }
                }
            }
        }

        val series = layers.map { layer ->
            layer.mappings.forEach { (aes, mapping) ->
                if (mapping is ScaledMapping<*>) {
                    if (xAxis == null && aes == X)
                        xAxis = mapping.toAxis()
                    if (yAxis == null && aes == Y)
                        yAxis = mapping.toAxis()

                    source.putIfAbsent(mapping.getId(), data[mapping.getId()]!!) // TODO(missing columns?)
                }
            }
            layer.toSeries()
        }

        val headersOfData = source.keys.toList()
        val datasetSource = listOf(headersOfData) + List(source.values.first().size) { i ->
            List(headersOfData.size) { j ->
                source[headersOfData[j]]!![i].toString()
            }
        }

        val dataset = Dataset(source = datasetSource)

        val textStyle = (features[TextStyleFeature.FEATURE_NAME] as? TextStyleFeature)?.toTextStyle()

        val animation = (features[AnimationPlotFeature.FEATURE_NAME] as? AnimationPlotFeature)

        return Option(
            title, legend, grid, xAxis, yAxis, polar, radiusAxis, angleAxis, radar, dataset, series, textStyle,
            animation?.enable, animation?.threshold, animation?.duration, animation?.easing?.name, animation?.delay
        )
    }

    private fun Geom.getType(): String = (this as EchartsGeom).name

    private fun ScaledMapping<*>.getId(): String = this.columnScaled.source.id

    private fun ScaledMapping<*>.toAxis(): Axis {
        val scaleMap = this.columnScaled.scale
        val show: Boolean = true
        val grid: Int? = null
        val alignTicks: Boolean? = null
        val position: Position? = null
        val offset: Int? = null
        var min: String? = null
        var max: String? = null
        val type: AxisType = when (scaleMap) { // TODO match Mapping
            is PositionalCategoricalScale<*> -> AxisType.CATEGORY
            is PositionalContinuousScale<*> -> {
                min = scaleMap.limits?.first?.toString()
                max = scaleMap.limits?.second?.toString()
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
        val nameLocation: NameLocation? = null
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
        val encode =
            Encode((this.mappings[X] as ScaledMapping<*>).getId(), (this.mappings[Y] as ScaledMapping<*>).getId())
        val name = settings.getNPSValue<String>(NAME)

        return when (geom) {
            LINE -> {
                this.toLineSeries(name, encode)
            }

            BAR -> {
                BarSeries(name = name, encode = encode)
            }
//            SCATTER -> {}
//            BOXPLOT -> {}
//            PIE -> {}
//            CANDLESTICK -> {}
            else -> TODO("exception?")
        }

    }
}
