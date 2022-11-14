package org.jetbrains.kotlinx.ggdsl.echarts.translator

import org.jetbrains.kotlinx.ggdsl.echarts.*
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.*
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.BarSeries
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.LineSeries
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.Series
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.Encode
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.toLineSeries
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Setting
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.ir.scale.CategoricalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.ContinuousScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.UnspecifiedScale
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
        val title: Title? = null
        val legend: Legend? = null
        val grid: Grid? = null
        val polar: Polar? = null
        val radiusAxis: RadiusAxis? = null
        val angleAxis: AngleAxis? = null
        val radar: Radar? = null

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

        return Option(title, legend, grid, xAxis, yAxis, polar, radiusAxis, angleAxis, radar, dataset, series)
    }

    private fun Geom.getType(): String = (this as EchartsGeom).name

    private fun ScaledMapping<*>.getId(): String = this.columnScaled.source.id

    private fun ScaledMapping<*>.toAxis(): Axis {
        val show: Boolean = true
        val grid: Int? = null
        val alignTicks: Boolean? = null
        val position: Position? = null
        val offset: Int? = null
        val type: AxisType = when (this) { // TODO match Mapping
            is CategoricalScale -> AxisType.CATEGORY
            is ContinuousScale -> AxisType.VALUE
            is ScaledUnspecifiedDefaultPositionalMapping, is UnspecifiedScale -> {
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
        val min: String? = null
        val max: String? = null
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
//        val symbol = settings.getNPSValue<Symbol>(SYMBOL)
//        val smooth = settings.getNPSValue<Boolean>(SMOOTH)

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
