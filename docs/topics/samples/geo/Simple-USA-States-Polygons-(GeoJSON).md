# Simple USA States Polygons (GeoJSON)

<web-summary>
Discover USA states mapping in Kotlin using Kandy, a clear and straightforward example of geographical visualization.
This example demonstrates how to create a basic map using GeoJSON data, showing the simplicity of polygon-based geographical representation.
</web-summary>

<card-summary>
USA states mapping in Kotlin with Kandy: An elegant demonstration of geographical visualization using GeoJSON data and polygon plotting.
</card-summary>

<link-summary>
Explore the USA states mapping example using Kotlin and Kandy, showing how to create a clean geographical visualization with GeoJSON data.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.geo.samples.gallery.Geo-->

<!---FUN usa_simple_poly_json-->

```kotlin
val usaStates =
    GeoDataFrame.readGeoJson("https://raw.githubusercontent.com/AndreiKingsley/datasets/refs/heads/main/USA.json")

usaStates.plot {
    geoPolygon()
}
```

<!---END-->

![usa_simple_poly_json](usa_simple_poly_json.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/geo/usa_simple_poly_json.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/XdMXS59RgEroqazyUZUwVl" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
