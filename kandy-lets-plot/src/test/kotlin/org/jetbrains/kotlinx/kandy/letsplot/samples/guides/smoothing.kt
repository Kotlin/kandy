package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.apache.commons.math3.distribution.NormalDistribution
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.area
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.layers.ribbon
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotGrid
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.statistics.kandy.layers.smoothLine
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statSmooth
import org.jetbrains.kotlinx.statistics.plotting.smooth.SmoothMethod
import org.jetbrains.kotlinx.statistics.plotting.smooth.statSmooth
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertNotNull

class Smoothing : SampleHelper("stat", "guides") {

    private val xs = (-100..100).map { it / 50.0 }
    private val lineFormula = { x: Double -> 2.0 / (x * x + 0.5) }
    private val noises = NormalDistribution(0.0, 0.1).sample(xs.size).toList()
    private val ys = xs.zip(noises).map { lineFormula(it.first) + it.second }
    private val indices = (xs.indices).shuffled(Random(42)).take(xs.size * 1 / 3).sorted()
    private val newXs = indices.map { xs[it] }
    private val newYs = indices.map { ys[it] }
    private val df = dataFrameOf(
        "speed" to newXs,
        "efficiency" to newYs
    )
    private val speed = column<Double>("speed")
    private val efficiency = column<Double>("efficiency")

    @Test
    fun guideSmoothGenerateData() {
        // SampleStart
        // To generate the data, we use a standard java math library
        // https://commons.apache.org/proper/commons-math/

        // Generate line with formula
        val xs = (-100..100).map { it / 50.0 }
        val lineFormula = { x: Double -> 2.0 / (x * x + 0.5) }
        // Generate noises from normal distribution
        val noises = NormalDistribution(0.0, 0.1).sample(xs.size).toList()
        val ys = xs.zip(noises).map { lineFormula(it.first) + it.second }
        // And drop 2/3 points
        val random = Random(42)
        val (newXs, newYs) = xs.zip(ys).shuffled(random).take(xs.size * 1 / 3).sortedBy { it.first }.unzip()
        // SampleEnd
        assertNotNull(newXs)
        assertNotNull(newYs)
    }

    @Test
    fun guideSmoothGatherIntoDF() {
        // SampleStart
        // Gather them into the DataFrame
        val df = dataFrameOf(
            "speed" to newXs,
            "efficiency" to newYs
        )
        df.head(5)
        // SampleEnd
    }

    @Test
    fun guideSmoothDfStatSmooth() {
        // SampleStart
        df.statSmooth("speed", "efficiency").head(5)
        // SampleEnd
    }

