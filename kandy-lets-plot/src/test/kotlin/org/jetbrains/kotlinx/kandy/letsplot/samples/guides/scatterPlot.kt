package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.api.convert
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.with
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.invoke
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.feature.position
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.settings.Symbol
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class Scatter : SampleHelper("geoms", "guides") {

    private val rand = java.util.Random(123)
    private val n = 20
    private val dataset = dataFrameOf(
        "cond" to List(n / 2) { "A" } + List(n / 2) { "B" },
        "xvar" to List(n) { i: Int -> i },
        "yvar" to List(n) { i: Int -> i + rand.nextGaussian() * 3 }
    )
    private val cond = "cond"<String>()
    private val xvar = "xvar"<Int>()
    private val yvar = "yvar"<Double>()

    private val datasetOverlapping = dataset.convert { xvar and yvar }.with {
        (it.toDouble() / 5).toInt() * 5
    }

    @Test
    fun guideScatterData() {
        // SampleStart
        // This example was found at:
        // www.cookbook-r.com/Graphs/Scatterplots_(ggplot2)
        val rand = java.util.Random(123)
        val n = 20
        val dataset = dataFrameOf(
            "cond" to List(n / 2) { "A" } + List(n / 2) { "B" },
            "xvar" to List(n) { i: Int -> i },
            "yvar" to List(n) { i: Int -> i + rand.nextGaussian() * 3 }
        )
        val cond = "cond"<String>()
        val xvar = "xvar"<Int>()
        val yvar = "yvar"<Double>()
        // SampleEnd
        assertNotNull(dataset.get { cond })
        assertNotNull(dataset.get { xvar })
        assertNotNull(dataset.get { yvar })
    }

    @Test
    fun guideScatterBasicScatterPlot() {
        // SampleStart
        plot(dataset) {
            points {
                x(xvar)
                y(yvar)
                symbol = Symbol.CIRCLE_OPEN
            }
        }
            // SampleEnd
            .savePlotSVGSample()
    }

    @Test
    fun guideScatterScatterPlotWithDiffSymbol() {
        // SampleStart
        plot(dataset) {
            points {
                x(xvar)
                y(yvar)
                color(cond)
                symbol(cond)
                size = 5.0
            }
            layout {
                size = 700 to 350
            }
        }
            // SampleEnd
            .savePlotSVGSample()
    }

    @Test
    fun guideScatterScatterPlotWithOpenSymbol() {
        // SampleStart
        plot(dataset) {
            points {
                x(xvar)
                y(yvar)
                color(cond)
                symbol(cond) {
                    scale = categorical(range = listOf(Symbol.CIRCLE_OPEN, Symbol.TRIANGLE_OPEN))
                }
                size = 5.0
            }
            layout {
                size = 700 to 350
            }
        }
            // SampleEnd
            .savePlotSVGSample()
    }

    @Test
    fun guideScatterDatasetOverlapping() {
        // SampleStart
        // Create data with overlapping points.
        val datasetOverlapping = dataset.convert { xvar and yvar }.with {
            (it.toDouble() / 5).toInt() * 5
        }
        // SampleEnd
        assertEquals(typeOf<Int>(), datasetOverlapping.get { xvar }.type())
        assertEquals(typeOf<Int>(), datasetOverlapping.get { yvar }.type())
    }

    @Test
    fun guideScatterHandlingOverPlotting() {
        // SampleStart
        plot(datasetOverlapping) {
            points {
                x(xvar) {
                    axis.breaks(listOf(0, 5, 10, 15))
                }
                y(yvar)
                alpha = .3
                size = 7.0
            }
            layout {
                size = 700 to 350
            }
        }
            // SampleEnd
            .savePlotSVGSample()
    }

    @Test
    fun guideScatterHandlingOverPlottingJitter() {
        // SampleStart
        plot(datasetOverlapping) {
            points {
                x(xvar) {
                    axis.breaks(listOf(0, 5, 10, 15))
                }
                y(yvar)
                symbol = Symbol.CIRCLE_OPEN

                position = Position.jitter(.1, .1)
            }
            layout {
                size = 700 to 350
            }
        }
        // SampleEnd
//            .saveSample()
    }
}