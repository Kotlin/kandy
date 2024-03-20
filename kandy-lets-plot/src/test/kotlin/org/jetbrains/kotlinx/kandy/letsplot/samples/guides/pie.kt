package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.pie
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotGrid
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.scales.BrewerPalette
import org.jetbrains.kotlinx.kandy.letsplot.scales.categoricalColorBrewer
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.letsplot.style.Style
import kotlin.test.Test
import kotlin.test.assertNotNull

class Pie : SampleHelper("geoms", "guides") {

    private val blankStyle = Style.createCustom {
        global.line {
            blank = true
        }
        blankAxes()
    }

    private val dataset = dataFrameOf(
        "name" to listOf("a", "b", "c", "d", "b"),
        "value" to listOf(40, 90, 10, 50, 20)
    )

    private val length = dataFrameOf(
        "name" to listOf(
            "20-50 km",
            "50-75 km",
            "10-20 km",
            "75-100 km",
            "3-5 km",
            "7-10 km",
            "5-7 km",
            ">100 km",
            "2-3 km"
        ),
        "count" to listOf(1109, 696, 353, 192, 168, 86, 74, 65, 53),
        "explode" to listOf(.0, .0, .0, .1, .1, .2, .3, .4, .6)
    )

    private val calories = dataFrameOf(
        "slice" to listOf(35, 25, 25, 15),
        "label" to listOf("Apples", "Bananas", "Cherries", "Dates"),
        "explode" to listOf(.1, .0, .0, .0)
    )

    private val name = column<String>("name")
    private val value = column<Int>("value")
    private val count = column<Int>("count")
    private val explode = column<Double>("explode")
    private val slice = column<Int>("slice")
    private val label = column<String>("label")


    @Test
    fun guidePieBlankStyle() {
        // SampleStart
        val blankStyle = Style.createCustom {
            global.line {
                blank = true
            }
            blankAxes()
        }
        // SampleEnd
        assertNotNull(blankStyle.global.line?.blank)
    }

    @Test
    fun guidePieData() {
        // SampleStart
        val dataset = dataFrameOf(
            "name" to listOf('a', 'b', 'c', 'd', 'b'),
            "value" to listOf(40, 90, 10, 50, 20)
        )
        // SampleEnd
        assertNotNull(dataset["name"])
        assertNotNull(dataset["value"])
    }

    @Test
    fun guidePieBasicPieChart() {
        // SampleStart
        dataset.plot {
            pie {
                slice(value)
                fillColor(name)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guidePieCustomizedPieChart() {
        // SampleStart
        dataset.plot {
            pie {
                slice(value)
                fillColor(name) {
                    scale = categoricalColorBrewer(BrewerPalette.Qualitative.Set1)
                }
                size = 20.0
                stroke = 1.0
                strokeColor = Color.WHITE
                hole = 0.5
            }
            layout {
                style(blankStyle)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guidePieLengthData() {
        // SampleStart
        val length = dataFrameOf(
            "name" to listOf(
                "20-50 km",
                "50-75 km",
                "10-20 km",
                "75-100 km",
                "3-5 km",
                "7-10 km",
                "5-7 km",
                ">100 km",
                "2-3 km"
            ),
            "count" to listOf(1109, 696, 353, 192, 168, 86, 74, 65, 53),
            "explode" to listOf(.0, .0, .0, .1, .1, .2, .3, .4, .6)
        )
        // SampleEnd
        assertNotNull(length["name"])
        assertNotNull(length["count"])
        assertNotNull(length["explode"])
    }

    @Test
    fun guidePieExplodePieChart() {
        // SampleStart
        length.plot {
            pie {
                fillColor(name) {
                    scale = continuous(Color.named("dark_blue"), Color.LIGHT_GREEN)
                }
                slice(count)
                explode(explode)
                stroke = 1.0
                strokeColor = Color.BLACK
                size = 20.0
            }
            layout.style(blankStyle)
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guidePieCaloriesData() {
        // SampleStart
        val calories = dataFrameOf(
            "slice" to listOf(35, 25, 25, 15),
            "label" to listOf("Apples", "Bananas", "Cherries", "Dates"),
            "explode" to listOf(.1, .0, .0, .0)
        )
        // SampleEnd
        assertNotNull(calories["slice"])
        assertNotNull(calories["label"])
        assertNotNull(calories["explode"])
    }

    @Test
    fun guidePieChartsInPlotGrid() {
        // SampleStart
        plotGrid(
            listOf(
                calories.plot {
                    pie {
                        slice(slice)
                        explode(explode)
                        fillColor(label) {
                            scale = categoricalColorBrewer(BrewerPalette.Qualitative.Set1)
                        }
                        size = 15.0
                    }
                    layout {
                        style(blankStyle)
                    }
                },
                calories.plot {
                    pie {
                        slice(slice)
                        explode(explode)
                        fillColor(label) {
                            scale = categoricalColorBrewer(BrewerPalette.Qualitative.Set1)
                        }
                        size = 15.0
                        hole = 0.8
                    }
                    layout {
                        style(blankStyle)
                    }
                }
            )
        )
            // SampleEnd
            .saveSample()
    }
}