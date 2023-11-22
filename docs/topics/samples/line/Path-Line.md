# Path Line

<web-summary>
Discover the 'Path Line' example in Kotlin with Kandy, where a path line graph is used to illustrate the relationship between temperature and performance.
This example is ideal for visualizing trends in challenging conditions.
</web-summary>

<card-summary>
'Path Line' in Kotlin with Kandy: A graph that cleverly depicts performance changes against temperature, ideal for understanding trends in extreme conditions.
</card-summary>

<link-summary>
Explore the 'Path Line' example in Kotlin using Kandy to see how path lines can effectively represent performance metrics across varying temperatures.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Lines-->

<!---FUN path_line-->

```kotlin
val dist = listOf(100, 90, 80, 70, 60, 50, 40)
val temp = listOf(-45.5, -44.4, -40.0, -43.2, -41.5, -35.5, -39.9)

plot {
    layout {
        title = "Performance Dependency on Temperature"
        subtitle = "Analysis of Material Performance Decline at Extremely Low Temperatures"
        yAxisLabel = "Performance Measure"
        size = 600 to 550
    }
    path {
        y(dist)
        x(temp) {
            axis.name = "Temperature (°C)"
        }
        color = Color.BLUE
        type = LineType.LONGDASH
    }
}
```

<!---END-->

![Path Line](path_line.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/line/path_line.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/5zlpAPH1qFTqjEElTEeLSQ" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
