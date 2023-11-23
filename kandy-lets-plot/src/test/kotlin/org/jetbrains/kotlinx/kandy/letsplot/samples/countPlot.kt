package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.barsH
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.LegendType
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.jetbrains.kotlinx.statistics.kandy.layers.countPlot
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statCount
import kotlin.test.Test

class CountPlot : SampleHelper("countPlot") {

    @Test
    fun countPlot_simple_dataframe() {
        // SampleStart
        val dataframe = dataFrameOf(
            "categories" to listOf(
                "A", "B", "C", "C", "B",
                "A", "C", "B", "A", "B",
                "C", "A", "B", "A", "A",
                "C", "A", "A", "B", "C",
                "C", "A", "A", "C", "B",
                "C", "C", "A", "A", "A",
                "B", "C", "B", "A", "B",
                "C", "A", "A", "B", "A",
                "C", "A", "C", "A", "C"
            )
        )

        dataframe.plot {
            countPlot("categories")
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun countPlot_simple_collections() {
        // SampleStart
        val categories = listOf(
            "A", "B", "C", "C", "B",
            "A", "C", "B", "A", "B",
            "C", "A", "B", "A", "A",
            "C", "A", "A", "B", "C",
            "C", "A", "A", "C", "B",
            "C", "C", "A", "A", "A",
            "B", "C", "B", "A", "B",
            "C", "A", "A", "B", "A",
            "C", "A", "C", "A", "C"
        )

        plot {
            countPlot(categories)
        }
        // SampleEnd
    }

    @Test
    fun countPlot_settings_dataframe() {
        // SampleStart
        val classesDF = dataFrameOf(
            "classes" to listOf(
                "First", "Second", "Third", "Third", "Second",
                "Third", "First", "Second", "Third", "First",
                "Third", "Second", "Third", "First", "Second",
                "Third", "First", "Third", "Second", "Third",
                "First", "Second", "Third", "First", "Third",
                "Second", "Third", "First", "Second", "Third",
                "First", "Third", "Second", "Third", "First",
                "Second", "Third", "First", "Second", "Third"
            )
        )


        classesDF.plot {
            countPlot("classes") {
                alpha = 0.8
                fillColor(Stat.x) {
                    legend.type = LegendType.None
                }
                x.axis.name = "class"
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun countPlot_settings_collections() {
        // SampleStart
        val classes = listOf(
            "First", "Second", "Third", "Third", "Second",
            "Third", "First", "Second", "Third", "First",
            "Third", "Second", "Third", "First", "Second",
            "Third", "First", "Third", "Second", "Third",
            "First", "Second", "Third", "First", "Third",
            "Second", "Third", "First", "Second", "Third",
            "First", "Third", "Second", "Third", "First",
            "Second", "Third", "First", "Second", "Third"
        )


        plot {
            countPlot(classes) {
                alpha = 0.8
                fillColor(Stat.x) {
                    legend.type = LegendType.None
                }
                x.axis.name = "class"
            }
        }
        // SampleEnd
    }

    @Test
    fun countPlot_grouped() {
        // SampleStart
        val categories = listOf(
            "easy", "medium", "hard", "medium", "easy",
            "hard", "hard", "easy", "easy", "hard", "medium",
            "hard", "easy", "easy", "easy", "medium",
            "hard", "hard", "hard", "medium", "easy",
            "hard", "medium", "hard", "hard", "hard",
            "medium", "medium", "easy", "medium", "hard",
            "hard", "easy", "hard", "medium", "medium",
            "hard", "hard", "hard", "easy", "hard",
            "hard", "easy", "medium", "medium", "hard",
            "medium", "medium", "easy", "hard", "medium",
            "hard", "medium", "easy", "easy",
        )

        val years = listOf(
            "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023"
        )


        val df = dataFrameOf(
            "category" to categories,
            "year" to years
        )

        df.groupBy("category").plot {
            countPlot("year")
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun countPlot_horizontal_dataframe() {
        // SampleStart
        val transportsDF = dataFrameOf("transports" to listOf(
            "metro", "bicycle", "car", "bus", "bus", "bicycle", "bicycle", "bus", "bus", "bus",
            "bus", "bus", "bus", "bus", "bicycle", "bicycle", "bus", "bicycle", "bus", "car",
            "metro", "bus", "metro", "metro", "bus", "bus", "bus", "metro", "bicycle", "metro",
            "bus", "metro", "bicycle", "metro", "bicycle", "bicycle", "bus", "bicycle", "metro",
            "bicycle", "metro", "bicycle", "bus", "bicycle", "bus", "bicycle", "bicycle", "bicycle",
            "bus", "bicycle", "metro", "bus", "bicycle", "bus", "bus", "bus", "bus", "bus", "bus",
            "metro", "metro", "bicycle", "metro", "bus", "bus", "metro", "metro", "bicycle", "bus",
            "metro", "metro", "bicycle", "bus", "bus", "bicycle", "car", "bus", "bicycle", "bus",
            "metro", "bus", "metro", "bicycle", "metro", "bicycle", "bicycle", "bicycle", "bicycle"
        ))

        transportsDF.plot {
            statCount("transports") {
                val transport = Stat.x named "transport"
                barsH {
                    x(Stat.count)
                    y(transport)
                    fillColor(transport) {
                        scale = categorical(
                            "bus" to Color.hex("#FFD700"),
                            "car" to Color.hex("#FF6347"),
                            "bicycle" to Color.hex("#32CD32"),
                            "metro" to Color.hex("#4169E1")
                        )
                        legend.type = LegendType.None
                    }
                }
            }
            layout.title = "Distribution of transport used by students"
        }
        // SampleEnd
            .saveSample()
    }

    @Test
    fun countPlot_horizontal_collections() {
        // SampleStart
        val transports = listOf(
            "metro", "bicycle", "car", "bus", "bus", "bicycle", "bicycle", "bus", "bus", "bus",
            "bus", "bus", "bus", "bus", "bicycle", "bicycle", "bus", "bicycle", "bus", "car",
            "metro", "bus", "metro", "metro", "bus", "bus", "bus", "metro", "bicycle", "metro",
            "bus", "metro", "bicycle", "metro", "bicycle", "bicycle", "bus", "bicycle", "metro",
            "bicycle", "metro", "bicycle", "bus", "bicycle", "bus", "bicycle", "bicycle", "bicycle",
            "bus", "bicycle", "metro", "bus", "bicycle", "bus", "bus", "bus", "bus", "bus", "bus",
            "metro", "metro", "bicycle", "metro", "bus", "bus", "metro", "metro", "bicycle", "bus",
            "metro", "metro", "bicycle", "bus", "bus", "bicycle", "car", "bus", "bicycle", "bus",
            "metro", "bus", "metro", "bicycle", "metro", "bicycle", "bicycle", "bicycle", "bicycle"
        )

        plot {
            statCount(transports) {
                val transport = Stat.x named "transport"
                barsH {
                    x(Stat.count)
                    y(transport)
                    fillColor(transport) {
                        scale = categorical(
                            "bus" to Color.hex("#FFD700"),
                            "car" to Color.hex("#FF6347"),
                            "bicycle" to Color.hex("#32CD32"),
                            "metro" to Color.hex("#4169E1")
                        )
                        legend.type = LegendType.None
                    }
                }
            }
            layout.title = "Distribution of transport used by students"
        }
        // SampleEnd
    }
}
