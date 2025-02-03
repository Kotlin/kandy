# Several Ribbons

<web-summary>
Explore 'Several Ribbons' in Kotlin using Kandy to see how multiple ribbon plots can be combined for comparative data analysis.
This example skillfully illustrates the overlaying of different currencies change data in a single chart for a comprehensive view.
</web-summary>

<card-summary>
'Several Ribbons' in Kotlin with Kandy: A demonstration of layering multiple ribbon plots, ideal for juxtaposing varied data sets in an insightful and visually appealing manner.
</card-summary>

<link-summary>
Dive into 'Several Ribbons' using Kotlin and Kandy, a sophisticated example of blending multiple ribbon plots, perfect for in-depth comparative data visualization.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Ribbon-->

<!---FUN ribbon_grouped-->
<tabs>
<tab title="Dataframe">

```kotlin
val times = listOf("00:00", "06:00", "12:00", "18:00", "24:00")
val btcMin = listOf(310, 225, 202, 278, 360)
val btcMax = listOf(334, 307, 243, 293, 388)
val ethMin = listOf(180, 205, 256, 300, 280)
val ethMax = listOf(210, 234, 299, 322, 331)

val dataset = dataFrameOf(
    "time" to times + times,
    "min" to btcMin + ethMin,
    "max" to btcMax + ethMax,
    "currency" to List(5) { "btc" } + List(5) { "eth" }
)

dataset.groupBy("currency").plot {
    ribbon {
        x("time")
        y.axis {
            limits = 170..400
            name = "price, tokens"
        }
        yMin("min")
        yMax("max")
        fillColor("currency") {
            legend.breaksLabeled("btc" to "Bubble Tea\nCoin", "eth" to "E-Traders\nHedgehogs")
        }
        alpha = 0.6
        borderLine.width = 0.0
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val times = listOf("00:00", "06:00", "12:00", "18:00", "24:00")
val btcMin = listOf(310, 225, 202, 278, 360)
val btcMax = listOf(334, 307, 243, 293, 388)
val ethMin = listOf(180, 205, 256, 300, 280)
val ethMax = listOf(210, 234, 299, 322, 331)

val dataset = mapOf(
    "time" to times + times,
    "min" to btcMin + ethMin,
    "max" to btcMax + ethMax,
    "currency" to List(5) { "btc" } + List(5) { "eth" }
)

dataset.plot {
    groupBy("currency") {
        ribbon {
            x("time")
            y {
                axis.limits = 170..400
                axis.name = "price, tokens"
            }
            yMin("min")
            yMax("max")
            fillColor("currency") {
                legend.breaksLabeled("btc" to "Bubble Tea\nCoin", "eth" to "E-Traders\nHedgehogs")
            }
            alpha = 0.6
            borderLine.width = 0.0
        }
    }
}
```

</tab></tabs>
<!---END-->

![Several Ribbons](ribbon_grouped.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/ribbon/ribbon_grouped.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/a7ZjlJrHqOFTCgVylDfdlL" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
