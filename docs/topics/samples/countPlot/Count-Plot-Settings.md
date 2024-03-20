# Count Plot Settings

<web-summary>
Explore 'Count Plot Settings' in Kotlin using Kandy, showcasing various customizations and settings for count plots.
This example is ideal for learning how to tweak count plot appearances for more engaging and informative visualizations.
</web-summary>

<card-summary>
'Count Plot Settings' in Kotlin with Kandy: A detailed demonstration of the versatility in count plot customization,
perfect for tailoring visual representations to specific data storytelling needs.
</card-summary>

<link-summary>
Explore the 'Count Plot Settings' example using Kotlin and Kandy to understand the depth of customization available in count plot, from color adjustments to layout tweaks.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.CountPlot-->

<!---FUN countPlot_settings-->
<tabs>
<tab title="Dataframe">

```kotlin
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
```

</tab>
<tab title="Collections">

```kotlin
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
```

</tab></tabs>
<!---END-->


![Count Plot Settings](countPlot_settings.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/countPlot/countPlot_settings.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/R93sslqKTUeW5fXxYU22aj" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
