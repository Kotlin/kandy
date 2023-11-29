package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.api.columnOf
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.ir.scale.Scale
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.feature.position
import org.jetbrains.kotlinx.kandy.letsplot.layers.*
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.settings.Symbol
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.Test
import kotlin.test.assertNotNull

class ErrorBars : SampleHelper("geoms", "guides") {

    private val supp by columnOf("OJ", "OJ", "OJ", "VC", "VC", "VC")
    private val dose by columnOf(0.5, 1.0, 2.0, 0.5, 1.0, 2.0)
    private val length by columnOf(13.23, 22.70, 26.06, 7.98, 16.77, 26.14)
    private val len_min by columnOf(11.83, 21.2, 24.50, 4.24, 15.26, 23.35)
    private val len_max by columnOf(15.63, 24.9, 27.11, 10.72, 19.28, 28.93)
    private val data = dataFrameOf(supp, dose, length, len_min, len_max)

    private val posD = Position.dodge(0.1)

    private val customColorScale = Scale.categorical<Color, String>(
        range = listOf(Color.ORANGE, Color.named("dark_green"))
    )

    @Test
    fun guideErrorBarsData() {
        // SampleStart
        // This example was found at: www.cookbook-r.com/Graphs/Plotting_means_and_error_bars_(ggplot2)
        val supp by columnOf("OJ", "OJ", "OJ", "VC", "VC", "VC")
        val dose by columnOf(0.5, 1.0, 2.0, 0.5, 1.0, 2.0)
        val length by columnOf(13.23, 22.70, 26.06, 7.98, 16.77, 26.14)
        val len_min by columnOf(11.83, 21.2, 24.50, 4.24, 15.26, 23.35)
        val len_max by columnOf(15.63, 24.9, 27.11, 10.72, 19.28, 28.93)
        val data = dataFrameOf(supp, dose, length, len_min, len_max)
        // SampleEnd
        assertNotNull(data[supp])
        assertNotNull(data[dose])
        assertNotNull(data[length])
        assertNotNull(data[len_min])
        assertNotNull(data[len_max])
    }

    @Test
    fun guideErrorBarsWithLines() {
        // SampleStart
        plot(data) {
            x(dose)
            errorBars {
                yMin(len_min)
                yMax(len_max)
                borderLine.color(supp)

                width = .1
            }
            line {
                y(length)
                color(supp)
            }
            points {
                y(length)
                color(supp)
            }
        }
            // SampleEnd
            .saveSample()

    }

    @Test
    fun guideErrorBarsWithLinesAndPosition() {
        // SampleStart
        val posD = Position.dodge(0.1)
        plot(data) {
            x(dose)

            errorBars {
                yMin(len_min)
                yMax(len_max)
                borderLine.color(supp)

                width = .1
                position = posD
            }

            line {
                y(length)
                color(supp)

                position = posD
            }

            points {
                y(length)
                color(supp)

                position = posD
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideErrorBarsWithLinesGrouped() {
        // SampleStart
        plot(data) {
            x(dose)
            groupBy(supp) {
                errorBars {
                    yMin(len_min)
                    yMax(len_max)

                    borderLine.color = Color.BLACK
                    width = .1
                    position = posD
                }
            }


            line {
                y(length)
                color(supp)

                position = posD
            }

            points {
                y(length)
                color(supp)

                size = 5.0
                position = posD
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideErrorBarsCustomColorScale() {
        // SampleStart
        val customColorScale = Scale.categorical<Color, String>(
            range = listOf(Color.ORANGE, Color.named("dark_green"))
        )
        // SampleEnd
        assertNotNull(customColorScale.rangeValues)
    }

    @Test
    fun guideErrorBarsWithLinesCustomColorScale() {
        // SampleStart
        plot(data) {
            x(dose)

            groupBy(supp) {
                errorBars {
                    yMin(len_min)
                    yMax(len_max)

                    borderLine.color = Color.BLACK
                    width = .1
                    position = posD
                }
            }

            line {
                y(length)
                color(supp) { scale = customColorScale }

                position = posD
            }

            points {
                y(length)
                color(supp) { scale = customColorScale }

                symbol = Symbol.CIRCLE_FILLED
                size = 5.0
                fillColor = Color.WHITE
                position = posD
            }

            layout {
                title = "The Effect of Vitamin C on Tooth Growth in Guinea Pigs"
                size = 700 to 400
                xAxisLabel = "Dose (mg)"
                yAxisLabel = "Tooth length (mm)"

                theme {
                    legend {
                        justification(1.0, 0.0)
                        position(1.0, 0.0)
                    }
                }
            }

        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideErrorBarsOnBars() {
        // SampleStart
        plot(data) {
            x(dose)

            bars {
                y(length)
                fillColor(supp) { scale = customColorScale }

                borderLine.color = Color.BLACK
                position = Position.dodge()
            }

            groupBy(supp) {
                errorBars {
                    yMin(len_min)
                    yMax(len_max)

                    borderLine.color = Color.BLACK
                    width = .1
                    position = Position.dodge(0.9)
                }
            }


            layout {
                size = 700 to 400
                xAxisLabel = "Dose (mg)"
                yAxisLabel = "Tooth length (mm)"

                theme {
                    legend {
                        justification(0.0, 1.0)
                        position(0.0, 1.0)
                    }
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideErrorBarsCrossbars() {
        // SampleStart
        plot(data) {
            x(dose)

            crossBars {
                yMin(len_min)
                yMax(len_max)
                y(length)
                borderLine.color(supp) { scale = customColorScale }

                fatten = 5.0
                position = Position.dodge(0.95)
            }

            layout {
                size = 700 to 400
                xAxisLabel = "Dose (mg)"
                yAxisLabel = "Tooth length (mm)"
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideErrorBarsLineRanges() {
        // SampleStart
        plot(data) {
            x(dose)

            lineRanges {
                yMin(len_min)
                yMax(len_max)
                borderLine.color(supp) { scale = customColorScale }

                position = posD
            }

            line {
                y(length)
                color(supp) { scale = customColorScale }

                position = posD
            }

            layout {
                size = 700 to 400
                xAxisLabel = "Dose (mg)"
                yAxisLabel = "Tooth length (mm)"
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideErrorBarsPointRanges() {
        // SampleStart
        plot(data) {
            x(dose)

            pointRanges {
                y(length)
                yMin(len_min)
                yMax(len_max)
                color(supp) { scale = customColorScale }

                position = posD
            }

            line {
                y(length)
                color(supp) { scale = customColorScale }

                position = posD
            }

            layout {
                size = 700 to 400
                xAxisLabel = "Dose (mg)"
                yAxisLabel = "Tooth length (mm)"
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideErrorBarsConfiguredPointRanges() {
        // SampleStart
        plot(data) {
            x(dose)

            line {
                y(length)
                color(supp) { scale = customColorScale }

                position = posD
            }

            pointRanges {
                y(length)
                yMin(len_min)
                yMax(len_max)

                innerPoint {
                    fatten = 1.0
                    symbol = Symbol.DIAMOND_FILLED
                    fillColor(supp) { scale = customColorScale }
                }

                size = 5.0
                color = Color.rgb(230, 230, 230)
                position = posD
            }

            layout {
                size = 700 to 400
                xAxisLabel = "Dose (mg)"
                yAxisLabel = "Tooth length (mm)"
            }
        }
            // SampleEnd
            .saveSample()
    }
}