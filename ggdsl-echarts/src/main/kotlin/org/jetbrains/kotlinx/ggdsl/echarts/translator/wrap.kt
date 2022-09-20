package org.jetbrains.kotlinx.ggdsl.echarts.translator

import org.jetbrains.kotlinx.ggdsl.echarts.*
import org.jetbrains.kotlinx.ggdsl.echarts.animation.AnimationFeature
import org.jetbrains.kotlinx.ggdsl.echarts.stack.Stack
import org.jetbrains.kotlinx.ggdsl.echarts.util.color.EchartsColorOption
import org.jetbrains.kotlinx.ggdsl.echarts.util.color.LinearGradientColor
import org.jetbrains.kotlinx.ggdsl.echarts.util.color.RadialGradientColor
import org.jetbrains.kotlinx.ggdsl.echarts.util.color.toEchartsColorOption
import org.jetbrains.kotlinx.ggdsl.echarts.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.echarts.util.symbol.Symbol
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.ir.scale.*
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.color.StandardColor
import kotlin.reflect.KType
import kotlin.reflect.typeOf

internal data class DataInfo(
    val data: List<List<Any>>,
    val header: Map<String, Int>
)

internal fun NamedData.wrap(): DataInfo {
    val header = keys.toList()
    val values = values.toList()
    val size = values.first().size

    val idToDim = header.mapIndexed { index, s -> s to index }.toMap()

    val source = listOf(header) +
        ((0 until size).map { rowIndex ->
            header.indices.map { columnIndex ->
                values.getOrNull(columnIndex)?.getOrNull(rowIndex)!!
            }
        })
    //source.add(header)
    /*
    for (i in 0 until size) {
        source.add(
            header.map { data[it]!![i].toString() }
        )
    }

     */

    return DataInfo(source, idToDim)
}

internal fun Geom.toType(): String {
    return when (this) {
        POINT -> "scatter"
        BAR -> "bar"
        LINE -> "line"
        else -> TODO()
    }
}

// todo!!!
internal val colors = listOf("red", "blue", "green", "yellow", "purple", "orange", "pink")
    .map { Color.fromName(it) }
internal val sizes = listOf(20.0, 28.0, 36.0, 44.0, 52.0, 60.0, 68.0)
internal val alphas = listOf(0.2, 0.25, 0.3, 0.35, 0.4, 0.45, 0.5)
internal val symbols = listOf("circle", "rect", "triangle", "diamond", "roundRect", "pin", "arrow")

// TODO better
internal fun createInRange(aes: AesName, valuesString: List<Any>?, size: Int, isContinuous: Boolean): InRange {
    return when (aes) {
        COLOR -> InRange(
            color = valuesString?.map { it as EchartsColorOption }
                ?: if (isContinuous) {
                    colors.take(2).map {
                        it.toEchartsColorOption()
                    }
                } else {
                    colors.take(size).map {
                        it.toEchartsColorOption()
                    }
                }
        )

        SIZE -> InRange(
            symbolSize = valuesString?.map { (it as Number).toDouble() }
                ?: if (isContinuous) {
                    listOf(20.0, 60.0) // todo move constants.kt
                } else {
                    sizes.take(size)
                }
        )

        ALPHA -> InRange(
            colorAlpha = valuesString?.map { (it as Number).toDouble() }
                ?: if (isContinuous) {
                    listOf(0.3, 0.85) // todo move constants.kt
                } else {
                    alphas.take(size)
                }
        )

        SYMBOL -> InRange(
            symbol = valuesString?.map { wrapValue(it) as String }
                ?: if (isContinuous) {
                    TODO("error")
                } else {
                    symbols.take(size)
                }
        )

        else -> {
            TODO()
        }
    }
}

//todo
internal fun wrapValue(value: Any): Any {
    return when (value) {
        is LineType -> value.name
        is Symbol -> value.name
        is Color -> wrapColor(value)
        else -> value //.toString()
    }
}

// todo trash

internal var visualMapCounter = 0

internal fun defaultPiecewiseVisualMap(aes: AesName, dim: Int, seriesIndex: Int, data: List<Any>): VisualMap {
    val categoriesString = data.toSet().map { it.toString() }
    return VisualMap(
        /*
        show = show,
        text = name?.let { listOf(it) },
        calculable = calculable,

         */
        type = "piecewise",
        //   show = true, // TODO
        dimension = dim,
        categories = categoriesString,
        inRange = createInRange(aes, null, categoriesString.size, false),
        seriesIndex = seriesIndex,

        right = 10,
        top = (visualMapCounter++) * 150
    )
}

