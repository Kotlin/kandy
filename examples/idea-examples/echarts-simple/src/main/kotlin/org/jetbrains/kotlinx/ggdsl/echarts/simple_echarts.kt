package org.jetbrains.kotlinx.ggdsl.echarts

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.dsl.column.invoke
import org.jetbrains.kotlinx.ggdsl.echarts.aes.x
import org.jetbrains.kotlinx.ggdsl.echarts.features.label.LabelPosition
import org.jetbrains.kotlinx.ggdsl.echarts.features.label.label
import org.jetbrains.kotlinx.ggdsl.echarts.features.legend
import org.jetbrains.kotlinx.ggdsl.echarts.layers.bars
import org.jetbrains.kotlinx.ggdsl.echarts.layers.layout
import org.jetbrains.kotlinx.ggdsl.echarts.layers.line
import org.jetbrains.kotlinx.ggdsl.echarts.settings.Symbol
import org.jetbrains.kotlinx.ggdsl.echarts.settings.pct
import org.jetbrains.kotlinx.ggdsl.echarts.settings.px
import org.jetbrains.kotlinx.ggdsl.util.color.Color


public fun main() {
    val dataset = mapOf(
        "days_of_week" to listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
        "evaporation" to listOf(2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6),
        "precipitation" to listOf(2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6),
        "temp" to listOf(2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3)
    )

    // TODO save as HTML
    /*val simplePlot =*/
    plot(dataset) {
        x("days_of_week"<String>())


        bars {
            name("Precipitation")
            y("precipitation"<Double>().scaled(continuousPos(0.0 to 200.0)))
            color("temp"<Double>().scaled(continuous(rangeLimits = Color.GREY to Color.BLUE)))
            label {
                position = LabelPosition.TOP
                formatter = "{@precipitation} ml"
            }
        }

        line {
            name("Evaporation")
            y("evaporation"<Double>())
            symbol(Symbol.diamond(20.0))
        }

        layout {
            title.text = "Precipitation and evaporation per week"
            legend {
                left = 50.pct
                bottom = 0.px
            }
        }
    }
}
