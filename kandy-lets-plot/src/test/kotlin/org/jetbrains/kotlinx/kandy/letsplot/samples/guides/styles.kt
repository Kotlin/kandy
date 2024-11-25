package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.count
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.*
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.facetGridX
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotGrid
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.style.*
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.Anchor
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.tooltips
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.Test
import kotlin.test.assertNotNull

class Styles : SampleHelper("layout", "guides") {

    private val df =
        DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")

    private val year = column<Int>("year")
    private val cty = column<Int>("cty")
    private val hwy = column<Int>("hwy")
    private val fl = column<String>("fl")
    private val count = column<Int>("count")

    private val bPlotDf = df.groupBy { fl }.count()

    private val fPlotDf = df.groupBy { fl and year }.count()

    private val eLine = LayoutParameters.line(color = Color.RED, width = 4.0)
    private val eLine2 = LayoutParameters.line(color = Color.hex("#fcae91"), width = 1.0)
    private val eLine3 = LayoutParameters.line(width = 4.0)
    private val eBackground = LayoutParameters.background(
        borderLineColor = Color.hex("#2c7fb8"),
        fillColor = Color.hex("#edf8b1"),
        borderLineWidth = 2.0
    )
    private val eText = LayoutParameters.text(color = Color.hex("#31a354"))

    private fun pointPlotWithStyle(
        name: String,
        baseStyle: Style = Style.Minimal2,
        customStyleBuilder: CustomStyle.() -> Unit = {}
    ) =
        df.plot {
            points {
                x(cty)
                y(hwy)
            }
            layout {
                title = name
                style(baseStyle, customStyleBuilder)
            }
        }

    private fun barPlotWithStyle(
        name: String,
        baseStyle: Style = Style.Minimal2,
        customStyleBuilder: CustomStyle.() -> Unit = {}
    ) =
        bPlotDf.plot {
            bars {
                x(fl)
                y(count)
                fillColor(fl)
            }
            layout {
                title = name
                style(baseStyle, customStyleBuilder)
            }
        }

    private fun barFacetPlotWithStyle(
        name: String,
        baseStyle: Style = Style.Minimal2,
        customStyleBuilder: CustomStyle.() -> Unit = {}
    ) =
        fPlotDf.plot {
            bars {
                x(fl)
                y(count)
                fillColor(fl)
            }
            facetGridX(year)
            layout {
                title = name
                style(baseStyle, customStyleBuilder)
            }
        }

    @Test
    fun guideStylesReadData() {
        // SampleStart
        val df =
            DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
        df.head(3)
        // SampleEnd
    }

    @Test
    fun guideStylesPointPlotWithoutStyle() {
        // SampleStart
        fun pointPlotWithStyle(
            name: String,
            baseStyle: Style = Style.Minimal2,
            customStyleBuilder: CustomStyle.() -> Unit = {}
        ) =
            df.plot {
                points {
                    x(cty)
                    y(hwy)
                }
                layout {
                    title = name
                    style(baseStyle, customStyleBuilder)
                }
            }

        df.plot {
            points {
                x(cty)
                y(hwy)
            }
        }
            // SampleEnd
            .saveSample()

    }

    @Test
    fun guideStylesCountFlDf() {
        // SampleStart
        val bPlotDf = df.groupBy { fl }.count()
        // SampleEnd
        assertNotNull(bPlotDf["count"])
    }

