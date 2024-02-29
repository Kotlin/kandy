package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.ir.scale.Scale
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.boxes
import org.jetbrains.kotlinx.kandy.letsplot.layers.errorBars
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotGrid
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.LegendType
import org.jetbrains.kotlinx.kandy.letsplot.y
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.jetbrains.kotlinx.statistics.kandy.layers.candlestick
import org.jetbrains.kotlinx.statistics.kandy.statplots.candlestick
import org.jetbrains.kotlinx.statistics.kandy.statplots.configure
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statCandlestick
import org.jetbrains.kotlinx.statistics.plotting.candlestick.statCandlestick
import kotlin.test.*

class Candlestick : SampleHelper("stat", "guides") {
    private val xList = listOf("Jan", "Feb", "Mar", "Apr", "May")
    private val openList = listOf(14.2, 6.7, 8.8, 11.2, 4.0)
    private val highList = listOf(15.5, 9.6, 10.7, 11.7, 9.9)
    private val lowList = listOf(7.5, 6.1, 8.5, 5.4, 4.0)
    private val closeList = listOf(8.0, 8.6, 10.7, 6.5, 9.8)

    private val df = dataFrameOf(
        "x" to xList,
        "open" to openList,
        "high" to highList,
        "low" to lowList,
        "close" to closeList,
    )

    private val x = column<String>("x")
    private val open = column<Double>("open")
    private val high = column<Double>("high")
    private val low = column<Double>("low")
    private val close = column<Double>("close")

    @Test
    fun guideCandlestickGenerateData() {
        // SampleStart
        // Create a simple candlestick dataset
        val xList = listOf("Jan", "Feb", "Mar", "Apr", "May")
        val openList = listOf(14.2, 6.7, 8.8, 11.2, 4.0)
        val highList = listOf(15.5, 9.6, 10.7, 11.7, 9.9)
        val lowList = listOf(7.5, 6.1, 8.5, 5.4, 4.0)
        val closeList = listOf(8.0, 8.6, 10.7, 6.5, 9.8)
        // Gather lists into df as columns
        val df = dataFrameOf(
            "x" to xList,
            "open" to openList,
            "high" to highList,
            "low" to lowList,
            "close" to closeList,
        )
        df.head()
        // SampleEnd
    }

    @Test
    fun guideCandlestickStatCandlestick() {
        // SampleStart
        df.statCandlestick("x", "open", "high", "low", "close")
        // SampleEnd
    }

    @Test
    fun guideCandlestickStatCandlestickErrorBarsPlot() {
        // SampleStart
        plot {
            statCandlestick(xList, openList, lowList, highList, closeList) {
                errorBars {
                    x(Stat.x)
                    yMin(Stat.lower)
                    yMax(Stat.upper)
                    borderLine.color(Stat.isIncreased) {
                        scale = categorical(true to Color.GREEN, false to Color.RED)
                        legend.type = LegendType.None
                    }
                }
            }
        }
        // SampleEnd
            .saveSample()
    }

    @Test
    @Suppress("UNUSED_EXPRESSION")
    fun guideCandlestickStatCandlestickBoxesPlot() {
        // SampleStart
        val statCandlestickBoxesPlot = df.plot {
            statCandlestick("x", "open", "high", "low", "close") {
                boxes {
                    x(Stat.x)
                    yMin(Stat.min)
                    lower(Stat.lower)
                    upper(Stat.upper)
                    yMax(Stat.max)
                    // temporary solution, middle shoudn't be necceasary
                    middle(List(Stat.x.size()) {null})
                    val colorScale = Scale.categorical(true to Color.GREEN, false to Color.RED)
                    fillColor(Stat.isIncreased) {
                        scale = colorScale
                        // remove legend
                        legend.type = LegendType.None
                    }
                    borderLine.color(Stat.isIncreased) {
                        scale = colorScale
                        // remove legend
                        legend.type = LegendType.None
                    }
                    alpha= 0.6
                    // remove whisker ends
                    whiskerWidth = 0.0
                }
            }
            layout.title = "`statCandlestick` + `boxes`"
        }
        statCandlestickBoxesPlot
        // SampleEnd
            .saveSample()
    }


