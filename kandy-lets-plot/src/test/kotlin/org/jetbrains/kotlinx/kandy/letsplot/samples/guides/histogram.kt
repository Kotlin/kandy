package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.apache.commons.math3.distribution.NormalDistribution
import org.apache.commons.math3.distribution.UniformRealDistribution
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.feature.position
import org.jetbrains.kotlinx.kandy.letsplot.layers.area
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.layers.vLine
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotGrid
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.scales.BrewerPalette
import org.jetbrains.kotlinx.kandy.letsplot.scales.categoricalColorBrewer
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.tooltips
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.statistics.binning.BinsAlign
import org.jetbrains.kotlinx.statistics.binning.BinsOption
import org.jetbrains.kotlinx.statistics.dataframe.stat.mean
import org.jetbrains.kotlinx.statistics.kandy.layers.histogram
import org.jetbrains.kotlinx.statistics.kandy.statplots.configure
import org.jetbrains.kotlinx.statistics.kandy.statplots.histogram
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statBin
import org.jetbrains.kotlinx.statistics.plotting.bin.statBin
import kotlin.test.Test

class Histogram : SampleHelper("stat", "guides") {

    private val depthList = NormalDistribution(500.0, 100.0).sample(1000).toList()
    private val coeffList = UniformRealDistribution(0.0, 1.0).sample(1000).toList()
    private val df = dataFrameOf(
        "depth" to depthList,
        "coeff" to coeffList
    )
    private val depth = column<Double>("depth")
    private val coeff = column<Double>("coeff")

    @Test
    fun guideHistogramGenerateData() {
        // SampleStart
        // Generate sample from normal distribution
        val depthList = NormalDistribution(500.0, 100.0).sample(1000).toList()
        // Generate sample from uniform distribution
        val coeffList = UniformRealDistribution(0.0, 1.0).sample(1000).toList()
        // gather them into the DataFrame
        val df = dataFrameOf(
            "depth" to depthList,
            "coeff" to coeffList
        )
        df.head()
        // SampleEnd
    }

    @Test
    fun guideHistogramStatBin() {
        // SampleStart
        df.statBin("depth", "coeff", binsOption = BinsOption.byNumber(10))
        // SampleEnd
    }

    @Test
    fun guideHistogramStatBinAreaPlot() {
        // SampleStart
        plot {
            statBin(depthList, binsAlign = BinsAlign.center(500.0)) {
                // new `StatBin` dataset here
                area {
                    // use `Stat.*` columns for mappings
                    x(Stat.x)
                    y(Stat.count)
                    fillColor = Color.RED
                    alpha = 0.5
                }
            }
        }
        // SampleEnd
//            .saveSample()
    }

    @Test
    @Suppress("UNUSED_EXPRESSION")
    fun guideHistogramStatBinBarsPlot() {
        // SampleStart
        val statBinBarsPlot = df.plot {
            statBin("depth") {
                bars {
                    x(Stat.x)
                    y(Stat.count)
                }
            }
            layout.title = "`statBin` + `bars`"
        }
        statBinBarsPlot
        // SampleEnd
//            .saveSample()
    }

