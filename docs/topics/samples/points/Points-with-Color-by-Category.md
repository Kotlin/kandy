# Points with Color by Category

<web-summary>
Discover 'Points with Color by Category' in Kotlin using Kandy, a scatter plot example that uses color coding to differentiate data categories.
This approach highlights the distribution and relationships between groups in a dataset, making it ideal for comparative analysis.
</web-summary>

<card-summary>
'Points with Color by Category' in Kotlin with Kandy: A scatter plot that brings clarity to data grouping through color differentiation,
perfect for visualizing multi-category datasets.
</card-summary>

<link-summary>
Explore 'Points with Color by Category' using Kandy, where color coding in scatter plots provides a clear distinction between data categories.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Points-->

<!---FUN points_with_color_by_category-->
<tabs>
<tab title="Dataframe">

```kotlin
val dataset = dataFrameOf(
    "xShot" to listOf(
        4.02, 5.24, 4.41, 3.99, 3.10, 4.73, 3.20, 6.53, 7.05, 2.81,
        5.80, 3.87, 4.16, 6.78, 0.52, 0.64, 0.15, 6.09, 5.70, 6.37
    ),
    "yShot" to listOf(
        2.39, 1.95, 1.13, 1.90, 0.29, 1.56, 0.35, 2.30, 1.27, 1.01,
        0.65, 1.89, 1.11, 1.39, 0.05, 1.51, 1.49, 1.51, 2.30, 1.66
    ),
    "outcome" to listOf(
        false, true, false, true, true, true, true, true, true, false,
        true, true, false, false, true, false, false, true, true, false
    )
)

dataset.plot {
    points {
        x("xShot") { axis.name = "Horizontal Position (meters)" }
        y("yShot") { axis.name = "Vertical Position (meters)" }
        size = 8.5
        color("outcome") {
            scale = categorical(
                true to Color.GREEN, false to Color.RED
            )
            legend {
                name = "Outcome"
                breaksLabeled(true to "Goal", false to "Miss")
            }
        }
    }
    layout.title = "Penalty Shot Outcomes Analysis"
}
```

</tab>
<tab title="Collections">

```kotlin
val xShot = listOf(
    4.02, 5.24, 4.41, 3.99, 3.10, 4.73, 3.20, 6.53, 7.05, 2.81,
    5.80, 3.87, 4.16, 6.78, 0.52, 0.64, 0.15, 6.09, 5.70, 6.37
)
val yShot = listOf(
    2.39, 1.95, 1.13, 1.90, 0.29, 1.56, 0.35, 2.30, 1.27, 1.01,
    0.65, 1.89, 1.11, 1.39, 0.05, 1.51, 1.49, 1.51, 2.30, 1.66
)
val outcome = listOf(
    false, true, false, true, true, true, true, true, true, false,
    true, true, false, false, true, false, false, true, true, false
)

val dataset = mapOf(
    "xShot" to xShot,
    "yShot" to yShot,
    "outcome" to outcome
)


plot(dataset) {
    points {
        x("xShot") { axis.name = "Horizontal Position (meters)" }
        y("yShot") { axis.name = "Vertical Position (meters)" }
        size = 8.5
        color("outcome") {
            scale = categorical(
                true to Color.GREEN, false to Color.RED
            )
            legend {
                name = "Outcome"
                breaksLabeled(true to "Goal", false to "Miss")
            }
        }
    }
    layout.title = "Penalty Shot Outcomes Analysis"
}
```

</tab></tabs>
<!---END-->

![Points with Color by Category](points_with_color_by_category.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/points/points_with_color_by_category.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/8Kilp33UJouST4D8oltcna" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
