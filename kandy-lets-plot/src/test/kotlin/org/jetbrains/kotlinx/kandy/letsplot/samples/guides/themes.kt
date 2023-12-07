package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.count
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.coordFlip
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.facetGridX
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotGrid
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.theme.*
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.Anchor
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.tooltips
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.Test
import kotlin.test.assertNotNull

class Themes : SampleHelper("layout", "guides") {

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

    private fun pointPlotWithTheme(
        name: String,
        inTheme: Theme = Theme.Minimal2,
        cusTheme: CustomTheme.() -> Unit = {}
    ) =
        df.plot {
            points {
                x(cty)
                y(hwy)
            }
            layout {
                title = name
                theme(inTheme, cusTheme)
            }
        }

    private fun barPlotWithTheme(name: String, inTheme: Theme = Theme.Minimal2, cusTheme: CustomTheme.() -> Unit = {}) =
        bPlotDf.plot {
            bars {
                x(fl)
                y(count)
                fillColor(fl)
            }
            layout {
                title = name
                theme(inTheme, cusTheme)
            }
        }

    private fun barFacetPlotWithTheme(
        name: String, inTheme: Theme = Theme.Minimal2, cusTheme: CustomTheme.() -> Unit = {}
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
                theme(inTheme, cusTheme)
            }
        }

    @Test
    fun guideThemesReadData() {
        // SampleStart
        val df =
            DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
        df.head(3)
        // SampleEnd
    }

    @Test
    fun guideThemesPointPlotWithoutTheme() {
        // SampleStart
        fun pointPlotWithTheme(name: String, inTheme: Theme = Theme.Minimal2, cusTheme: CustomTheme.() -> Unit = {}) =
            df.plot {
                points {
                    x(cty)
                    y(hwy)
                }
                layout {
                    title = name
                    theme(inTheme, cusTheme)
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
    fun guideThemesCountFlDf() {
        // SampleStart
        val bPlotDf = df.groupBy { fl }.count()
        // SampleEnd
        assertNotNull(bPlotDf["count"])
    }

    @Test
    fun guideThemesBarPlotWithoutTheme() {
        // SampleStart
        fun barPlotWithTheme(name: String, inTheme: Theme = Theme.Minimal2, cusTheme: CustomTheme.() -> Unit = {}) =
            bPlotDf.plot {
                bars {
                    x(fl)
                    y(count)
                    fillColor(fl)
                }
                layout {
                    title = name
                    theme(inTheme, cusTheme)
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
    fun guideThemesCountFlAndYearDf() {
        // SampleStart
        val fPlotDf = df.groupBy { fl and year }.count()
        // SampleEnd
        assertNotNull(fPlotDf[count])
    }

    @Test
    fun guideThemesBarsFacetGridX() {
        // SampleStart
        fun barFacetPlotWithTheme(
            name: String, inTheme: Theme = Theme.Minimal2, cusTheme: CustomTheme.() -> Unit = {}
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
                    theme(inTheme, cusTheme)
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
    fun guideThemesUtilElements() {
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
    fun guideThemesAllPrepThemes() {
        // SampleStart
        plotGrid(
            listOf(
                pointPlotWithTheme("Minimal2 Theme - by default"),
                pointPlotWithTheme("Light Theme", Theme.Light),
                pointPlotWithTheme("Classic Theme", Theme.Classic),
                pointPlotWithTheme("Grey Theme", Theme.Grey),
                pointPlotWithTheme("Minimal Theme", Theme.Minimal),
                pointPlotWithTheme("None Theme", Theme.None)
            ),
            nCol = 2,
            fit = true
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideThemesOrangeCustomTheme() {
        // SampleStart
        val yellowLight = Color.hex("#ffffcc")
        val orangeDark = Color.hex("#7f2704")
        val orangeNormal = Color.hex("#f16913")
        val orangeLight = Color.hex("#fff5eb")

        val themeOrangeConstructor: CustomTheme.() -> Unit = {
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
                pointPlotWithTheme("Scatter plot", Theme.None, themeOrangeConstructor),
                barPlotWithTheme("Bar plot", Theme.None, themeOrangeConstructor)
            ),
            nCol = 2,
            fit = true
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideThemesCustomThemeForLegend() {
        // SampleStart
        barPlotWithTheme("Place legend") {
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
    fun guideThemesAxisTooltip() {
        // SampleStart
        plotGrid(
            listOf(
                pointPlotWithTheme("blank tooltip x-axis") {
                    xAxis.tooltip.background {
                        blank = true
                    }
                },
                pointPlotWithTheme("background tooltip x-axis") {
                    xAxis.tooltip.background(eBackground)
                }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideThemesConfigureGridLine() {
        // SampleStart
        plotGrid(
            listOf(
                pointPlotWithTheme("Default"),
                pointPlotWithTheme("Configured grid line") {
                    global.line(eLine)
                }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideThemesConfigureBackgroundGrid() {
        // SampleStart
        plotGrid(
            listOf(
                barPlotWithTheme("None Theme", Theme.None),
                barPlotWithTheme("None theme + Rect", Theme.None) {
                    global.background(eBackground)
                }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideThemesTextLegendStrip() {
        // SampleStart
        plotGrid(
            listOf(
                barFacetPlotWithTheme("Default"),
                barFacetPlotWithTheme("Text") { global.text(eText) },
                barFacetPlotWithTheme("Legend text") { legend.text(eText) },
                barFacetPlotWithTheme("Strip text") { strip.text(eText) }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideThemesTextTitleLegend() {
        // SampleStart
        plotGrid(
            listOf(
                barPlotWithTheme("Default"),
                barPlotWithTheme("Text") { global.text(eText) },
                barPlotWithTheme("Plot title") { plotCanvas.title(eText) },
                barPlotWithTheme("Legend title") { legend.title(eText) }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideThemesBlankAxis() {
        // SampleStart
        plotGrid(
            listOf(
                pointPlotWithTheme("Default"),
                pointPlotWithTheme("Blank axis") {
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
    fun guideThemesBackgroundPanel() {
        // SampleStart
        plotGrid(
            listOf(
                pointPlotWithTheme("Default"),
                pointPlotWithTheme("Panel background") {
                    panel.background(eBackground)
                }
            ),
            nCol = 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun guideThemesPanelGrid() {
        // SampleStart
        plotGrid(
            listOf(
                barPlotWithTheme("Blank panel grid") {
                    panel.grid.lineGlobal {
                        blank = true
                    }
                },
                pointPlotWithTheme("Line panel grid") {
                    panel.grid.lineGlobal(eLine)
                },
                pointPlotWithTheme("Major line panel grid\n Minor line panel grid") {
                    panel.grid {
                        majorLine(eLine)
                        minorLine(eLine2)
                    }
                },
                barPlotWithTheme("Blank major x-line panel grid\n major y-line panel grid\n minor y-line panel grid") {
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
    fun guideThemesStripBackground() {
        // SampleStart
        plotGrid(
            listOf(
                barFacetPlotWithTheme("Grey Theme", Theme.Grey),
                barFacetPlotWithTheme("Strip background") { strip.background(eBackground) },
                barFacetPlotWithTheme("Blank strip background") {
                    strip.background {
                        blank = true
                    }
                },
                barFacetPlotWithTheme("Blank strip text") {
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
    fun guideThemesOnTopXAxis() {
        // SampleStart
        plotGrid(
            listOf(
                barPlotWithTheme("x-axis line") { xAxis.line(eLine3) },
                barPlotWithTheme("On top x-axis line") {
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
    fun guideThemesCoordFlip() {
        // SampleStart
        plotGrid(
            listOf(
                fPlotDf.plot {
                    bars {
                        x(fl)
                        y(count)
                        fillColor(fl)
                    }
                    coordFlip()
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
                    coordFlip()
                    layout {
                        title = "Plot 1"
                        theme {
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
                    coordFlip()
                    layout {
                        title = "Plot 2"
                        theme {
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
                    coordFlip()
                    layout {
                        title = "Plot 3"
                        theme {
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
                    coordFlip()
                    layout {
                        title = "Plot 4"
                        theme {
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
                    coordFlip()
                    layout {
                        title = "Plot 5"
                        theme {
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
    fun guideThemesTooltipAndBackground() {
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
                        theme {
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