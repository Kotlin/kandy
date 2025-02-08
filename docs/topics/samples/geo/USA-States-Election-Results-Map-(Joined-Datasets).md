# USA States Election Results Map (Joined Datasets)

<web-summary>
Discover election results visualization in Kotlin using Kandy, a comprehensive example of geographical data analysis.
This example demonstrates joining electoral and geographical datasets to create an informative color-coded states map.
</web-summary>

<card-summary>
USA election results mapping in Kotlin with Kandy: An elegant demonstration of data joining and visualization using color-coded states.
</card-summary>

<link-summary>
Explore the USA election results mapping example using Kotlin and Kandy, showing how to combine and visualize electoral data geographically.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.geo.samples.gallery.Geo-->

<!---FUN usa_election_results_joined-->

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

val usa2024electionResults =
    DataFrame.readCSV("https://gist.githubusercontent.com/AndreiKingsley/348687222aecc4f0eb39e3d81acd515b/raw/a9914352dbdfb426f9146dda633ee382d936b000/usa_2024_election_states.csv")
val winner by column<String>()

val usaStatesWithElectionResults = usaAdjusted.modify {
    innerJoin(usa2024electionResults) { name }
}

usaStatesWithElectionResults.plot {
    geoMap {
        fillColor(winner) {
            scale = categorical(
                "Republican" to Color.hex("#CC3333"),
                "Democrat" to Color.hex("#3366CC")
            )
        }
        tooltips(name, winner)
    }
    layout {
        title = "USA 2024 President Election Results"
        size = 700 to 500
        style(Style.Void) {
            legend.position = LegendPosition.Top
        }
    }
}
```

<!---END-->

![usa_election_results_joined](usa_election_results_joined.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/geo/usa_election_results_joined.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/Fc7nLX7nMuDYJ0My2XZqfB" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
