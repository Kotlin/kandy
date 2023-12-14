package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.statistics.kandy.layers.densityPlot
import kotlin.test.Test
import kotlin.test.assertNotNull

class ExportToFile : SampleHelper("other", "guides") {

    private val rand = java.util.Random(42)
    private val n = 500
    private val dataset = dataFrameOf(
        "rating" to List(n / 2) { rand.nextGaussian() } + List(n / 2) { rand.nextGaussian() * 1.5 + 1.5 },
        "cond" to List(n / 2) { "A" } + List(n / 2) { "B" }
    )

    private val rating = column<Double>("rating")
    private val cond = column<String>("cond")


    @Test
    fun guideExportGenerateRandomData() {
        // SampleStart
        // Create random density data
        val rand = java.util.Random(42)
        val n = 500
        val dataset = dataFrameOf(
            "rating" to List(n / 2) { rand.nextGaussian() } + List(n / 2) { rand.nextGaussian() * 1.5 + 1.5 },
            "cond" to List(n / 2) { "A" } + List(n / 2) { "B" }
        )
        // SampleEnd
        assertNotNull(dataset[rating])
        assertNotNull(dataset[cond])
    }

    @Test
    fun guideExportDensityPlot() {
        // SampleStart
        // Density plot
        val myPlot = plot(dataset) {
            groupBy(cond) {
                densityPlot(rating, trim = true) {
                    fillColor = Color.GREY
                    alpha = 0.6
                    borderLine.color("cond")
                }
            }
        }

        myPlot
            // SampleEnd
            .saveSample()
    }
}