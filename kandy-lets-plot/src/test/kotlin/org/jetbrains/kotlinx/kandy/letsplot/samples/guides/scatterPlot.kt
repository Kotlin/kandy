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
    private val data = dataFrameOf(
        "cond" to List(n / 2) { "A" } + List(n / 2) { "B" },
        "xvar" to List(n) { i: Int -> i },
        "yvar" to List(n) { i: Int -> i + rand.nextGaussian() * 3 }
    )
    private val cond = "cond"<String>()
    private val xvar = "xvar"<Int>()
    private val yvar = "yvar"<Double>()

    private val data1 = data.convert { xvar and yvar }.with {
        (it.toDouble() / 5).toInt() * 5
    }

    @Test
    fun guideScatterData() {
        // SampleStart
        // This example was found at:
        // www.cookbook-r.com/Graphs/Scatterplots_(ggplot2)
        val rand = java.util.Random(123)
        val n = 20
        val data = dataFrameOf(
            "cond" to List(n / 2) { "A" } + List(n / 2) { "B" },
            "xvar" to List(n) { i: Int -> i },
            "yvar" to List(n) { i: Int -> i + rand.nextGaussian() * 3 }
        )
        val cond = "cond"<String>()
        val xvar = "xvar"<Int>()
        val yvar = "yvar"<Double>()
        // SampleEnd
        assertNotNull(data[cond])
        assertNotNull(data[xvar])
        assertNotNull(data[yvar])
    }

    @Test
    fun guideScatterBasicScatterPlot() {
        // SampleStart
        plot(data) {
            points {
                x(xvar)
                y(yvar)
                symbol = Symbol.CIRCLE_OPEN
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideScatterScatterPlotWithDiffSymbol() {
        // SampleStart
        plot(data) {
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
            .saveSample()
    }

    @Test
    fun guideScatterScatterPlotWithOpenSymbol() {
        // SampleStart
        plot(data) {
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
            .saveSample()
    }

    @Test
    fun guideScatterData1() {
        // SampleStart
        // Create data with overlapping points.
        val data1 = data.convert { xvar and yvar }.with {
            (it.toDouble() / 5).toInt() * 5
        }
        // SampleEnd
        assertEquals(typeOf<Int>(), data1[xvar].type())
        assertEquals(typeOf<Int>(), data1[yvar].type())
    }

    @Test
    fun guideScatterHandlingOverPlotting() {
        // SampleStart
        plot(data1) {
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
            .saveSample()
    }

    @Test
    fun guideScatterHandlingOverPlottingJitter() {
        // SampleStart
        plot(data1) {
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
            .saveSample()
    }
}