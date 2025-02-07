# USA States Map Adjusted

<web-summary>
Discover map adjustments in Kotlin using Kandy, a clear and straightforward example of geographical data manipulation.
This example demonstrates how to reposition states like Alaska and Hawaii for better map visualization and readability.
</web-summary>

<card-summary>
USA states map adjustment in Kotlin with Kandy: An elegant demonstration of geometric transformations for improved map layout.
</card-summary>

<link-summary>
Explore the USA states map adjustment example using Kotlin and Kandy, showing how to enhance map readability through state repositioning.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.geo.samples.gallery.Geo-->

<!---FUN usa_adjusted-->

```kotlin
val usaStates =
    GeoDataFrame.readGeoJson("https://raw.githubusercontent.com/AndreiKingsley/datasets/refs/heads/main/USA.json")
val name by column<String>()
val geometry by column<Geometry>()

val usaAdjusted = usaStates.modify {
    update(geometry).where { name() == "Alaska" }.with {
        it.scaleAroundCenter(0.5).translate(40.0, -40.0)
    }
        // move Hawaii and Puerto Rico
        .update(geometry).where { name() == "Hawaii" }.with { it.translate(65.0, 0.0) }
        .update(geometry).where { name() == "Puerto Rico" }.with { it.translate(-10.0, 5.0) }
}

usaAdjusted.plot {
    geoMap()
}
```

<!---END-->

![usa_adjusted](usa_adjusted.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/area/simple_area.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/LmZB0wrcS6YNG09OENeQsH" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
