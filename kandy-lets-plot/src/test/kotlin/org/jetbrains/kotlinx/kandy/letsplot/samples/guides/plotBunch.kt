package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.apache.commons.math3.distribution.MultivariateNormalDistribution
import org.jetbrains.kotlinx.kandy.dsl.continuousPos
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.ir.scale.Scale
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotBunch
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.theme.style
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.statistics.kandy.layers.histogram
import kotlin.test.Test
import kotlin.test.assertNotNull


class PlotBunch : SampleHelper("multiplot", "guides") {

    private val cov: Array<DoubleArray> = arrayOf(
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(0.0, 1.0)
    )
    private val means: DoubleArray = doubleArrayOf(0.0, 0.0)
    private val xy = MultivariateNormalDistribution(means, cov).sample(400)

    private val xs = xy.map { it[0] }
    private val ys = xy.map { it[1] }

    private val scaleX = Scale.continuousPos(-3.5..3.5)

    private val upperTheme = style {
        blankAxes()
        axis.text { }
        yAxis.title { }
        panel.grid.majorYLine {
            blank = true
        }
    }
    private val lowerTheme = style {
        blankAxes()
        yAxis.text { }
        axis.title { }
    }


    @Test
    fun guideBunchGenerateData() {
        // SampleStart
        val cov: Array<DoubleArray> = arrayOf(
            doubleArrayOf(1.0, 0.0),
            doubleArrayOf(0.0, 1.0)
        )
        val means: DoubleArray = doubleArrayOf(0.0, 0.0)
        val xy = MultivariateNormalDistribution(means, cov).sample(400)

        val xs = xy.map { it[0] }
        val ys = xy.map { it[1] }
        // SampleEnd
        assertNotNull(xs.first())
        assertNotNull(ys.first())
    }

    @Test
    fun guideBunchScatterPlot() {
        // SampleStart
        plot {
            points {
                x(xs)
                y(ys)
                color = Color.GREY
                alpha = .4
            }
            layout {
                size = 600 to 200
                xAxisLabel = "x"
                yAxisLabel = "y"
            }
        }
        // SampleEnd
//            .saveSample()

    }

    @Suppress("UNUSED_EXPRESSION")
    @Test
    fun guideBunchHistogramPlot() {
        // SampleStart
        plot {
            histogram(xs) {
                fillColor = Color.named("dark_magenta")
            }
            layout {
                size = 600 to 200
                xAxisLabel = "x"
            }
        }
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideBunchCombineScatterAndHistogram() {
        // SampleStart
        val scaleX = Scale.continuousPos(-3.5..3.5)
        plotBunch {
            add(plot {
                histogram(xs) {
                    x { scale = scaleX }
                    fillColor = Color.named("dark_magenta")
                }
                layout {
                    size = 600 to 200
                    xAxisLabel = "x"
                }
            }, 0, 0)
            add(plot {
                points {
                    x(xs) { scale = scaleX }
                    y(ys)
                    color = Color.GREY
                    alpha = .4
                }
                layout {
                    size = 600 to 200
                    xAxisLabel = "x"
                    yAxisLabel = "y"
                }
            }, 0, 200)
        }
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideBunchCreateThemes() {
        // SampleStart
        val upperTheme = style {
            blankAxes()
            axis.text { }
            yAxis.title { }
            panel.grid.majorYLine {
                blank = true
            }
        }
        val lowerTheme = style {
            blankAxes()
            yAxis.text { }
            axis.title { }
        }
        // SampleEnd
        assertNotNull(upperTheme)
        assertNotNull(lowerTheme)
    }

    @Test
    fun guideBunchCombineWithThemes() {
        // SampleStart
        plotBunch {
            add(plot {
                histogram(xs) {
                    x { scale = scaleX }
                    fillColor = Color.named("dark_magenta")
                }
                layout {
                    size = 600 to 200
                    xAxisLabel = "x"
                    style(upperTheme)
                }
            }, 0, 0)
            add(plot {
                points {
                    x(xs) { scale = scaleX }
                    y(ys)
                    color = Color.GREY
                    alpha = .4
                }
                layout {
                    size = 600 to 200
                    xAxisLabel = "x"
                    yAxisLabel = "y"
                    style(lowerTheme)
                }
            }, 0, 200)
        }
        // SampleEnd
//            .saveSample()
    }

    @Test
    fun guideBunchConfigureSizes() {
        // SampleStart
        plotBunch {
            add(plot {
                histogram(xs) {
                    x { scale = scaleX }
                    fillColor = Color.named("dark_magenta")
                }
                layout {
                    size = 600 to 200
                    xAxisLabel = "x"
                    style(upperTheme)
                }
            }, 0, 0, 600, 100)
            add(plot {
                points {
                    x(xs) { scale = scaleX }
                    y(ys)
                    color = Color.GREY
                    alpha = .4
                }
                layout {
                    size = 600 to 200
                    xAxisLabel = "x"
                    yAxisLabel = "y"
                    style(lowerTheme)
                }
            }, 0, 100, 600, 300)
        }
        // SampleEnd
//            .saveSample()
    }
}