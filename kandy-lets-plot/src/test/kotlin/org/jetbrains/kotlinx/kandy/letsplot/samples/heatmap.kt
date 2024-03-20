package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.y
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.jetbrains.kotlinx.statistics.kandy.layers.heatmap
import kotlin.test.Test

class Heatmap : SampleHelper("heatmap") {

    @Test
    fun heatmap_simple_dataframe() {
        // SampleStart
        val dataframe = dataFrameOf(
            "days" to listOf(
                "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Sun",
                "Sat", "Thu", "Fri", "Tue", "Wed", "Sun", "Mon", "Thu",
                "Sun", "Sat", "Tue", "Mon", "Thu", "Wed", "Fri", "Sat",
                "Tue", "Sun", "Fri", "Sat", "Thu", "Mon", "Wed", "Tue",
                "Thu", "Mon", "Sun", "Fri", "Wed", "Sat", "Tue", "Thu",
                "Sat", "Tue", "Sun", "Mon", "Wed", "Fri", "Thu", "Sat",
                "Thu", "Fri", "Sun", "Tue", "Sat", "Wed", "Mon", "Thu",
                "Wed", "Tue", "Sat", "Fri", "Sun", "Thu", "Mon", "Tue",
                "Fri", "Thu", "Wed", "Sun", "Sat", "Mon", "Tue", "Thu",
                "Tue", "Wed", "Sun", "Mon", "Thu", "Sat", "Fri", "Tue",
                "Thu", "Sun", "Fri", "Sat", "Mon", "Wed", "Tue", "Thu",
                "Sat", "Mon", "Tue", "Thu", "Fri", "Sun", "Wed", "Sat",
                "Sun", "Fri", "Tue", "Thu", "Sat", "Mon", "Wed", "Sun",
                "Mon", "Wed", "Sat", "Fri", "Thu", "Tue", "Sun", "Sat",
            ),
            "drinks" to listOf(
                "soda", "tea", "coffee", "tea", "soda", "tea", "coffee", "soda",
                "coffee", "soda", "tea", "coffee", "soda", "tea", "coffee", "tea",
                "coffee", "soda", "tea", "soda", "coffee", "tea", "soda", "coffee",
                "soda", "tea", "coffee", "tea", "soda", "coffee", "tea", "soda",
                "tea", "soda", "coffee", "tea", "soda", "coffee", "soda", "tea",
                "coffee", "soda", "tea", "soda", "coffee", "tea", "soda", "coffee",
                "soda", "coffee", "tea", "soda", "coffee", "soda", "tea", "coffee",
                "soda", "coffee", "tea", "soda", "tea", "soda", "coffee", "tea",
                "tea", "coffee", "soda", "tea", "coffee", "soda", "tea", "soda",
                "tea", "soda", "coffee", "soda", "tea", "coffee", "soda", "coffee",
                "tea", "coffee", "soda", "tea", "soda", "coffee", "soda", "tea",
                "coffee", "soda", "tea", "coffee", "tea", "soda", "coffee", "soda",
                "soda", "tea", "coffee", "soda", "tea", "coffee", "soda", "tea",
                "coffee", "tea", "soda", "coffee", "tea", "soda", "coffee", "soda"
            )
        )

        dataframe.plot {
            heatmap("days", "drinks")
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun heatmap_simple_collections() {
        // SampleStart
        val days = listOf(
            "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Sun",
            "Sat", "Thu", "Fri", "Tue", "Wed", "Sun", "Mon", "Thu",
            "Sun", "Sat", "Tue", "Mon", "Thu", "Wed", "Fri", "Sat",
            "Tue", "Sun", "Fri", "Sat", "Thu", "Mon", "Wed", "Tue",
            "Thu", "Mon", "Sun", "Fri", "Wed", "Sat", "Tue", "Thu",
            "Sat", "Tue", "Sun", "Mon", "Wed", "Fri", "Thu", "Sat",
            "Thu", "Fri", "Sun", "Tue", "Sat", "Wed", "Mon", "Thu",
            "Wed", "Tue", "Sat", "Fri", "Sun", "Thu", "Mon", "Tue",
            "Fri", "Thu", "Wed", "Sun", "Sat", "Mon", "Tue", "Thu",
            "Tue", "Wed", "Sun", "Mon", "Thu", "Sat", "Fri", "Tue",
            "Thu", "Sun", "Fri", "Sat", "Mon", "Wed", "Tue", "Thu",
            "Sat", "Mon", "Tue", "Thu", "Fri", "Sun", "Wed", "Sat",
            "Sun", "Fri", "Tue", "Thu", "Sat", "Mon", "Wed", "Sun",
            "Mon", "Wed", "Sat", "Fri", "Thu", "Tue", "Sun", "Sat",
        )
        val drinks = listOf(
            "soda", "tea", "coffee", "tea", "soda", "tea", "coffee", "soda",
            "coffee", "soda", "tea", "coffee", "soda", "tea", "coffee", "tea",
            "coffee", "soda", "tea", "soda", "coffee", "tea", "soda", "coffee",
            "soda", "tea", "coffee", "tea", "soda", "coffee", "tea", "soda",
            "tea", "soda", "coffee", "tea", "soda", "coffee", "soda", "tea",
            "coffee", "soda", "tea", "soda", "coffee", "tea", "soda", "coffee",
            "soda", "coffee", "tea", "soda", "coffee", "soda", "tea", "coffee",
            "soda", "coffee", "tea", "soda", "tea", "soda", "coffee", "tea",
            "tea", "coffee", "soda", "tea", "coffee", "soda", "tea", "soda",
            "tea", "soda", "coffee", "soda", "tea", "coffee", "soda", "coffee",
            "tea", "coffee", "soda", "tea", "soda", "coffee", "soda", "tea",
            "coffee", "soda", "tea", "coffee", "tea", "soda", "coffee", "soda",
            "soda", "tea", "coffee", "soda", "tea", "coffee", "soda", "tea",
            "coffee", "tea", "soda", "coffee", "tea", "soda", "coffee", "soda"
        )

        plot {
            heatmap(days, drinks)
        }
        // SampleEnd
    }

    @Test
    fun heatmap_settings() {
        // SampleStart
        val df = DataFrame.readCSV(
            fileOrUrl = "https://raw.githubusercontent.com/Kotlin/dataframe/master/examples/idea-examples/titanic/src/main/resources/titanic.csv",
            delimiter = ';', parserOptions = ParserOptions(locale = java.util.Locale.FRENCH)
        )

        df.plot {
            heatmap("embarked", "pclass") {
                borderLine {
                    width = 0.8
                    color = Color.BLACK
                }
                fillColor(Stat.count) {
                    scale = continuous(Color.WHITE..Color.RED)
                    legend.name = "number of\n passangers"
                }
            }
            y.axis.breaks(df["pclass"].distinct().toList(), format = "d")
        }
            // SampleEnd
            .saveSample()
    }

}
