package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.feature.position
import org.jetbrains.kotlinx.kandy.letsplot.layers.abLine
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.scales.categoricalColorHue
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.LegendType
import org.jetbrains.kotlinx.kandy.letsplot.settings.Symbol
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.math.sin
import kotlin.test.Test

class Points : SampleHelper("points") {

    @Test
    fun basic_points_plot_dataframe() {
        // SampleStart
        val data = dataFrameOf(
            "xs" to listOf(
                5.93, 9.15, 3.76, 5.04, 2.23,
                7.47, 2.59, 11.67, 7.90, 3.71,
                0.03, 2.73, 4.61, 5.44, 1.76,
                14.46, 1.89
            ),
            "ys" to listOf(
                14.66, 13.80, 5.37, 6.40, 6.86,
                2.98, 6.69, 5.48, 3.67, 12.36,
                0.01, 14.47, 14.56, 9.19, 12.86,
                5.37, 0.90
            )
        )

        data.plot {
            points {
                x("xs")
                y("ys")
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun basic_points_plot_collections() {
        // SampleStart
        val xs = listOf(
            5.93, 9.15, 3.76, 5.04, 2.23,
            7.47, 2.59, 11.67, 7.90, 3.71,
            0.03, 2.73, 4.61, 5.44, 1.76,
            14.46, 1.89
        )
        val ys = listOf(
            14.66, 13.80, 5.37, 6.40, 6.86,
            2.98, 6.69, 5.48, 3.67, 12.36,
            0.01, 14.47, 14.56, 9.19, 12.86,
            5.37, 0.90
        )

        plot {
            points {
                x(xs)
                y(ys)
            }
        }
        // SampleEnd
    }

    @Test
    fun points_settings_dataframe() {
        // SampleStart
        val data = dataFrameOf(
            "xs" to listOf(
                5.93, 9.15, 3.76, 5.04, 2.23,
                7.47, 2.59, 11.67, 7.90, 3.71,
                0.03, 2.73, 4.61, 5.44, 1.76,
                14.46, 1.89
            ),
            "ys" to listOf(
                14.66, 13.80, 5.37, 6.40, 6.86,
                2.98, 6.69, 5.48, 3.67, 12.36,
                0.01, 14.47, 14.56, 9.19, 12.86,
                5.37, 0.90
            )
        )

        data.plot {
            points {
                x("xs")
                y("ys")
                size = 10.0
                color = Color.BLUE
                symbol = Symbol.DIAMOND
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun points_settings_collections() {
        // SampleStart
        val xs = listOf(
            5.93, 9.15, 3.76, 5.04, 2.23,
            7.47, 2.59, 11.67, 7.90, 3.71,
            0.03, 2.73, 4.61, 5.44, 1.76,
            14.46, 1.89
        )
        val ys = listOf(
            14.66, 13.80, 5.37, 6.40, 6.86,
            2.98, 6.69, 5.48, 3.67, 12.36,
            0.01, 14.47, 14.56, 9.19, 12.86,
            5.37, 0.90
        )

        plot {
            points {
                x(xs)
                y(ys)
                size = 10.0
                color = Color.BLUE
                symbol = Symbol.DIAMOND
            }
        }
        // SampleEnd
    }

    @Test
    fun points_with_abLine_dataframe() {
        // SampleStart
        val xValues by columnOf(
            7.13, 9.30, 7.84, 7.08, 5.51,
            8.40, 5.69, 11.59, 12.53, 4.98,
            10.29, 6.88, 7.38, 12.03, 0.92
        )
        val yValues by columnOf(
            7.05, 8.23, 6.74, 7.95, 5.38,
            7.47, 4.88, 9.17, 9.30, 6.17,
            6.58, 5.87, 6.45, 10.53, 3.13
        )

        plot {
            points {
                x(xValues)
                y(yValues)
                size = 7.0
                color = Color.LIGHT_BLUE
            }
            abLine {
                slope.constant(0.5)
                intercept.constant(3)
                color = Color.RED
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun points_with_abLine_collections() {
        // SampleStart
        val xValues = listOf(
            7.13, 9.30, 7.84, 7.08, 5.51,
            8.40, 5.69, 11.59, 12.53, 4.98,
            10.29, 6.88, 7.38, 12.03, 0.92
        )
        val yValues = listOf(
            7.05, 8.23, 6.74, 7.95, 5.38,
            7.47, 4.88, 9.17, 9.30, 6.17,
            6.58, 5.87, 6.45, 10.53, 3.13
        )

        plot {
            points {
                x(xValues)
                y(yValues)
                size = 7.0
                color = Color.LIGHT_BLUE
            }
            abLine {
                slope.constant(0.5)
                intercept.constant(3)
                color = Color.RED
            }
        }
        // SampleEnd
    }

    @Test
    fun points_gradient() {
        // SampleStart
        val xs = List(100) { kotlin.random.Random.nextDouble(0.0, 10.0) }
        val ys = List(100) { kotlin.random.Random.nextDouble(0.0, 10.0) }
        val gradient = List(100) { kotlin.random.Random.nextDouble(0.0, 100.0) }
        plot {
            points {
                x(xs)
                y(ys)
                size = 7.5
                color(gradient) {
                    scale = continuous(Color.LIGHT_BLUE..Color.PURPLE)
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun points_with_color_by_category_dataframe() {
        // SampleStart
        val data = dataFrameOf(
            "xShot" to listOf(
                4.02, 5.24, 4.41, 3.99, 3.10, 4.73, 3.20, 6.53, 7.05, 2.81,
                5.80, 3.87, 4.16, 6.78, 0.52, 0.64, 0.15, 6.09, 5.70, 6.37
            ),
            "yShot" to listOf(
                2.39, 1.95, 1.13, 1.90, 0.29, 1.56, 0.35, 2.30, 1.27, 1.01,
                0.65, 1.89, 1.11, 1.39, 0.05, 1.51, 1.49, 1.51, 2.30, 1.66
            ),
            "outcome" to listOf(
                false, true, false, true, true, true, true, true, true, false,
                true, true, false, false, true, false, false, true, true, false
            )
        )

        data.plot {
            layout.title = "Penalty Shot Outcomes Analysis"
            points {
                x("xShot") { axis.name = "Horizontal Position (meters)" }
                y("yShot") { axis.name = "Vertical Position (meters)" }
                size = 8.5
                color("outcome") {
                    scale = categorical(
                        true to Color.GREEN, false to Color.RED
                    )
                    legend.name = "Outcome\n(Green for Goals\nRed for Misses)"
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun points_with_color_by_category_collections() {
        // SampleStart
        val xShot = listOf(
            4.02, 5.24, 4.41, 3.99, 3.10, 4.73, 3.20, 6.53, 7.05, 2.81,
            5.80, 3.87, 4.16, 6.78, 0.52, 0.64, 0.15, 6.09, 5.70, 6.37
        )
        val yShot = listOf(
            2.39, 1.95, 1.13, 1.90, 0.29, 1.56, 0.35, 2.30, 1.27, 1.01,
            0.65, 1.89, 1.11, 1.39, 0.05, 1.51, 1.49, 1.51, 2.30, 1.66
        )
        val outcome = listOf(
            false, true, false, true, true, true, true, true, true, false,
            true, true, false, false, true, false, false, true, true, false
        )


        plot {
            layout.title = "Penalty Shot Outcomes Analysis"
            points {
                x(xShot) { axis.name = "Horizontal Position (meters)" }
                y(yShot) { axis.name = "Vertical Position (meters)" }
                size = 8.5
                color(outcome) {
                    scale = categorical(
                        true to Color.GREEN, false to Color.RED
                    )
                    legend.name = "Outcome\n(Green for Goals\nRed for Misses)"
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun fixed_points() {
        // SampleStart
        plot {
            points {
                x.constant(0.5)
                y(listOf(1, 2, 3, 4, 5, 6, 7))
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun jittered_points_dataframe() {
        // SampleStart
        val data = dataFrameOf(
            "type" to List(50) { "a" } + List(50) { "b" },
            "value" to List(50) { kotlin.random.Random.nextDouble(0.1, 0.6) } +
                    List(50) { kotlin.random.Random.nextDouble(-0.5, 0.4) }
        )

        val type = column<String>("type")
        val value = column<Double>("value")

        data.plot {
            points {
                x(type)
                y(value)
                color(type)
                position = Position.jitter()
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun jittered_points_collections() {
        // SampleStart
        val type = List(50) { "a" } + List(50) { "b" }
        val value = List(50) { kotlin.random.Random.nextDouble(0.1, 0.6) } +
                List(50) { kotlin.random.Random.nextDouble(-0.5, 0.4) }

        plot {
            points {
                x(type)
                y(value)
                color(type)
                position = Position.jitter()
            }
        }
        // SampleEnd
    }

    @Test
    fun bubble_chart() {
        // SampleStart
        val dayOfWeek = listOf(
            "Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue", "Wed",
            "Thu", "Fri", "Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue", "Wed", "Thu"
        )
        val week = listOf(
            17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 18, 19, 19, 19,
            19, 19, 19, 19, 20, 20, 20, 20, 20, 20, 20, 21, 21, 21, 21
        )
        val contributions = listOf(
            2, 1, 7, 0, 0, 0, 5, 11, 4, 5, 0, 1, 3, 8, 1,
            6, 12, 1, 0, 0, 0, 15, 7, 3, 0, 1, 2, 5, 6, 3
        )

        plot {
            points {
                x(week) {
                    axis.name = "Week"
                }
                y(dayOfWeek) {
                    axis {
                        breaks(listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun").reversed())
                        name = "Day of week"
                    }
                }
                color = Color.BLUE
                size(contributions) {
                    scale = continuous(5.0..20.0, 1..15)
                    legend.name = "Contributions"
                }
            }
            layout.title = "May GitHub contributions"
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun complex_bubble_chart() {
        // SampleStart
        val country by columnOf(
            "Australia", "Canada", "China", "Cuba", "Finland", "France", "Germany",
            "Iceland", "India", "Japan", "North Korea", "South Korea", "New Zealand",
            "Norway", "Poland", "Russia", "Turkey", "United Kingdom", "United States"
        )
        val lifeExp1998 = columnOf(
            75.49, 76.03, 66.35, 58.75, 79.64, 82.92, 80.68,
            70.71, 60.30, 82.78, 69.57, 74.61, 71.18,
            71.43, 75.22, 72.65, 74.56, 75.19, 81.12
        ) named "lifeExp"
        val pop1998 = columnOf(
            19000, 30000, 1250000, 11000, 5000,
            59000, 82000, 280, 950000, 126000,
            24000, 47000, 3800, 4400, 38600,
            147000, 63000, 59000, 273000
        ) named "population"
        val gdpPerCapita1998 = columnOf(
            29000, 32000, 750, 5000, 28000,
            27000, 32000, 34000, 450, 33000,
            600, 12000, 22000, 40000, 9000,
            4000, 3000, 28000, 35000
        ) named "gdp"
        val data1998 = dataFrameOf(country, lifeExp1998, pop1998, gdpPerCapita1998).add("year") { 1998 }

        val lifeExp2023 = columnOf(
            79.44, 73.60, 70.46, 63.34, 72.10, 78.15, 80.70,
            79.88, 63.13, 81.53, 59.33, 71.59, 76.56,
            71.97, 78.21, 78.38, 79.76, 84.77, 82.39
        ) named "lifeExp"
        val pop2023 = columnOf(
            25000, 38000, 1400000, 11300, 5500,
            67000, 83000, 340, 1350000, 126000,
            25000, 51000, 5000, 5300, 38000,
            146000, 82000, 67000, 331000
        ) named "population"
        val gdpPerCapita2023 = columnOf(
            55000, 52000, 10000, 7000, 48000,
            44000, 50000, 60000, 2000, 45000,
            1200, 30000, 34000, 70000, 15000,
            9000, 10000, 40000, 60000
        ) named "gdp"
        val data2023 = dataFrameOf(country, lifeExp2023, pop2023, gdpPerCapita2023).add("year") { 2023 }

        val data = data1998.fullJoin(data2023)

        data.groupBy("year").plot {
            layout.title = "Life Expectancy and GDP by Country"
            points {
                x("gdp") { axis.name = "GDP per capita (in dollars)" }
                y("lifeExp") { axis.name = "Life expectancy (years)" }
                size("population") {
                    legend.type = LegendType.None
                    scale = continuous(5.0..20.0)
                }
                color("year") {
                    legend.name = ""
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun complex_bubble_chart_part2() {
        val country by columnOf(
            "Australia", "Canada", "China", "Cuba", "Finland", "France", "Germany",
            "Iceland", "India", "Japan", "North Korea", "South Korea", "New Zealand",
            "Norway", "Poland", "Russia", "Turkey", "United Kingdom", "United States"
        )
        val lifeExp1998 = columnOf(
            75.49, 76.03, 66.35, 58.75, 79.64, 82.92, 80.68,
            70.71, 60.30, 82.78, 69.57, 74.61, 71.18,
            71.43, 75.22, 72.65, 74.56, 75.19, 81.12
        ) named "lifeExp"
        val pop1998 = columnOf(
            19000, 30000, 1250000, 11000, 5000,
            59000, 82000, 280, 950000, 126000,
            24000, 47000, 3800, 4400, 38600,
            147000, 63000, 59000, 273000
        ) named "population"
        val gdpPerCapita1998 = columnOf(
            29000, 32000, 750, 5000, 28000,
            27000, 32000, 34000, 450, 33000,
            600, 12000, 22000, 40000, 9000,
            4000, 3000, 28000, 35000
        ) named "gdp"
        val data = dataFrameOf(country, lifeExp1998, pop1998, gdpPerCapita1998).add("year") { 1998 }
        // SampleStart
        data.filter { "year"<Int>() == 1998 }.plot {
            layout.title = "Life Expectancy and GDP by Country (1998)"
            points {
                x("gdp") { axis.name = "GDP per capita (in dollars)" }
                y("lifeExp") { axis.name = "Life expectancy (years)" }
                size("population") {
                    legend.type = LegendType.None
                    scale = continuous(2.0..15.0)
                }
                color("country") {
                    scale = categoricalColorHue()
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun complex_bubble_chart_part3() {
        val country by columnOf(
            "Australia", "Canada", "China", "Cuba", "Finland", "France", "Germany",
            "Iceland", "India", "Japan", "North Korea", "South Korea", "New Zealand",
            "Norway", "Poland", "Russia", "Turkey", "United Kingdom", "United States"
        )
        val lifeExp2023 = columnOf(
            79.44, 73.60, 70.46, 63.34, 72.10, 78.15, 80.70,
            79.88, 63.13, 81.53, 59.33, 71.59, 76.56,
            71.97, 78.21, 78.38, 79.76, 84.77, 82.39
        ) named "lifeExp"
        val pop2023 = columnOf(
            25000, 38000, 1400000, 11300, 5500,
            67000, 83000, 340, 1350000, 126000,
            25000, 51000, 5000, 5300, 38000,
            146000, 82000, 67000, 331000
        ) named "population"
        val gdpPerCapita2023 = columnOf(
            55000, 52000, 10000, 7000, 48000,
            44000, 50000, 60000, 2000, 45000,
            1200, 30000, 34000, 70000, 15000,
            9000, 10000, 40000, 60000
        ) named "gdp"
        val data = dataFrameOf(country, lifeExp2023, pop2023, gdpPerCapita2023).add("year") { 2023 }
        // SampleStart
        data.filter { "year"<Int>() == 2023 }.plot {
            layout.title = "Life Expectancy and GDP by Country (2023)"
            points {
                x("gdp") { axis.name = "GDP per capita (in dollars)" }
                y("lifeExp") { axis.name = "Life expectancy (years)" }
                size("population") {
                    legend.type = LegendType.None
                    scale = continuous(2.0..15.0)
                }
                color("country") {
                    scale = categoricalColorHue()
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun functional_scatter_plot() {
        // SampleStart

        val xs = (-30..30).map { it.toDouble() / 5.0 }
        val function = { x: Double -> sin(x) }
        val ys = xs.map(function)

        plot {
            points {
                x(xs)
                y(ys)
            }
        }
        // SampleEnd
            .saveSample()
    }
}