@Suppress("UNUSED_PARAMETER")
internal fun defaultContinuousVisualMap(aes: AesName, dim: Int, seriesIndex: Int, data: List<Any>): VisualMap {
    return VisualMap(
        /*
        show = show,
        text = name?.let { listOf(it) },
        calculable = calculable,

         */
        type = "continuous",
        //  show = false, // TODO
        dimension = dim,
        // todo count
        inRange = createInRange(aes, null, -1, true),
        seriesIndex = seriesIndex,

        right = 10,
        top = (visualMapCounter++) * 150
    )
}

// TODO!!! seriesIndex
internal fun Scale.toVisualMap(
    aes: AesName,
    dim: Int,
    seriesIndex: Int,
    data: List<Any>,
    domainType: KType,
): VisualMap {
    /*
    val legend = (this as NonPositionalScale<*, *>).legend as? EchartsLegend<*, *>
    val name = legend?.name
    val show = legend?.show
    val calculable = legend?.calculable

     */
    // todo date!!!

    return when (this) {
        is NonPositionalCategoricalScale<*, *> -> {
            val categoriesString = if (domainCategories?.isNotEmpty() == true) {
                domainCategories!!.map { value -> value.toString() }
            } else {
                data.toSet().map { it.toString() }
            }
            // TODO wrapValue
            val valuesString = rangeValues?.map { value ->
                wrapValue(value)
            }
            val inRange = createInRange(aes, valuesString, categoriesString.size, isContinuous = false)
            VisualMap(
                /*
                show = show,
                text = name?.let { listOf(it) },
                calculable = calculable,

                 */
                type = "piecewise",
                dimension = dim,
                categories = categoriesString,
                inRange = inRange,
                seriesIndex = seriesIndex,

                right = 10,
                top = (visualMapCounter++) * 150
            )
        }

        is NonPositionalContinuousScale<*, *> -> {
            val min = domainLimits?.first?.toString()?.toDouble()
            val max = domainLimits?.second?.toString()?.toDouble()
            val valuesString = rangeLimits?.let {
                listOf(wrapValue(it.first), wrapValue(it.second))
            } ?: listOf()
            val inRange = createInRange(aes, valuesString, -1, isContinuous = true)
            VisualMap(
                /*
                show = show,
                text = name?.let { listOf(it) },
                calculable = calculable,

                 */
                type = "continuous",
                //  show = false, // TODO
                dimension = dim,
                min = min,
                max = max,
                inRange = inRange,
                seriesIndex = seriesIndex,

                right = 10,
                top = (visualMapCounter++) * 150
            )
        }

        is DefaultUnspecifiedScale -> {
            when (domainType) {
                // todo other, date
                typeOf<String>() -> defaultPiecewiseVisualMap(aes, dim, seriesIndex, data)
                else -> defaultContinuousVisualMap(aes, dim, seriesIndex, data)
            }
        }

        is NonPositionalCategoricalUnspecifiedScale -> defaultPiecewiseVisualMap(aes, dim, seriesIndex, data)
        is NonPositionalContinuousUnspecifiedScale -> defaultContinuousVisualMap(aes, dim, seriesIndex, data)

        else -> {
            TODO("error")
        }
    }

}

internal fun Scale.toAxis(/*data: List<Any>,*/ domainType: KType): Axis {
    // todo date
    /*
    val axis = (this as PositionalScale<*>).axis as? EchartsAxis<*>
    val name = axis?.name
    val show = axis?.show

     */
    return when (this) {
        is PositionalCategoricalScale<*> -> {
            Axis(
                /*
                show = show,
                // TODO SORT NUMERICAL???
                name = name,

                 */
                type = "category", // todo move constant name
                // todo need data???
                data = categories?.map { value -> value.toString() }//data.toSet().map { it.toString() }
            )
        }

        is PositionalContinuousScale<*> -> {
            Axis(
                /*
                show = show,
                name = name,

                 */
                type = "value",// todo move constant name
                min = limits?.first?.toString(),
                max = limits?.second?.toString(),
            )
        }
        // todo add axis here
        is PositionalCategoricalUnspecifiedScale -> {
            Axis(type = "category")
        }

        is PositionalContinuousUnspecifiedScale -> {
            Axis(type = "value")
        }

        is DefaultUnspecifiedScale -> {
            when (domainType) {
                // todo other types
                typeOf<String>() -> {
                    Axis(
                        /*
                        show = show,
                        name = name,
                         */
                        type = "category",
                        // data = data.toSet().map { it.toString() } // todo need ???
                    )
                }

                else -> {
                    Axis(/*
                    show = show,
                    name = name,
                    */
                        type = "value"
                    )
                }
            }
        }

        else -> {
            println(this)
            TODO()
        }
    }
}


@Suppress("UNCHECKED_CAST")
internal fun <T : Any> Map<AesName, Setting>.getNPSValue(key: AesName): T? {
    return (this[key] as? NonPositionalSetting<*>)?.value as? T
}

