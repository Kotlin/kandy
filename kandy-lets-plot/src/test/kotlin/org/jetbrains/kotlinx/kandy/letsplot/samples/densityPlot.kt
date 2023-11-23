package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.letsplot.y
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.jetbrains.kotlinx.statistics.kandy.layers.densityPlot
import org.jetbrains.kotlinx.statistics.kandy.layers.histogram
import kotlin.test.Test

class DensityPlot : SampleHelper("densityPlot") {

    @Test
    fun densityPlot_simple_dataframe() {
        // SampleStart
        val random = java.util.Random(42)

        val dataframe = dataFrameOf(
            "sample" to List(1000) { random.nextGaussian() }
        )

        dataframe.plot {
            densityPlot("sample")
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun densityPlot_simple_collections() {
        // SampleStart
        val random = java.util.Random(42)

        val sample = List(1000) { random.nextGaussian() }

        plot {
            densityPlot(sample)
        }
        // SampleEnd
    }

    @Test
    fun densityPlot_settings_dataframe() {
        // SampleStart
        val experimentalDataDf = dataFrameOf(
            "length" to listOf(
                5.92, 6.44, 5.87, 4.99, 5.23,
                5.67, 4.89, 5.34, 5.78, 5.12,
                5.56, 5.23, 5.78, 6.01, 5.56,
                5.67, 5.89, 5.45, 6.12, 5.78,
                6.34, 5.67, 6.45, 5.34, 5.89,
                6.01, 5.78, 5.23, 5.67, 6.12,
                6.23, 5.45, 5.56, 5.67, 5.78,
                5.56, 6.23, 5.78, 6.34, 6.12,
                5.89, 6.45, 5.78, 6.34, 5.67,
                6.56, 5.45, 5.78, 5.89, 6.12,
                4.67, 4.79, 5.14, 5.28, 5.22,
            )
        )

        experimentalDataDf.plot {
            densityPlot("length", adjust = 0.8) {
                alpha = 0.5
                fillColor = Color.RED
                borderLine {
                    color = Color.GREEN
                    width = 0.5
                }
                x.axis.name = "length"
            }
            layout.title = "Flight length experiment"
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun densityPlot_settings_collections() {
        // SampleStart
        val experimentalData = listOf(
            5.92, 6.44, 5.87, 4.99, 5.23,
            5.67, 4.89, 5.34, 5.78, 5.12,
            5.56, 5.23, 5.78, 6.01, 5.56,
            5.67, 5.89, 5.45, 6.12, 5.78,
            6.34, 5.67, 6.45, 5.34, 5.89,
            6.01, 5.78, 5.23, 5.67, 6.12,
            6.23, 5.45, 5.56, 5.67, 5.78,
            5.56, 6.23, 5.78, 6.34, 6.12,
            5.89, 6.45, 5.78, 6.34, 5.67,
            6.56, 5.45, 5.78, 5.89, 6.12,
            4.67, 4.79, 5.14, 5.28, 5.22,
        )

        plot {
            densityPlot(experimentalData, adjust = 0.8) {
                alpha = 0.5
                fillColor = Color.RED
                borderLine {
                    color = Color.GREEN
                    width = 0.5
                }
                x.axis.name = "length"
            }
            layout.title = "Flight length experiment"
        }
        // SampleEnd
    }

    @Test
    fun densityPlot_grouped() {
        // SampleStart
        val macaqueWeights = listOf(
            1.563, 2.173, 2.118, 2.722, 2.384, 1.789, 2.649,
            1.299, 1.566, 1.821, 2.151, 1.554, 1.464, 1.611,
            2.178, 2.652, 2.031, 2.265, 1.919, 1.283, 2.365,
            2.540, 2.779, 2.040, 2.138, 1.783, 1.442, 1.413,
            2.224, 2.345, 1.753, 2.284, 2.465, 1.430, 2.617,
            1.667, 2.462, 1.777, 2.104, 2.404, 1.276, 1.489,
            2.662, 1.853, 2.315, 1.589, 1.586, 2.569, 1.888,
            1.414, 1.623, 2.084, 2.484, 2.348, 1.496, 2.238,
            1.778, 2.173, 2.411, 2.540, 2.325, 1.324, 2.252,
            1.978, 2.075, 2.585, 2.414, 2.162, 1.584, 2.399,
            1.757, 2.109, 1.436, 2.628, 2.370, 1.679, 1.970,
            2.360, 2.005, 2.179, 2.177, 1.941, 2.282, 2.067,
            2.288, 1.481, 1.638, 2.164, 2.485, 1.797, 2.369,
            1.690, 2.143, 1.743, 2.357, 2.280, 1.824, 1.678,
            2.531, 1.958, 1.466, 1.877, 2.005
        )
        val chimpanzeeWeights = listOf(
            4.530, 4.257, 4.381, 4.935, 3.392, 4.611, 2.499, 4.306,
            4.119, 3.622, 4.099, 4.832, 2.456, 3.411, 4.426, 2.940,
            4.529, 3.635, 2.991, 4.372, 2.319, 4.358, 1.882, 3.472,
            4.828, 4.005, 3.452, 3.854, 3.455, 2.996, 4.031, 4.382,
            2.947, 4.752, 2.466, 4.558, 2.548, 3.172, 3.010, 3.472,
            5.258, 2.746, 4.022, 3.013, 4.942, 3.206, 3.779, 4.367,
            3.853, 2.483, 3.957, 2.943, 4.791, 3.432, 3.530, 2.576,
            2.833, 4.650, 5.515, 4.413, 2.699, 3.310, 5.047, 3.047,
            4.095, 4.712, 2.600, 3.523, 3.106, 4.110, 2.894, 3.373,
            2.656, 3.684, 5.171, 4.049, 4.773, 3.456, 4.420, 2.090,
            3.155, 4.678, 3.240,
        )

        val df = dataFrameOf(
            "weight" to macaqueWeights + chimpanzeeWeights,
            "group" to macaqueWeights.map { "macaque" } + chimpanzeeWeights.map { "chimpanzee" }
        )

        df.groupBy("group").plot {
            densityPlot("weight") {
                alpha = 0.5
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun densityPlot_with_histogram() {
        // SampleStart
        val df = DataFrame.readCSV(
            fileOrUrl = "https://raw.githubusercontent.com/Kotlin/dataframe/master/examples/idea-examples/titanic/src/main/resources/titanic.csv",
            delimiter = ';', parserOptions = ParserOptions(locale = java.util.Locale.FRENCH)
        )
        val ages = df["age"].dropNulls().cast<Double>()

        plot {
            histogram(ages) {
                alpha = 0.9
                fillColor = Color.WHITE
                y(Stat.density)
            }
            densityPlot(ages) {
                alpha = 0.5
                fillColor = Color.hex(0xFF6666)
            }
            layout.title = "Titanc passangers age distribution"
        }
            // SampleEnd
            .saveSample()
    }

}
