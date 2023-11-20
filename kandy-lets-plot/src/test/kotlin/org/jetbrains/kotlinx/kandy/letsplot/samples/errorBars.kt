package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.dataframe.api.columnOf
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.layers.errorBars
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.letsplot.y
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.jetbrains.kotlinx.statistics.kandy.layers.boxplot
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statBoxplot
import kotlin.test.Test

class ErrorBars : SampleHelper("errorBars") {

    @Test
    fun simple_error_bar_plot_dataframe() {
        // SampleStart
        val years by columnOf(2018, 2019, 2020, 2021, 2022)
        val costMin by columnOf(62.7, 64.7, 72.1, 73.7, 68.5)
        val costMax by columnOf(68.9, 71.3, 78.9, 76.5, 72.1)

        plot {
            errorBars {
                x(years)
                yMin(costMin)
                yMax(costMax)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun simple_error_bar_plot_collections() {
        // SampleStart
        val years = listOf(2018, 2019, 2020, 2021, 2022)
        val costMin = listOf(62.7, 64.7, 72.1, 73.7, 68.5)
        val costMax = listOf(68.9, 71.3, 78.9, 76.5, 72.1)

        plot {
            errorBars {
                x(years)
                yMin(costMin)
                yMax(costMax)
            }
        }
        // SampleEnd
    }

    @Test
    fun error_bars_settings_dataframe() {
        // SampleStart
        val years by columnOf(2018, 2019, 2020, 2021, 2022)
        val costMin by columnOf(62.7, 64.7, 72.1, 73.7, 68.5)
        val costMax by columnOf(68.9, 71.3, 78.9, 76.5, 72.1)
        val data = dataFrameOf(years, costMin, costMax)

        data.plot {
            errorBars {
                x(years)
                yMin(costMin)
                yMax(costMax)
                width = 1.1
                borderLine {
                    width = 1.5
                    color = Color.RED
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun error_bars_settings_collections() {
        // SampleStart
        val years = listOf(2018, 2019, 2020, 2021, 2022)
        val costMin = listOf(62.7, 64.7, 72.1, 73.7, 68.5)
        val costMax = listOf(68.9, 71.3, 78.9, 76.5, 72.1)

        plot {
            errorBars {
                x(years)
                yMin(costMin)
                yMax(costMax)
                width = 1.1
                borderLine {
                    width = 1.5
                    color = Color.RED
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun error_bars_with_line_dataframe() {
        // SampleStart
        val years = listOf(2018, 2019, 2020, 2021, 2022)
        val costMin = listOf(62.7, 64.7, 72.1, 73.7, 68.5)
        val costMax = listOf(68.9, 71.3, 78.9, 76.5, 72.1)
        val mid = costMin.zip(costMax).map { (it.first + it.second) / 2.0 }
        val data = dataFrameOf(
            years.toColumn("years"),
            costMin.toColumn("min"),
            mid.toColumn("mid"),
            costMax.toColumn("max")
        )

        data.plot {
            x("years")
            y("mid")
            line {
                color = Color.BLUE
            }
            errorBars {
                yMin("min")
                yMax("max")
                borderLine.type = LineType.LONGDASH
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun error_bars_with_line_collections() {
        // SampleStart
        val years = listOf(2018, 2019, 2020, 2021, 2022)
        val costMin = listOf(62.7, 64.7, 72.1, 73.7, 68.5)
        val costMax = listOf(68.9, 71.3, 78.9, 76.5, 72.1)
        val mid = costMin.zip(costMax).map { (it.first + it.second) / 2.0 }

        plot {
            x(years)
            y(mid)
            line {
                color = Color.BLUE
            }
            errorBars {
                yMin(costMin)
                yMax(costMax)
                borderLine.type = LineType.LONGDASH
            }
        }
        // SampleEnd
    }

    @Test
    fun fixed_error_bars_dataframe() {
        // SampleStart
        val years = listOf(2018, 2019, 2020, 2021, 2022)
        val costMin = listOf(62.7, 64.7, 72.1, 73.7, 68.5)
        val costMax = listOf(68.9, 71.3, 78.9, 76.5, 72.1)
        val data = dataFrameOf(years.toColumn("years"), costMin.toColumn("min"), costMax.toColumn("max"))

        plot(data) {
            errorBars {
                x("years")
                yMin.constant(20.0)
                yMax("max")
                width = 0.5
                borderLine.width = 1.3
            }
        }
        // SampleEnd
            .saveSample()
    }

    @Test
    fun fixed_error_bars_collections() {
        // SampleStart
        val years = listOf(2018, 2019, 2020, 2021, 2022)
        val costMax = listOf(68.9, 71.3, 78.9, 76.5, 72.1)

        plot {
            errorBars {
                x(years)
                yMin.constant(20.0)
                yMax(costMax)
                width = 0.5
                borderLine.width = 1.3
            }
        }
        // SampleEnd
    }

    @Test
    fun border_line_error_bars() {
        // SampleStart
        val years = listOf(2018, 2019, 2020, 2021, 2022)
        val costMin = listOf(62.7, 64.7, 72.1, 73.7, 68.5)
        val costMax = listOf(68.9, 71.3, 78.9, 76.5, 72.1)
        val mid = costMin.zip(costMax).map { (it.first + it.second) / 2.0 }

        plot {
            errorBars {
                x(years)
                yMin(costMin)
                yMax(costMax)
                borderLine {
                    color(mid) {
                        scale = continuous(Color.BLACK..Color.GREEN)
                    }
                    width = 1.8
                }
            }
        }
        // SampleEnd
            .saveSample()
    }

    @Test
    fun grouped_error_bars_dataframe() {
        // SampleStart
        val data = dataFrameOf(
            "time" to (1..5).toList() + (1..5).toList(),
            "min" to listOf(2.0, 3.4, 3.5, 5.5, 2.5) + listOf(1.0, 2.0, 3.0, 4.0, 3.7),
            "max" to listOf(3.0, 5.2, 5.0, 5.8, 3.4) + listOf(5.0, 4.0, 3.5, 5.0, 4.2),
            "category" to List(5) { "a" } + List(5) { "b" }
        )

        data.groupBy("category").plot {
            errorBars {
                x("time")
                yMin("min")
                yMax("max")
                borderLine.color("category")
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun grouped_error_bars_collections() {
        // SampleStart
        val data = mapOf(
            "time" to (1..5).toList() + (1..5).toList(),
            "min" to listOf(2.0, 3.4, 3.5, 5.5, 2.5) + listOf(1.0, 2.0, 3.0, 4.0, 3.7),
            "max" to listOf(3.0, 5.2, 5.0, 5.8, 3.4) + listOf(5.0, 4.0, 3.5, 5.0, 4.2),
            "category" to List(5) { "a" } + List(5) { "b" }
        )

        data.plot {
            groupBy("category") {
                errorBars {
                    x("time")
                    yMin("min")
                    yMax("max")
                    borderLine.color("category")
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun error_bars_with_boxplot() {
        // SampleStart
        val random = java.util.Random(42)

        val valuesA = List(100) { 3.0 + random.nextGaussian() * 0.5 }
        val valuesB = List(100) { 1.5 + random.nextDouble() * 4.5 }
        val valuesC = valuesA.zip(valuesB).map { (it.first + it.second) / 2.0 }


        val df = dataFrameOf(
            "value" to valuesA + valuesB + valuesC,
            "group" to  List(100) {"a"} + List(100) {"b"} + List(100) {"c"}
        )

        df.plot {
            statBoxplot("group", "value") {
                errorBars {
                    x(Stat.x)
                    yMin(Stat.min)
                    yMax(Stat.max)
                    borderLine.color(Stat.x)
                }
            }
        }
        // SampleEnd
            .saveSample()
    }
}