    @Test
    @Suppress("UNUSED_EXPRESSION")
    fun guideHistogramHistPlot() {
        // SampleStart
        val histogramPlot = plot {
            histogram(depthList)
            layout.title = "`histogram`"
        }
        histogramPlot
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideHistogramCompareBarsVsHist() {
        val statBinBarsPlot = df.plot {
            statBin("depth") {
                bars {
                    x(Stat.x)
                    y(Stat.count)
                }
            }
            layout.title = "`statBin` + `bars`"
        }
        val histogramPlot = plot {
            histogram(depthList)
            layout.title = "`histogram`"
        }
        // SampleStart
        plotGrid(listOf(statBinBarsPlot, histogramPlot))
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideHistogramHistFillColor() {
        // SampleStart
        df.plot {
            histogram(depth, binsAlign = BinsAlign.center(500.0)) {
                // Change a column mapped on `y` to `Stat.density`
                y(Stat.density)
                // Filling color depends on `density` statistic
                fillColor(Stat.density) {
                    scale = continuous(Color.YELLOW..Color.RED)
                }
                borderLine.color = Color.BLACK
            }
        }
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideHistogramSmoothLineWithPoints() {
        val depth = df[depth]
        val coeff = df[coeff]
        // SampleStart
        df.plot {
            // Count sample mean
            val mean = depth.mean()
            // Add weighted histogram
            histogram(depth, coeff, binsOption = BinsOption.byNumber(10), binsAlign = BinsAlign.boundary(mean))
            // We can add other layers as well.
            // Let's add a vertical mark line in the mean of sample
            vLine {
                xIntercept.constant(mean)
                tooltips { line("Depth mean: ${String.format("%.2f", mean)}m") }
                color = Color.RED; width = 3.0
            }
            x.axis.name = "depth, m"
        }
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideHistogramSimpleHistPlot() {
        // SampleStart
        histogram(depthList, binsAlign = BinsAlign.center(500.0))
            // SampleEnd
            .toPlot()
//            .saveSample()
    }

    @Test
    fun guideHistogramSimpleHistOnDf() {
        // SampleStart
        df.histogram("depth")
            // SampleEnd
            .toPlot()
//            .saveSample()
    }

    @Test
    fun guideHistogramHistWithWeight() {
        val depth = df[depth]
        val coeff = df[coeff]
        // SampleStart
        df.histogram(binsOption = BinsOption.byNumber(10)) {
            x(depth)
            weight(coeff)
        }
            // SampleEnd
            .toPlot()
//            .saveSample()
    }

    @Test
    fun guideHistogramConfiguredHistPlot() {
        val depth = df[depth]
        // SampleStart
        df.histogram(binsOption = BinsOption.byNumber(15)) {
            x(depth)
        }.configure {
            // Bars + StatBin + PlotContext
            // Can't add a new layer
            x.limits = 100..900
            // Can add bar mapping, include on `Stat.*` columns
            fillColor(Stat.count) { scale = continuous(Color.GREEN..Color.RED) }
            // Can configure general plot adjustments
            layout {
                title = "Configured histogram plot"
                size = 600 to 350
            }
        }
        // SampleEnd
//            .saveSample()

    }

    // Grouped data
    private val rangesA = NormalDistribution(500.0, 100.0).sample(5000).toList()
    private val rangesB = NormalDistribution(400.0, 80.0).sample(5000).toList()

    private val rangesDF = dataFrameOf(
        "range" to rangesA + rangesB,
        "category" to List(5000) { "A" } + List(5000) { "B" }
    )
    private val range = column<Double>("range")
    private val category = column<Double>("category")

    private val groupedRangesDF = rangesDF.groupBy { category }

    @Test
    fun guideHistogramGenerateGropedData() {
        // SampleStart
        // Create two samples from normal distribution with different mean/std
        val rangesA = NormalDistribution(500.0, 100.0).sample(5000).toList()
        val rangesB = NormalDistribution(400.0, 80.0).sample(5000).toList()

        // Gather them into `DataFrame` with "A" and "B" keys in the "category" column
        val rangesDF = dataFrameOf(
            "range" to rangesA + rangesB,
            "category" to List(5000) { "A" } + List(5000) { "B" }
        )
        rangesDF.head(5)
        // SampleEnd
    }

    @Test
    @Suppress("UNUSED_EXPRESSION")
    fun guideHistogramGroupedData() {
        // SampleStart
        // Group it by "category"
        val groupedRangesDF = rangesDF.groupBy { category }
        groupedRangesDF
        // SampleEnd
    }

    @Test
    fun guideHistogramGroupedStatBin() {
        val range = rangesDF[range]
        // SampleStart
        groupedRangesDF.statBin { x(range) }
        // SampleEnd
    }

    @Test
    fun guideHistogramGroupedStatBinAreaPlot() {
        // SampleStart
        groupedRangesDF.plot {
            statBin(range) {
                area {
                    x(Stat.x)
                    y(Stat.density)
                }
            }
        }
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideHistogramGroupedStatBinAreaWithColor() {
        // SampleStart
        groupedRangesDF.plot {
            statBin(range) {
                area {
                    x(Stat.x)
                    y(Stat.density)
                    // can access "key." columns and create mapping from them
                    fillColor(category)
                    alpha = 0.6
                }
            }
        }
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideHistogramGroupedHist() {
        // SampleStart
        groupedRangesDF.plot {
            histogram(range)
        }
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideHistogramCustomizedGroupedHist() {
        // SampleStart
        groupedRangesDF.plot {
            histogram(range) {
                fillColor(category) {
                    scale = categorical(listOf(Color.GREEN, Color.ORANGE))
                }
                borderLine.width = 0.0
                width = 1.0
                // Adjust position of bars from different groups
                position = Position.stack()
            }
        }
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideHistogramGrSimpleHist() {
        // SampleStart
        groupedRangesDF.histogram("range")
            // SampleEnd
            .toPlot()
//            .saveSample()
    }

    @Test
    fun guideHistogramConfiguredGrSimpleHist() {
        val range = rangesDF[range]
        // SampleStart
        groupedRangesDF.histogram(binsAlign = BinsAlign.center(500.0)) { x(range) }.configure {
            alpha = 0.6
            // make the bars from different groups overlap with each other
            position = Position.identity()
            // can access key column by name as `String`
            fillColor("category") { scale = categoricalColorBrewer(BrewerPalette.Qualitative.Dark2) }
        }
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideHistogramGroupByHistPlot() {
        // SampleStart
        rangesDF.plot {
            groupBy(category) {
                histogram(range)
            }
        }
        // SampleEnd
//            .saveSample()
    }
}