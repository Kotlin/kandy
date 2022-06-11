package org.jetbrains.kotlinx.ggdsl.echarts.translator

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.echarts.*
import org.jetbrains.kotlinx.ggdsl.echarts.stack.stack
import org.jetbrains.kotlinx.ggdsl.echarts.translator.NON_POS_CAT_NAME
import org.jetbrains.kotlinx.ggdsl.echarts.translator.POS_CAT_NAME
import org.jetbrains.kotlinx.ggdsl.echarts.translator.POS_CONT_NAME
import org.jetbrains.kotlinx.ggdsl.echarts.translator.toOption
import org.jetbrains.kotlinx.ggdsl.echarts.util.color.ColorStop
import org.jetbrains.kotlinx.ggdsl.echarts.util.color.GradientOption
import org.jetbrains.kotlinx.ggdsl.echarts.util.color.LinearGradientColor
import org.jetbrains.kotlinx.ggdsl.echarts.util.color.SimpleColorOption
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ToOptionTest {

    @Test
    fun testSimple() {
        val simpleDataset: NamedData = mapOf(
            "time" to listOf(1, 2, 3),
            "val" to listOf(2.0, 3.3, 2.9)
        )
        val plot = plot(simpleDataset) {
            x(source<Int>("time"))
            y(source<Int>("val"))
            points {
                color(Color.RED)
            }
        }
        assertEquals(
            MetaOption(
                Option(
                    dataset = Dataset(
                        listOf(
                            listOf("time", "val"),
                            listOf("1", "2.0"),
                            listOf("2", "3.3"),
                            listOf("3", "2.9")
                        )
                    ),
                    xAxis = listOf(
                        Axis(type = POS_CONT_NAME)
                    ),
                    yAxis = listOf(
                        Axis(type = POS_CONT_NAME)
                    ),
                    visualMap = listOf(),
                    series = listOf(
                        Series(
                            type = "scatter",
                            encode = XYEncode(
                                x = "time",
                                y = "val"
                            ),
                            itemStyle = ItemStyle(
                                color = SimpleColorOption("red")
                            )
                        )
                    )
                ),
                null
            ),
            plot.toOption()
        )
    }

    @Test
    fun testComplex() {
        val dataset: NamedData = mapOf(
            "time" to listOf(1, 2, 3, 4),
            "val_max" to listOf(2.5, 4.1, 3.8, 2.8),
            "val_min_main_part" to listOf(1.0, 0.11, 2.0, 0.3),
            "val_min_rest" to listOf(0.9, 3.2, 2.0, 2.1),
            "type" to listOf("static", "dynamic", "dynamic", "dynamic"),
            "type_main_part" to listOf("raw", "dry", "dry", "raw"),
            "val_avg" to listOf(2.0, 3.3, 2.9, 1.8)
        )

        val time = source<Int>("time")
        val valMax = source<Double>("val_max")
        val valMinMainPart = source<Double>("val_min_main_part")
        val valMinRest = source<Double>("val_min_rest")
        val valAvg = source<Double>("val_avg")
        val type = source<String>("type")
        val typeMainPart = source<String>("type_main_part")

        val plotTitle = "Important plot"
        val plotSize = 800 to 600


        val plot = plot(dataset) {
            x(time.scaled(
                categoricalPos(
                    listOf(1, 2, 3, 4)
                )
            ))
            val minStack = stack("min")
            bars {
                y(valMinMainPart)
                color(typeMainPart.scaled(
                    categorical(
                        rangeValues = listOf(
                            Color.fromHex("#002137"),
                            LinearGradientColor(
                                colors = listOf(0.0 to Color.WHITE, 1.0 to Color.fromHex("#008cf0"))
                            )
                        )
                    )
                ))
                stack = minStack
            }
            bars {
                y(valMinRest)
                color(Color.fromHex("#696969"))
                stack = minStack
            }
            bars {
                y(valMax)
                color(type.scaled(
                    categorical(
                        listOf("dynamic", "static"),
                        listOf(Color.RED, Color.GREEN)
                    )
                ))
            }
            line {
                y(valAvg)
                width(4.0)
                color(Color.fromRGB(200, 0, 200))
            }
            layout {
                title = plotTitle
                size = plotSize
            }
        }
        assertEquals(
            MetaOption(
                Option(
                    dataset = Dataset(
                        listOf(
                            listOf("time","val_max","val_min_main_part","val_min_rest","type","type_main_part","val_avg"),
                            listOf("1","2.5","1.0","0.9","static","raw","2.0"),
                            listOf("2","4.1","0.11","3.2","dynamic","dry","3.3"),
                            listOf("3","3.8","2.0","2.0","dynamic","dry","2.9"),
                            listOf("4","2.8","0.3","2.1","dynamic","raw","1.8")
                        )
                    ),
                    xAxis = listOf(
                        Axis(type = POS_CAT_NAME, data = listOf("1", "2", "3", "4"))
                    ),
                    yAxis = listOf(
                        Axis(type = POS_CONT_NAME)
                    ),
                    visualMap = listOf(
                        VisualMap(
                            type = NON_POS_CAT_NAME,
                            dimension = 5,
                            seriesIndex = 0,
                            categories = listOf("raw", "dry"),
                            inRange = InRange(
                                color = listOf(
                                    SimpleColorOption("#002137"),
                                    GradientOption(
                                        type = "linear",
                                        x = 0.0,
                                        y = 0.0,
                                        x2 = 0.0,
                                        y2 = 1.0,
                                        colorStops = listOf(
                                            ColorStop(0.0, "white"),
                                            ColorStop(1.0, "#008cf0")
                                        )
                                    )
                                )
                            ),
                            top = 0,
                            right = 10
                        ),
                        VisualMap(
                            type = NON_POS_CAT_NAME,
                            dimension = 4,
                            seriesIndex = 2,
                            categories = listOf("dynamic", "static"),
                            inRange = InRange(
                                color = listOf(
                                    SimpleColorOption("red"),
                                    SimpleColorOption("green")
                                )
                            ),
                            top = 150,
                            right = 10
                        ),
                    ),
                    series = listOf(
                        Series(
                            type = "bar",
                            encode = XYEncode(
                                x = "time",
                                y = "val_min_main_part"
                            ),
                            itemStyle = ItemStyle(),
                            stack = "min"
                        ),
                        Series(
                            type = "bar",
                            encode = XYEncode(
                                x = "time",
                                y = "val_min_rest"
                            ),
                            itemStyle = ItemStyle(
                                color = SimpleColorOption("#696969")
                            ),
                            stack = "min"
                        ),
                        Series(
                            type = "bar",
                            encode = XYEncode(
                                x = "time",
                                y = "val_max"
                            ),
                            itemStyle = ItemStyle(),
                        ),
                        Series(
                            type = "line",
                            encode = XYEncode(
                                x = "time",
                                y = "val_avg"
                            ),
                            lineStyle = LineStyle(
                                color = SimpleColorOption("#C800C8"),
                                width = 4.0
                            ),
                        )
                    ),
                    title = Title(
                        text = "Important plot"
                    )
                ),
                plotSize
            ),
            plot.toOption()
        )
    }

}