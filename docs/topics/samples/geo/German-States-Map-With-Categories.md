# German States Map With Categories

<web-summary>
Discover categorical mapping in Kotlin using Kandy, a clear and straightforward example of geographical data visualization.
This example shows how to create a German states map with categorical data, demonstrating effective use of colors for data representation.
</web-summary>

<card-summary>
German states categorical mapping in Kotlin with Kandy: An elegant demonstration of geographical visualization with color-coded categories.
</card-summary>

<link-summary>
Explore the German states categorical mapping example using Kotlin and Kandy, showing how to visualize geographical data with categories.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.geo.samples.gallery.Geo-->

<!---FUN germany_map_categories-->

```kotlin
val worldStates =
    GeoDataFrame.readShapefile("https://github.com/AndreiKingsley/datasets/raw/refs/heads/main/ne_10m_admin_1_states_provinces/ne_10m_admin_1_states_provinces.shp")

val germanStates = worldStates.modify {
    filter { "admin"<String>() == "Germany" }
}

germanStates.plot {
    geoMap {
        fillColor("name") {
            legend {
                name = "State"
                type = LegendType.DiscreteLegend(nCol = 2)
            }
        }

        borderLine {
            width = 0.5
            color = Color.BLACK
        }
    }
    layout {
        title = "Germany States"
        size = 900 to 600
        layout.style(Style.Void)
    }
}
```

<!---END-->

![germany_map_categories](germany_map_categories.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/area/simple_area.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/LmZB0wrcS6YNG09OENeQsH" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
