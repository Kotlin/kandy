package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.LegendType
import org.jetbrains.kotlinx.kandy.letsplot.theme.LegendDirection
import org.jetbrains.kotlinx.kandy.letsplot.theme.LegendPosition
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.letsplot.y
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.Test

class LegendAndAxis : SampleHelper("layout", "guides") {

    private val mpgDf =
        DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
    private val displ = column<Double>("displ")
    private val hwy = column<Int>("hwy")
    private val manufacturer = column<String>("manufacturer")
    private val cty = column<Int>("cty")
    private val drv = column<String>("drv")

    @Test
    fun guideLegendAxisReadData() {
        // SampleStart
        val mpgDf =
            DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
        mpgDf.head()
        // SampleEnd
    }

    @Test
    fun guideLegendAxisDefaultLegend() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(manufacturer)
                size = 5.0
            }
            layout.size = 600 to 250
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideLegendAxisTwoColumnsLegend() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(manufacturer) {
                    legend.type = LegendType.DiscreteLegend(nCol = 2)
                }
                size = 5.0
            }
            layout {
                title = "Two columns legend"
                size = 600 to 250
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideLegendAxisFillingLegendCols() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(manufacturer) {
                    legend.type = LegendType.DiscreteLegend(nCol = 2, byRow = true)
                }
                size = 5.0
            }
            layout {
                title = "Two columns legend filled by rows"
                size = 600 to 250
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideLegendAxisBottomLegend() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(manufacturer) {
                    legend.type = LegendType.DiscreteLegend(nRow = 5)
                }
                size = 5.0
            }
            layout {
                title = "Five rows legend and below"
                size = 700 to 400
                style {
                    axis.title { blank = true }
                    legend.position = LegendPosition.Bottom
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideLegendAxisContinuousLegend() {
        // SampleStart
        mpgDf.plot {
            x(displ)
            y(hwy)
            points {
                size = 5.0
                color(cty)
                symbol(drv)
            }
            layout.size = 700 to 350
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideLegendAxisLegendHorizontalDirection() {
        // SampleStart
        mpgDf.plot {
            x(displ)
            y(hwy)
            points {
                size = 5.0
                color(cty)
                symbol(drv)
            }
            layout {
                size = 700 to 350
                style {
                    legend {
                        justification(1.0, 1.0)
                        position(1.0, 1.0)
                        direction = LegendDirection.HORIZONTAL
                    }
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideLegendAxisConfigureLegend() {
        // SampleStart
        mpgDf.plot {
            x(displ)
            y(hwy)
            points {
                size = 5.0
                color(cty) {
                    scale = continuous(range = Color.named("dark_blue")..Color.named("light_blue"))
                    legend {
                        name = "City MPG"
                        type = LegendType.ColorBar(barHeight = 10.0, barWidth = 300.0)
                    }
                }
                symbol(drv) {
                    legend {
                        name = "Drive-train"
                        breaksLabeled("f" to "front", "r" to "rear", "4" to "4X4")
                    }
                }
            }
            layout {
                size = 700 to 350
                style {
                    yAxis.line { blank = true }
                    legend {
                        justification(1.0, 1.0)
                        position(1.0, 1.0)
                        direction = LegendDirection.HORIZONTAL
                    }
                }
                xAxisLabel = "Engine displacement (L)"
                yAxisLabel = "Highway MPG"
            }
        }
            // SampleEnd
            .saveSample()
    }
}