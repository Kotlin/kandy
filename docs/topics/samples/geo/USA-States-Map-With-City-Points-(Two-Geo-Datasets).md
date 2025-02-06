# USA States Map With City Points (Two Geo Datasets)

<web-summary>
Discover 'Simple Area' in Kotlin using Kandy, a clear and straightforward example of area plotting.
This example visualizes cost data over several years, demonstrating the effectiveness of area charts in data representation.
</web-summary>

<card-summary>
'Simple Area' in Kotlin with Kandy: An elegant demonstration of area plots, showcasing yearly cost trends in a visually intuitive manner.
</card-summary>

<link-summary>
Explore the 'Simple Area' example using Kotlin and Kandy, a perfect illustration of how area charts can be used to depict trends over time.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.geo.samples.gallery.Geo-->

<!---FUN usa_with_cities-->

```kotlin
val usaStates =
    GeoDataFrame.readGeoJson("https://raw.githubusercontent.com/AndreiKingsley/datasets/refs/heads/main/USA.json")
val name by column<String>()

val usaCities =
    GeoDataFrame.readGeoJson("https://github.com/AndreiKingsley/datasets/raw/refs/heads/main/USA_cities.json")
val pop_min by column<Int>()

usaStates.modify { filter { name() !in listOf("Alaska", "Hawaii", "Puerto Rico") }
}.plot {
    geoMap() {
        fillColor = Color.hex("#E4F1FE")
        alpha = 0.7
        borderLine.color = Color.hex("#2A5D78")
    }
    withData(usaCities) {
        geoPoints {
            color = Color.hex("#FF6F61")
            alpha = 0.95
            size(pop_min) {
                scale = continuous(4.0..18.0)
                legend.type = LegendType.None
            }

            tooltips(name, pop_min)
        }
    }

    layout {
        style(Style.Void)
    }
}
```

<!---END-->

![usa_with_cities](usa_with_cities.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/area/simple_area.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/LmZB0wrcS6YNG09OENeQsH" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
