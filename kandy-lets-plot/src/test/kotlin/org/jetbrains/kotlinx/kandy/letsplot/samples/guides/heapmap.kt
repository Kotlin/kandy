package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.*
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotGrid
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.scales.BrewerPalette
import org.jetbrains.kotlinx.kandy.letsplot.scales.continuousColorBrewer
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.statistics.kandy.layers.heatmap
import org.jetbrains.kotlinx.statistics.kandy.statplots.configure
import org.jetbrains.kotlinx.statistics.kandy.statplots.heatmap
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statCount2D
import org.jetbrains.kotlinx.statistics.plotting.count2d.statCount2D
import kotlin.test.Test

class Heatmap : SampleHelper("stat", "guides") {

    private val mpgDF =
        DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
    private val `class` = column<String>("class")
    private val drv = column<String>("drv")
    private val hwy = column<Int>("hwy")
    private val df = mpgDF[`class`, drv, hwy]

    @Test
    fun guideHeatmapReadAutoDf() {
        // SampleStart
        // Use "mpg" dataset
        val mpgDF =
            DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
        mpgDF.head(5)
        // SampleEnd
    }

    @Test
    fun guideHeatmapTakeThreeCols() {
        // SampleStart
        // We need only three columns
        val df = mpgDF["class", "drv", "hwy"]
        df.head(5)
        // SampleEnd
    }

    @Test
    fun guideHeatmapStatCount2D() {
        // SampleStart
        df.statCount2D("class", "drv", "hwy")
        // SampleEnd
    }

    @Test
    fun guideHeatmapStatCount2DPlot() {
        // SampleStart
        df.plot {
            statCount2D(`class`, drv) {
                // New `StatCount` dataset here
                points {
                    // Use `Stat.*` columns for mappings
                    x(Stat.x) {
                        axis.expand(0.0, 0.5)
                    }
                    y(Stat.y)
                    size(Stat.count) {
                        scale = continuous(10.0..30.0)
                    }
                    color = Color.RED
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideHeatmapStatCount2DTile() {
        // SampleStart
        val statCount2DAndTilePlot = df.plot {
            statCount2D("class", "drv") {
                tiles {
                    x(Stat.x)
                    y(Stat.y)
                    fillColor(Stat.count)
                }
            }
            layout.title = "`statCount2D()` + `tile()` layer"
        }
        statCount2DAndTilePlot
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideHeatmapHeatmapInDf() {
        // SampleStart
        val heatmapLayerPlot = df.plot {
            heatmap(`class`, drv)
            layout.title = "`heatmap()` layer"
        }
        heatmapLayerPlot
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideHeatmapTileVsHeatMap() {
        val statCount2DAndTilePlot = mpgDF.plot {
            statCount2D("class", "drv") {
                tiles { x(Stat.x); y(Stat.y); fillColor(Stat.count) }
            }
            layout.title = "`statCount2D()` + `tile()` layer"
        }
        val heatmapLayerPlot = df.plot {
            heatmap(`class`, drv); layout.title = "`heatmap()` layer"
        }
        // SampleStart
        plotGrid(listOf(statCount2DAndTilePlot, heatmapLayerPlot))
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideHeatmapWithFillColor() {
        // SampleStart
        df.plot {
            heatmap(`class`, drv) {
                // Swap coordinate mappings:
                x(Stat.y)
                y(Stat.x)
                // Default mapping but with custom scale
                fillColor(Stat.count) {
                    scale = continuousColorBrewer(BrewerPalette.Sequential.Reds)
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideHeatmapSimpleHeatmapInDf() {
        // SampleStart
        df.plot {
            heatmap(`class`, drv, hwy)
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideHeatmapWithIterable() {
        // SampleStart
        heatmap(
            listOf("A", "A", "A", "B", "B", "C", "B", "B"),
            listOf(1, 1, 1, 2, 1, 2, 1, 2),
        )
            // SampleEnd
            .toPlot()
            .saveSample()
    }

    @Test
    fun guideHeatmapHeatmapOnDf() {
        // SampleStart
        df.heatmap("class", "drv")
            // SampleEnd
            .toPlot()
            .saveSample()
    }

    @Test
    fun guideHeatmapWithWeight() {
        val `class` = df[`class`]
        val drv = df[drv]
        val hwy = df[hwy]
        // SampleStart
        df.heatmap {
            x(`class`)
            y(drv)
            weight(hwy)
        }
            // SampleEnd
            .toPlot()
            .saveSample()
    }

    @Test
    fun guideHeatmapConfigureHeatmap() {
        val `class` = df[`class`]
        val drv = df[drv]
        val hwy = df[hwy]
        // SampleStart
        df.heatmap {
            x(`class`)
            y(drv)
            weight(hwy)
        }.configure {
            // Tile + StatCount2D + PlotContext
            // Can't add new layer
            // Can add tile mapping, including for `Stat.*` columns
            fillColor(Stat.count) {
                scale = continuous(Color.GREEN..Color.RED)
            }
            alpha = 0.6
            // Can configure general plot adjustments
            layout {
                title = "Configured `heatmap` plot"
                size = 600 to 350
            }
        }
            // SampleEnd
            .saveSample()
    }
}