    @Test
    @Suppress("UNUSED_EXPRESSION")
    fun guideCandlestickCandlestickPlot() {
        // SampleStart
        val candlestickPlot = plot {
            candlestick(xList, openList, highList, lowList, closeList)
            layout.title = "`candlestick`"
        }
        candlestickPlot
        // SampleEnd
            .saveSample()
    }

    @Test
    fun guideCandlestickCompareBoxesVsCandlestick() {
        val statCandlestickBoxesPlot = df.plot {
            statCandlestick("x", "open", "high", "low", "close") {
                boxes {
                    x(Stat.x)
                    yMin(Stat.min)
                    lower(Stat.lower)
                    upper(Stat.upper)
                    yMax(Stat.max)
                    // temporary solution, middle shoudn't be necceasary
                    middle(List(Stat.x.size()) { null })
                    val colorScale = Scale.categorical(true to Color.GREEN, false to Color.RED)
                    fillColor(Stat.isIncreased) {
                        scale = colorScale
                        // remove legend
                        legend.type = LegendType.None
                    }
                    borderLine.color(Stat.isIncreased) {
                        scale = colorScale
                        // remove legend
                        legend.type = LegendType.None
                    }
                    alpha = 0.6
                    // remove whisker ends
                    whiskerWidth = 0.0
                }
            }
            layout.title = "`statCandlestick` + `boxes`"
        }
        val candlestickPlot = plot {
            candlestick(xList, openList, highList, lowList, closeList)
            layout.title = "`candlestick`"
        }
        // SampleStart
        plotGrid(listOf(statCandlestickBoxesPlot, candlestickPlot))
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideCandlestickLayerCustomizationDSL() {
        // SampleStart
        df.plot {
            candlestick(x, open, high, low, close) {
                // Boxes context + StatCandlestick context
                // change fill color when increased
                increase {
                    fillColor = Color.hex("#00FFFF")
                }
                // change fill color when decreased
                decrease.fillColor = Color.hex("#FF0000")
                // set constant border line color for all candles
                borderLine.color = Color.GREY
            }
        }
        // SampleEnd
            .saveSample()
    }

    @Test
    fun guideCandlestickLayerCustomizationStatAPI() {
        // SampleStart
        df.plot {
            candlestick(x, open, high, low, close) {
                // Boxes context + StatCandlestick context
                alpha(Stat.isIncreased) {
                    scale = categorical(true to 1.0, false to 0.1)
                    legend {
                        name = ""
                        breaksLabeled(true to "increase", false to "decrease")
                    }
                }
                fillColor = Color.GREY
                borderLine.color = Color.GREY
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideCandlestickSimpleCandlestickPlot() {
        // SampleStart
        candlestick(xList, openList, highList, lowList, closeList)
         // SampleEnd
            .toPlot()
            .saveSample()
    }

    @Test
    fun guideCandlestickSimpleCandlestickPlotOnDf() {
        // SampleStart
        df.candlestick("x", "open", "high", "low", "close")
        // SampleEnd
            .toPlot()
            .saveSample()
    }

    @Test
    fun guideCandlestickSimpleCandlestickPlotOnDfWithSelectors() {
        val x = df[x]
        val open = df[open]
        val high = df[high]
        val low = df[low]
        val close = df[close]
        // SampleStart
        df.candlestick {
            x(x)
            open(open)
            high(high)
            low(low)
            close(close)
        }
            // SampleEnd
            .toPlot()
            .saveSample()
    }

    @Test
    fun guideCandlestickCandlestickPlotConfigured() {
        // SampleStart
        df.candlestick("x", "open", "high", "low", "close").configure {
            // Boxes + StatCandlestick + PlotContext
            // Can't add a new layer
            y.limits = 3.0..17.0
            increase {
                borderLine.color = Color.BLUE
            }
            decrease.borderLine.color = Color.YELLOW
            borderLine.width = 2.5
            fillColor = Color.GREY
            alpha = 0.6
            // Can configure general plot adjustments
            layout {
                title = "Configured candlestick plot"
                size = 800 to 400
            }
        }
            // SampleEnd
            .saveSample()
    }
}