package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.LegendType
import org.jetbrains.kotlinx.kandy.letsplot.settings.font.FontFace
import org.jetbrains.kotlinx.kandy.letsplot.theme.LegendPosition
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.Test

class TitleSubtitleCaption : SampleHelper("layout", "guides") {

    private val mpgDf =
        DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")

    private val displ = column<Double>("displ")
    private val drv = column<String>("drv")
    private val cty = column<Int>("cty")
    private val hwy = column<Int>("hwy")

    @Test
    fun guideTitleReadData() {
        // SampleStart
        val mpgDf =
            DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
        mpgDf.head()
        // SampleEnd
    }

    @Test
    fun guideTitleDefaultPointPlot() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(drv)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideTitleSetTitle1() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(drv)
            }
            layout.title = "The plot title using 'layout.title'"
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideTitleSetTitle2() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(drv)
            }
            layout {
                title = "The plot title using 'Layout context'"
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideTitleSetTitleAndSubtitle() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(drv)
            }
            layout {
                title = "The plot title"
                subtitle = "The plot subtitle"
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideTitleSetTitleSubtitleCaption() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(drv)
            }
            layout {
                title = "The plot title"
                subtitle = "The plot subtitle"
                caption = "The plot caption"
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideTitleChangeTitlesColor() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(drv)
            }
            layout {
                title = "The plot title"
                subtitle = "The plot subtitle"
                caption = "The plot caption"
                style {
                    global.title {
                        color = Color.BLUE
                    }
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideTitleChangeColorAndFontFace() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(drv)
            }
            layout {
                title = "The plot title"
                subtitle = "The plot subtitle"
                caption = "The plot caption"
                style {
                    global {
                        text {
                            fontFace = FontFace.ITALIC
                        }
                        title {
                            fontFace = FontFace.ITALIC
                        }
                    }
                    plotCanvas {
                        title {
                            color = Color.BLUE
                        }
                        subtitle {
                            fontFace = FontFace.ITALIC
                        }
                        caption {
                            fontFace = FontFace.ITALIC
                        }
                    }
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideTitleSetColorsForTitleSubtitleCaption() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(drv)
            }
            layout {
                title = "The plot title"
                subtitle = "The plot subtitle"
                caption = "The plot caption"
                style {
                    plotCanvas {
                        title {
                            color = Color.BLUE
                        }
                        subtitle {
                            color = Color.RED
                        }
                        caption {
                            color = Color.named("dark_green")
                        }
                    }
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideTitleComplexTitles() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(drv)
            }
            layout {
                title = "The plot title:\nFuel efficiency for most popular models of car"
                subtitle = "The plot subtitle:\nPoints are colored by the type of drive train"
                caption = "The plot caption:\nmpg dataset"
                style {
                    plotCanvas {
                        subtitle {
                            color = Color.GREY
                        }
                    }
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideTitleLegendName() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(cty) { legend.name = "City mileage" }
                symbol(drv) { legend.name = "Drive type" }
                size = 4.0
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideTitleLegendPosition() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(cty) { legend.name = "City mileage" }
                symbol(drv) { legend.name = "Drive type" }
                size = 4.0
            }
            layout.style {
                legend.position = LegendPosition.Bottom
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideTitleComplexLegendNames() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(cty) { legend.name = "City mileage\n(mpg)" }
                symbol(drv) { legend.name = "Drive type\n(front/4/rear wheel)" }
                size = 4.0
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideTitleComplexLegendBottom() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(cty) { legend.name = "City mileage\n(mpg)" }
                symbol(drv) { legend.name = "Drive type\n(front/4/rear wheel)" }
                size = 4.0
            }
            layout.style {
                legend.position = LegendPosition.Bottom
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideTitleTypeOfLegend() {
        // SampleStart
        mpgDf.plot {
            points {
                x(displ)
                y(hwy)
                color(cty) { legend.name = "City mileage\n(mpg)" }
                symbol(drv) {
                    legend {
                        type = LegendType.DiscreteLegend(nRow = 3)
                        name = "Drive type\n(front/4/rear wheel)"
                    }
                }
                size = 4.0
            }
            layout.style {
                legend.position = LegendPosition.Bottom
            }
        }
            // SampleEnd
            .saveSample()
    }
}