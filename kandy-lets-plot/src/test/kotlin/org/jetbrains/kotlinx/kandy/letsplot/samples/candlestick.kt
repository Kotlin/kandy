package org.jetbrains.kotlinx.kandy.letsplot.samples

import kotlinx.datetime.LocalDate
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.letsplot.y
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.jetbrains.kotlinx.statistics.kandy.layers.candlestick
import kotlin.test.Test

class Candlestick : SampleHelper("candlestick") {

    @Test
    fun candlestick_simple_dataframe() {
        // SampleStart
        val df = dataFrameOf(
            "month" to listOf("Jan", "Feb", "Mar", "Apr", "May"),
            "open" to listOf(14.2, 6.7, 8.8, 11.2, 4.0),
            "high" to listOf(15.5, 9.6, 10.7, 11.7, 9.9),
            "low" to listOf(7.5, 6.1, 8.5, 5.4, 4.0),
            "close" to listOf(8.0, 8.6, 10.7, 6.5, 9.8)
        )

        df.plot {
            candlestick("month", "open", "high", "low", "close")
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun candlestick_simple_collections() {
        // SampleStart
        val month = listOf("Jan", "Feb", "Mar", "Apr", "May")
        val open = listOf(14.2, 6.7, 8.8, 11.2, 4.0)
        val high = listOf(15.5, 9.6, 10.7, 11.7, 9.9)
        val low = listOf(7.5, 6.1, 8.5, 5.4, 4.0)
        val close = listOf(8.0, 8.6, 10.7, 6.5, 9.8)

        plot {
            candlestick(month, open, high, low, close)
        }
        // SampleEnd
    }

    @Test
    fun candlestick_settings_dsl_dataframe() {
        // SampleStart
        val df = dataFrameOf(
            "date" to List(10) { LocalDate(2022, 1, it + 1) },
            "open" to listOf(10.0, 15.0, 12.0, 18.0, 14.0, 16.0, 20.0, 22.0, 19.0, 25.0),
            "high" to listOf(18.0, 17.0, 20.0, 22.0, 18.0, 22.0, 25.0, 24.0, 27.0, 28.0),
            "low" to listOf(8.0, 10.0, 9.0, 11.0, 12.0, 15.0, 18.0, 17.0, 18.0, 22.0),
            "close" to listOf(15.0, 12.0, 18.0, 14.0, 16.0, 20.0, 22.0, 19.0, 25.0, 23.0),
        )

        df.plot {
            candlestick("date", "open", "high", "low", "close") {
                increase {
                    fillColor = Color.hex("#00fefe")
                    alpha = 0.9
                }
                decrease {
                    fillColor = Color.hex("#ea2211")
                    alpha = 0.5
                }
                borderLine.color = Color.GREY
                width = 0.7
            }
            y.axis.name = "Price, €"
            x.axis.name = "Date"
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun candlestick_settings_dsl_collections() {
        // SampleStart
        val date = List(10) { LocalDate(2022, 1, it + 1) }
        val open = listOf(10.0, 15.0, 12.0, 18.0, 14.0, 16.0, 20.0, 22.0, 19.0, 25.0)
        val high = listOf(18.0, 17.0, 20.0, 22.0, 18.0, 22.0, 25.0, 24.0, 27.0, 28.0)
        val low = listOf(8.0, 10.0, 9.0, 11.0, 12.0, 15.0, 18.0, 17.0, 18.0, 22.0)
        val close = listOf(15.0, 12.0, 18.0, 14.0, 16.0, 20.0, 22.0, 19.0, 25.0, 23.0)

        plot {
            candlestick(date, open, high, low, close) {
                increase {
                    fillColor = Color.hex("#00fefe")
                    alpha = 0.9
                }
                decrease {
                    fillColor = Color.hex("#ea2211")
                    alpha = 0.5
                }
                borderLine.color = Color.GREY
                width = 0.7
            }
            y.axis.name = "Price, €"
            x.axis.name = "Date"
        }
        // SampleEnd
    }

    @Test
    fun candlestick_settings_stat_api_dataframe() {
        // SampleStart
        val df = dataFrameOf(
            "year" to listOf("2018", "2019", "2020", "2021", "2022", "2023"),
            "open" to listOf(10.0, 15.0, 12.0, 18.0, 14.0, 16.0),
            "high" to listOf(18.0, 17.0, 20.0, 22.0, 18.0, 22.0),
            "low" to listOf(8.0, 10.0, 9.0, 11.0, 12.0, 15.0),
            "close" to listOf(15.0, 12.0, 18.0, 14.0, 16.0, 20.0),
        )

        df.plot {
            candlestick("year", "open", "high", "low", "close") {
                alpha(Stat.isIncreased) {
                    scale = categorical(true to 1.0, false to 0.05)
                    legend {
                        name = ""
                        breaksLabeled(true to "increase", false to "decrease")
                    }
                }
                fillColor = Color.GREY
                borderLine.color = Color.GREY
            }
            x.axis {
                name = "Year"
                breaks(format = "d")
            }
            layout.size = 750 to 400
        }
            // SampleEnd
            .saveSample(savePreview = true)
    }

    @Test
    fun candlestick_settings_stat_api_collections() {
        // SampleStart
        val year = listOf("2018", "2019", "2020", "2021", "2022", "2023")
        val opens = listOf(10.0, 15.0, 12.0, 18.0, 14.0, 16.0)
        val highs = listOf(18.0, 17.0, 20.0, 22.0, 18.0, 22.0)
        val lows = listOf(8.0, 10.0, 9.0, 11.0, 12.0, 15.0)
        val closes = listOf(15.0, 12.0, 18.0, 14.0, 16.0, 20.0)

        plot {
            candlestick(year, opens, highs, lows, closes) {
                alpha(Stat.isIncreased) {
                    scale = categorical(true to 1.0, false to 0.05)
                    legend {
                        name = ""
                        breaksLabeled(true to "increase", false to "decrease")
                    }
                }
                fillColor = Color.GREY
                borderLine.color = Color.GREY
            }
            x.axis {
                name = "Year"
                breaks(format = "d")
            }
            layout.size = 750 to 400
        }
        // SampleEnd
    }
}
