# Tiles Settings

<web-summary>
Enhance your heatmap skills with 'Tiles Settings' in Kandy.
This example showcases advanced techniques in tile plot customization for more informative and visually appealing data presentations.
</web-summary>

<card-summary>
Advance your heatmap capabilities with 'Tiles Settings' in Kandy, featuring in-depth customization options for tile plots.
</card-summary>

<link-summary>
Dive into sophisticated heatmap customizations with 'Tiles Settings' in Kandy, perfect for elevating your data visualization techniques.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Tiles-->

<!---FUN tiles_settings-->
<tabs>
<tab title="Dataframe">

```kotlin
val data = dataFrameOf(
    "store" to listOf("A", "B", "C", "A", "B", "C", "A", "B", "C"),
    "time" to listOf(
        "morning", "morning", "morning",
        "afternoon", "afternoon", "afternoon",
        "evening", "evening", "evening"
    ),
    "money" to listOf(75, 64, 59, 82, 88, 91, 69, 77, 73)
)

data.plot {
    tiles {
        x("store")
        y("time")
        height = 0.7
        borderLine {
            width = 0.8
            color = Color.BLACK
        }
        fillColor("money") {
            scale = continuous(Color.RED..Color.GREEN)
        }
        alpha = 0.5
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val store = listOf("A", "B", "C", "A", "B", "C", "A", "B", "C")
val time = listOf(
    "morning", "morning", "morning",
    "afternoon", "afternoon", "afternoon",
    "evening", "evening", "evening"
)
val money = listOf(75, 64, 59, 82, 88, 91, 69, 77, 73)

plot {
    tiles {
        x(store)
        y(time)
        height = 0.7
        borderLine {
            width = 0.8
            color = Color.BLACK
        }
        fillColor(money) {
            scale = continuous(Color.RED..Color.GREEN)
        }
        alpha = 0.5
    }
}
```

</tab></tabs>
<!---END-->

![Tiles Settings](tiles_settings.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/tiles/tiles_settings.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/oL9KrIPXv0nJPfp62Yrv26" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
