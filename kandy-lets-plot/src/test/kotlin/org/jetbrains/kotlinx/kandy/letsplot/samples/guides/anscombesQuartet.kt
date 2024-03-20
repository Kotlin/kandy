package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.ParserOptions
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.describe
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.layers.abLine
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.facetWrap
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import java.util.*
import kotlin.test.Test

class AnscombesQuartet : SampleHelper("multiplot", "guides") {

    private val df =
        DataFrame.readCSV(
            "https://gist.githubusercontent.com/ericbusboom/b2ac1d366c005cd2ed8c/raw/c92c66e43d144fa9c29dbd602d5af6988e8db533/anscombes.csv",
            parserOptions = ParserOptions(locale = Locale.ENGLISH)
        )

    private val dataset = column<String>("dataset")

    @Test
    fun guideAnscombesQuartetReadData() {
        // SampleStart
        val df =
            DataFrame.readCSV(
                "https://gist.githubusercontent.com/ericbusboom/b2ac1d366c005cd2ed8c/raw/c92c66e43d144fa9c29dbd602d5af6988e8db533/anscombes.csv",
                parserOptions = ParserOptions(locale = Locale.ENGLISH)
            )

        df.head()
        // SampleEnd
    }

    @Test
    fun guideAnscombesQuartetDataDescribe() {
        // SampleStart
        df.describe()
        // SampleEnd
    }

    @Test
    fun guideAnscombesQuartetPlot() {
        // SampleStart
        df.plot {
            points {
                x("x") {
                    scale = continuous(0.0..20.0)
                }
                y("y") {
                    scale = continuous(0.0..20.0)
                }
                color(dataset)
                size = 5.0
            }

            abLine {
                slope.constant(0.5)
                intercept.constant(3)

                width = 1.7
                alpha = 0.7
            }

            facetWrap(nCol = 2) {
                facet(dataset)
            }
        }
            // SampleEnd
            .saveSample()
    }
}