    @Test
    fun guideSmoothPlotWithAreaAndPoints() {
        // SampleStart
        plot {
            statSmooth(newXs, newYs) {
                // new `StatSmooth` dataset here
                area {
                    // use `Stat.*` columns for mappings
                    x(Stat.x)
                    y(Stat.y)
                }
            }
            points {
                x(newXs)
                y(newYs)
            }
        }
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideSmoothPlotWithRibbonAndPoints() {
        // SampleStart
        df.plot {
            statSmooth(speed, efficiency, method = SmoothMethod.Polynomial(2), smootherPointCount = 250) {
                ribbon {
                    x(Stat.x)
                    yMin(Stat.yMin)
                    yMax(Stat.yMax)
                }
            }
            // Dataset is not changed here
            points {
                x(speed)
                y(efficiency)
            }
        }
        // SampleEnd
//            .saveSample()
    }

    @Test
    @Suppress("UNUSED_EXPRESSION")
    fun guideSmoothLineLayer() {
        // SampleStart
        val smoothLineLayerPlot = plot {
            smoothLine(newXs, newYs)
            layout.title = "`smoothLine()` layer"
        }
        smoothLineLayerPlot
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideSmoothCompareLineAndSmoothLine() {
        val smoothLineLayerPlot = plot {
            smoothLine(newXs, newYs)
            layout.title = "`smoothLine()` layer"
        }
        // SampleStart
        // Compare it with `statSmooth` + usual `line`
        val statSmoothAndLinePlot = plot {
            statSmooth(newXs, newYs) {
                line {
                    x(Stat.x)
                    y(Stat.y)
                }
            }
            layout.title = "`statSmooth()` + non-statistical `line` layer"
        }
        plotGrid(listOf(smoothLineLayerPlot, statSmoothAndLinePlot))
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideSmoothSmoothLineWithPoints() {
        // SampleStart
        df.plot {
            smoothLine(speed, efficiency, SmoothMethod.LOESS(span = 0.1), 120) {
                // change a column mapped on `y` to `Stat.scaled`
                y(Stat.yMax)
                color = Color.RED
                width = 4.0
            }
            points {
                x(speed)
                y(efficiency)
            }
        }
        // SampleEnd
//            .saveSample()
    }

    // Grouped data
    private val fA = { x: Double -> 0.02 * x * x * x - 0.2 * x * x + 0.1 * x + 2.1 }
    private val fB = { x: Double -> -0.1 * x * x * x + 0.5 * x * x - 0.8 }
    private val xRange = (-500..500).map { it / 100.0 }
    private val noisesA = NormalDistribution(0.0, 0.05).sample(xRange.size).toList()
    private val noisesB = NormalDistribution(0.0, 0.2).sample(xRange.size).toList()
    private val valuesA = xRange.zip(noisesA).map { fA(it.first) + it.second }
    private val valuesB = xRange.zip(noisesB).map { fB(it.first) + it.second }

    private val indicesAB = (xRange.indices).shuffled(Random(42)).take(xRange.size * 1 / 3).sorted()

    private val xsA = indicesAB.map { xRange[it] }
    private val ysA = indicesAB.map { valuesA[it] }
    private val xsB = xsA
    private val ysB = indicesAB.map { valuesB[it] }

    private val valuesDF = dataFrameOf(
        "time" to xsA + xsB,
        "value" to ysA + ysB,
        "category" to List(xsA.size) { "A" } + List(xsB.size) { "B" }
    )

    private val time = column<Double>("time")
    private val value = column<Double>("value")
    private val category = column<String>("category")

    private val groupedDF = valuesDF.groupBy { category }

    @Test
    fun guideSmoothGroupedData() {
        // SampleStart
        // Generate two lines
        val fA = { x: Double -> 0.02 * x * x * x - 0.2 * x * x + 0.1 * x + 2.1 }
        val fB = { x: Double -> -0.1 * x * x * x + 0.5 * x * x - 0.8 }
        val xRange = (-500..500).map { it / 100.0 }
        val noisesA = NormalDistribution(0.0, 0.05).sample(xRange.size).toList()
        val noisesB = NormalDistribution(0.0, 0.2).sample(xRange.size).toList()
        val valuesA = xRange.zip(noisesA).map { fA(it.first) + it.second }
        val valuesB = xRange.zip(noisesB).map { fB(it.first) + it.second }

        val (xsA, ysA) = xRange.zip(valuesA).shuffled(Random(17)).take(xRange.size * 1 / 3).sortedBy { it.first }
            .unzip()
        val (xsB, ysB) = xRange.zip(valuesB).shuffled(Random(17)).take(xRange.size * 1 / 3).sortedBy { it.first }
            .unzip()
        // SampleEnd
        assertNotNull(xsA)
        assertNotNull(xsB)
        assertNotNull(ysA)
        assertNotNull(ysB)
    }

    @Test
    fun guideSmoothGatherGroupedDataIntoDF() {
        // SampleStart
        // Gather them into `DataFrame` with "A" and "B" keys in "category" column
        val valuesDF = dataFrameOf(
            "time" to xsA + xsB,
            "value" to ysA + ysB,
            "category" to List(xsA.size) { "A" } + List(xsB.size) { "B" }
        )
        valuesDF.head(5)
        // SampleEnd
    }

    @Test
    @Suppress("UNUSED_EXPRESSION")
    fun guideSmoothGroupingDf() {
        // SampleStart
        // Group it by "category"
        val groupedDF = valuesDF.groupBy { category }
        groupedDF
        // SampleEnd
    }

    @Test
    fun guideSmoothGroupedDFStatSmooth() {
        val time = valuesDF[time]
        val value = valuesDF[value]
        // SampleStart
        groupedDF.statSmooth { x(time); y(value) }
        // SampleEnd
    }

    @Test
    fun guideSmoothGroupedStatSmoothPlot() {
        // SampleStart
        groupedDF.plot {
            statSmooth(time, value) {
                line {
                    x(Stat.x)
                    y(Stat.y)
                }
            }
        }
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideSmoothGroupedStatSmoothPlotWithColor() {
        // SampleStart
        groupedDF.plot {
            statSmooth(time, value, method = SmoothMethod.Polynomial(3)) {
                line {
                    x(Stat.x)
                    y(Stat.y)
                    color(category)
                }
            }
        }
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideSmoothGroupedSmoothLine() {
        // SampleStart
        groupedDF.plot {
            smoothLine(time, value)
        }
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideSmoothCustomizedGroupedSmoothLine() {
        // SampleStart
        groupedDF.plot {
            smoothLine(time, value) {
                color = Color.GREEN
                type(category)
            }
        }
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideSmoothGroupBySmoothLine() {
        // SampleStart
        valuesDF.plot {
            groupBy(category) {
                smoothLine(time, value)
            }
        }
        // SampleEnd
//            .saveSample()
    }
}