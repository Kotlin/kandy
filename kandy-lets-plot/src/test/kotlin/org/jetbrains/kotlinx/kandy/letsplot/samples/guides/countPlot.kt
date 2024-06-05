package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.dataframe.get
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.feature.position
import org.jetbrains.kotlinx.kandy.letsplot.layers.*
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotGrid
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.scales.BrewerPalette
import org.jetbrains.kotlinx.kandy.letsplot.scales.categoricalColorBrewer
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.tooltips
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.statistics.kandy.layers.countPlot
import org.jetbrains.kotlinx.statistics.kandy.statplots.configure
import org.jetbrains.kotlinx.statistics.kandy.statplots.countPlot
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statCount
import org.jetbrains.kotlinx.statistics.plotting.count.statCount
import kotlin.test.Test

class CountPlot : SampleHelper("stat", "guides") {

    private val mpgDF =
        DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
    private val `class` = column<String>("class")
    private val drv = column<String>("drv")
    private val hwy = column<Int>("hwy")
    private val df = mpgDF[`class`, drv, hwy]

    @Test
    fun guideCountReadAutoDf() {
        // SampleStart
        // Use "mpg" dataset
        val mpgDF =
            DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
        mpgDF.head(5)
        // SampleEnd
    }

    @Test
    fun guideCountTakeThreeCols() {
        // SampleStart
        // We need only three columns
        val df = mpgDF["class", "drv", "hwy"]
        df.head(5)
        // SampleEnd
    }

    @Test
    fun guideCountStatCount() {
        // SampleStart
        df.statCount("class", "hwy")
        // SampleEnd
    }

    @Test
    fun guideCountStatCountPointsPlot() {
        // SampleStart
        plot {
            statCount(df["class"]) {
                // New `StatCount` dataset here
                points {
                    // Use `Stat.*` columns for mappings
                    x(Stat.x)
                    y(Stat.count)
                    size(Stat.count)
                    color(Stat.x)
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideCountStatCountBarsPlot() {
        // SampleStart
        val statCountAndBarsPlt = df.plot {
            statCount("class") {
                bars {
                    x(Stat.x)
                    y(Stat.count)
                }
            }
            layout.title = "`statCount()` + `bars()` layer"
        }
        statCountAndBarsPlt
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideCountSimplePlot() {
        // SampleStart
        val countPlt = plot {
            countPlot(df["class"])
            layout.title = "`countPlot()` layer"
        }
        countPlt
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideCountBarsVsCountPlot() {
        val statCountAndBarsPlt = df.plot {
            statCount("class") {
                bars {
                    x(Stat.x)
                    y(Stat.count)
                }
            }
            layout.title = "`statCount()` + `bars()` layer"
        }
        val countPlt = plot {
            countPlot(df["class"])
            layout.title = "`countPlot()` layer"
        }
        // SampleStart
        plotGrid(listOf(statCountAndBarsPlt, countPlt))
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideCountStatCountFillColor() {
        // SampleStart
        df.plot {
            countPlot(`class`) {
                // filling color depends on `count` statistic
                fillColor(Stat.count) {
                    scale = continuous(Color.GREEN..Color.RED)
                }
                borderLine.color = Color.BLACK
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideCountWithHLine() {
        // SampleStart
        df.plot {
            countPlot(`class`, hwy)
            // We can add other layers as well.
            // Let's add a horizontal mark line with constant y intercept:
            hLine {
                val criticalCount = 500
                yIntercept.constant(criticalCount)
                tooltips { line("Critical count: ${String.format("%d", criticalCount)}") }
                color = Color.RED; width = 3.0
            }
            x.axis.name = "Car class"
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideCountPlotWithItr() {
        // SampleStart
        countPlot(listOf("A", "A", "A", "B", "B", "C", "B", "B"))
            // SampleEnd
            .toPlot()
            .saveSample()
    }

    @Test
    fun guideCountSimpleCountPlot() {
        // SampleStart
        df.countPlot("class")
            // SampleEnd
            .toPlot()
            .saveSample()
    }

    @Test
    fun guideCountPlotWithWeight() {
        val `class` = df[`class`]
        val hwy = df[hwy]
        // SampleStart
        df.countPlot {
            x(`class`)
            weight(hwy)
        }
            // SampleEnd
            .toPlot()
            .saveSample()
    }

    @Test
    fun guideCountConfigurePlot() {
        val `class` = df[`class`]
        // SampleStart
        df.countPlot {
            x(`class`)
        }.configure {
            // Bars + StatCount + PlotContext
            // can't add new layer
            // can add bars mapping, including for `Stat.*` columns
            fillColor(Stat.x)
            alpha = 0.6
            // can configure general plot adjustments
            layout {
                title = "Configured `countPlot` plot"
                size = 600 to 350
            }
        }
            // SampleEnd
            .saveSample()
    }

    // Grouped data
    private val groupedDF = df.groupBy { drv }

    @Test
    @Suppress("UNUSED_EXPRESSION")
    fun guideCountGroupDf() {
        // SampleStart
        // group our dataframe by `drv` column
        val groupedDF = df.groupBy { drv }
        groupedDF
        // SampleEnd
    }

    @Test
    fun guideCountGroupedStatCount() {
        val `class` = df[`class`]
        // SampleStart
        groupedDF.statCount { x(`class`) }
        // SampleEnd
    }

    @Test
    fun guideCountGroupedStatCountBarsPlot() {
        // SampleStart
        groupedDF.plot {
            statCount(`class`) {
                bars {
                    x(Stat.x)
                    y(Stat.countWeighted)
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideCountGroupedStatCountBarsWithColorPlot() {
        // SampleStart
        groupedDF.plot {
            statCount(`class`) {
                bars {
                    x(Stat.x)
                    y(Stat.countWeighted)
                    fillColor(drv)
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideCountGroupedCountPlot() {
        // SampleStart
        groupedDF.plot {
            countPlot("class")
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideCountConfigureGroupedCountPlot() {
        // SampleStart
        groupedDF.plot {
            countPlot("class") {
                fillColor(drv) {
                    scale = categorical(listOf(Color.GREEN, Color.ORANGE, Color.LIGHT_PURPLE))
                }
                borderLine.width = 0.0
                width = 1.0
                // adjust position of bars
                position = Position.stack()
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideCountSimpleGroupedCountPlot() {
        // SampleStart
        groupedDF.countPlot("class")
            // SampleEnd
            .toPlot()
            .saveSample()
    }

    @Test
    fun guideCountConfigureSimpleGroupedCountPlot() {
        val `class` = df[`class`]
        // SampleStart
        groupedDF.countPlot { x(`class`) }.configure {
            alpha = 0.6
            // make the bars from different groups overlap with each other
            position = Position.identity()
            // can access key column by name as `String`
            fillColor("drv") { scale = categoricalColorBrewer(BrewerPalette.Qualitative.Dark2) }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideCountGroupByCountPlot() {
        // SampleStart
        df.plot {
            groupBy(drv) {
                countPlot(`class`)
            }
        }
            // SampleEnd
            .saveSample()
    }
}