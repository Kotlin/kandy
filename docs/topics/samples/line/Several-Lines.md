# Several Lines

<web-summary>
Explore the 'Several Lines' example in Kotlin with Kandy to see how multiple line plots can be effectively used for data comparison in a single graph.
</web-summary>

<card-summary>
Kotlin 'Several Lines' Example: Demonstrating the power of multi-line graphs for comparative data analysis with Kandy.
</card-summary>

<link-summary>
Delve into the 'Several Lines' Kotlin example using Kandy, showcasing the use of multiple lines in a single graph for enhanced data visualization.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Lines-->

<!---FUN several_lines-->
<tabs>
<tab title="Dataframe">

```kotlin
val months = listOf(1, 2, 3, 4, 5)
val salesProducts = listOf(200.0, 220.0, 180.0, 240.0, 210.0)
val salesClothes = listOf(150.0, 130.0, 160.0, 140.0, 170.0)
val salesElectronics = listOf(300.0, 320.0, 310.0, 330.0, 340.0)

val data = dataFrameOf(
    "month" to months + months + months,
    "sales" to salesProducts + salesClothes + salesElectronics,
    "category" to List(5) { "Products" } + List(5) { "Clothes" } + List(5) { "Electronics" }
)

data.groupBy("category").plot {
    line {
        x("month")
        y("sales")
        color("category")
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val months = listOf(1, 2, 3, 4, 5)
val salesProducts = listOf(200.0, 220.0, 180.0, 240.0, 210.0)
val salesClothes = listOf(150.0, 130.0, 160.0, 140.0, 170.0)
val salesElectronics = listOf(300.0, 320.0, 310.0, 330.0, 340.0)

val data = mapOf(
    "month" to months + months + months,
    "sales" to salesProducts + salesClothes + salesElectronics,
    "category" to List(5) { "Products" } + List(5) { "Clothes" } + List(5) { "Electronics" }
)

data.plot {
    groupBy("category") {
        line {
            x("month")
            y("sales")
            color("category")
        }
    }
}
```

</tab></tabs>
<!---END-->

![Several Lines](several_lines.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/line/several_lines.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/9qSVjZVKxM8VpVP5z5JS4E" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
