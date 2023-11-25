# Subtitle And Caption

<web-summary>
Explore 'Subtitle And Caption' in Kotlin using Kandy to add additional plot inscriptions.
</web-summary>

<card-summary>
'Subtitle And Caption' in Kotlin with Kandy: supplement plot layout settings - subtitle and caption.
</card-summary>

<link-summary>
Dive into 'Subtitle And Caption' using Kotlin and Kandy, a secondary plot notations.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Layout-->

<!---FUN subtitle_and_caption-->

```kotlin
plot {
    bars {
        x(listOf("a", "b", "c", "d", "e"))
        y(listOf(15, 14, 17, 19, 10))
    }
    layout {
        title = "Plot title\nwith a line break"
        subtitle = "Plot subtitle"
        caption = "Plot caption"
        size = 700 to 350
    }
}
```

<!---END-->


![Subtitle And Caption](subtitle_and_caption.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/layout/subtitle_and_caption.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/UKufIkDLK2rb0eeWqgQ1S1" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