    @Test
    fun guideStylesBarPlotWithoutStyle() {
        // SampleStart
        fun barPlotWithStyle(
            name: String,
            baseStyle: Style = Style.Minimal2,
            customStyleBuilder: CustomStyle.() -> Unit = {}
        ) =
            bPlotDf.plot {
                bars {
                    x(fl)
                    y(count)
                    fillColor(fl)
                }
                layout {
                    title = name
                    style(baseStyle, customStyleBuilder)
                }
            }

        bPlotDf.plot {
            bars {
                x(fl)
                y(count)
                fillColor(fl)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideStylesCountFlAndYearDf() {
        // SampleStart
        val fPlotDf = df.groupBy { fl and year }.count()
        // SampleEnd
        assertNotNull(fPlotDf[count])
    }

    @Test
    fun guideStylesBarsFacetGridX() {
        // SampleStart
        fun barFacetPlotWithStyle(
            name: String,
            baseStyle: Style = Style.Minimal2,
            customStyleBuilder: CustomStyle.() -> Unit = {}
        ) =
            fPlotDf.plot {
                bars {
                    x(fl)
                    y(count)
                    fillColor(fl)
                }
                facetGridX(year)
                layout {
                    title = name
                    style(baseStyle, customStyleBuilder)
                }
            }

        fPlotDf.plot {
            bars {
                x(fl)
                y(count)
                fillColor(fl)
            }
            facetGridX(year)
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideStylesUtilElements() {
        // SampleStart
        val eLine = LayoutParameters.line(color = Color.RED, width = 4.0)
        val eLine2 = LayoutParameters.line(color = Color.hex("#fcae91"), width = 1.0)
        val eLine3 = LayoutParameters.line(width = 4.0)
        val eBackground = LayoutParameters.background(
            borderLineColor = Color.hex("#2c7fb8"),
            fillColor = Color.hex("#edf8b1"),
            borderLineWidth = 2.0
        )
        val eText = LayoutParameters.text(color = Color.hex("#31a354"))
        val (w, h) = listOf(400) to listOf(300)
        // SampleEnd
        assertNotNull(eLine)
        assertNotNull(eLine2)
        assertNotNull(eLine3)
        assertNotNull(eBackground)
        assertNotNull(eText)
        assertNotNull(w)
        assertNotNull(h)
    }

    @Test
    fun guideStylesAllPrepStyles() {
        // SampleStart
        plotGrid(
            listOf(
                pointPlotWithStyle("Minimal2 Style - by default"),
                pointPlotWithStyle("Light Style", Style.Light),
                pointPlotWithStyle("Classic Style", Style.Classic),
                pointPlotWithStyle("Grey Style", Style.Grey),
                pointPlotWithStyle("Minimal Style", Style.Minimal),
                pointPlotWithStyle("None Style", Style.None)
            ),
            nCol = 2,
            fit = true
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideStylesOrangeCustomStyle() {
        // SampleStart
        val yellowLight = Color.hex("#ffffcc")
        val orangeDark = Color.hex("#7f2704")
        val orangeNormal = Color.hex("#f16913")
        val orangeLight = Color.hex("#fff5eb")

        val styleOrangeConstructor: CustomStyle.() -> Unit = {
            global {
                line {
                    color = orangeNormal
                    width = 2.0
                }
                background {
                    borderLineColor = orangeNormal
                    fillColor = orangeLight
                    borderLineWidth = 2.0
                }
                text {
                    color = orangeDark
                }
            }
            axis {
                ticks {
                    color = orangeNormal
                    width = 1.0
                }
                ticksLength = 7.0
                onTop = true
            }
            legend {
                background {
                    borderLineWidth = 1.0
                }
                position = LegendPosition.Bottom
            }
            panel.grid {
                majorLine {
                    color = orangeNormal
                    width = .5
                }
                minorLine {
                    blank = true
                }
            }
            plotCanvas.background {
                fillColor = yellowLight
                borderLineWidth = 1.0
            }
            axis.tooltip.background {
                borderLineColor = orangeDark
            }
        }

        plotGrid(
            listOf(
                pointPlotWithStyle("Scatter plot", Style.None, styleOrangeConstructor),
                barPlotWithStyle("Bar plot", Style.None, styleOrangeConstructor)
            ),
            nCol = 2,
            fit = true
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideStylesCustomStyleForLegend() {
        // SampleStart
        barPlotWithStyle("Place legend") {
            legend {
                position(1.0, 1.0)
                justification(1.0, 1.0)
                direction = LegendDirection.HORIZONTAL
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideStylesAxisTooltip() {
        // SampleStart
        plotGrid(
            listOf(
                pointPlotWithStyle("blank tooltip x-axis") {
                    xAxis.tooltip.background {
                        blank = true
                    }
                },
                pointPlotWithStyle("background tooltip x-axis") {
                    xAxis.tooltip.background(eBackground)
                }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideStylesConfigureGridLine() {
        // SampleStart
        plotGrid(
            listOf(
                pointPlotWithStyle("Default"),
                pointPlotWithStyle("Configured grid line") {
                    global.line(eLine)
                }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideStylesConfigureBackgroundGrid() {
        // SampleStart
        plotGrid(
            listOf(
                barPlotWithStyle("None style", Style.None),
                barPlotWithStyle("None style + Rect", Style.None) {
                    global.background(eBackground)
                }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideStylesTextLegendStrip() {
        // SampleStart
        plotGrid(
            listOf(
                barFacetPlotWithStyle("Default"),
                barFacetPlotWithStyle("Text") { global.text(eText) },
                barFacetPlotWithStyle("Legend text") { legend.text(eText) },
                barFacetPlotWithStyle("Strip text") { strip.text(eText) }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideStylesTextTitleLegend() {
        // SampleStart
        plotGrid(
            listOf(
                barPlotWithStyle("Default"),
                barPlotWithStyle("Text") { global.text(eText) },
                barPlotWithStyle("Plot title") { plotCanvas.title(eText) },
                barPlotWithStyle("Legend title") { legend.title(eText) }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideStylesBlankAxis() {
        // SampleStart
        plotGrid(
            listOf(
                pointPlotWithStyle("Default"),
                pointPlotWithStyle("Blank axis") {
                    axis.line {
                        blank = true
                    }
                }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideStylesBackgroundPanel() {
        // SampleStart
        plotGrid(
            listOf(
                pointPlotWithStyle("Default"),
                pointPlotWithStyle("Panel background") {
                    panel.background(eBackground)
                }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideStylesPanelGrid() {
        // SampleStart
        plotGrid(
            listOf(
                barPlotWithStyle("Blank panel grid") {
                    panel.grid.lineGlobal {
                        blank = true
                    }
                },
                pointPlotWithStyle("Line panel grid") {
                    panel.grid.lineGlobal(eLine)
                },
                pointPlotWithStyle("Major line panel grid\n Minor line panel grid") {
                    panel.grid {
                        majorLine(eLine)
                        minorLine(eLine2)
                    }
                },
                barPlotWithStyle("Blank major x-line panel grid\n major y-line panel grid\n minor y-line panel grid") {
                    panel.grid {
                        majorXLine {
                            blank = true
                        }
                        majorYLine(eLine)
                        minorYLine(eLine2)
                    }
                }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideStylesStripBackground() {
        // SampleStart
        plotGrid(
            listOf(
                barFacetPlotWithStyle("Grey Style", Style.Grey),
                barFacetPlotWithStyle("Strip background") { strip.background(eBackground) },
                barFacetPlotWithStyle("Blank strip background") {
                    strip.background {
                        blank = true
                    }
                },
                barFacetPlotWithStyle("Blank strip text") {
                    strip.text {
                        blank = true
                    }
                }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideStylesOnTopXAxis() {
        // SampleStart
        plotGrid(
            listOf(
                barPlotWithStyle("x-axis line") { xAxis.line(eLine3) },
                barPlotWithStyle("On top x-axis line") {
                    xAxis {
                        line(eLine3)
                        onTop = true
                    }
                }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideStylesCoordFlip() {
        // SampleStart
        plotGrid(
            listOf(
                fPlotDf.plot {
                    bars {
                        x(fl)
                        y(count)
                        fillColor(fl)
                    }
                    coordinatesTransformation = CoordinatesTransformation.cartesianFlipped()
                    layout {
                        title = "Default"
                    }
                },
                fPlotDf.plot {
                    bars {
                        x(fl)
                        y(count)
                        fillColor(fl)
                    }
                    coordinatesTransformation = CoordinatesTransformation.cartesianFlipped()
                    layout {
                        title = "Plot 1"
                        style {
                            global {
                                line(eLine)
                                text(eText)
                            }
                        }
                    }
                },
                fPlotDf.plot {
                    bars {
                        x(fl)
                        y(count)
                        fillColor(fl)
                    }
                    coordinatesTransformation = CoordinatesTransformation.cartesianFlipped()
                    layout {
                        title = "Plot 2"
                        style {
                            axis {
                                line(eLine)
                                onTop = true
                            }
                            legend.title(eText)
                        }
                    }
                },
                fPlotDf.plot {
                    bars {
                        x(fl)
                        y(count)
                        fillColor(fl)
                    }
                    coordinatesTransformation = CoordinatesTransformation.cartesianFlipped()
                    layout {
                        title = "Plot 3"
                        style {
                            plotCanvas.title(eText)
                            panel {
                                background(eBackground)
                                grid {
                                    majorXLine {
                                        blank = true
                                    }
                                    minorXLine {
                                        blank = true
                                    }
                                    majorYLine(eLine)
                                    minorYLine(eLine2)
                                }
                            }
                        }
                    }
                },
                fPlotDf.plot {
                    bars {
                        x(fl)
                        y(count)
                        fillColor(fl)
                    }
                    coordinatesTransformation = CoordinatesTransformation.cartesianFlipped()
                    layout {
                        title = "Plot 4"
                        style {
                            strip {
                                background(eBackground)
                                text(eText)
                            }
                        }
                    }
                },
                fPlotDf.plot {
                    bars {
                        x(fl)
                        y(count)
                        fillColor(fl)
                    }
                    coordinatesTransformation = CoordinatesTransformation.cartesianFlipped()
                    layout {
                        title = "Plot 5"
                        style {
                            xAxis {
                                text(eText)
                                title(eText)
                                ticks(eLine)
                                line(eLine2)
                                tooltip.background {
                                    blank = true
                                }
                            }
                        }
                    }
                }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideStylesTooltipAndBackground() {
        // SampleStart
        plotGrid(
            listOf(
                df.plot {
                    points {
                        x(cty)
                        y(hwy)
                        color(fl)

                        tooltips(anchor = Anchor.TOP_CENTER, minWidth = 50.0) {}
                    }
                    layout.title = "Tooltip: top-center"
                },
                df.plot {
                    points {
                        x(cty)
                        y(hwy)
                        color(fl)

                        tooltips(anchor = Anchor.TOP_CENTER, minWidth = 50.0) {}
                    }
                    layout {
                        title = "Grey background"
                        style {
                            panel.background {
                                borderLineColor = Color.PURPLE
                                fillColor = Color.GREY
                            }
                        }
                    }
                }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }
}