internal fun Layer.toSeries(wrappedData: List<List<Any>>?): Series {

    // TODO STYLE, type series

    val size = settings.getNPSValue<Double>(SIZE)
    val color = settings.getNPSValue<Color>(COLOR)
    val alpha = settings.getNPSValue<Double>(ALPHA)
    val borderColor = settings.getNPSValue<Color>(BORDER_COLOR)
    val borderWidth = settings.getNPSValue<Double>(BORDER_SIZE)
    val symbol = settings.getNPSValue<Symbol>(SYMBOL)
    val width = settings.getNPSValue<Double>(WIDTH)
    val lineType = settings.getNPSValue<LineType>(LINE_TYPE)

    val stack = (features[Stack.FEATURE_NAME] as? Stack)?.name

    return Series(
        type = geom.toType(),
        encode = XYEncode(
            x = mappings[X]!!.sourceId(),
            y = mappings[Y]!!.sourceId()
        ),
        symbolSize = size?.let { it.toInt() * 4 }, // TODO
        itemStyle = if (geom != LINE) {
            ItemStyle(
                color = color?.let { wrapColor(it) },
                opacity = alpha,
                borderColor = borderColor?.let { wrapColor(it) },
                borderWidth = borderWidth,
            )
        } else {
            null
        },
        // TODO
        symbol = symbol?.let { wrapValue(it) as String },
        barWidth = if (geom == BAR) {
            width
        } else {
            null
        },
        lineStyle = if (geom == LINE) {
            LineStyle(
                width = width,
                color = color?.let { wrapColor(it) },
                type = lineType?.name // todo add wrapper
            )
        } else {
            null
        },
        stack = stack,
        data = wrappedData?.map { it.map { el -> el.toString() } }
    )
}

internal fun Mapping.sourceId(): String {
    return when (this) {
        is NonScalablePositionalMapping<*> -> source.id
        is ScaledMapping<*> -> sourceScaled.source.id
    }
}

// todo better serializer
internal fun wrapColor(color: Color): EchartsColorOption {
    return when (color) {
        is StandardColor -> color.toEchartsColorOption()
        is LinearGradientColor -> color.toEchartsColorOption()
        is RadialGradientColor -> color.toEchartsColorOption()
        else -> TODO()
    }
}

public fun Plot.toOption(): MetaOption {
    visualMapCounter = 0 // todo
    val (source, idToDim) = dataset.wrap()
    // TODO!!!

    val visualMaps = mutableListOf<VisualMap>()
    visualMapCounter = 0

    var xAxis = Axis("value")
    var yAxis = Axis("value")

    //val xAxes = mutableListOf<Axis>()
    //val yAxes = mutableListOf<Axis>()
    //val series = mutableListOf<Series>()

    val layerToData = layers.mapIndexed { index, layer ->
        index to if (layer.data === dataset) {
            null
        } else {
            layer.data?.wrap()
        }
    }.toMap()

    layers.forEachIndexed { index, layer ->
        layer.mappings.forEach { (aes, mapping) ->
            if (mapping is ScaledMapping<*>) {
                val scale = mapping.sourceScaled.scale
                val srcId = layer.mappings[aes]!!.sourceId()
                val data = layer.data?.get(srcId) ?: dataset[srcId]!!
                val seriesIndex = layerToData[index]?.header?.get(srcId) ?: idToDim[srcId]!!
                val domainType = mapping.domainType
                when (aes) {
                    X -> xAxis = scale.toAxis(domainType)
                    Y -> yAxis = scale.toAxis(domainType)
                    else -> visualMaps.add(
                        scale.toVisualMap(
                            aes,
                            seriesIndex,
                            index,
                            data,
                            mapping.domainType
                        )
                    ) // TODO
                }
            }
            // TODO X  and Y
            //val xAxisIndex = xAxes.size
            //val yAxisIndex = yAxes.size
        }

    }
    //todo echarts layout
    return MetaOption(
        Option(
            dataset = Dataset(source.map { it.map { el -> el.toString() } }),
            xAxis = listOf(xAxis),
            yAxis = listOf(yAxis),
            visualMap = visualMaps,
            series = layers.mapIndexed { index, layer -> layer.toSeries(layerToData[index]?.data) },
            title = (layout as? EChartsLayout)?.title?.let { Title(it) }
        ).apply {
            (features[AnimationFeature.FEATURE_NAME] as? AnimationFeature)?.let {
                animation = true
                animationThreshold = it.threshold
                animationDuration = it.duration
                animationEasing = it.easing.name
                animationDelay = it.delay
            }
        }, (layout as? EChartsLayout)?.size
    )
}


