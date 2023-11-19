# Several Areas

<web-summary>
Explore 'Several Areas' in Kotlin using Kandy to see how multiple area plots can be combined for comparative data analysis.
This example skillfully illustrates the overlaying of different data sets in a single chart for a comprehensive view.
</web-summary>

<card-summary>
'Several Areas' in Kotlin with Kandy: A demonstration of layering multiple area plots, ideal for juxtaposing varied data sets in an insightful and visually appealing manner.
</card-summary>

<link-summary>
Dive into 'Several Areas' using Kotlin and Kandy, a sophisticated example of blending multiple area plots, perfect for in-depth comparative data visualization.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Area-->

<!---FUN several_areas-->
<tabs>
<tab title="Dataframe">

```kotlin
val data = dataFrameOf(
    "year" to listOf("2016", "2017", "2018", "2019", "2020", "2021"),
    "Apple" to listOf(700, 800, 750, 900, 850, 950),
    "Google" to listOf(1000, 950, 1200, 1150, 1250, 1300),
    "Microsoft" to listOf(600, 700, 650, 700, 750, 800),
    "Meta" to listOf(1100, 1200, 1150, 1300, 1250, 1350),
    "Amazon" to listOf(300, 400, 350, 450, 500, 600)
).gather("Apple", "Google", "Microsoft", "Meta", "Amazon").into("company", "users")

data.groupBy("company").plot {
    layout.title = "User Growth Dynamics"
    area {
        x("year")
        y("users")
        fillColor("company") {
            scale = categorical(
                "Apple" to Color.hex("#FF45ED"),
                "Google" to Color.hex("#3DEA62"),
                "Microsoft" to Color.BLACK,
                "Meta" to Color.hex("#FDB60D"),
                "Amazon" to Color.hex("#087CFA")
            )
        }
        borderLine.color("company")
        alpha = 0.3
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val year = listOf("2016", "2017", "2018", "2019", "2020", "2021")

val usersApple = listOf(700, 800, 750, 900, 850, 950)
val usersGoogle = listOf(1000, 950, 1200, 1150, 1250, 1300)
val usersMicrosoft = listOf(600, 700, 650, 700, 750, 800)
val usersMeta = listOf(1100, 1200, 1150, 1300, 1250, 1350)
val usersAmazon = listOf(300, 400, 350, 450, 500, 600)

val data = mapOf(
    "year" to year + year + year + year + year,
    "users" to usersApple + usersGoogle + usersMicrosoft + usersMeta + usersAmazon,
    "company" to List(6) { "Apple" } + List(6) { "Google" } + List(6) { "Microsoft" } + List(6) { "Meta" } + List(
        6
    ) { "Amazon" }
)

plot(data) {
    layout.title = "User Growth Dynamics"
    groupBy("company") {
        area {
            x("year")
            y("users")
            fillColor("company") {
                scale = categorical(
                    "Apple" to Color.hex("#FF45ED"),
                    "Google" to Color.hex("#3DEA62"),
                    "Microsoft" to Color.BLACK,
                    "Meta" to Color.hex("#FDB60D"),
                    "Amazon" to Color.hex("#087CFA")
                )
            }
            borderLine.color("company")
            alpha = 0.3
        }
    }
}
```

</tab></tabs>
<!---END-->

![Several Areas](several_areas.png) { border-effect="rounded" }

[//]: # (TODO)
<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/area/several_areas.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/NFGYJFW8oMlsu5aROAxRGq" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
