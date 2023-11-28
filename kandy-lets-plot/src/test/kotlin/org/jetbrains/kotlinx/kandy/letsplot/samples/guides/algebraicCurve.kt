package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.invoke
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.raster
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.LegendType
import org.jetbrains.kotlinx.kandy.letsplot.theme.Theme
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.pow
import kotlin.test.Test
import kotlin.test.assertNotNull

class AlgebraicCurve : SampleHelper("geoms", "guides") {

    private fun linspace(start: Double, stop: Double, num: Int): List<Double> {
        return List(num) { i -> start + i * ((stop - start) / (num - 1)) }
    }

    private fun F(x: Double, y: Double, a: Double = 0.0, b: Double = 0.0): Double {
        return y.pow(2) - x.pow(3) - a * x - b
    }

    private fun level(z: Double, c: Double = 1.0): Double {
        return exp(-c * abs(z))
    }

    private val n = 300
    private val a = -1.0
    private val b = 0.0
    private val xRange = linspace(-3.0, 3.0, n + 1)
    private val yRange = linspace(-3.0, 3.0, n + 1)
    private val zippedData = xRange.map { x ->
        yRange.map { y -> Triple(x, y, level(F(x, y, a = a, b = b), c = 10.0)) }
    }.flatten()
    private val data = mapOf(
        "x" to zippedData.map { it.first },
        "y" to zippedData.map { it.second },
        "z" to zippedData.map { it.third },
    )

    @Test
    fun guideAlgebraicCurveLinspace() {
        // SampleStart
        fun linspace(start: Double, stop: Double, num: Int): List<Double> {
            return List(num) { i -> start + i * ((stop - start) / (num - 1)) }
        }
        // SampleEnd
    }

    @Test
    fun guideAlgebraicCurveFLevel() {
        // SampleStart
        fun F(x: Double, y: Double, a: Double = 0.0, b: Double = 0.0): Double {
            return y.pow(2) - x.pow(3) - a * x - b
        }

        fun level(z: Double, c: Double = 1.0): Double {
            return exp(-c * abs(z))
        }
        // SampleEnd
    }

    @Test
    fun guideAlgebraicCurveData() {
        // SampleStart
        val n = 300
        val a = -1.0
        val b = 0.0
        val xRange = linspace(-3.0, 3.0, n + 1)
        val yRange = linspace(-3.0, 3.0, n + 1)
        val zippedData = xRange.map { x ->
            yRange.map { y -> Triple(x, y, level(F(x, y, a = a, b = b), c = 10.0)) }
        }.flatten()
        val data = mapOf(
            "x" to zippedData.map { it.first },
            "y" to zippedData.map { it.second },
            "z" to zippedData.map { it.third },
        )
        // SampleEnd
        assertNotNull(data["x"])
        assertNotNull(data["y"])
        assertNotNull(data["z"])
    }

    @Test
    fun guideAlgebraicCurvePlot() {
        // SampleStart
        plot(data) {
            raster {
                x("x"<Double>())
                y("y"<Double>())
                fillColor("z"<Double>()) {
                    scale = continuous(range = Color.hex("#253494")..Color.hex("#ffffcc"))
                    legend.type = LegendType.None
                }
            }

            layout {
                title = "Elliptic curve with a = $a, b = $b"
                subtitle = "Simple way to draw an algebraic curve - with `raster` layer"
                size = 800 to 600

                theme(Theme.Classic) {
                    blankAxes()
                }
            }
        }
            // SampleEnd
            .saveSample()
    }


}