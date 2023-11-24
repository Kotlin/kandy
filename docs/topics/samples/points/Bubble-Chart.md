# Bubble Chart

<web-summary>
Explore 'Bubble Chart' in Kotlin using Kandy, a visually engaging way to represent multidimensional data.
This example demonstrates how bubble sizes can vary based on data values, adding an extra layer of information to the traditional scatter plot.
</web-summary>

<card-summary>
'Bubble Chart' in Kotlin with Kandy: An advanced scatter plot that uses bubble sizes to represent additional data dimensions,
perfect for a more nuanced data visualization.
</card-summary>

<link-summary>
Dive into 'Bubble Chart' using Kotlin and Kandy, where scatter plots are enhanced with variable-sized bubbles.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Points-->

<!---FUN bubble_chart-->

```kotlin
val dayOfWeek = listOf(
    "Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue", "Wed",
    "Thu", "Fri", "Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue", "Wed", "Thu"
)
val week = listOf(
    17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 18, 19, 19, 19,
    19, 19, 19, 19, 20, 20, 20, 20, 20, 20, 20, 21, 21, 21, 21
)
val contributions = listOf(
    2, 1, 7, 0, 0, 0, 5, 11, 4, 5, 0, 1, 3, 8, 1,
    6, 12, 1, 0, 0, 0, 15, 7, 3, 0, 1, 2, 5, 6, 3
)

plot {
    points {
        x(week) {
            axis.name = "Week"
        }
        y(dayOfWeek) {
            axis {
                breaks(listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun").reversed())
                name = "Day of week"
            }
        }
        color = Color.BLUE
        size(contributions) {
            scale = continuous(5.0..20.0, 1..15)
            legend.name = "Contributions"
        }
    }
    layout.title = "May GitHub contributions"
}
```

<!---END-->

![Bubble Chart](bubble_chart.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/points/bubble_chart.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/Llxl5L1tCDf0fvOSlm0ibe" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
