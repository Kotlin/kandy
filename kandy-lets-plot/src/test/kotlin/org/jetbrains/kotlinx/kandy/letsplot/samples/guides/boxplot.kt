package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.apache.commons.math3.distribution.NormalDistribution
import org.apache.commons.math3.distribution.UniformRealDistribution
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.feature.position
import org.jetbrains.kotlinx.kandy.letsplot.layers.*
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotGrid
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.settings.Symbol
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.statistics.kandy.layers.boxplot
import org.jetbrains.kotlinx.statistics.kandy.statplots.boxplot
import org.jetbrains.kotlinx.statistics.kandy.statplots.configure
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statBoxplot
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statBoxplotOutliers
import org.jetbrains.kotlinx.statistics.plotting.boxplot.statBoxplot
import org.jetbrains.kotlinx.statistics.plotting.boxplotOutliers.statBoxplotOutliers
import kotlin.test.Test
import kotlin.test.assertNotNull

class Boxplot : SampleHelper("stat", "guides") {

    private val rateA = NormalDistribution(37.8, 4.3).sample(5000).toList()
    private val rateB = UniformRealDistribution(20.0, 50.0).sample(1000).toList()
    private val rateC = (rateA + rateB).filter { it >= 36.0 }
    val df = dataFrameOf(
        "rate" to rateA + rateB + rateC,
        "cond" to List(rateA.size) { "A" } + List(rateB.size) { "B" } + List(rateC.size) { "C" }
    )
    private val rate = column<Double>("rate")
    private val cond = column<String>("cond")


    @Test
    fun guideBoxplotGenerateData() {
        // SampleStart
        // To generate the data, we use a standard java math library
        // https://commons.apache.org/proper/commons-math/
        // Generate sample from normal distribution
        val rateA = NormalDistribution(37.8, 4.3).sample(5000).toList()
        // Generate sample from uniform distribution
        val rateB = UniformRealDistribution(20.0, 50.0).sample(1000).toList()
        // Combine two previous samples and filter them by lower bound for third sample
        val rateC = (rateA + rateB).filter { it >= 36.0 }
        // SampleEnd
        assertNotNull(rateC)
    }

    @Test
    fun guideBoxplotGatherDataIntoDf() {
        // SampleStart
        // gather them into the DataFrame in a single column and with corresponding keys in column `cond`
        val df = dataFrameOf(
            "rate" to rateA + rateB + rateC,
            "cond" to List(rateA.size) { "A" } + List(rateB.size) { "B" } + List(rateC.size) { "C" }
        )
        df.head(5)
        // SampleEnd
    }

    @Test
    fun guideBoxplotStatBoxplot() {
        // SampleStart
        df.statBoxplot("cond", "rate")
        // SampleEnd
    }

    @Test
    fun guideBoxplotStatBoxplotOutliers() {
        // SampleStart
        df.statBoxplotOutliers("cond", "rate").head(5)
        // SampleEnd
    }

    @Test
    fun guideBoxplotStatBoxplotErrorBarsPlotWithPoints() {
        // SampleStart
        df.plot {
            statBoxplot(cond, rate) {
                // New "StatBoxplot" dataset here
                errorBars {
                    // Use `Stat.*` columns for mappings
                    x(Stat.x)
                    yMin(Stat.min)
                    yMax(Stat.max)
                    borderLine.color(Stat.x)
                }
            }
            // Initial dataset here
            points {
                x("cond")
                y("rate")
                size = 0.5
                alpha = 0.2
                color("cond")
                position = Position.jitter()
            }
        }
            // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideBoxplotStatBoxplotOutlierPointsPlot() {
        // SampleStart
        df.plot {
            statBoxplotOutliers(cond, rate) {
                // New "StatBoxplotOutliers" dataset here
                points {
                    x(Stat.x)
                    y(Stat.y)
                    color(Stat.x)
                }
            }
        }
            // SampleEnd
//            .saveSample()
    }

    @Test
    @Suppress("UNUSED_EXPRESSION")
    fun guideBoxplotManualBoxes() {
        // SampleStart
        val manualBoxplot = df.plot {
            statBoxplot(cond, rate) {
                boxes {
                    // All positional aesthetics match boxplot statistics
                    x(Stat.x)
                    yMin(Stat.min)
                    lower(Stat.lower)
                    middle(Stat.middle)
                    upper(Stat.upper)
                    yMax(Stat.max)
                }
            }
            statBoxplotOutliers(cond, rate) {
                points {
                    x(Stat.x)
                    y(Stat.y)
                }
            }
            layout {
                title = "`statBoxplot` + `boxes` \n" +
                        "and `statBoxplotOutliers` + `points`"
            }
        }
        manualBoxplot
            // SampleEnd
//            .saveSample()
    }

