# Stacked Bars

<web-summary>
Explore 'Stacked Bars' in Kotlin using Kandy to see how data segments can be stacked in bar plots for cumulative analysis.
This example is perfect for visualizing how different components contribute to a total within each category, providing a layered understanding of the data.
</web-summary>

<card-summary>
'Stacked Bars' in Kotlin with Kandy: A dynamic approach to bar chart visualization that stacks data segments,
offering an insightful view into how individual parts make up a whole.
</card-summary>

<link-summary>
Dive into 'Stacked Bars' using Kotlin and Kandy, where bar plots are transformed by stacking data layers.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Bars-->

<!---FUN stacked_bars-->
<tabs>
<tab title="Dataframe">

```kotlin
val data = dataFrameOf(
    "day" to listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
    "coffee" to listOf(0.81, 0.78, 0.72, 0.65, 0.73, 0.49, 0.38),
    "tea" to listOf(0.12, 0.16, 0.21, 0.26, 0.24, 0.22, 0.30),
    "soda" to listOf(0.07, 0.06, 0.07, 0.09, 0.03, 0.29, 0.32),
).gather("coffee", "tea", "soda").into("drink", "amount")

data.groupBy("drink").plot {
    layout.title = "Weekly Beverage Consumption Trends"
    bars {
        x("day")
        y("amount")
        fillColor("drink") {
            scale = categorical(
                "coffee" to Color.hex("#6F4E37"),
                "tea" to Color.hex("#C2D4AB"),
                "soda" to Color.hex("#B5651D")
            )
        }
        position = Position.stack()
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
val coffee = listOf(0.81, 0.78, 0.72, 0.65, 0.73, 0.49, 0.38)
val tea = listOf(0.12, 0.16, 0.21, 0.26, 0.24, 0.22, 0.30)
val soda = listOf(0.07, 0.06, 0.07, 0.09, 0.03, 0.29, 0.32)
val data = mapOf(
    "day" to days + days + days,
    "amount" to coffee + tea + soda,
    "drink" to List(7) { "coffee" } + List(7) { "tea" } + List(7) { "soda" }
)

data.plot {
    layout.title = "Weekly Beverage Consumption Trends"
    groupBy("drink") {
        bars {
            x("day")
            y("amount")
            fillColor("drink") {
                scale = categorical(
                    "coffee" to Color.hex("#6F4E37"),
                    "tea" to Color.hex("#C2D4AB"),
                    "soda" to Color.hex("#B5651D")
                )
            }
        }
    }
}
```

</tab></tabs>
<!---END-->

![Stacked Bars](stacked_bars.png) { border-effect="rounded" }

[//]: # (TODO)
<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/bars/stacked_bars.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/NFGYJFW8oMlsu5aROAxRGq" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
