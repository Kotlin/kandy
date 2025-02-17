package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.ParserOptions
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.filter
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.hLine
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.layers.text
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.Anchor
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.tooltips
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.letsplot.y
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.jetbrains.kotlinx.statistics.dataframe.stat.mean
import java.util.*
import kotlin.test.Test

class LabelFormat : SampleHelper("layout", "guides") {

    private val economics = DataFrame.readCSV(
        "https://vincentarelbundock.github.io/Rdatasets/csv/ggplot2/economics.csv",
        parserOptions = ParserOptions(Locale.ENGLISH)
    ).filter { "date"<LocalDate>() >= LocalDate(2001, 1, 1) }

    private val date = column<LocalDate>("date")
    private val uempmed = column<Double>("uempmed")

    @Test
    fun guideLabelReadData() {
        // SampleStart
        // The US Unemployment Rates 2000-2016
        val economics = DataFrame.readCSV(
            "https://vincentarelbundock.github.io/Rdatasets/csv/ggplot2/economics.csv",
            parserOptions = ParserOptions(Locale.ENGLISH)
        ).filter { "date"<LocalDate>() >= LocalDate(2001, 1, 1) }

        economics.head()
        // SampleEnd
    }

    @Test
    fun guideLabelDefaultPlot() {
        // SampleStart
        economics.plot {
            line {
                x(date)
                y(uempmed) { axis.name = "unemployment rate" }
            }
            layout.size = 900 to 400
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideLabelFormattingXAndY() {
        // SampleStart
        economics.plot {
            line {
                x(date) { axis.breaks(format = "%b %Y") }
                y(uempmed) {
                    axis {
                        name = "unemployment rate"
                        breaks(format = "{} %")
                    }
                }
            }
            layout.size = 900 to 400
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideLabelFormattingBreaksManually() {
        // SampleStart
        economics.plot {
            line {
                x(date) { axis.breaks(format = "%b %Y") }
                y(uempmed) {
                    axis {
                        name = "unemployment rate"
                        breaks(listOf(5.0, 15.0, 25.0), format = "{} %")
                    }
                }
            }
            layout.size = 900 to 400
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideLabelConfigureTooltips() {
        // SampleStart
        economics.plot {
            line {
                x(date) { axis.breaks(format = "%b %Y") }
                y(uempmed) {
                    axis {
                        name = "unemployment rate"
                        breaks(format = "{} %")
                    }
                }
                tooltips(anchor = Anchor.TOP_CENTER, minWidth = 170.0) {
                    line("Unemployment rate:|^y")
                }
            }
            layout.size = 900 to 400
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideLabelFormatValueTooltip() {
        // SampleStart
        economics.plot {
            line {
                x(date)
                y(uempmed) {
                    axis {
                        name = "unemployment rate"
                    }
                }
                tooltips(formats = mapOf(date to "%B %Y"), anchor = Anchor.TOP_CENTER, minWidth = 170.0) {
                    line("@uempmed % in @date")
                }
            }
            layout.size = 900 to 400
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideLabelMeanMarkLine() {
        // SampleStart
        val unemploymentMean = economics[uempmed].mean()


        economics.plot {
            x(date)
            y(uempmed)
            line {
                tooltips(formats = mapOf(date to "%B %Y"), anchor = Anchor.TOP_CENTER, minWidth = 170.0) {
                    line("Unemployment rate:|^y %")
                }
            }
            hLine {
                yIntercept.constant(unemploymentMean)
                color = Color.RED
                type = LineType.DASHED
                tooltips(enable = false)
            }
            text {
                label = "${String.format("%.2f", unemploymentMean)} %"
                x.constant(LocalDate(2001, 1, 1).atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds())
                y.constant(unemploymentMean + 0.5)
            }
            y.axis.name = "unemployment rate"
            layout {
                title = "The US Unemployment Rates 2000-2016."
                size = 900 to 400
            }
        }
            // SampleEnd
            .saveSample()
    }
}