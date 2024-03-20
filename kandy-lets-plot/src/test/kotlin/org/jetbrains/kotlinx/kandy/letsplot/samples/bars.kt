package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.feature.position
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.layers.barsH
import org.jetbrains.kotlinx.kandy.letsplot.scales.Transformation
import org.jetbrains.kotlinx.kandy.letsplot.scales.categoricalColorHue
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.jetbrains.kotlinx.statistics.kandy.layers.histogram
import org.jetbrains.kotlinx.statistics.kandy.statplots.histogram
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statBin
import kotlin.math.sin
import kotlin.test.Test

class Bars : SampleHelper("bars") {

    @Test
    fun simple_bar_plot_dataframe() {
        // SampleStart
        val dataset = dataFrameOf(
            "city" to listOf("London", "Paris", "Berlin", "Madrid", "Rome"),
            "perc" to listOf(45, 50, 60, 40, 30)
        )

        dataset.plot {
            layout.title = "Public Transport Usage in European Cities"
            bars {
                x("city") { axis.name = "City" }
                y("perc") { axis.name = "Public Transport Usage (%)" }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun simple_bar_plot_collections() {
        // SampleStart
        val city = listOf("London", "Paris", "Berlin", "Madrid", "Rome")
        val perc = listOf(45, 50, 60, 40, 30)


        plot {
            layout.title = "Public Transport Usage in European Cities"
            bars {
                x(city) { axis.name = "City" }
                y(perc) { axis.name = "Public Transport Usage (%)" }
            }
        }
        // SampleEnd
    }

    @Test
    fun bar_settings_dataframe() {
        // SampleStart
        val candy by columnOf(
            "Honey Stars", "Fairy Tale Caramels", " ChocoDream", "Fruity Clouds",
            "Minty Spheres", "Sour Strips", "Vanilla Bars"
        )
        val sugar by columnOf(65, 58, 53, 35, 40, 45, 50)

        plot {
            layout {
                title = "Sugar content"
                xAxisLabel = "Candy Name"
                yAxisLabel = "Sugar Content (g per 100g)"
            }
            bars {
                x(candy)
                y(sugar) { scale = continuous(0..100) }
                fillColor = Color.ORANGE
                alpha = 0.85
                borderLine {
                    color = Color.GREY
                    width = 1.3
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun bar_settings_collections() {
        // SampleStart
        val candy = listOf(
            "Honey Stars", "Fairy Tale Caramels", " ChocoDream", "Fruity Clouds",
            "Minty Spheres", "Sour Strips", "Vanilla Bars"
        )
        val sugar = listOf(65, 58, 53, 35, 40, 45, 50)

        plot {
            layout {
                title = "Sugar content"
                xAxisLabel = "Candy Name"
                yAxisLabel = "Sugar Content (g per 100g)"
            }
            bars {
                x(candy)
                y(sugar) { scale = continuous(0..100) }
                fillColor = Color.ORANGE
                alpha = 0.85
                borderLine {
                    color = Color.GREY
                    width = 1.3
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun bar_gradient_dataframe() {
        // SampleStart
        val cities by columnOf("London", "Paris", "Berlin", "Madrid", "Rome", "Amsterdam", "Prague")
        val airPollution by columnOf(70, 65, 50, 60, 55, 45, 53)
        val numberOfCars by columnOf(3000, 2800, 1800, 2500, 2100, 1300, 2000)

        val dataset = dataFrameOf(cities, airPollution, numberOfCars)

        dataset.plot {
            layout.title = "Air Pollution and Vehicle Count Analysis"
            bars {
                x(cities) { axis.name = "City" }
                y(numberOfCars) { axis.name = "Number of cars (thousands)" }
                fillColor(airPollution) {
                    legend.name = "Air Pollution\n Level (AQI)"
                    scale = continuous(Color.GREEN..Color.RED)
                }
                alpha = 0.8
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun bar_gradient_collections() {
        // SampleStart
        val dataset = mapOf(
            "city" to listOf("London", "Paris", "Berlin", "Madrid", "Rome", "Amsterdam", "Prague"),
            "airPollution" to listOf(70, 65, 55, 60, 50, 45, 52),
            "numberOfCars" to listOf(3000, 2800, 2000, 2500, 2200, 1500, 1800)
        )

        dataset.plot {
            bars {
                x("city") { axis.name = "City" }
                y("numberOfCars") { axis.name = "Number of cars (thousands)" }
                fillColor("airPollution") {
                    legend.name = "Air Pollution\n Level (AQI)"
                    scale = continuous(Color.GREEN..Color.RED)
                }
                alpha = 0.8
            }

        }
        // SampleEnd
    }

    @Test
    fun fixed_bar() {
        // SampleStart
        plot {
            x(listOf(2017, 2018, 2019, 2020, 2021, 2022, 2023)) {
                axis.breaks(format = "d")
            }
            bars {
                y.constant(100)
                width = 0.5
                fillColor = Color.GREY
                alpha = 0.3
            }
            bars {
                y(listOf(20, 100, 50, 80, 70, 10, 30))
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun bar_with_reversed_axis_dataframe() {
        // SampleStart
        val dataset = dataFrameOf(
            "task" to listOf("a", "b", "c", "d", "e"),
            "time" to listOf(30, 25, 20, 35, 28)
        )

        dataset.plot {
            bars {
                x("task")
                y("time") {
                    scale = continuous(transform = Transformation.REVERSE)
                }
                fillColor = Color.hex("#07C3F2")
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun bar_with_reversed_axis_collections() {
        // SampleStart
        val dataset = mapOf(
            "task" to listOf("a", "b", "c", "d", "e"),
            "time" to listOf(30, 25, 20, 35, 28)
        )

        dataset.plot {
            bars {
                x("task")
                y("time") {
                    scale = continuous(transform = Transformation.REVERSE)
                }
                fillColor = Color.hex("#07C3F2")
            }
        }
        // SampleEnd
    }

    @Test
    fun horizontal_bars_dataframe() {
        // SampleStart
        val actors by columnOf(
            "John Doe", "Emma Stone", "Ryan Gosling", "Natalie Portman",
            "Brad Pitt", "Marilyn Monroe", "Leonardo DiCaprio"
        )
        val screenTime by columnOf(90, 75, 60, 85, 50, 40, 95)

        val dataset = dataFrameOf(actors, screenTime)

        dataset.plot {
            layout.title = "Screen Time of Hollywood Actors"
            barsH {
                y(actors) { axis.name = "Actors" }
                x(screenTime) { axis.name = "minutes" }
                alpha = 0.75
                fillColor(actors) {
                    scale = categoricalColorHue()
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun horizontal_bars_collections() {
        // SampleStart
        val actors = listOf(
            "John Doe", "Emma Stone", "Ryan Gosling", "Natalie Portman",
            "Brad Pitt", "Marilyn Monroe", "Leonardo DiCaprio"
        )
        val screenTime = listOf(90, 75, 60, 85, 50, 40, 95)

        plot {
            layout.title = "Screen Time of Hollywood Actors"
            barsH {
                y(actors) { axis.name = "Actors" }
                x(screenTime) { axis.name = "minutes" }
                alpha = 0.75
                fillColor(actors) {
                    legend.name = "actors"
                    scale = categoricalColorHue()
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun grouped_bars_dataframe() {
        // SampleStart
        val dataset = dataFrameOf(
            "day" to listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
            "coffee" to listOf(0.81, 0.78, 0.72, 0.65, 0.73, 0.49, 0.38),
            "tea" to listOf(0.12, 0.16, 0.21, 0.26, 0.24, 0.22, 0.30),
            "soda" to listOf(0.07, 0.06, 0.07, 0.09, 0.03, 0.29, 0.32),
        ).gather("coffee", "tea", "soda").into("drink", "amount")

        dataset.groupBy("drink").plot {
            layout.title = "Weekly Beverage Consumption Trends"
            bars {
                x("day")
                y("amount")
                fillColor("drink") {
                    scale = categorical(
                        "coffee" to Color.hex("#6F4E37"),
                        "tea" to Color.hex("#C2D4AB"),
                        "soda" to Color.hex("#B5651D")
                    )
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun grouped_bars_collections() {
        // SampleStart
        val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        val coffee = listOf(0.81, 0.78, 0.72, 0.65, 0.73, 0.49, 0.38)
        val tea = listOf(0.12, 0.16, 0.21, 0.26, 0.24, 0.22, 0.30)
        val soda = listOf(0.07, 0.06, 0.07, 0.09, 0.03, 0.29, 0.32)
        val dataset = mapOf(
            "day" to days + days + days,
            "amount" to coffee + tea + soda,
            "drink" to List(7) { "coffee" } + List(7) { "tea" } + List(7) { "soda" }
        )

        dataset.plot {
            layout.title = "Weekly Beverage Consumption Trends"
            groupBy("drink") {
                bars {
                    x("day")
                    y("amount")
                    fillColor("drink") {
                        scale = categorical(
                            "coffee" to Color.hex("#6F4E37"),
                            "tea" to Color.hex("#C2D4AB"),
                            "soda" to Color.hex("#B5651D")
                        )
                    }
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun stacked_bars_dataframe() {
        // SampleStart
        val dataset = dataFrameOf(
            "day" to listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
            "coffee" to listOf(0.81, 0.78, 0.72, 0.65, 0.73, 0.49, 0.38),
            "tea" to listOf(0.12, 0.16, 0.21, 0.26, 0.24, 0.22, 0.30),
            "soda" to listOf(0.07, 0.06, 0.07, 0.09, 0.03, 0.29, 0.32),
        ).gather("coffee", "tea", "soda").into("drink", "amount")

        dataset.groupBy("drink").plot {
            layout.title = "Weekly Beverage Consumption Trends"
            bars {
                x("day")
                y("amount")
                fillColor("drink") {
                    scale = categorical(
                        "coffee" to Color.hex("#6F4E37"),
                        "tea" to Color.hex("#C2D4AB"),
                        "soda" to Color.hex("#B5651D")
                    )
                }
                position = Position.stack()
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun stacked_bars_collections() {
        // SampleStart
        val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        val coffee = listOf(0.81, 0.78, 0.72, 0.65, 0.73, 0.49, 0.38)
        val tea = listOf(0.12, 0.16, 0.21, 0.26, 0.24, 0.22, 0.30)
        val soda = listOf(0.07, 0.06, 0.07, 0.09, 0.03, 0.29, 0.32)
        val dataset = mapOf(
            "day" to days + days + days,
            "amount" to coffee + tea + soda,
            "drink" to List(7) { "coffee" } + List(7) { "tea" } + List(7) { "soda" }
        )

        dataset.plot {
            layout.title = "Weekly Beverage Consumption Trends"
            groupBy("drink") {
                bars {
                    x("day")
                    y("amount")
                    fillColor("drink") {
                        scale = categorical(
                            "coffee" to Color.hex("#6F4E37"),
                            "tea" to Color.hex("#C2D4AB"),
                            "soda" to Color.hex("#B5651D")
                        )
                    }
                    position = Position.stack()
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun functional_bars_plot() {
        // SampleStart
        val xs = (-80..80).map { it.toDouble() / 8.0 }
        val function = { x: Double -> sin(x / 1.5) }
        val ys = xs.map(function)

        plot {
            bars {
                x(xs)
                y(ys)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun histogram_1() {
        // SampleStart

        val random = java.util.Random(1111)
        val sample = List(1000) { random.nextGaussian() }

        dataFrameOf("sample" to sample).histogram("sample")
        // SampleEnd
    }

    @Test
    fun histogram_2() {
        // SampleStart

        val random = java.util.Random(1111)
        val sample = List(1000) { random.nextGaussian() }

        plot {
            histogram(sample)
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun histogram_3() {
        // SampleStart

        val random = java.util.Random(1111)
        val sample = List(1000) { random.nextGaussian() }

        plot {
            statBin(sample) {
                bars {
                    x(Stat.x)
                    y(Stat.count)
                }
            }
        }
        // SampleEnd
    }
}