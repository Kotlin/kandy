package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.apache.commons.math3.distribution.NormalDistribution
import org.apache.commons.math3.distribution.UniformRealDistribution
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.get
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.feature.position
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.layers.vLine
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.statistics.binning.BinsAlign
import org.jetbrains.kotlinx.statistics.binning.BinsOption
import org.jetbrains.kotlinx.statistics.kandy.layers.histogram
import org.jetbrains.kotlinx.statistics.kandy.statplots.Stat
import org.jetbrains.kotlinx.statistics.kandy.statplots.configure
import org.jetbrains.kotlinx.statistics.kandy.statplots.histogram
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statBin
import org.jetbrains.kotlinx.statistics.plotting.bin.statBin
import org.jetbrains.kotlinx.statistics.stats.mean
import kotlin.test.Test
import kotlin.test.assertNotNull

class StatisticsGuide : SampleHelper("stat", "guides") {

    private val sample = NormalDistribution().sample(1000).toList()
    private val weights = UniformRealDistribution(0.0, 1.0).sample(1000).toList()

    private val statBinData = statBin(sample, null, BinsOption.byNumber(20), BinsAlign.center(0.0))

    private val df = dataFrameOf("sample" to sample, "weigths" to weights)

    private val sampleA = NormalDistribution(1.5, 1.0).sample(1000).toList()
    private val sampleB = NormalDistribution(4.0, 2.0).sample(1000).toList()

    // Gather them into `DataFrame` with "A" and "B" keys in the "category" column
    private val dfAB = dataFrameOf(
        "sample" to sampleA + sampleB,
        "type" to List(1000) { "A" } + List(1000) { "B" }
    )

    private val type = column<String>("type")

    private val gbAB = dfAB.groupBy { type }


    @Test
    fun guideStatGenerateSampleAndWeights() {
        // SampleStart
        // Generate sample from normal distribution
        val sample = NormalDistribution().sample(1000).toList()
        // Generate weights from uniform distribution
        val weights = UniformRealDistribution(0.0, 1.0).sample(1000).toList()
        // SampleEnd
        assertNotNull(sample)
        assertNotNull(weights)
    }

    @Test
    fun guideStatBinData() {
        // SampleStart
        val statBinData = statBin(
            sample, // Pass a sample as an input
            null, // Don't provide weights
            BinsOption.byNumber(20), // Set the number of bins
            BinsAlign.center(0.0) // Set the align of bins
        )
        // SampleEnd
        assertNotNull(statBinData.Stat.x)
        assertNotNull(statBinData.Stat.count)
        assertNotNull(statBinData.Stat.density)
    }

