# Count Plot Horizontal

<web-summary>
Explore 'Count Plot Horizontal' in Kotlin using Kandy, showcasing building count plot with horizontal orientation.
This example is ideal for learning how to build a count plot using horizontal bars.
</web-summary>

<card-summary>
'Count Plot Horizontal' in Kotlin with Kandy: A detailed demonstration of the building a custom count plot with horizontal orientation.
</card-summary>

<link-summary>
Explore the 'Count Plot Horizontal' example using Kotlin and Kandy with custom count plot building for horizontal orientation.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.CountPlot-->

<!---FUN countPlot_horizontal-->
<tabs>
<tab title="Dataframe">

```kotlin
val transportsDF = dataFrameOf(
    "transports" to listOf(
        "metro", "bicycle", "car", "bus", "bus", "bicycle", "bicycle",
        "bus", "bus", "bus", "bus", "bus", "bus", "bus", "bicycle", "bicycle",
        "bus", "bicycle", "bus", "car", "metro", "bus", "metro", "metro",
        "bus", "bus", "bus", "metro", "bicycle", "metro", "bus", "metro",
        "bicycle", "metro", "bicycle", "bicycle", "bus", "bicycle", "metro",
        "bicycle", "metro", "bicycle", "bus", "bicycle", "bus", "bicycle",
        "bicycle", "bicycle", "bus", "bicycle", "metro", "bus", "bicycle",
        "bus", "bus", "bus", "bus", "bus", "bus", "metro", "metro", "bicycle",
        "metro", "bus", "bus", "metro", "metro", "bicycle", "bus", "metro",
        "metro", "bicycle", "bus", "bus", "bicycle", "car", "bus", "bicycle",
        "bus", "metro", "bus", "metro", "bicycle", "metro", "bicycle", "bicycle"
    )
)

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
```

</tab>
<tab title="Collections">

```kotlin
val transports = listOf(
    "metro", "bicycle", "car", "bus", "bus", "bicycle", "bicycle",
    "bus", "bus", "bus", "bus", "bus", "bus", "bus", "bicycle", "bicycle",
    "bus", "bicycle", "bus", "car", "metro", "bus", "metro", "metro",
    "bus", "bus", "bus", "metro", "bicycle", "metro", "bus", "metro",
    "bicycle", "metro", "bicycle", "bicycle", "bus", "bicycle", "metro",
    "bicycle", "metro", "bicycle", "bus", "bicycle", "bus", "bicycle",
    "bicycle", "bicycle", "bus", "bicycle", "metro", "bus", "bicycle",
    "bus", "bus", "bus", "bus", "bus", "bus", "metro", "metro", "bicycle",
    "metro", "bus", "bus", "metro", "metro", "bicycle", "bus", "metro",
    "metro", "bicycle", "bus", "bus", "bicycle", "car", "bus", "bicycle",
    "bus", "metro", "bus", "metro", "bicycle", "metro", "bicycle", "bicycle"
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
```

</tab></tabs>
<!---END-->


![Count Plot Horizontal](countPlot_settings.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/countPlot/countPlot_horizontal.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/aY1UtfN63NviI1AlftUo83" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
