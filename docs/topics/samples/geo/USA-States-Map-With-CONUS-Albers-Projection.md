# USA States Map With CONUS Albers Projection

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

<!---FUN usa_conus_albers-->

```kotlin
val usaStates =
    GeoDataFrame.readGeoJson("https://raw.githubusercontent.com/AndreiKingsley/datasets/refs/heads/main/USA.json")

val usaAlbers = usaStates
    .modify { filter { "name"<String>() !in listOf("Alaska", "Hawaii", "Puerto Rico") }
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
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/area/simple_area.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/LmZB0wrcS6YNG09OENeQsH" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
