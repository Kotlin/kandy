package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.layers.tiles
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.settings.Symbol
import org.jetbrains.kotlinx.kandy.letsplot.style.LegendPosition
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.Anchor
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.tooltips
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.value
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.jetbrains.kotlinx.statistics.kandy.layers.boxplot
import org.jetbrains.kotlinx.statistics.kandy.layers.densityPlot
import java.util.*
import kotlin.test.Test
import kotlin.test.assertNotNull

class Tooltips : SampleHelper("layout", "guides") {

    private val mpgDf =
        DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")

    private val manufacturer = column<String>("manufacturer")
    private val model = column<String>("model")
    private val displ = column<Double>("displ")
    private val year = column<Int>("year")
    private val drv = column<String>("drv")
    private val cty = column<Int>("cty")
    private val hwy = column<Int>("hwy")
    private val `class` = column<String>("class")


    @Test
    fun guideTooltipReadAutoDf() {
        // SampleStart
        val mpgDf =
            DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
        mpgDf.head()
        // SampleEnd
    }

    @Test
    fun guideTooltipVariablesTooltip() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(cty)
                fillColor(drv)
                size(hwy)

                symbol = Symbol.CIRCLE_FILLED
                color = Color.GREY

                tooltips(manufacturer, model, `class`, drv)
            }
        }
        // SampleEnd
    }

    @Test
    fun guideTooltipLinesTooltip() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(cty)
                fillColor(drv)
                size(hwy)

                symbol = Symbol.CIRCLE_FILLED
                color = Color.GREY

                tooltips {
                    line("${manufacturer.tooltipValue()} ${model.tooltipValue()}")
                    line("cty/hwy|${cty.tooltipValue(".1f")}/${hwy.tooltipValue(".1f")}")
                    line(`class`)
                    line("drive train", drv.tooltipValue("{}wd"))
                    line(year, format = "d")
                }
            }

            layout {
                style {
                    legend.position = LegendPosition.None
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun guideTooltipVarLineTooltip() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(cty)
                fillColor(drv)
                size(hwy)

                symbol = Symbol.CIRCLE_FILLED
                color = Color.GREY

                tooltips {
                    varLine("hwy", "{.2f} (mpg)")
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun guideTooltipAnchorAndMinWidth() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(cty)
                fillColor(drv)
                size(hwy)

                symbol = Symbol.CIRCLE_FILLED
                color = Color.GREY

                tooltips(anchor = Anchor.TOP_CENTER, minWidth = 200.0) {
                    line("${manufacturer.tooltipValue()} ${model.tooltipValue()}")
                    line("cty/hwy|${cty.tooltipValue(".1f")}/${hwy.tooltipValue(".1f")}")
                    line(`class`)
                    line("drive train", drv.tooltipValue("{}wd"))
                    line(year, format = "d")
                }
            }
        }
        // SampleEnd
    }

    private val irisDf = DataFrame.readCSV(
        "https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/iris.csv",
        parserOptions = ParserOptions(Locale.ENGLISH)
    )

    private val species = column<String>("species")
    private val sepal_length = column<Double>("sepal_length")

    @Test
    fun guideTooltipReadIrisDf() {
        // SampleStart
        val irisDf = DataFrame.readCSV(
            "https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/iris.csv",
            parserOptions = ParserOptions(Locale.ENGLISH)
        )
        irisDf.head()
        // SampleEnd
    }

    @Test
    fun guideTooltipDensityPlotTooltip() {
        // SampleStart
        irisDf.groupBy { species }.plot {
            densityPlot(sepal_length) {
                tooltips(anchor = Anchor.TOP_RIGHT) {
                    line(species.tooltipValue())
                    line("length", Stat.x.tooltipValue())
                    line("density", Stat.density.tooltipValue())
                }
            }
            layout {
                style { legend.position = LegendPosition.None }
            }
        }
        // SampleEnd
    }

    @Test
    fun guideTooltipBoxplotTooltip() {
        // SampleStart
        mpgDf.plot {
            boxplot(`class`, hwy) {
                boxes {
                    tooltips {
                        line("y min/max", "${Stat.min.tooltipValue()}, ${Stat.max.tooltipValue()}")
                        line("lower/upper", "${Stat.lower.tooltipValue()}, ${Stat.upper.tooltipValue()}")
                        line("middle", Stat.middle.tooltipValue())
                    }
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun guideTooltipBoxplotAnchorCenter() {
        // SampleStart
        mpgDf.plot {
            boxplot(`class`, hwy) {
                boxes {
                    tooltips(anchor = Anchor.TOP_CENTER) {
                        line("middle", Stat.middle.tooltipValue(".2f"))
                        line("lower/upper", "${Stat.lower.tooltipValue("d")}, ${Stat.upper.tooltipValue("d")}")
                        line("y min/max", "${Stat.min.tooltipValue("d")}, ${Stat.max.tooltipValue("d")}")
                    }
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun guideTooltipHide() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(cty)
                fillColor(drv)
                size(hwy)

                symbol = Symbol.CIRCLE_FILLED
                color = Color.GREY

                tooltips(enable = false)
            }
        }
        // SampleEnd
    }

    @Test
    fun guideTooltipSplittingText() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(cty)
                fillColor(drv)
                size(hwy)

                symbol = Symbol.CIRCLE_FILLED
                color = Color.GREY

                tooltips {
                    line("${value(manufacturer)} \n${value(model)}")
                    line(`class`)
                    line(year)
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun guideTooltipTitle() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(cty)
                fillColor(drv)
                size(hwy)

                symbol = Symbol.CIRCLE_FILLED
                color = Color.GREY

                tooltips(title = "${value(manufacturer)} ${value(model)}") {}
            }
            layout {
                style { legend.position = LegendPosition.None }
            }
        }
        // SampleEnd
    }

    @Test
    fun guideTooltipVariablesTitle() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(cty)
                fillColor(drv)
                size(hwy)

                symbol = Symbol.CIRCLE_FILLED
                color = Color.GREY

                tooltips(`class`, year, title = "${value(manufacturer)} ${value(model)}") {}
            }
            layout {
                style { legend.position = LegendPosition.None }
            }
        }
        // SampleEnd
    }

    @Test
    fun guideTooltipSplittingTextInTitle() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(cty)
                fillColor(drv)
                size(hwy)

                symbol = Symbol.CIRCLE_FILLED
                color = Color.GREY

                tooltips(title = "Car info: \n${value(manufacturer)} ${value(model)}") {
                    line(`class`)
                    line("drive train", value(drv))
                    line(year)
                }
            }
            layout {
                style { legend.position = LegendPosition.None }
            }
        }
        // SampleEnd
    }

    private val dataset = dataFrameOf("x" to listOf(0.0, 1.0), "y" to listOf(0.0, 1.0))


    @Test
    fun guideTooltipDatasetForStyle() {
        // SampleStart
        val dataset = dataFrameOf("x" to listOf(0.0, 1.0), "y" to listOf(0.0, 1.0))
        // SampleEnd
        assertNotNull(dataset["x"])
        assertNotNull(dataset["y"])
    }

    @Test
    fun guideTooltipWithoutStyle() {
        // SampleStart
        plot(dataset) {
            tiles {
                x("x")
                y("y")
                fillColor = Color.GREY

                tooltips(title = "Tooltip title") {
                    line("label|value")
                }
            }
        }
        // SampleEnd
    }

    @Test
    fun guideTooltipWithStyle() {
        // SampleStart
        plot(dataset) {
            tiles {
                x("x")
                y("y")
                fillColor = Color.GREY

                tooltips(title = "Tooltip title") {
                    line("label|value")
                }
            }

            layout.style {
                layerTooltips {
                    background {
                        borderLineColor = Color.hex("#225e32")
                        fillColor = Color.hex("#238b45")
                        borderLineWidth = 2.0
                    }
                    text {
                        color = Color.hex("#bae4b3")
                    }
                    title {
                        color = Color.hex("#edf8e9")
                    }
                }
                xAxis.tooltip.text {
                    color = Color.GREEN
                }
            }
        }
        // SampleEnd
    }
}