# USA States Map Adjusted

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

<!---FUN usa_adjusted-->

```kotlin
val usaStates =
    GeoDataFrame.readGeoJson("https://raw.githubusercontent.com/AndreiKingsley/datasets/refs/heads/main/USA.json")
val name by column<String>()
val geometry by column<Geometry>()

val usaAdjusted = usaStates.modify {
    // custom extensions for `Geometry` based on JTS API;
    // scale and move Alaska
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
