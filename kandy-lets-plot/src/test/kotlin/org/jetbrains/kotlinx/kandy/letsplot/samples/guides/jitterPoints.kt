package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.position
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import kotlin.test.Test

class JitterPoints : SampleHelper("geoms", "guides") {

    private val mpgDf =
        DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")

    private val drv = column<String>("drv")
    private val hwy = column<Int>("hwy")

    @Test
    fun guideJitterPointsSimplePoints() {
        // SampleStart
        mpgDf.plot {
            points {
                x(drv)
                y(hwy)
                color(drv)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideJitterPointsJitterPoints() {
        // SampleStart
        mpgDf.plot {
            points {
                x(drv)
                y(hwy)
                color(drv)
                position = Position.jitter()
            }
        }
            // SampleEnd
            .saveSample()
    }
}