    @Test
    @Suppress("UNUSED_EXPRESSION")
    fun guideBoxplotDefaultMappings() {
        // SampleStart
        val boxplotPlot = df.plot {
            // Statistical boxplot layer - receives "statBoxplotArgs" and has default mappings
            boxplot(cond, rate)
            layout.title = "`boxplot()`"
        }
        boxplotPlot
            // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideBoxplotManualVsDefault() {
        val manualBoxplot = df.plot {
            statBoxplot(cond, rate) {
                boxes {
                    x(Stat.x); yMin(Stat.min); lower(Stat.lower); middle(Stat.middle)
                    upper(Stat.upper); yMax(Stat.max)
                }
            }
            statBoxplotOutliers(cond, rate) {
                points { x(Stat.x); y(Stat.y) }
            }
            layout.title = "`statBoxplot` + `boxes` \n and `statBoxplotOutliers` + `points`"
        }
        val boxplotPlot = df.plot {
            boxplot(cond, rate); layout.title = "`boxplot()`"
        }
        // SampleStart
        plotGrid(listOf(manualBoxplot, boxplotPlot))
            // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideBoxplotWithColor() {
        // SampleStart
        df.plot {
            boxplot(cond, rate) {
                boxes {
                    // Boxes context + StatBoxplot context
                    // filling color depends on `x` category
                    fillColor(Stat.x)
                }
                // hide outliers
                outliers.show = false
            }
        }
            // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideBoxplotCustomizedOutliers() {
        // SampleStart
        df.plot {
            boxplot(cond, rate) {
                boxes {
                    fatten = 0.5
                    alpha = 0.6
                    // Border line color depends on `x` category
                    borderLine.color(Stat.x)
                }
                outliers {
                    // points context + StatBoxplotOutliers context
                    // color depends on `x` category
                    color(Stat.x)
                    symbol = Symbol.ASTERIX
                }
            }
        }
            // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideBoxplotSimpleBoxplotOnDf() {
        // SampleStart
        plot {
            boxplot(rateC)
        }
            // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideBoxplotSimpleBoxplot() {
        // SampleStart
        // There's an additional argument "showOutliers"
        df.boxplot("cond", "rate", showOutliers = false)
            // SampleEnd
//            .toPlot()
//            .saveSample()
    }

    @Test
    fun guideBoxplotSimpleBoxplotWithWhisker() {
        val rate = df[rate]
        val cond = df[cond]
        // SampleStart
        df.boxplot(whiskerIQRRatio = 2.0) {
            x(cond)
            y(rate)
        }
            // SampleEnd
//            .toPlot()
//            .saveSample()
    }

    @Test
    fun guideBoxplotConfiguredBoxplot() {
        val rate = df[rate]
        val cond = df[cond]
        // SampleStart
        df.boxplot {
            x(cond)
            y(rate)
        }.configure {
            // BoxplotLayer + PlotContext
            // can't add new layer but can configure `boxes` and `outliers`
            boxes {
                alpha = 0.7
                fillColor(Stat.middle) { scale = continuous(Color.GREEN..Color.RED) }
            }
            outliers {
                color(Stat.x)
                // jittered outliers
                position = Position.jitter(0.1, 0.0)
            }
            // can configure general plot adjustments
            layout {
                title = "Configured boxplot"
                size = 600 to 350
            }
        }
            // SampleEnd
//            .saveSample()
    }

    // Grouped data
    private val mpgDF =
        DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
    private val `class` = column<String>("class")
    private val hwy = column<Int>("hwy")
    private val drv = column<String>("drv")
    private val mpgShortDF = mpgDF[`class`, hwy, drv]
    private val groupedDF = mpgShortDF.groupBy { drv }


    @Test
    fun guideBoxplotReadAutoDf() {
        // SampleStart
        // Use "mpg" dataset
        val mpgDF =
            DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
        mpgDF.head()
        // SampleEnd
    }

    @Test
    fun guideBoxplotSelectThreeCols() {
        // SampleStart
        // We need only three columns
        val mpgShortDF = mpgDF["class", "hwy", "drv"]
        mpgShortDF.head(5)
        // SampleEnd
    }

    @Test
    @Suppress("UNUSED_EXPRESSION")
    fun guideBoxplotGroupedDf() {
        // SampleStart
        // group it by "drv"
        val groupedDF = mpgShortDF.groupBy { drv }
        groupedDF
        // SampleEnd
    }

    @Test
    fun guideBoxplotGroupedStatBoxplot() {
        val `class` = mpgDF[`class`]
        val hwy = mpgDF[hwy]
        // SampleStart
        groupedDF.statBoxplot { x(`class`); y(hwy) }
        // SampleEnd
    }

    @Test
    fun guideBoxplotGroupedStatBoxplotEBPlot() {
        // SampleStart
        groupedDF.plot {
            statBoxplot(`class`, hwy) {
                errorBars {
                    x(Stat.x)
                    yMin(Stat.min)
                    yMax(Stat.max)
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideBoxplotGroupedBorderLines() {
        // SampleStart
        groupedDF.plot {
            statBoxplot(`class`, hwy) {
                errorBars {
                    x(Stat.x)
                    yMin(Stat.min)
                    yMax(Stat.max)
                    borderLine.color(drv)
                    position = Position.dodge()
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideBoxplotSimpleGroupedBoxplotInDf() {
        // SampleStart
        groupedDF.plot {
            boxplot(`class`, hwy)
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideBoxplotCustomizeGroupedBoxplot() {
        // SampleStart
        groupedDF.plot {
            boxplot(`class`, hwy) {
                boxes {
                    borderLine.color(drv)
                    // `identity` position, i.e boxes are overlapping
                    position = Position.identity()
                    alpha = 0.5
                }
                outliers.show = false
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideBoxplotSimpleGrBoxplotOnDf() {
        // SampleStart
        groupedDF.boxplot("class", "hwy")
        // SampleEnd
            .toPlot()
            .saveSample()
    }

    @Test
    fun guideBoxplotConfigureGroupedBoxplot() {
        val `class` = mpgDF[`class`]
        val hwy = mpgDF[hwy]
        // SampleStart
        groupedDF.boxplot {
            x(`class`)
            y(hwy)
        }.configure {
            boxes.borderLine.color = Color.hex("#000080")
            outliers {
                color(drv)
            }
            layout {
                size = 750 to 450
                title = "Configured grouped boxplot"
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideBoxplotGroupByBoxplot() {
        // SampleStart
        mpgShortDF.plot {
            groupBy(drv) {
                boxplot(`class`, hwy)
            }
        }
            // SampleEnd
            .saveSample()
    }
}