package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.area
import org.jetbrains.kotlinx.kandy.letsplot.layers.hLine
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.scales.Transformation
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model.limits
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.letsplot.y
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.jetbrains.kotlinx.statistics.kandy.layers.densityPlot
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statSmooth
import org.jetbrains.kotlinx.statistics.plotting.smooth.SmoothMethod
import kotlin.math.cos
import kotlin.math.sin
import kotlin.test.Test

class Area : SampleHelper("area") {

    @Test
    fun simple_area_dataframe() {
        // SampleStart
        val dataframe = dataFrameOf(
            "years" to listOf("2017", "2018", "2019", "2020", "2021", "2022", "2023"),
            "cost" to listOf(56.1, 22.7, 34.7, 82.1, 53.7, 68.5, 39.9)
        )

        dataframe.plot {
            area {
                x("years")
                y("cost")
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun simple_area_collections() {
        // SampleStart
        val years = listOf("2017", "2018", "2019", "2020", "2021", "2022", "2023")
        val cost = listOf(56.1, 22.7, 34.7, 82.1, 53.7, 68.5, 39.9)

        plot {
            area {
                x(years)
                y(cost)
            }
        }
        // SampleEnd
    }

    @Test
    fun area_settings_dataframe() {
        // SampleStart
        val loadServer = dataFrameOf(
            "time" to listOf("00:00", "03:00", "06:00", "09:00", "12:00", "15:00", "18:00", "21:00"),
            "load" to listOf(10, 5, 15, 50, 75, 60, 80, 40)
        )
        val time = column<String>("time")
        val load = column<Int>("load")

        loadServer.plot {
            area {
                x(time) { axis.name = "Time" }
                y(load) {
                    axis.name = "Load (%)"
                    scale = continuous(0..100)
                }
                borderLine {
                    color = Color.ORANGE
                    type = LineType.DASHED
                    width = 2.5
                }
                fillColor = Color.RED
                alpha = 0.7
            }

            layout.title = "Daily Server Load Dynamics"
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun area_settings_collections() {
        // SampleStart
        val time = listOf("00:00", "03:00", "06:00", "09:00", "12:00", "15:00", "18:00", "21:00")
        val load = listOf(10, 5, 15, 50, 75, 60, 80, 40)

        plot {
            area {
                x(time) { axis.name = "Time" }
                y(load) { axis.name = "Load (%)" }
                borderLine {
                    color = Color.ORANGE
                    type = LineType.DASHED
                    width = 2.5
                }
                fillColor = Color.RED
                alpha = 0.7
            }

            layout.title = "Daily Server Load Dynamics"
        }
        // SampleEnd
    }

    @Test
    fun area_fixed_dataframe() {
        // SampleStart
        val month by columnOf(
            "January", "February",
            "March", "April", "May",
            "June", "July", "August",
            "September", "October", "November",
            "December"
        )
        val waterLvl by columnOf(4.5, 4.7, 5.0, 5.5, 6.0, 6.5, 6.7, 6.2, 5.8, 5.3, 4.8, 4.6)
        val reservoirDf = dataFrameOf(month, waterLvl)

        plot(reservoirDf) {
            x(month) {
                axis.name = "Month"
            }
            y.axis {
                name = "Water Level (meters)"
                limits = 3.0..8.0
            }
            line {
                y(waterLvl)
            }
            area {
                y.constant(5.0)
                borderLine.type = LineType.DOTTED
                alpha = 0.5
                fillColor = Color.RED
            }

            layout {
                title = "Water Level"
                subtitle = "Annual Water Level Fluctuations in Reservoir"
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun area_fixed_collections() {
        // SampleStart
        val reservoirDf = mapOf(
            "month" to listOf(
                "January", "February",
                "March", "April", "May",
                "June", "July", "August",
                "September", "October", "November",
                "December"
            ),
            "waterLvl" to listOf(4.5, 4.7, 5.0, 5.5, 6.0, 6.5, 6.7, 6.2, 5.8, 5.3, 4.8, 4.6)
        )

        plot(reservoirDf) {
            x("month") {
                axis.name = "Month"
            }
            y.axis {
                name = "Water Level (meters)"
                limits = 3.0..8.0
            }
            line {
                y("waterLvl")
            }
            area {
                y.constant(5.0)
                borderLine.type = LineType.DOTTED
                alpha = 0.5
                fillColor = Color.RED
            }

            layout {
                title = "Water Level"
                subtitle = "Annual Water Level Fluctuations in Reservoir"
            }
        }
        // SampleEnd
    }

    @Test
    fun area_with_reversed_axis_dataframe() {
        // SampleStart
        val `Day of the Week` by columnOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        val `Star Rating (Reversed)` by columnOf(4, 2, 1, 2, 3, 4, 1)

        plot {
            layout.title = "Weekly Star Ratings"
            layout.subtitle = "A reversed perspective"
            area {
                x(`Day of the Week`)
                y(`Star Rating (Reversed)`) {
                    scale = continuous(0..5, transform = Transformation.REVERSE)
                }
                fillColor = Color.hex("#FCF84A")
                alpha = 0.75
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun area_with_reversed_axis_collections() {
        // SampleStart
        val week = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        val stars = listOf(4, 2, 1, 2, 3, 4, 1)

        plot {
            layout.title = "Weekly Star Ratings"
            layout.subtitle = "A reversed perspective"
            area {
                x(week) { axis.name = "Day of the Week" }
                y(stars) {
                    axis.name = "Star Rating (Reversed)"
                    scale = continuous(0..5, transform = Transformation.REVERSE)
                }
                fillColor = Color.hex("#FCF84A")
                alpha = 0.75
            }
        }
        // SampleEnd
    }

    @Test
    fun several_areas_dataframe() {
        // SampleStart
        val dataset = dataFrameOf(
            "year" to listOf("2016", "2017", "2018", "2019", "2020", "2021"),
            "Apple" to listOf(700, 800, 750, 900, 850, 950),
            "Google" to listOf(1000, 950, 1200, 1150, 1250, 1300),
            "Microsoft" to listOf(600, 700, 650, 700, 750, 800),
            "Meta" to listOf(1100, 1200, 1150, 1300, 1250, 1350),
            "Amazon" to listOf(300, 400, 350, 450, 500, 600)
        ).gather("Apple", "Google", "Microsoft", "Meta", "Amazon").into("company", "users")

        dataset.groupBy("company").plot {
            layout.title = "User Growth Dynamics"
            area {
                x("year")
                y("users")
                fillColor("company") {
                    scale = categorical(
                        "Apple" to Color.hex("#FF45ED"),
                        "Google" to Color.hex("#3DEA62"),
                        "Microsoft" to Color.BLACK,
                        "Meta" to Color.hex("#FDB60D"),
                        "Amazon" to Color.hex("#087CFA")
                    )
                }
                borderLine.color("company")
                alpha = 0.3
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun several_areas_collections() {
        // SampleStart
        val year = listOf("2016", "2017", "2018", "2019", "2020", "2021")

        val usersApple = listOf(700, 800, 750, 900, 850, 950)
        val usersGoogle = listOf(1000, 950, 1200, 1150, 1250, 1300)
        val usersMicrosoft = listOf(600, 700, 650, 700, 750, 800)
        val usersMeta = listOf(1100, 1200, 1150, 1300, 1250, 1350)
        val usersAmazon = listOf(300, 400, 350, 450, 500, 600)

        val dataset = mapOf(
            "year" to year + year + year + year + year,
            "users" to usersApple + usersGoogle + usersMicrosoft + usersMeta + usersAmazon,
            "company" to List(6) { "Apple" } + List(6) { "Google" } + List(6) { "Microsoft" } + List(6) { "Meta" } + List(
                6
            ) { "Amazon" }
        )

        plot(dataset) {
            layout.title = "User Growth Dynamics"
            groupBy("company") {
                area {
                    x("year")
                    y("users")
                    fillColor("company") {
                        scale = categorical(
                            "Apple" to Color.hex("#FF45ED"),
                            "Google" to Color.hex("#3DEA62"),
                            "Microsoft" to Color.BLACK,
                            "Meta" to Color.hex("#FDB60D"),
                            "Amazon" to Color.hex("#087CFA")
                        )
                    }
                    borderLine.color("company")
                    alpha = 0.3
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun functional_area_plot() {
        // SampleStart
        val xs = (-2000..2000).map { it / 500.0f }
        val function = { x: Float -> sin(x) * cos(x * 2 + 1) * sin(3 * x + 2) }
        val ys = xs.map(function)

        plot {
            area {
                x(xs)
                y(ys)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun area_with_mark_line_dataframe() {
        // SampleStart
        val months = listOf(
            "January", "February",
            "March", "April", "May",
            "June", "July", "August",
            "September", "October", "November",
            "December"
        )
        val tempBerlin =
            listOf(-0.5, 0.0, 4.8, 9.0, 14.3, 17.5, 19.2, 18.9, 14.5, 9.7, 4.7, 1.0)
        val tempMadrid =
            listOf(6.3, 7.9, 11.2, 12.9, 16.7, 21.1, 24.7, 24.2, 20.3, 15.4, 9.9, 6.6)

        val df = dataFrameOf(
            "month" to months + months,
            "temperature" to tempBerlin + tempMadrid,
            "city" to List(12) { "Berlin" } + List(12) { "Madrid" }
        )

        df.plot {
            area {
                x("month")
                y("temperature")
                fillColor("city") {
                    scale = categorical("Berlin" to Color.hex("#07C3F2"), "Madrid" to Color.hex("#FDB60D"))
                }
                alpha = 0.5
                borderLine.width = 1.5
            }
            hLine {
                yIntercept.constant(tempBerlin.average())
                color = Color.BLACK
                width = 2.0
                type = LineType.DASHED
            }
            hLine {
                yIntercept.constant(tempMadrid.average())
                color = Color.RED
                width = 2.0
                type = LineType.DASHED
            }
            layout.size = 1000 to 450
        }
            // SampleEnd
            .saveSample(true)
    }

    @Test
    fun area_with_mark_line_collections() {
        // SampleStart
        val months = listOf(
            "January", "February",
            "March", "April", "May",
            "June", "July", "August",
            "September", "October", "November",
            "December"
        )
        val tempBerlin =
            listOf(-0.5, 0.0, 4.8, 9.0, 14.3, 17.5, 19.2, 18.9, 14.5, 9.7, 4.7, 1.0)
        val tempMadrid =
            listOf(6.3, 7.9, 11.2, 12.9, 16.7, 21.1, 24.7, 24.2, 20.3, 15.4, 9.9, 6.6)

        val df = mapOf(
            "month" to months + months,
            "temperature" to tempBerlin + tempMadrid,
            "city" to List(12) { "Berlin" } + List(12) { "Madrid" }
        )

        df.plot {
            area {
                x("month")
                y("temperature")
                fillColor("city") {
                    scale = categorical("Berlin" to Color.hex("#07C3F2"), "Madrid" to Color.hex("#FDB60D"))
                }
                alpha = 0.5
                borderLine.width = 1.5
            }
            hLine {
                yIntercept.constant(tempBerlin.average())
                color = Color.PURPLE
                alpha = 0.9
                type = LineType.DASHED
            }
            hLine {
                yIntercept.constant(tempMadrid.average())
                color = Color.ORANGE
                alpha = 0.9
                type = LineType.DASHED
            }
            layout.size = 1000 to 450
        }
        // SampleEnd
    }

    @Test
    fun smoothed_area() {
        // SampleStart
        val xs = listOf(-3.0, -2.5, -2.0, -1.5, -1.0, 0.0, 1.0, 1.5, 2.0, 2.5, 3.0)
        val ys = listOf(5.4, 1.2, 3.4, 0.1, 0.6, 2.1, 0.6, 2.2, 3.4, 4.5, 6.7)

        plot {
            statSmooth(xs, ys, smootherPointCount = 30) {
                area {
                    x(Stat.x)
                    y(Stat.y)
                    borderLine {
                        color = Color.GREEN
                        width = 2.0
                    }
                    alpha = 0.6
                    fillColor = Color.LIGHT_GREEN
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun smoothed_area_with_points() {
        // SampleStart
        val xs = listOf(-3.0, -2.5, -2.0, -1.5, -1.0, 0.0, 1.0, 1.5, 2.0, 2.5, 3.0)
        val ys = listOf(5.4, 1.2, 3.4, 0.7, 0.8, 2.1, 0.6, 2.2, 3.4, 4.5, 6.7)

        plot {
            statSmooth(xs, ys, method = SmoothMethod.LOESS(span = 0.3)) {
                area {
                    x(Stat.x)
                    y(Stat.y)
                    alpha = 0.75
                    fillColor = Color.LIGHT_GREEN
                    borderLine.color = Color.LIGHT_PURPLE
                }
            }
            points {
                size = 4.0
                color = Color.ORANGE
                x(xs)
                y(ys)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun density_plot() {
        // SampleStart
        val random = java.util.Random(42)

        val sample = List(1000) { random.nextGaussian() }

        plot {
            densityPlot(sample)// SampleEnd
            {
                x(Stat.x.map { it.toFloat() })
                y(Stat.density.map { it.toFloat() })
            }
            // SampleStart
        }
            // SampleEnd
            .saveSample()
    }
}