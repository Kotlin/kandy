# German States Map With Simple Settings (Shapefile)

<web-summary>
Discover German states mapping in Kotlin using Kandy, a clear and straightforward example of geographical visualization.
This example demonstrates how to create a basic map using shapefile data, showing the effectiveness of simple styling settings.
</web-summary>

<card-summary>
German states mapping in Kotlin with Kandy: An elegant demonstration of geographical visualization using shapefile data and basic styling options.
</card-summary>

<link-summary>
Explore the German states mapping example using Kotlin and Kandy, showing how to create a clean geographical visualization with shapefile data.
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
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/geo/germany_map_settings_shapefile.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/7Kt3yTM4bQGTofFnKBaiUI" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
