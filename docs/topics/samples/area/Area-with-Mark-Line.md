# Area with Mark Line

<web-summary>
Discover 'Area with Mark Line' in Kotlin using Kandy, where area plots are enhanced with horizontal mark lines.
This example compares the average temperatures of Berlin and Madrid, highlighting them with distinct dashed lines for a clear visual contrast.
</web-summary>

<card-summary>
'Area with Mark Line' in Kotlin with Kandy: An innovative plot combining area charts with marked average lines, providing a visual comparison of temperature trends in Berlin and Madrid.
</card-summary>

<link-summary>
Explore 'Area with Mark Line' in Kotlin using Kandy, a creative approach to juxtaposing average temperature trends against monthly data for two cities with distinctively marked lines.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Area-->

<!---FUN area_with_mark_line-->
<tabs>
<tab title="Dataframe">

```kotlin
val months = listOf(
    "January", "February",
    "March", "April", "May",
    "June", "July", "August",
    "September", "October", "November",
    "December"
)
val tempBerlin =
    listOf(-0.5, 0.0, 4.8, 9.0, 14.3, 17.5, 19.2, 18.9, 14.5, 9.7, 4.7, 1.0)
val tempMadrid =
    listOf(6.3, 7.9, 11.2, 12.9, 16.7, 21.1, 24.7, 24.2, 20.3, 15.4, 9.9, 6.6)

val df = dataFrameOf(
    "month" to months + months,
    "temperature" to tempBerlin + tempMadrid,
    "city" to List(12) { "Berlin" } + List(12) { "Madrid" }
)

df.plot {
    area {
        x("month")
        y("temperature")
        fillColor("city") {
            scale = categorical("Berlin" to Color.hex("#07C3F2"), "Madrid" to Color.hex("#FDB60D"))
        }
        alpha = 0.5
        borderLine.width = 1.5
    }
    hLine {
        yIntercept.constant(tempBerlin.average())
        color = Color.BLACK
        width = 2.0
        type = LineType.DASHED
    }
    hLine {
        yIntercept.constant(tempMadrid.average())
        color = Color.RED
        width = 2.0
        type = LineType.DASHED
    }
    layout.size = 1000 to 450
}
```

</tab>
<tab title="Collections">

```kotlin
val months = listOf(
    "January", "February",
    "March", "April", "May",
    "June", "July", "August",
    "September", "October", "November",
    "December"
)
val tempBerlin =
    listOf(-0.5, 0.0, 4.8, 9.0, 14.3, 17.5, 19.2, 18.9, 14.5, 9.7, 4.7, 1.0)
val tempMadrid =
    listOf(6.3, 7.9, 11.2, 12.9, 16.7, 21.1, 24.7, 24.2, 20.3, 15.4, 9.9, 6.6)

val df = mapOf(
    "month" to months + months,
    "temperature" to tempBerlin + tempMadrid,
    "city" to List(12) { "Berlin" } + List(12) { "Madrid" }
)

df.plot {
    area {
        x("month")
        y("temperature")
        fillColor("city") {
            scale = categorical("Berlin" to Color.hex("#07C3F2"), "Madrid" to Color.hex("#FDB60D"))
        }
        alpha = 0.5
        borderLine.width = 1.5
    }
    hLine {
        yIntercept.constant(tempBerlin.average())
        color = Color.PURPLE
        alpha = 0.9
        type = LineType.DASHED
    }
    hLine {
        yIntercept.constant(tempMadrid.average())
        color = Color.ORANGE
        alpha = 0.9
        type = LineType.DASHED
    }
    layout.size = 1000 to 450
}
```

</tab></tabs>
<!---END-->

![Area with Mark Line](area_with_mark_line.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/area/area_with_mark_line.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/TkrTBZDadEWHeBmsxwfqiP" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
