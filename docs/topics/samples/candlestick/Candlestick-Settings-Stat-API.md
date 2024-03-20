# Candlestick Settings Stat API

<web-summary>
Explore 'Candlestick Settings Stat API' in Kotlin using Kandy, showcasing various customizations 
and settings for candlestick chart plots. This example is ideal for learning how to customize
your plot with statistics API.
</web-summary>

<card-summary>
'Candlestick Settings Stat API' in Kotlin with Kandy: A detailed demonstration of the versatility in
candlestick chart customization with special statistical API.
</card-summary>

<link-summary>
Explore the 'Candlestick Settings Stat API' example using Kotlin and Kandy to understand the depth of 
customization available in candlestick chart plotting.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Candlestick-->

<!---FUN candlestick_settings_stat_api-->
<tabs>
<tab title="Dataframe">

```kotlin
val df = dataFrameOf(
    "year" to listOf("2018", "2019", "2020", "2021", "2022", "2023"),
    "open" to listOf(10.0, 15.0, 12.0, 18.0, 14.0, 16.0),
    "high" to listOf(18.0, 17.0, 20.0, 22.0, 18.0, 22.0),
    "low" to listOf(8.0, 10.0, 9.0, 11.0, 12.0, 15.0),
    "close" to listOf(15.0, 12.0, 18.0, 14.0, 16.0, 20.0),
)

df.plot {
    candlestick("year", "open", "high", "low", "close") {
        alpha(Stat.isIncreased) {
            scale = categorical(true to 1.0, false to 0.05)
            legend {
                name = ""
                breaksLabeled(true to "increase", false to "decrease")
            }
        }
        fillColor = Color.GREY
        borderLine.color = Color.GREY
    }
    x.axis {
        name = "Year"
        breaks(format = "d")
    }
    layout.size = 750 to 400
}
```

</tab>
<tab title="Collections">

```kotlin
val year = listOf("2018", "2019", "2020", "2021", "2022", "2023")
val opens = listOf(10.0, 15.0, 12.0, 18.0, 14.0, 16.0)
val highs = listOf(18.0, 17.0, 20.0, 22.0, 18.0, 22.0)
val lows = listOf(8.0, 10.0, 9.0, 11.0, 12.0, 15.0)
val closes = listOf(15.0, 12.0, 18.0, 14.0, 16.0, 20.0)

plot {
    candlestick(year, opens, highs, lows, closes) {
        alpha(Stat.isIncreased) {
            scale = categorical(true to 1.0, false to 0.05)
            legend {
                name = ""
                breaksLabeled(true to "increase", false to "decrease")
            }
        }
        fillColor = Color.GREY
        borderLine.color = Color.GREY
    }
    x.axis {
        name = "Year"
        breaks(format = "d")
    }
    layout.size = 750 to 400
}
```

</tab></tabs>
<!---END-->


![Candlestick Settings Stat Api](candlestick_settings_stat_api.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/candlestick/candlestick_settings_2.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/0VZzfzOogM3v1ePEqDdF4y" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
