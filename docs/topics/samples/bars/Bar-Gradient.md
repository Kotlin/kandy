# Bar Gradient

<web-summary>
Explore the 'Bar Gradient' example in Kotlin using Kandy, a visually striking demonstration of using color gradients in bar plots.
This example illustrates how gradients can be effectively used to represent varying data intensities or categories within a bar chart, enhancing both aesthetics and data interpretation.
</web-summary>

<card-summary>
'Bar Gradient' in Kotlin with Kandy:
A vivid portrayal of data using color gradients in bar charts, perfect for adding a visual depth to categorical comparisons.
</card-summary>

<link-summary>
Dive into 'Bar Gradient' using Kotlin and Kandy, showcasing the dynamic use of color gradients in bar plotting to enrich data visualization and provide more nuanced insights.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Bars-->

<!---FUN bar_gradient-->
<tabs>
<tab title="Dataframe">

```kotlin
val cities by columnOf("London", "Paris", "Berlin", "Madrid", "Rome", "Amsterdam", "Prague")
val airPollution by columnOf(70, 65, 50, 60, 55, 45, 53)
val numberOfCars by columnOf(3000, 2800, 1800, 2500, 2100, 1300, 2000)

val data = dataFrameOf(cities, airPollution, numberOfCars)

data.plot {
    layout.title = "Air Pollution and Vehicle Count Analysis"
    bars {
        x(cities) { axis.name = "City" }
        y(numberOfCars) { axis.name = "Number of cars (thousands)" }
        fillColor(airPollution) {
            legend.name = "Air Pollution\n Level (AQI)"
            scale = continuous(Color.GREEN..Color.RED)
        }
        alpha = 0.8
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val data = mapOf(
    "city" to listOf("London", "Paris", "Berlin", "Madrid", "Rome", "Amsterdam", "Prague"),
    "airPollution" to listOf(70, 65, 55, 60, 50, 45, 52),
    "numberOfCars" to listOf(3000, 2800, 2000, 2500, 2200, 1500, 1800)
)

data.plot {
    bars {
        x("city") { axis.name = "City" }
        y("numberOfCars") { axis.name = "Number of cars (thousands)" }
        fillColor("airPollution") {
            legend.name = "Air Pollution\n Level (AQI)"
            scale = continuous(Color.GREEN..Color.RED)
        }
        alpha = 0.8
    }

}
```

</tab></tabs>
<!---END-->

![Bar Gradient](bar_gradient.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/bars/bar_gradient.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/b1a0jHEcdyrG3sIyzWN2nJ" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
