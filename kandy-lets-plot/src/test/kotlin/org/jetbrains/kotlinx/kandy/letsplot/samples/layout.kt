package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.LegendType
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.jetbrains.kotlinx.statistics.binning.BinsOption
import org.jetbrains.kotlinx.statistics.kandy.layers.histogram
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statBin
import kotlin.test.Test

class Layout : SampleHelper("layout") {

    @Test
    fun base_layout_settings() {
        // SampleStart
        plot {
            line {
                x(listOf(1, 2, 3, 4, 5))
                y(listOf(5, 4, 7, 9, 10))
            }
            layout {
                title = "Plot title"
                size = 800 to 300
            }
        }
            // SampleEnd
            .saveSample(true)
    }

    @Test
    fun subtitle_and_caption() {
        // SampleStart
        plot {
            bars {
                x(listOf("a", "b", "c", "d", "e"))
                y(listOf(15, 14, 17, 19, 10))
            }
            layout {
                title = "Plot title\nwith a line break"
                subtitle = "Plot subtitle"
                caption = "Plot caption"
                size = 700 to 350
            }
        }
        // SampleEnd
            .saveSample(true)
    }

    @Test
    fun axis_and_legend_configuration() {
        // SampleStart
        val df = DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")

        df.plot {
            points {
                x("displ") {
                    axis.name = "engine displacement, liters"
                }
                y("hwy") {
                    axis {
                        name = "highway mileage, mpg"
                        breaks(listOf(15, 25, 35, 45), format = "d")
                    }
                }
                color("cty") {
                    legend {
                        name = "city mileage"
                        breaks(format = "{d} mpg")
                    }
                }
                symbol("drv") {
                    legend {
                        type = LegendType.DiscreteLegend(nRow = 2)
                        name = "drive type"
                        breaksLabeled("4" to "4WD", "r" to "RWD", "f" to "FWD")
                    }
                }
                size = 4.0
            }
        }
            // SampleEnd
            .saveSample()
    }

}
