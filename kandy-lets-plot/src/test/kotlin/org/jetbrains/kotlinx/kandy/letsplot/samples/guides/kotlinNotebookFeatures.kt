package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.theme.Theme
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.tooltips
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertNotNull

class KotlinNotebookFeatures : SampleHelper("other", "guides") {

    private val rand = Random(42)

    private val simpleData = dataFrameOf(
        "time" to (1..10).toList(),
        "value" to List(10) { rand.nextDouble(0.0, 1.0) },
        "type" to (List(5) { "a" } + List(5) { "b" }).shuffled(rand),
        "active" to List(10) { rand.nextBoolean() },
    )

    private val time = column<Int>("time")
    private val value = column<Double>("value")
    private val type = column<String>("type")
    private val active = column<Boolean>("active")

    @Test
    fun guideKTNBFeaturesGenerateData() {
        // SampleStart
        val rand = Random(42)

        val simpleData = dataFrameOf(
            "time" to (1..10).toList(),
            "value" to List(10) { rand.nextDouble(0.0, 1.0) },
            "type" to (List(5) { "a" } + List(5) { "b" }).shuffled(rand),
            "active" to List(10) { rand.nextBoolean() },
        )
        // SampleEnd

        assertNotNull(simpleData["time"])
        assertNotNull(simpleData["value"])
        assertNotNull(simpleData["type"])
        assertNotNull(simpleData["active"])
    }

    @Test
    @Suppress("UNUSED_EXPRESSION")
    fun guideKTNBFeaturesPointsPlot() {
        // SampleStart
        val plot = simpleData.plot {
            points {
                x(time)
                y(value)
                color(type)
                size = 6.0

                tooltips(type, active)
            }
        }
        plot
        // SampleEnd
    }

    @Test
    fun guideKTNBFeaturesHighContrastDark() {
        // SampleStart
        simpleData.plot {
            points {
                x(time)
                y(value)
                color(type)
                size = 6.0

                tooltips(type, active)
            }

            layout.flavor = Theme.HIGH_CONTRAST_DARK
        }
        // SampleEnd
    }
}

