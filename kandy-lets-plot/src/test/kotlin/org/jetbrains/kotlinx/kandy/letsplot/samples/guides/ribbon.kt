package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.kandy.dsl.invoke
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.layers.ribbon
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import kotlin.math.abs
import kotlin.math.pow
import kotlin.test.Test
import kotlin.test.assertNotNull

class Ribbon : SampleHelper("geoms", "guides") {

    private fun generateDataMap(n: Int = 15, a: Double = 1.0): Map<String, List<Any>> {
        val rand = java.util.Random(42)
        val x = List(2 * n + 1) { i -> a * (i - n).toDouble() / n }
        val tMin = x.map { a * a - it.pow(2) - abs(rand.nextGaussian()) }
        val tMax = x.map { a * a - it.pow(2) + abs(rand.nextGaussian()) }
        return mapOf("day" to (1..x.size).toList(), "minTemp" to tMin, "maxTemp" to tMax)
    }

    private val dataMap = generateDataMap(a = 2.0)

    @Test
    fun guideRibbonData() {
        // SampleStart
        fun generateDataMap(n: Int = 15, a: Double = 1.0): Map<String, List<Any>> {
            val rand = java.util.Random(42)
            val x = List(2 * n + 1) { i -> a * (i - n).toDouble() / n }
            val tMin = x.map { a * a - it.pow(2) - abs(rand.nextGaussian()) }
            val tMax = x.map { a * a - it.pow(2) + abs(rand.nextGaussian()) }
            return mapOf("day" to (1..x.size).toList(), "minTemp" to tMin, "maxTemp" to tMax)
        }

        val dataMap = generateDataMap(a = 2.0)
        // SampleEnd
        assertNotNull(dataMap["day"])
        assertNotNull(dataMap["minTemp"])
        assertNotNull(dataMap["maxTemp"])
    }

    @Test
    fun guideRibbonPlot() {
        // SampleStart
        plot(dataMap) {
            ribbon {
                x("day"<Int>()) { axis.name = "time" }
                yMin("minTemp"<Double>())
                yMax("maxTemp"<Double>())
            }
        }
            // SampleEnd
            .saveSample()
    }
}