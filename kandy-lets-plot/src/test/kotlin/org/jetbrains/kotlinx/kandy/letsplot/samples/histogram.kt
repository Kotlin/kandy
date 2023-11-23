package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.jetbrains.kotlinx.statistics.binning.BinsOption
import org.jetbrains.kotlinx.statistics.kandy.layers.histogram
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statBin
import kotlin.test.Test

class Histogram : SampleHelper("histogram") {

    @Test
    fun histogram_simple_dataframe() {
        // SampleStart
        val random = java.util.Random(42)

        val dataframe = dataFrameOf(
            "sample" to List(1000) { random.nextGaussian() }
        )

        dataframe.plot {
            histogram("sample")
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun histogram_simple_collections() {
        // SampleStart
        val random = java.util.Random(42)

        val sample = List(1000) { random.nextGaussian() }

        plot {
            histogram(sample)
        }
        // SampleEnd
    }

    @Test
    fun histogram_settings_dataframe() {
        // SampleStart
        val expreimentalDF = dataFrameOf("length" to listOf(
            5.92, 6.44, 5.87, 4.99, 5.23,
            5.67, 4.89, 5.34, 5.78, 5.12,
            5.56, 5.23, 5.78, 6.01, 5.56,
            5.67, 5.89, 5.45, 6.12, 5.78,
            6.34, 5.67, 6.45, 5.34, 5.89,
            6.01, 5.78, 5.23, 5.67, 6.12,
            6.23, 5.45, 5.56, 5.67, 5.78,
            5.56, 6.23, 5.78, 6.34, 6.12,
            5.89, 6.45, 5.78, 6.34, 5.67,
            6.56, 5.45, 5.78, 5.89, 6.12,
            4.67, 4.79, 5.14, 5.28, 5.22,
        ))
        expreimentalDF.plot {
            histogram("length", binsOption = BinsOption.byNumber(12)) {
                width = 0.8
                alpha = 0.9
                fillColor = Color.RED
                borderLine {
                    color = Color.GREEN
                    width = 0.5
                }
                x.axis.name = "length"
            }
            layout.title = "Flight length experiment"
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun histogram_settings_collections() {
        // SampleStart
        val experimentalData = listOf(
            5.92, 6.44, 5.87, 4.99, 5.23,
            5.67, 4.89, 5.34, 5.78, 5.12,
            5.56, 5.23, 5.78, 6.01, 5.56,
            5.67, 5.89, 5.45, 6.12, 5.78,
            6.34, 5.67, 6.45, 5.34, 5.89,
            6.01, 5.78, 5.23, 5.67, 6.12,
            6.23, 5.45, 5.56, 5.67, 5.78,
            5.56, 6.23, 5.78, 6.34, 6.12,
            5.89, 6.45, 5.78, 6.34, 5.67,
            6.56, 5.45, 5.78, 5.89, 6.12,
            4.67, 4.79, 5.14, 5.28, 5.22,
        )

        plot {
            histogram(experimentalData, binsOption = BinsOption.byNumber(12)) {
                width = 0.8
                alpha = 0.9
                fillColor = Color.RED
                borderLine {
                    color = Color.GREEN
                    width = 0.5
                }
                x.axis.name = "length"
            }
            layout.title = "Flight length experiment"
        }
        // SampleEnd
    }

    @Test
    fun histogram_grouped() {
        // SampleStart
        val random = java.util.Random(42)

        val sampleA = List(1000) {random.nextGaussian() * 0.7 + 2.0}
        val sampleB = List(1000) {random.nextGaussian() * 1.4 + 3.5}

        val df = dataFrameOf(
            "sample" to sampleA + sampleB,
            "group" to sampleA.map { "A" } + sampleB.map { "B" }
        )

        df.groupBy("group").plot {
            histogram("sample")
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun histogram_with_line() {
        // SampleStart
        val random = java.util.Random(42)

        val sample = List(1000) {random.nextGaussian()}

        plot {
            statBin(sample, binsOption = BinsOption.byNumber(15)) {
                bars {
                    alpha = 0.9
                    x(Stat.x)
                    y(Stat.count)
                }
                line {
                    x(Stat.x)
                    y(Stat.count)
                    color = Color.RED
                    width = 1.5
                    type = LineType.LONGDASH
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

}
