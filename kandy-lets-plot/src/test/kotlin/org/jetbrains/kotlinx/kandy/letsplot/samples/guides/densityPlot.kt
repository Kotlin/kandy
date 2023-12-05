package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.apache.commons.math3.distribution.NormalDistribution
import org.apache.commons.math3.distribution.UniformRealDistribution
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.kandy.dsl.categorical
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
import org.jetbrains.kotlinx.statistics.dataframe.stat.mean
import org.jetbrains.kotlinx.statistics.kandy.layers.densityPlot
import org.jetbrains.kotlinx.statistics.kandy.statplots.configure
import org.jetbrains.kotlinx.statistics.kandy.statplots.densityPlot
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statDensity
import org.jetbrains.kotlinx.statistics.plotting.density.BandWidth
import org.jetbrains.kotlinx.statistics.plotting.density.Kernel
import org.jetbrains.kotlinx.statistics.plotting.density.statDensity
import kotlin.test.Test
import kotlin.test.assertNotNull

class DensityPlot : SampleHelper("stat", "guides") {

    private val depthList = NormalDistribution(500.0, 100.0).sample(1000).toList()
    private val coeffList = UniformRealDistribution(0.0, 1.0).sample(1000).toList()
    val df = dataFrameOf(
        "depth" to depthList,
        "coeff" to coeffList
    )
    private val depth = column<Double>("depth")
    private val coeff = column<Double>("coeff")

    @Test
    fun guideDensityGenerateData() {
        // SampleStart
        // To generate the data, we use a standard java math library
        // https://commons.apache.org/proper/commons-math/
        // Generate sample from normal distribution
        val depthList = NormalDistribution(500.0, 100.0).sample(1000).toList()
        // Generate sample from uniform distribution
        val coeffList = UniformRealDistribution(0.0, 1.0).sample(1000).toList()
        // SampleEnd
        assertNotNull(depthList)
        assertNotNull(coeffList)
    }

    @Test
    fun guideDensityGatherDataIntoDf() {
        // SampleStart
        // Gather them into the DataFrame
        val df = dataFrameOf(
            "depth" to depthList,
            "coeff" to coeffList
        )
        df.head()
        // SampleEnd
    }

    @Test
    fun guideDensityStatDensity() {
        // SampleStart
        df.statDensity("depth", "coeff").head()
        // SampleEnd
    }

    @Test
    fun guideDensityStatDensityLinePlot() {
        // SampleStart
        plot {
            statDensity(depthList, adjust = 0.2) {
                // New `StatDensity` dataset here
                line {
                    // Use `Stat.*` columns for mappings
                    x(Stat.x)
                    y(Stat.density)
                    color(Stat.density)
                }
            }
        }
        // SampleEnd
        // .saveSample()
    }

    @Test
    @Suppress("UNUSED_EXPRESSION")
    fun guideDensityStatDensityAreaPlot() {
        // SampleStart
        val statDensityAndAreaPlot = df.plot {
            statDensity("depth") {
                area {
                    x(Stat.x)
                    y(Stat.density)
                }
            }
            layout.title = "`statDensity()` + `area()` layer"
        }
        statDensityAndAreaPlot
        // SampleEnd
        // .saveSample()
    }

