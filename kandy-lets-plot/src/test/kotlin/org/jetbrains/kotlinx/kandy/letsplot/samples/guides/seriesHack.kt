package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.test.Test
import kotlin.test.assertNotNull

class SeriesHack : SampleHelper("other", "guides") {

    private val xs = listOf(1, 2, 3, 4, 5)
    private val ysA = listOf(1.0, 2.5, 3.0, 3.5, 5.0)
    private val ysB = listOf(0.5, 1.5, 3.0, 1.5, 0.0)
    private val ysC = listOf(3.0, 5.0, 2.0, 3.0, 5.0)
    private val df = dataFrameOf(
        "xs" to xs,
        "ysA" to ysA,
        "ysB" to ysB,
        "ysC" to ysC
    )

    @Test
    fun guideSeriesHackCreateLists() {
        // SampleStart
        val xs = listOf(1, 2, 3, 4, 5)
        val ysA = listOf(1.0, 2.5, 3.0, 3.5, 5.0)
        val ysB = listOf(0.5, 1.5, 3.0, 1.5, 0.0)
        val ysC = listOf(3.0, 5.0, 2.0, 3.0, 5.0)
        // SampleEnd
        assertNotNull(xs)
        assertNotNull(ysA)
        assertNotNull(ysB)
        assertNotNull(ysC)
    }

    @Test
    fun guideSeriesHackLinesPlot() {
        // SampleStart
        plot {
            x(xs)
            line {
                y(ysA)
                color = Color.RED
            }
            line {
                y(ysB)
                color = Color.GREEN
            }
            line {
                y(ysC)
                color = Color.BLUE
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideSeriesHackGatheredLines() {
        // SampleStart
        plot {
            line {
                x(xs + xs + xs)
                y(ysA + ysB + ysC)
                color(xs.map { "A" } + xs.map { "B" } + xs.map { "C" })
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    @Suppress("UNUSED_EXPRESSION")
    fun guideSeriesHackDataFrame() {
        // SampleStart
        val df = dataFrameOf(
            "xs" to xs,
            "ysA" to ysA,
            "ysB" to ysB,
            "ysC" to ysC,
        )
        df
        // SampleEnd
    }

    @Test
    fun guideSeriesHackBarsSeries() {
        // SampleStart
        // Doesn't work - bars are overlapped, and we can't change that
        df.plot {
            x(xs)
            bars {
                y(ysA)
                fillColor = Color.RED
            }
            bars {
                y(ysB)
                fillColor = Color.GREEN
            }
            bars {
                y(ysC)
                fillColor = Color.BLUE
            }
        }
            // SampleEnd
            .saveSample()
    }

    private val gatheredDF = df.gather("ysA", "ysB", "ysC")
        .mapKeys { it.drop(2) }
        .into(keyColumn = "group", valueColumn = "ys")

    @Test
    fun guideSeriesHackGatherDataFrame() {
        val ysA = column<Double>("ysA")
        val ysB = column<Double>("ysB")
        val ysC = column<Double>("ysC")
        // SampleStart
        val gatheredDF = df.gather {
            ysA and ysB and ysC
        }
            .mapKeys { it.drop(2) } // Take group name as a key
            .into(keyColumn = "group", valueColumn = "ys")
        gatheredDF.head()
        // SampleEnd
    }

    @Test
    fun guideSeriesHackBarOnGatheredDf() {
        val xs = column<Int>("xs")
        val ys = column<Double>("ys")
        val group = column<String>("group")
        // SampleStart
        gatheredDF.plot {
            bars {
                x(xs)
                y(ys)
                fillColor(group)
            }
        }
            // SampleEnd
            .saveSample()
    }
}