# Tiles with Color by Category

<web-summary>
Discover the colorful insights of 'Tiles with Color by Category' in Kandy.
This example demonstrates the effective use of categorical colors in tile plots for enhanced data categorization.
</web-summary>

<card-summary>
Explore categorical color schemes in 'Tiles with Color by Category' using Kandy.
This example illustrates how different hues can categorize data in tile plots.
</card-summary>

<link-summary>
Learn about categorical color usage in tile plots with 'Tiles with Color by Category' in Kandy.
This example highlights how color enhances data differentiation.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Tiles-->

<!---FUN tiles_color_categories-->
<tabs>
<tab title="Dataframe">

```kotlin
val cities = listOf("Yerevan", "Berlin", "Amsterdam", "Paphos")
val types = listOf("A", "B", "C")
val random = kotlin.random.Random(42)
val year22 = List(4) { types.random(random) }
val year23 = List(4) { types.random(random) }
val year24 = List(4) { types.random(random) }

val dataset = dataFrameOf(
    "city" to cities,
    "2022" to year22,
    "2023" to year23,
    "2024" to year24
).gather("2022", "2023", "2024").into("year", "value")

plot(dataset) {
    tiles {
        x("city")
        y("year") {
            scale = categorical()
            axis.breaks(format = "d")
        }
        width = 0.5
        height = 0.9
        fillColor("value") {
            legend.breaks(types)
        }
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val types = listOf("A", "B", "C")
val cities = listOf("Yerevan", "Berlin", "Amsterdam", "Paphos")
val years = listOf(2022, 2023, 2024)
val random = kotlin.random.Random(42)
val tripples = cities.flatMap { city ->
    years.map { year -> (city to year) to types.random(random) }
}

val (cityToYear, value) = tripples.unzip()
val (city, year) = cityToYear.unzip()

plot {
    tiles {
        x(city)
        y(year) {
            scale = categorical()
            axis.breaks(format = "d")
        }
        width = 0.5
        height = 0.9
        fillColor(value) {
            legend.breaks(types)
        }
    }
}
```

</tab></tabs>
<!---END-->

![Tiles with Color by Category](tiles_color_categories.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/tiles/tiles_color_categories.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/En8gUkFpaA3Vd7ofVf8haX" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