    @Test
    fun guideStatBinDataPlot() {
        // SampleStart
        statBinData.plot {
            bars {
                x(Stat.x)
                y(Stat.count)
            }
            layout.title = "Our awesome histogram!"
        }
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideStatDfFromSampleAndWeights() {
        // SampleStart
        val df = dataFrameOf("sample" to sample, "weights" to weights)
        // SampleEnd
        assertNotNull(df["sample"])
        assertNotNull(df["weights"])
    }

    @Test
    fun guideStatDfStatBinAndVLinePlot() {
        // SampleStart
        df.plot {
            statBin(sample, weights, binsOption = BinsOption.byWidth(0.25)) {
                // New `StatBin` dataset inside this context
                line {
                    // The old dataset is not actual, so we can use `Stat.` columns of a new one
                    x(Stat.x)
                    y(Stat.density)
                }
            }
            // Dataset hasn't changed here, so we can use it in the usual way
            vLine {
                xIntercept.constant(sample.mean())
                width = 3.0
                color = Color.RED
            }
        }
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideStatSimpleHistogram() {
        // SampleStart
        plot {
            // Equal to `statBin` + `bars` + x/y mappings on Stat.x/Stat.count
            histogram(sample)
        }
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideStatCustomizedHistogram() {
        // SampleStart
        plot {
            histogram(sample, weights, binsAlign = BinsAlign.center(0.0)) {
                // This context combines `bars` and `statBin` context; that means we can
                // make `bars` mappings and use `Stat.` columns.
                // By default, `Stat.count` is mapped on `y` if weights are not provided;
                // however, we can easily override mapping to `y`, for example, from `Stat.density`
                y(Stat.density)
                fillColor(Stat.density) {
                    scale = continuous(Color.GREEN..Color.RED)
                }
            }
            x.limits = -3.5..3.5
        }
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideStatSimpleHistogram2() {
        // SampleStart
        histogram(sample)
            // SampleEnd
            .toPlot()
        // .saveSample()
    }

    @Test
    fun guideStatSimpleHistogram3() {
        // SampleStart
        df.histogram("sample", binsOption = BinsOption.byNumber(10))
            // SampleEnd
            .toPlot()
        // .saveSample()
    }

    @Test
    fun guideStatSimpleHistogram4() {
        val sample = sample.toColumn()
        val weights = weights.toColumn()
        // SampleStart
        df.histogram {
            x(sample)
            weight(weights)
        }
            // SampleEnd
            .toPlot()
        // .saveSample()
    }

    @Test
    fun guideStatConfiguredHistogram4() {
        val sample = sample.toColumn()
        // SampleStart
        df.histogram(BinsOption.byNumber(14), BinsAlign.boundary(0.0)) {
            x(sample)
        }.configure {
            // StatBin + Bars + Plot contexts
            x.limits = -3.5..3.5
            y(Stat.density)
            borderLine.color = Color.BLACK
            layout.title = "Configured histogram"
        }
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideStatGenerateSampleAAndSampleB() {
        // SampleStart
        // Generate two samples from a normal distribution with different mean/std
        val sampleA = NormalDistribution(1.5, 1.0).sample(1000).toList()
        val sampleB = NormalDistribution(4.0, 2.0).sample(1000).toList()

        // Gather them into `DataFrame` with "A" and "B" keys in the "category" column
        val dfAB = dataFrameOf(
            "sample" to sampleA + sampleB,
            "type" to List(1000) { "A" } + List(1000) { "B" }
        )
        // SampleEnd
        assertNotNull(dfAB)
    }

    @Test
    fun guideStatGroupedStatBinPlot() {
        val sample = column<Double>("sample")
        val type = column<String>("type")
        // SampleStart
        gbAB.plot {
            statBin(sample) {
                bars {
                    x(Stat.x)
                    y(Stat.count)
                    fillColor(type)
                    borderLine.width = 0.0
                    position = Position.dodge()
                }
                line {
                    x(Stat.x)
                    y(Stat.count)
                    color(type)
                }
            }
        }
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideStatGroupedHistogram() {
        val sample = column<Double>("sample")
        // SampleStart
        gbAB.plot {
            histogram(sample)
        }
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideStatCustomizedGroupedHistogram() {
        val sample = column<Double>("sample")
        val type = column<String>("type")
        // SampleStart
        gbAB.plot {
            histogram(sample, binsOption = BinsOption.byNumber(12)) {
                fillColor(type)
                borderLine.color = Color.BLACK
                position = Position.stack()
            }
        }
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideStatSimpleGroupedHistogram() {
        // SampleStart
        gbAB.histogram("sample")
            // SampleEnd
            .toPlot()
        // .saveSample()
    }

    @Test
    fun guideStatConfiguredGroupedHistogram() {
        val sample = dfAB[{ "sample"<Double>() }].single()
        val type = column<String>("type")
        // SampleStart
        gbAB.histogram(BinsOption.byNumber(20), binsAlign = BinsAlign.boundary(0.0)) {
            x(sample)
        }.configure {
            fillColor(type) {
                scale = categorical(listOf(Color.GREEN, Color.ORANGE))
            }
            layout {
                size = 650 to 350
                title = "Configured grouped histogram!"
            }
        }
        // SampleEnd
        // .saveSample()
    }
}