package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.tiles
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.LegendType
import org.jetbrains.kotlinx.kandy.letsplot.theme.Theme
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.jetbrains.kotlinx.statistics.kandy.layers.heatmap
import kotlin.test.Test

class Tiles : SampleHelper("tiles") {

    @Test
    fun basic_tile_plot() {
        // SampleStart
        plot {
            tiles {
                x(listOf("A", "B"))
                y(listOf(1.0, 2.0))
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun tiles_settings_dataframe() {
        // SampleStart
        val dataset = dataFrameOf(
            "store" to listOf("A", "B", "C", "A", "B", "C", "A", "B", "C"),
            "time" to listOf(
                "morning", "morning", "morning",
                "afternoon", "afternoon", "afternoon",
                "evening", "evening", "evening"
            ),
            "money" to listOf(75, 64, 59, 82, 88, 91, 69, 77, 73)
        )

        dataset.plot {
            tiles {
                x("store")
                y("time")
                height = 0.7
                borderLine {
                    width = 0.8
                    color = Color.BLACK
                }
                fillColor("money") {
                    scale = continuous(Color.RED..Color.GREEN)
                }
                alpha = 0.5
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun tiles_settings_collections() {
        // SampleStart
        val store = listOf("A", "B", "C", "A", "B", "C", "A", "B", "C")
        val time = listOf(
            "morning", "morning", "morning",
            "afternoon", "afternoon", "afternoon",
            "evening", "evening", "evening"
        )
        val money = listOf(75, 64, 59, 82, 88, 91, 69, 77, 73)

        plot {
            tiles {
                x(store)
                y(time)
                height = 0.7
                borderLine {
                    width = 0.8
                    color = Color.BLACK
                }
                fillColor(money) {
                    scale = continuous(Color.RED..Color.GREEN)
                }
                alpha = 0.5
            }
        }
        // SampleEnd
    }

    @Test
    fun fixed_tile() {
        // SampleStart
        val ys = listOf("a", "b", "c", "d")

        plot {
            tiles {
                x.constant(0.0)
                y(ys)
                alpha = 0.4
                fillColor = Color.PEACH
                borderLine.width = 0.5
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun tiles_gradient_dataframe() {
        // SampleStart
        val cities = listOf("Yerevan", "Berlin", "Amsterdam", "Paphos")
        val random = kotlin.random.Random(42)
        val year22 = List(4) { random.nextDouble() }
        val year23 = List(4) { random.nextDouble() }
        val year24 = List(4) { random.nextDouble() }

        val dataset = dataFrameOf(
            "city" to cities,
            "2022" to year22,
            "2023" to year23,
            "2024" to year24
        ).gather("2022", "2023", "2024").into("year", "value")

        dataset.plot {
            tiles {
                x("city")
                y("year") {
                    scale = categorical()
                    axis.breaks(format = "d")
                }
                fillColor("value")
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun tiles_gradient_collections() {
        // SampleStart
        val cities = listOf("Yerevan", "Berlin", "Amsterdam", "Paphos")
        val random = kotlin.random.Random(42)
        val year22 = List(4) { random.nextDouble() }
        val year23 = List(4) { random.nextDouble() }
        val year24 = List(4) { random.nextDouble() }

        val dataset = mapOf(
            "city" to cities + cities + cities,
            "year" to List(4) { "2022" } + List(4) { "2023" } + List(4) { "2024" },
            "value" to year22 + year23 + year24,
        )

        dataset.plot {
            tiles {
                x("city")
                y("year") {
                    scale = categorical()
                    axis.breaks(format = "d")
                }
                fillColor("value")
            }
        }
        // SampleEnd
    }

    @Test
    fun tiles_color_categories_dataframe() {
        // SampleStart
        val cities = listOf("Yerevan", "Berlin", "Amsterdam", "Paphos")
        val types = listOf("A", "B", "C")
        val random = kotlin.random.Random(42)
        val year22 = List(4) { types.random(random) }
        val year23 = List(4) { types.random(random) }
        val year24 = List(4) { types.random(random) }

        val dataset = dataFrameOf(
            "city" to cities,
            "2022" to year22,
            "2023" to year23,
            "2024" to year24
        ).gather("2022", "2023", "2024").into("year", "value")

        plot(dataset) {
            tiles {
                x("city")
                y("year") {
                    scale = categorical()
                    axis.breaks(format = "d")
                }
                width = 0.5
                height = 0.9
                fillColor("value") {
                    legend.breaks(types)
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun tiles_color_categories_collections() {
        // SampleStart
        val types = listOf("A", "B", "C")
        val cities = listOf("Yerevan", "Berlin", "Amsterdam", "Paphos")
        val years = listOf(2022, 2023, 2024)
        val random = kotlin.random.Random(42)
        val tripples = cities.flatMap { city ->
            years.map { year -> (city to year) to types.random(random) }
        }

        val (cityToYear, value) = tripples.unzip()
        val (city, year) = cityToYear.unzip()

        plot {
            tiles {
                x(city)
                y(year) {
                    scale = categorical()
                    axis.breaks(format = "d")
                }
                width = 0.5
                height = 0.9
                fillColor(value) {
                    legend.breaks(types)
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun basic_heatmap() {
        // SampleStart
        val random = kotlin.random.Random(42)
        val cols = (List(20) { "col1" } + List(50) { "col2" } + List(70) { "col3" }).shuffled(random)
        val rows = (List(40) { "row1" } + List(80) { "row2" } + List(20) { "row3" }).shuffled(random)

        plot {
            heatmap(cols, rows)
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun tiles_ktnb_logo() {
        // SampleStart
        val zipperCells = (0 until 40).flatMap { x ->
            val n = 3
            val sector = if (x <= 15) {
                0
            } else if (x <= 23) {
                1
            } else 2
            when (sector) {
                0 -> {
                    (0 until n).map { y -> x to x + (x - 1) / 2 + y }
                }

                1 -> {
                    (0 until n).map { y -> x to 38 - x + y }
                }

                else -> {
                    (0 until n).map { y -> x to x - 11 + (x - 19) / 2 + y }
                }
            }
        }

        val cells = (0 until 40).flatMap { x -> (0 until 40).map { y -> x to y } }.filter {
            it !in zipperCells
        }

        val xs = cells.map { it.first }
        val ys = cells.map { it.second }
        val colorFactor = xs.zip(ys).map { it.first + it.second }

        plot {
            tiles {
                x(xs)
                y(ys)
                fillColor(colorFactor) {
                    scale = continuous(Color.hex("#7d52fc")..Color.hex("#e34860"))
                    legend.type = LegendType.None
                }
            }
            layout {
                theme(Theme.Classic) {
                    blankAxes()
                }
            }
        }
            // SampleEnd
            .saveSample()
    }
}