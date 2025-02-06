# German States Map With Simple Settings (Shapefile)

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

<!---FUN germany_map_settings_shapefile-->

```kotlin
val worldStates =
    GeoDataFrame.readShapefile("https://github.com/AndreiKingsley/datasets/raw/refs/heads/main/ne_10m_admin_1_states_provinces/ne_10m_admin_1_states_provinces.shp")

val germanStates = worldStates.modify {
    filter { "admin"<String>() == "Germany" }
}

germanStates.plot {
    geoMap {
        fillColor = Color.hex("#E0E8D3")

        borderLine {
            width = 0.35
            color = Color.hex("#567A56")
        }
    }
    layout {
        title = "Germany States"
        layout.style(Style.Void)
    }
}
```

<!---END-->

![germany_map_settings_shapefile](germany_map_settings_shapefile.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/area/simple_area.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/LmZB0wrcS6YNG09OENeQsH" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
