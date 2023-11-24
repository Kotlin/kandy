# Simple Bars

<web-summary>
Explore 'Simple Bars' in Kotlin using Kandy for a basic yet insightful demonstration of bar plotting.
This example effectively utilizes simple bar charts to present categorical data, making it an ideal starting point for beginners in data visualization.
</web-summary>

<card-summary>
'Simple Bars' in Kotlin with Kandy: An elementary and clear example of bar chart visualization, perfect for straightforward comparisons of categorical data.
</card-summary>

<link-summary>
Dive into 'Simple Bars' using Kotlin and Kandy, a foundational example of bar plotting,
demonstrating the ease and clarity of representing categorical data visually.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Bars-->

<!---FUN simple_bar_plot-->
<tabs>
<tab title="Dataframe">

```kotlin
val data = dataFrameOf(
    "city" to listOf("London", "Paris", "Berlin", "Madrid", "Rome"),
    "perc" to listOf(45, 50, 60, 40, 30)
)

data.plot {
    layout.title = "Public Transport Usage in European Cities"
    bars {
        x("city") { axis.name = "City" }
        y("perc") { axis.name = "Public Transport Usage (%)" }
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val city = listOf("London", "Paris", "Berlin", "Madrid", "Rome")
val perc = listOf(45, 50, 60, 40, 30)


plot {
    layout.title = "Public Transport Usage in European Cities"
    bars {
        x(city) { axis.name = "City" }
        y(perc) { axis.name = "Public Transport Usage (%)" }
    }
}
```

</tab></tabs>
<!---END-->

![Simple Bars](simple_bar_plot.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/bars/simple_bar.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/zqcG2BuEoeeo8Ce1XZFcJL" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
