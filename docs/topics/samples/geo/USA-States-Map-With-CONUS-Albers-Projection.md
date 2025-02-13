# USA States Map With CONUS Albers Projection

<web-summary>
Discover CONUS Albers projection in Kotlin using Kandy, a clear and straightforward example of map projection application.
This example demonstrates how to create an optimized view of continental US states using the CONUS Albers projection.
</web-summary>

<card-summary>
Continental US mapping in Kotlin with Kandy: An elegant demonstration of the CONUS Albers projection for optimized state visualization.
</card-summary>

<link-summary>
Explore the CONUS Albers projection example using Kotlin and Kandy, showing how to create an accurate continental US states map.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.geo.samples.gallery.Geo-->

<!---FUN usa_conus_albers-->

```kotlin
val usaStates =
    GeoDataFrame.readGeoJson("https://raw.githubusercontent.com/AndreiKingsley/datasets/refs/heads/main/USA.json")

val usaAlbers = usaStates
    .modify {
        filter { "name"<String>() !in listOf("Alaska", "Hawaii", "Puerto Rico") }
    }
    .applyCrs(CRS.decode("EPSG:5070", true))

usaAlbers.plot {
    geoPolygon()
    layout.title = "US With CONUS Albers Projection"
}
```

<!---END-->

![usa_conus_albers](usa_conus_albers.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/geo/usa_conus_albers_json.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/8MhxNEJly32PtJlH5dJKES" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