    @Test
    @Suppress("UNUSED_EXPRESSION")
    fun guideDensityLayerPlot() {
        // SampleStart
        val densityLayerPlot = plot {
            densityPlot(depthList)
            layout.title = "`densityPlot()` layer"
        }
        densityLayerPlot
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideDensityStatDensityVsLayerPlot() {
        val statDensityAndAreaPlot = df.plot {
            statDensity("depth") {
                area {
                    x(Stat.x)
                    y(Stat.density)
                }
            }
            layout.title = "`statDensity()` + `area()` layer"
        }
        val densityLayerPlot = plot {
            densityPlot(depthList)
            layout.title = "`densityPlot()` layer"
        }
        // SampleStart
        plotGrid(listOf(statDensityAndAreaPlot, densityLayerPlot))
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideDensityColorAndBorderLine() {
        // SampleStart
        df.plot {
            densityPlot(depth) {
                // Change a column mapped on `y` to `Stat.scaled`
                y(Stat.scaled)
                alpha = 0.7
                fillColor = Color.RED
                borderLine.color = Color.BLACK
            }
        }
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideDensityVLinePlot() {
        val depth = df[depth]
        val coeff = df[coeff]
        // SampleStart
        df.plot {
            densityPlot(depth, coeff, n = 700, adjust = 0.8, bandWidth = BandWidth.value(17.0))
            // We can add other layers as well.
            // Let's add a horizontal mark line with constant y intercept:
            vLine {
                // Count sample mean
                val mean = depth.mean()
                xIntercept.constant(mean)
                tooltips { line("Depth mean: ${String.format("%.2f", mean)}m") }
                color = Color.RED; width = 2.0
            }
            x.axis.name = "depth, m"
        }
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideDensitySimpleDensityPlot() {
        // SampleStart
        densityPlot(depthList, kernel = Kernel.COSINE)
            // SampleEnd
            .toPlot()
        // .saveSample()
    }

    @Test
    fun guideDensitySimpleDensityPlotOnDf() {
        // SampleStart
        df.densityPlot("depth")
            // SampleEnd
            .toPlot()
        // .saveSample()
    }

    @Test
    fun guideDensitySimpleDensityPlotWithWeight() {
        val depth = df[depth]
        val coeff = df[coeff]
        // SampleStart
        df.densityPlot(adjust = 0.5) {
            x(depth)
            weight(coeff)
        }
            // SampleEnd
            .toPlot()
        // .saveSample()
    }

    @Test
    fun guideDensityConfigureDensityPlot() {
        val depth = df[depth]
        // SampleStart
        df.densityPlot {
            x(depth)
        }.configure {
            // Area + StatDensity + PlotContext
            // Can't add new layer
            // Can add area mapping, including for `Stat.*` columns
            fillColor(Stat.scaled) // doesn't work properly for now
            alpha = 0.6
            // Can configure general plot adjustments
            layout {
                title = "Configured `densityPlot` plot"
                size = 600 to 350
            }
        }
        // SampleEnd
        // .saveSample()
    }

    // Grouped data
    private val rangesA = NormalDistribution(500.0, 100.0).sample(5000).toList()
    private val rangesB = NormalDistribution(400.0, 80.0).sample(5000).toList()

    // Gather them into `DataFrame` with "A" and "B" keys in the "category" column
    private val rangesDF = dataFrameOf(
        "range" to rangesA + rangesB,
        "category" to List(5000) { "A" } + List(5000) { "B" }
    )
    private val range = column<Double>("range")
    private val category = column<String>("category")
    private val groupedRangesDF = rangesDF.groupBy { category }

    @Test
    fun guideDensityGenerateGroupedData() {
        // SampleStart
        // Create two samples from normal distribution with different mean/std
        val rangesA = NormalDistribution(500.0, 100.0).sample(5000).toList()
        val rangesB = NormalDistribution(400.0, 80.0).sample(5000).toList()

        // Gather them into `DataFrame` with "A" and "B" keys in the "category" column
        val rangesDF = dataFrameOf(
            "range" to rangesA + rangesB,
            "category" to List(5000) { "A" } + List(5000) { "B" }
        )
        rangesDF.head()
        // SampleEnd
    }

    @Test
    @Suppress("UNUSED_EXPRESSION")
    fun guideDensityGroupingData() {
        // SampleStart
        // Group it by "category"
        val groupedRangesDF = rangesDF.groupBy { category }
        groupedRangesDF
        // SampleEnd
    }

    @Test
    fun guideDensityGroupedStatDensity() {
        val range = rangesDF[range]
        // SampleStart
        groupedRangesDF.statDensity { x(range) }
        // SampleEnd
    }

    @Test
    fun guideDensityGroupedStatDensityLinePlot() {
        // SampleStart
        groupedRangesDF.plot {
            statDensity(range) {
                line {
                    x(Stat.x)
                    y(Stat.density)
                }
            }
        }
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideDensityGrStatDensityLinePlotWithColor() {
        // SampleStart
        groupedRangesDF.plot {
            statDensity(range) {
                line {
                    x(Stat.x)
                    y(Stat.density)
                    color(category)
                }
            }
        }
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideDensityGroupedDensityPlot() {
        // SampleStart
        groupedRangesDF.plot {
            densityPlot(range)
        }
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideDensityCustomizeGroupedDensityPlot() {
        // SampleStart
        groupedRangesDF.plot {
            densityPlot(range) {
                // Customize scale of default mapping
                fillColor(category) {
                    scale = categorical("A" to Color.GREEN, "B" to Color.ORANGE)
                }
                borderLine.color = Color.BLACK
                alpha = 0.5
            }
        }
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideDensityStackDensityPlot() {
        // SampleStart
        groupedRangesDF.plot {
            // Use trim
            densityPlot(range, trim = true) {
                // Adjust position of areas from different groups
                position = Position.stack()
                alpha = 0.8
            }
        }
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideDensitySimpleGroupedDensityPlot() {
        // SampleStart
        groupedRangesDF.densityPlot("range", bandWidth = BandWidth.value(10.0))
            // SampleEnd
            .toPlot()
        // .saveSample()
    }

    @Test
    fun guideDensityConfigureSimpleGroupedDensityPlot() {
        val range = rangesDF[range]
        // SampleStart
        groupedRangesDF.densityPlot(n = 750, trim = true, adjust = 0.75) { x(range) }.configure {
            alpha = 0.6
            position = Position.stack()
            // Can access key column by name as `String`
            fillColor("category") { scale = categoricalColorBrewer(BrewerPalette.Qualitative.Dark2) }
        }
        // SampleEnd
        // .saveSample()
    }

    @Test
    fun guideDensityGroupByDensityPlot() {
        // SampleStart
        rangesDF.plot {
            groupBy(category) {
                densityPlot(range)
            }
        }
        // SampleEnd
        // .saveSample()
    }
}