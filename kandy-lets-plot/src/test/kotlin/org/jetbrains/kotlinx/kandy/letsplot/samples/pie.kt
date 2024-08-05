package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.style.Style
import org.jetbrains.kotlinx.kandy.letsplot.layers.*
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.LegendType
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statCount
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.test.Test

class Pie : SampleHelper("pie") {

    @Test
    fun pie_base_dataframe() {
        // SampleStart
        val value by columnOf(15, 22, 40, 7, 31)
        val type by columnOf("A", "B", "C", "A", "D")
        val df = dataFrameOf(value, type)

        df.plot {
            pie {
                slice(value)
                fillColor(type)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun pie_base_collections() {
        // SampleStart
        val value = listOf(15, 22, 40, 7, 31)
        val type = listOf("A", "B", "C", "A", "D")

        plot {
            pie {
                slice(value)
                fillColor(type, "type")
            }
        }
        // SampleEnd
    }

    @Test
    fun pie_settings_dataframe() {
        // SampleStart
        val language by columnOf("Kotlin", "Java", "C++", "JavaScript", "C#", "Other")
        val users by columnOf(563, 481, 202, 406, 150, 312)
        val usersLanguages = dataFrameOf(language, users)

        usersLanguages.plot {
            pie {
                slice(users)
                fillColor(language) {
                    scale = categorical(
                        "Kotlin" to Color.hex("#1E88E5"),
                        "Java" to Color.hex("#D32F2F"),
                        "C++" to Color.hex("#7B1FA2"),
                        "JavaScript" to Color.hex("#FBC02D"),
                        "C#" to Color.hex("#388E3C"),
                        "Other" to Color.hex("#757575")
                    )
                }
                size = 33.0
                hole = 0.8
                alpha = 0.8
            }
            layout.style(Style.Void)
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun pie_settings_collections() {
        // SampleStart
        val language = listOf("Kotlin", "Java", "C++", "JavaScript", "C#", "Other")
        val users = listOf(563, 481, 202, 406, 150, 312)

        plot {
            pie {
                slice(users)
                fillColor(language) {
                    scale = categorical(
                        "Kotlin" to Color.hex("#1E88E5"),
                        "Java" to Color.hex("#D32F2F"),
                        "C++" to Color.hex("#7B1FA2"),
                        "JavaScript" to Color.hex("#FBC02D"),
                        "C#" to Color.hex("#388E3C"),
                        "Other" to Color.hex("#757575")
                    )
                }
                size = 33.0
                hole = 0.8
                alpha = 0.8
            }
            layout.style(Style.Void)
        }
        // SampleEnd
    }

    @Test
    fun pie_with_void_dataframe() {
        // SampleStart
        val platform by columnOf("Linux", "MacOS", "Windows")
        val count by columnOf(30, 239, 566)
        val df = dataFrameOf(platform, count)

        df.plot {
            pie {
                slice(count)
                fillColor(platform)
            }
            layout {
                style(Style.Void)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun pie_with_void_collections() {
        // SampleStart
        val platform = listOf("Linux", "MacOS", "Windows")
        val count = listOf(30, 239, 566)

        plot {
            pie {
                slice(count)
                fillColor(platform)
            }
            layout {
                style(Style.Void)
            }
        }
        // SampleEnd
    }

    @Test
    fun pie_explode_dataframe() {
        // SampleStart
        val range by columnOf("0-10m", "10-20m", "20-40m", "40-100m", "100-250m", ">250m",)
        val share by columnOf(0.42, 0.23, 0.15, 0.11, 0.06, 0.03)
        val explode by columnOf(0.20, 0.0, 0.04, 0.08, 0.12, 0.16)
        val df = dataFrameOf(range, share, explode)

        df.plot {
            pie {
                slice(share)
                fillColor(range) {
                    scale = continuous(Color.RED..Color.LIGHT_GREEN)
                }
                explode(explode)
                size = 25.0
            }
            layout {
                style(Style.Void)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun pie_explode_collections() {
        // SampleStart
        val range = listOf("0-10m", "10-20m", "20-40m", "40-100m", "100-250m", ">250m",)
        val share = listOf(0.42, 0.23, 0.15, 0.11, 0.06, 0.03)
        val explode = listOf(0.20, 0.0, 0.04, 0.08, 0.12, 0.16)

        plot {
            pie {
                slice(share)
                fillColor(range, "range") {
                    scale = continuous(Color.RED..Color.LIGHT_GREEN)
                }
                explode(explode)
                size = 25.0
            }
            layout {
                style(Style.Void)
            }
        }
        // SampleEnd
    }

    @Test
    fun nightingale_chart_dataframe() {
        // SampleStart
        val month by columnOf("Jan", "Feb", "Mar", "May", "Apr")
        val amount by columnOf(34.4, 25.1, 33.6, 20.0, 15.9)
        val df = dataFrameOf(month, amount)

        df.plot {
            pie {
                slice(amount)
                fillColor(month)
                size(amount) {
                    scale = continuous(10.0..25.0)
                    legend.type = LegendType.None
                }
            }
            layout {
                style(Style.Void)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun nightingale_chart_collections() {
        // SampleStart
        val month = listOf("Jan", "Feb", "Mar", "May", "Apr")
        val amount = listOf(34.4, 25.1, 33.6, 20.0, 15.9)

        plot {
            pie {
                slice(amount)
                fillColor(month, "month")
                size(amount) {
                    scale = continuous(10.0..25.0)
                    legend.type = LegendType.None
                }
            }
            layout {
                style(Style.Void)
            }
        }
        // SampleEnd
    }

    @Test
    fun pie_with_count_dataframe() {
        // SampleStart
        val continent by columnOf(
            "EU", "AF", "SA", "OC", "EU", "AF", "SA", "AF", "AS", "SA",
            "OC", "OC", "SA", "NA", "AF", "NA", "EU", "AF", "OC", "SA",
            "AF", "SA", "OC", "EU", "AF"
        )
        val df = dataFrameOf(continent)

        df.plot {
            statCount(continent) {
                pie {
                    slice(Stat.count)
                    fillColor(Stat.x named "continent")
                    size = 25.0
                }
            }
            layout {
                style(Style.Void)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun pie_with_count_collections() {
        // SampleStart
        val continent = listOf(
            "EU", "AF", "SA", "OC", "EU", "AF", "SA", "AF", "AS", "SA",
            "OC", "OC", "SA", "NA", "AF", "NA", "EU", "AF", "OC", "SA",
            "AF", "SA", "OC", "EU", "AF"
        )

        plot {
            statCount(continent) {
                pie {
                    slice(Stat.count)
                    fillColor(Stat.x named "continent")
                    size = 25.0
                }
            }
            layout {
                style(Style.Void)
            }
        }
        // SampleEnd
    }
}
