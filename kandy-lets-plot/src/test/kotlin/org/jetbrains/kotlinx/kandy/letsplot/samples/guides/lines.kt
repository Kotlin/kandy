package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.median
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.*
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotBunch
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.statistics.kandy.layers.smoothLine
import org.jetbrains.kotlinx.statistics.plotting.smooth.SmoothMethod
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.test.Test
import kotlin.test.assertNotNull

class Lines : SampleHelper("geoms", "guides") {

    private val mpgDf =
        DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")

    private val cty = column<Int>("cty")
    private val hwy = column<Int>("hwy")
    private val ctyMedian = mpgDf.median { cty }
    private val hwyMedian = mpgDf.median { hwy }

    private fun generateParabolicDataMap(n: Int = 25, a: Double = 1.0): Map<String, List<Double>> {
        val rand = java.util.Random(42)
        val x = List(2 * n + 1) { i -> a * (i - n).toDouble() / n }
        val y = x.map { i -> i * i + rand.nextGaussian() }
        return mapOf("x" to x, "y" to y)
    }

    private val pDataMap = generateParabolicDataMap(a = 3.0)
    private val xSrc = column<Double>("x")
    private val ySrc = column<Double>("y")

    @Test
    fun guideLinesCalculateMedian() {
        // SampleStart
        val ctyMedian = mpgDf.median { cty }
        val hwyMedian = mpgDf.median { hwy }
        // SampleEnd
        assertNotNull(ctyMedian)
        assertNotNull(hwyMedian)
    }

    @Test
    fun guideLinesSmoothLinear() {
        // SampleStart
        val medianColor = Color.hex("#756bb1")
        mpgDf.plot {
            points {
                x(cty)
                y(hwy)
            }
            vLine {
                xIntercept.constant(ctyMedian)
                color = medianColor
                type = LineType.DASHED
            }
            hLine {
                yIntercept.constant(hwyMedian)
                color = medianColor
                type = LineType.DASHED
            }
            smoothLine("cty", "hwy", method = SmoothMethod.Linear()) {
                color = Color.hex("#de2d26")
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideLinesBrokenLinesData() {
        // SampleStart
        fun generateParabolicDataMap(n: Int = 25, a: Double = 1.0): Map<String, List<Double>> {
            val rand = java.util.Random(42)
            val x = List(2 * n + 1) { i -> a * (i - n).toDouble() / n }
            val y = x.map { i -> i * i + rand.nextGaussian() }
            return mapOf("x" to x, "y" to y)
        }

        val pDataMap = generateParabolicDataMap(a = 3.0)
        val xSrc = column<Double>("x")
        val ySrc = column<Double>("y")
        // SampleEnd
        assertNotNull(pDataMap)
        assertNotNull(xSrc)
        assertNotNull(ySrc)
    }

    @Test
    fun guideLinesBrokenLinesLinePlot() {
        // SampleStart
        plot(pDataMap) {
            line {
                x(xSrc)
                y(ySrc)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideLinesBrokenLinesPathPlot() {
        // SampleStart
        plot(pDataMap) {
            path {
                x(xSrc)
                y(ySrc)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideLinesBrokenLinesStepPlot() {
        // SampleStart
        plot(pDataMap) {
            step {
                x(xSrc)
                y(ySrc)
            }
        }
            // SampleEnd
            .saveSample()
    }

    private fun generateArchimedeanDataMap(n: Int = 25, k: Double = 1.0, a: Double = 1.0): Map<String, List<Double>> {
        val phi = List(n) { i -> 2.0 * PI * k * i.toDouble() / (n - 1) }
        val r = phi.map { angle -> (a * angle) / (2.0 * PI) }
        val x = (r zip phi).map { p -> p.first * cos(p.second) }
        val y = (r zip phi).map { p -> p.first * sin(p.second) }
        return mapOf("x" to x, "y" to y)
    }

    private val aDataMap = generateArchimedeanDataMap(n = 200, k = 2.0)


    @Test
    fun guideLinesCompareLinePathSegmentsData() {
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
        assertNotNull(aDataMap["y"])
    }

    @Test
    fun guideLinesCompareLinePathSegmentsPlotBunch() {
        // SampleStart
        val linePlot = plot(aDataMap) {
            line {
                x(xSrc)
                y(ySrc)
            }
            layout.title = "Line Plot"
        }

        val pathPlot = plot(aDataMap) {
            path {
                x(xSrc)
                y(ySrc)
            }
            layout.title = "Path Plot"
        }

        val segments = plot(generateArchimedeanDataMap(n = 50)) {
            segments {
                xBegin(xSrc)
                yBegin(ySrc)
                xEnd.constant(0.0)
                yEnd.constant(0.0)
            }
        }
        plotBunch {
            add(linePlot, 0, 0, 350, 300)
            add(pathPlot, 350, 0, 350, 300)
            add(segments, 0, 300, 700, 300)
        }
            // SampleEnd
            .saveSample()
    }
}