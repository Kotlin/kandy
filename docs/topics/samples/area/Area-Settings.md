# Area Settings

<web-summary>
Explore 'Area Settings' in Kotlin with Kandy to visualize server load throughout the day.
This example demonstrates the impactful use of area plots with customized settings.
</web-summary>

<card-summary>
'Area Settings' in Kotlin with Kandy: A vivid representation of daily server load using area plots,
showcasing how to customize plot aesthetics for enhanced readability.
</card-summary>

<link-summary>
Dive into 'Area Settings' using Kotlin and Kandy, where server load data is elegantly visualized in an area plot, revealing patterns of demand over time.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Area-->

<!---FUN area_settings-->
<tabs>
<tab title="Dataframe">

```kotlin
val loadServer = dataFrameOf(
    "time" to listOf("00:00", "03:00", "06:00", "09:00", "12:00", "15:00", "18:00", "21:00"),
    "load" to listOf(10, 5, 15, 50, 75, 60, 80, 40)
)
val time = column<String>("time")
val load = column<Int>("load")

loadServer.plot {
    layout.title = "Daily Server Load Dynamics"
    area {
        x(time) { axis.name = "Time" }
        y(load) {
            axis.name = "Load (%)"
            scale = continuous(0..100)
        }
        borderLine {
            color = Color.ORANGE
            type = LineType.DASHED
            width = 2.5
        }
        fillColor = Color.RED
        alpha = 0.7
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val time = listOf("00:00", "03:00", "06:00", "09:00", "12:00", "15:00", "18:00", "21:00")
val load = listOf(10, 5, 15, 50, 75, 60, 80, 40)

plot {
    layout.title = "Daily Server Load Dynamics"
    area {
        x(time) { axis.name = "Time" }
        y(load) { axis.name = "Load (%)" }
        borderLine {
            color = Color.ORANGE
            type = LineType.DASHED
            width = 2.5
        }
        fillColor = Color.RED
        alpha = 0.7
    }
}
```

</tab></tabs>
<!---END-->

![Area Settings](area_settings.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/area/area_settings.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/MpM9dVMcLvIlpqR8qjTXzn" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
