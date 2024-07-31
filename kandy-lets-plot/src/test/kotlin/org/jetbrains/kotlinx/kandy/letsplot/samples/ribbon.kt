package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.dataframe.api.columnOf
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.layers.ribbon
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.letsplot.y
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statSmooth
import kotlin.math.cos
import kotlin.math.sin
import kotlin.test.Test

class Ribbon : SampleHelper("ribbon") {

    @Test
    fun ribbon_simple_dataframe() {
        // SampleStart
        val xs by columnOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)
        val mins by columnOf(0.2, 0.9, 0.55, 1.32, 2.2, 1.5)
        val maxs by columnOf(0.7, 1.4, 1.1, 2.1, 2.6, 2.2)
        val df = dataFrameOf(xs, mins, maxs)

        df.plot {
            ribbon {
                x(xs)
                yMin(mins)
                yMax(maxs)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun ribbon_simple_collections() {
        // SampleStart
        val xs = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)
        val mins = listOf(0.2, 0.9, 0.55, 1.32, 2.2, 1.5)
        val maxs = listOf(0.7, 1.4, 1.1, 2.1, 2.6, 2.2)

        plot {
            ribbon {
                x(xs)
                yMin(mins)
                yMax(maxs)
            }
        }
        // SampleEnd
    }

    @Test
    fun ribbon_settings_dataframe() {
        // SampleStart
        val year by columnOf("2019", "2020", "2021", "2022", "2023")
        val minCost by columnOf(56.5, 59.9, 60.8, 78.9, 75.5)
        val maxCost by columnOf(58.1, 69.3, 66.4, 108.3, 92.2)
        val df = dataFrameOf(year, minCost, maxCost)

        df.plot {
            ribbon {
                x(year)
                y {
                    axis.name = "cost"
                    scale = continuous(55.0..110.0)
                }
                yMin(minCost)
                yMax(maxCost)
                fillColor = Color.hex(0x3f21e6)
                alpha = 0.65
                borderLine {
                    color = Color.RED
                    width = 0.8
                    type = LineType.DASHED
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun ribbon_settings_collections() {
        // SampleStart
        val year = listOf("2019", "2020", "2021", "2022", "2023")
        val minCost = listOf(56.5, 59.9, 60.8, 78.9, 75.5)
        val maxCost = listOf(58.1, 69.3, 66.4, 108.3, 92.2)

        plot {
            ribbon {
                x(year)
                y {
                    axis.name = "cost"
                    scale = continuous(55.0..110.0)
                }
                yMin(minCost)
                yMax(maxCost)
                fillColor = Color.hex(0x3f21e6)
                alpha = 0.65
                borderLine {
                    color = Color.RED
                    width = 0.8
                    type = LineType.DASHED
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun ribbon_function_plot() {
        // SampleStart
        val xs = (-1000..1000).map { it / 250.0f }
        val function = { x: Float -> sin(2 * x) * cos(x / 2 - 3f) }
        val yActual = xs.map(function)
        val yLow = yActual.map { it - 0.25f }
        val yHigh = yActual.map { it + 0.25f }

        plot {
            ribbon {
                x(xs)
                yMin(yLow)
                yMax(yHigh)
                fillColor = Color.LIGHT_BLUE
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun ribbon_with_line_dataframe() {
        // SampleStart
        val day by columnOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        val low by columnOf(16345f, 18718f, 17541f, 17302f, 15991f, 18315f, 20189f)
        val high by columnOf(18252f, 19912f, 19001f, 21540f, 18770f, 20945f, 23007f)
        val mid by (low.values().zip(high.values()).map { (it.second + it.first) / 2 }).toColumn()
        val df = dataFrameOf(day, low, mid, high)

        df.plot {
            x(day) { axis.name = "day of week" }
            y {
                axis.name = "price"
                scale = continuous(15000f..24000f)
            }
            line {
                y(mid)
                color = Color.GREEN
                type = LineType.DOTTED
                width = 2.0
            }
            ribbon {
                yMin(low)
                yMax(high)
                borderLine.color = Color.BLUE
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun ribbon_with_line_collections() {
        // SampleStart
        val day = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        val low = listOf(16345f, 18718f, 17541f, 17302f, 15991f, 18315f, 20189f)
        val high = listOf(18252f, 19912f, 19001f, 21540f, 18770f, 20945f, 23007f)
        val mid = low.zip(high).map { (it.second + it.first) / 2 }

        plot {
            x(day) { axis.name = "day of week" }
            y {
                axis.name = "price"
                scale = continuous(15000f..24000f)
            }
            line {
                y(mid)
                color = Color.GREEN
                type = LineType.DOTTED
                width = 2.0
            }
            ribbon {
                yMin(low)
                yMax(high)
                borderLine.color = Color.BLUE
            }
        }
        // SampleEnd
    }

    @Test
    fun ribbon_grouped_dataframe() {
        // SampleStart
        val times = listOf("00:00", "06:00", "12:00", "18:00", "24:00")
        val btcMin = listOf(310, 225, 202, 278, 360)
        val btcMax = listOf(334, 307, 243, 293, 388)
        val ethMin = listOf(180, 205, 256, 300, 280)
        val ethMax = listOf(210, 234, 299, 322, 331)

        val dataset = dataFrameOf(
            "time" to times + times,
            "min" to btcMin + ethMin,
            "max" to btcMax + ethMax,
            "currency" to List(5) { "btc" } + List(5) { "eth" }
        )

        dataset.groupBy("currency").plot {
            ribbon {
                x("time")
                y {
                    limits = 170..400
                    axis.name = "price, tokens"
                }
                yMin("min")
                yMax("max")
                fillColor("currency") {
                    legend.breaksLabeled("btc" to "Bubble Tea\nCoin", "eth" to "E-Traders\nHedgehogs")
                }
                alpha = 0.6
                borderLine.width = 0.0
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun ribbon_grouped_collections() {
        // SampleStart
        val times = listOf("00:00", "06:00", "12:00", "18:00", "24:00")
        val btcMin = listOf(310, 225, 202, 278, 360)
        val btcMax = listOf(334, 307, 243, 293, 388)
        val ethMin = listOf(180, 205, 256, 300, 280)
        val ethMax = listOf(210, 234, 299, 322, 331)

        val dataset = mapOf(
            "time" to times + times,
            "min" to btcMin + ethMin,
            "max" to btcMax + ethMax,
            "currency" to List(5) { "btc" } + List(5) { "eth" }
        )

        dataset.plot {
            groupBy("currency") {
                ribbon {
                    x("time")
                    y {
                        limits = 170..400
                        axis.name = "price, tokens"
                    }
                    yMin("min")
                    yMax("max")
                    fillColor("currency") {
                        legend.breaksLabeled("btc" to "Bubble Tea\nCoin", "eth" to "E-Traders\nHedgehogs")
                    }
                    alpha = 0.6
                    borderLine.width = 0.0
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun regression_confidence_band() {
        // SampleStart
        val xs = listOf(
            -3.0, -2.8, -2.7, -2.6, -2.6, -2.5, -2.2, -3.1, -1.5,
            -0.2, 2.0, 1.2, 2.6, 2.1, 0.1, 1.2, 1.7, 0.0, 2.8,
            2.5, 0.2, 1.3, 2.5
        )
        val ys = listOf(
            -1.4, -1.2, -1.4, -1.3, -1.2, -1.1, 1.5, 2.4, 1.1, -0.9,
            3.5, 1.6, -0.7, 1.2, 0.1, 3.4, 2.8, 4.2, 1.16, 4.1, 2.2,
            1.4, 5.1
        )

        plot {
            statSmooth(xs, ys) {
                line {
                    x(Stat.x)
                    y(Stat.y)
                    width = 2.0
                    color = Color.BLUE
                }
                ribbon {
                    x(Stat.x)
                    yMin(Stat.yMin)
                    yMax(Stat.yMax)
                    borderLine.width = 0.0
                }
            }
        }
            // SampleEnd
            .saveSample()
    }
}