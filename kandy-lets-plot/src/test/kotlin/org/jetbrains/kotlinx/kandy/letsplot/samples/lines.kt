package org.jetbrains.kotlinx.kandy.letsplot.samples

import kotlinx.datetime.LocalDate
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.feature.Layout
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.*
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotGrid
import org.jetbrains.kotlinx.kandy.letsplot.scales.Transformation
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.letsplot.settings.Symbol
import org.jetbrains.kotlinx.kandy.letsplot.theme.Flavor
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.letsplot.y
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.statistics.kandy.layers.smoothLine
import org.jetbrains.kotlinx.statistics.plotting.smooth.SmoothMethod
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.test.Test
import kotlin.test.assertNotNull

class Lines : SampleHelper("line") {

    @Test
    fun simple_line_dataframe() {
        // SampleStart
        val years by columnOf("2018", "2019", "2020", "2021", "2022")
        val cost by columnOf(62.7, 64.7, 72.1, 73.7, 68.5)
        val df = dataFrameOf(years, cost)

        df.plot {
            line {
                x(years)
                y(cost)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun simple_line_collections() {
        // SampleStart
        val years = listOf("2018", "2019", "2020", "2021", "2022")
        val cost = listOf(62.7, 64.7, 72.1, 73.7, 68.5)

        plot {
            line {
                x(years)
                y(cost)
            }
        }
        // SampleEnd
    }

    @Test
    fun simple_line_settings_dataframe() {
        // SampleStart
        val museumVisitors = dataFrameOf("date", "visitors")(
            LocalDate(2023, 1, 1), 120,
            LocalDate(2023, 1, 15), 95,
            LocalDate(2023, 2, 1), 110,
            LocalDate(2023, 2, 15), 123,
            LocalDate(2023, 3, 1), 130,
            LocalDate(2023, 3, 15), 140,
            LocalDate(2023, 4, 1), 150,
            LocalDate(2023, 4, 15), 160,
            LocalDate(2023, 5, 1), 175,
            LocalDate(2023, 5, 15), 180
        ).convert("date").to<String>()

        museumVisitors.plot {
            line {
                x("date")
                y("visitors")
                type = LineType.DASHED
                color = Color.PURPLE
                width = 2.5
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun simple_line_settings_collections() {
        // SampleStart
        val date = listOf(
            LocalDate(2023, 1, 1),
            LocalDate(2023, 1, 15),
            LocalDate(2023, 2, 1),
            LocalDate(2023, 2, 15),
            LocalDate(2023, 3, 1),
            LocalDate(2023, 3, 15),
            LocalDate(2023, 4, 1),
            LocalDate(2023, 4, 15),
            LocalDate(2023, 5, 1),
            LocalDate(2023, 5, 15)
        ).map { it.toString() }
        val visitors = listOf(120, 95, 110, 123, 130, 140, 150, 160, 175, 180)

        plot {
            line {
                x(date)
                y(visitors)
                type = LineType.DASHED
                color = Color.PURPLE
                width = 2.5
            }
        }
        // SampleEnd
    }

    @Test
    fun line_with_points_dataframe() {
        // SampleStart
        val area by columnOf(30, 40, 50, 60, 70, 80, 90)
        val price by columnOf(60000, 80000, 75000, 90000, 85000, 95000, 90000)

        plot {
            x(price)
            y(area)
            line {
                color = Color.BLUE
                type = LineType.LONGDASH
            }
            points {
                size = 3.5
                symbol = Symbol.CIRCLE_OPEN
                color = Color.BLUE
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun line_with_points_collections() {
        // SampleStart
        val area by columnOf(30, 40, 50, 60, 70, 80, 90)
        val price by columnOf(60000, 80000, 75000, 90000, 85000, 95000, 90000)

        plot {
            x(price)
            y(area)
            line {
                color = Color.BLUE
                type = LineType.LONGDASH
            }
            points {
                size = 3.5
                symbol = Symbol.CIRCLE_OPEN
                color = Color.BLUE
            }
        }
        // SampleEnd
    }

    @Test
    fun line_fixed_coord_dataframe() {
        // SampleStart
        data class DayTemperature(val day: String, val temp: Int)

        val weeklyTemp = listOf(
            DayTemperature("Mon", 10),
            DayTemperature("Tue", 6),
            DayTemperature("Wed", 5),
            DayTemperature("Thu", 7),
            DayTemperature("Fri", 7),
            DayTemperature("Sat", 11),
            DayTemperature("Sun", 9)
        ).toDataFrame()

        weeklyTemp.plot {
            x("day")
            line {
                y("temp")
                color = Color.BLUE
            }
            line {
                y.constant(weeklyTemp[DayTemperature::temp].mean())
                color = Color.GREEN
                type = LineType.DOTTED
                width = 2.5
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun line_fixed_coord_collections() {
        // SampleStart
        val day = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        val temp = listOf(10, 6, 5, 7, 7, 11, 9)

        plot {
            x(day)
            line {
                y(temp)
                color = Color.BLUE
            }
            line {
                y.constant(temp.average())
                color = Color.GREEN
                type = LineType.DOTTED
                width = 2.5
            }
        }
        // SampleEnd
    }

    @Test
    fun line_color_gradient_dataframe() {
        // SampleStart
        val monthTemp = dataFrameOf("month", "temp")(
            "January", -5,
            "February", -3,
            "March", 2,
            "April", 10,
            "May", 16,
            "June", 20,
            "July", 22,
            "August", 21,
            "September", 15,
            "October", 9,
            "November", 3,
            "December", -2
        )

        monthTemp.plot {
            line {
                x("month")
                y("temp") {
                    scale = continuous(-10..25) // axis scale
                }
                color("temp") {
                    scale = continuous(Color.BLUE..Color.RED)
                }
                width = 3.0
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun line_color_gradient_collections() {
        // SampleStart
        val monthTemp = mapOf(
            "month" to listOf(
                "January", "February",
                "March", "April", "May",
                "June", "July", "August",
                "September", "October", "November",
                "December"
            ),
            "temp" to listOf(-5, -3, 2, 10, 16, 20, 22, 21, 15, 9, 3, -2)
        )

        monthTemp.plot {
            line {
                x("month")
                y("temp") {
                    scale = continuous(-10..25) // axis scale
                }
                color("temp") {
                    scale = continuous(Color.BLUE..Color.RED)
                }
                width = 3.0
            }
        }
        // SampleEnd
    }

    @Test
    fun line_reversed_axis_dataframe() {
        // SampleStart
        val product = ('A'..'F').toColumn("product")
        val rating = listOf(10, 7, 3, 5, 2, 1).toColumn("rating")
        val data = dataFrameOf(product, rating)

        plot(data) {
            line {
                x(rating) {
                    scale = continuous(min = 0, max = 12)
                }
                y(product) {
                    scale = continuous(transform = Transformation.REVERSE)
                }
                color = Color.RED
                alpha = 0.85
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun line_reversed_axis_collections() {
        // SampleStart
        val product = ('A'..'F')
        val rating = listOf(10, 7, 3, 5, 2, 1)

        plot {
            line {
                x(rating) {
                    scale = continuous(min = 0, max = 12)
                }
                y(product) {
                    scale = continuous(transform = Transformation.REVERSE)
                }
                color = Color.RED
                alpha = 0.85
            }
        }
        // SampleEnd
    }

    @Test
    fun several_lines_dataframe() {
        // SampleStart
        val months = listOf(1, 2, 3, 4, 5)
        val salesProducts = listOf(200.0, 220.0, 180.0, 240.0, 210.0)
        val salesClothes = listOf(150.0, 130.0, 160.0, 140.0, 170.0)
        val salesElectronics = listOf(300.0, 320.0, 310.0, 330.0, 340.0)

        val data = dataFrameOf(
            "month" to months + months + months,
            "sales" to salesProducts + salesClothes + salesElectronics,
            "category" to List(5) { "Products" } + List(5) { "Clothes" } + List(5) { "Electronics" }
        )

        data.groupBy("category").plot {
            line {
                x("month")
                y("sales")
                color("category")
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun several_lines_collections() {
        // SampleStart
        val months = listOf(1, 2, 3, 4, 5)
        val salesProducts = listOf(200.0, 220.0, 180.0, 240.0, 210.0)
        val salesClothes = listOf(150.0, 130.0, 160.0, 140.0, 170.0)
        val salesElectronics = listOf(300.0, 320.0, 310.0, 330.0, 340.0)

        val data = mapOf(
            "month" to months + months + months,
            "sales" to salesProducts + salesClothes + salesElectronics,
            "category" to List(5) { "Products" } + List(5) { "Clothes" } + List(5) { "Electronics" }
        )

        data.plot {
            groupBy("category") {
                line {
                    x("month")
                    y("sales")
                    color("category")
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun line_by_fun() {
        // SampleStart
        val xs = (-2000..2000).map { it / 500.0f }
        val function = { x: Float -> sin(x) * cos(x * 2 + 1) * sin(3 * x + 2) }
        val ys = xs.map(function)

        plot {
            line {
                x(xs)
                y(ys) {
                    scale = continuous(-1.0f..1.0f)
                }
                hLine {
                    yIntercept.constant(0.0)
                    color = Color.RED
                    type = LineType.DASHED
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun line_mark_dataframe() {
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
            line {
                x("month")
                y("temperature")
                color("city") {
                    scale = categorical("Berlin" to Color.PURPLE, "Madrid" to Color.ORANGE)
                }
                width = 1.5
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
            .saveSample(true)
    }

    @Test
    fun line_mark_collections() {
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
            line {
                x("month")
                y("temperature")
                color("city") {
                    scale = categorical("Berlin" to Color.PURPLE, "Madrid" to Color.ORANGE)
                }
                width = 1.5
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
    fun path_line() {
        // SampleStart
        val dist = listOf(100, 90, 80, 70, 60, 50, 40)
        val temp = listOf(-45.5, -44.4, -40.0, -43.2, -41.5, -35.5, -39.9)

        plot {
            layout {
                title = "Performance Dependency on Temperature"
                subtitle = "Analysis of Material Performance Decline at Extremely Low Temperatures"
                yAxisLabel = "Performance Measure"
                size = 600 to 550
            }
            path {
                y(dist)
                x(temp) {
                    axis.name = "Temperature (°C)"
                }
                color = Color.BLUE
                type = LineType.LONGDASH
            }
        }
            // SampleEnd
            .apply {
                val layout = (this.features as MutableMap)[FeatureName("layout")] as? Layout
                (this.features as MutableMap)[FeatureName("layout")] = layout?.copy(size = null) ?: Layout(size = null)
            }
            .saveSample()
    }

    @Test
    fun step_line() {
        // SampleStart
        val week = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        val start = listOf(120, 132, 101, 134, 90, 230, 210)
        val middle = listOf(220, 282, 201, 234, 290, 430, 410)
        val end = listOf(450, 432, 401, 454, 590, 530, 510)

        plot {
            x(week)
            step { y(start); color = Color.LIGHT_BLUE }
            points { y(start); symbol = Symbol.CIRCLE_OPEN; color = Color.BLUE }

            step {
                y(middle)
                color = Color.GREEN
                lineType = LineType.LONGDASH
            }
            points {
                y(middle)
                symbol = Symbol.CIRCLE_PLUS
                color = Color.GREEN
            }

            step {
                y(end)
                color = Color.YELLOW
            }
            points {
                y(end)
                symbol = Symbol.CIRCLE_FILLED
                fillColor = Color.YELLOW
                color = Color.GREY
            }
            layout {
                title = "Step line"
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun smoothed_line() {
        // SampleStart
        val xs = listOf(-3.0, -2.5, -2.0, -1.5, -1.0, 0.0, 1.0, 1.5, 2.0, 2.5, 3.0)
        val ys = listOf(-5.4, -1.2, 3.4, 0.1, -0.6, -2.1, 0.6, 2.2, 3.4, 4.5, 6.7)

        plot {
            smoothLine(xs, ys, smootherPointCount = 30) {// SampleEnd
                x(Stat.x.map { it.toFloat() })
                y(Stat.y.map { it.toFloat() })
                // SampleStart
                width = 2.3
                color = Color.GREEN
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun smoothed_curve_with_points() {
        // SampleStart
        val xs = listOf(-3.0, -2.5, -2.0, -1.5, -1.0, 0.0, 1.0, 1.5, 2.0, 2.5, 3.0)
        val ys = listOf(-5.4, -1.2, 3.4, 0.1, -0.6, -2.1, 0.6, 2.2, 3.4, 4.5, 6.7)

        plot {
            smoothLine(xs, ys, method = SmoothMethod.LOESS(span = 0.3)) {// SampleEnd
                x(Stat.x.map { it.toFloat() })
                y(Stat.y.map { it.toFloat() }) // SampleStart
                color = Color.GREEN
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
    fun line_and_path_comp_1() {
        // SampleStart
        fun generateArchimedeanDataMap(n: Int = 25, k: Double = 1.0, a: Double = 1.0): Map<String, List<Double>> {
            val phi = List(n) { i -> 2.0 * PI * k * i.toDouble() / (n - 1) }
            val r = phi.map { angle -> (a * angle) / (2.0 * PI) }
            val x = (r zip phi).map { p -> p.first * cos(p.second) }
            val y = (r zip phi).map { p -> p.first * sin(p.second) }
            return mapOf("x" to x, "y" to y)
        }

        val aDataMap = generateArchimedeanDataMap(n = 200, k = 2.0)
        // SampleEnd

        assertNotNull(aDataMap["x"])
    }

    @Test
    fun line_and_path_comp_2() {
        fun generateArchimedeanDataMap(n: Int = 25, k: Float = 1.0f, a: Float = 1.0f): Map<String, List<Float>> {
            val phi = List(n) { i -> 2.0f * PI.toFloat() * k * i.toFloat() / (n - 1) }
            val r = phi.map { angle -> (a * angle) / (2.0f * PI.toFloat()) }
            val x = (r zip phi).map { p -> p.first * cos(p.second) }
            val y = (r zip phi).map { p -> p.first * sin(p.second) }
            return mapOf("x" to x, "y" to y)
        }

        val aDataMap = generateArchimedeanDataMap(n = 200, k = 2.0f)
        // SampleStart
        val linePlot = plot(aDataMap) {
            line {
                x("x")
                y("y")
            }
            layout.title = "`line` layer"
        }
        val pathPlot = plot(aDataMap) {
            path {
                x("x")
                y("y")
            }
            layout.title = "`path` layer"
        }
        plotGrid(listOf(linePlot, pathPlot))
            // SampleEnd
            .saveSample(true)
    }
}
