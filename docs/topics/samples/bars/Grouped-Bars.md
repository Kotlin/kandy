# Grouped Bars

<web-summary>
Explore 'Grouped Bars' in Kotlin using Kandy, a clear demonstration of grouping in bar charts.
This example effectively showcases the comparison of multiple data sets side by side within the same category, ideal for comparative analysis across different groups or variables.
</web-summary>

<card-summary>
'Grouped Bars' in Kotlin with Kandy: A powerful tool for visual comparison,
this example highlights how to group data sets in bar charts for a comprehensive comparative view.
</card-summary>

<link-summary>
Dive into 'Grouped Bars' using Kotlin and Kandy,
where the concept of grouped bar plotting is used to juxtapose different data sets within the same categories.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Bars-->

<!---FUN grouped_bars-->
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

![Grouped Bars](grouped_bars.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/bars/grouped_bars.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/Rpt8bBAYsUm1v9bFCgyWH4" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
