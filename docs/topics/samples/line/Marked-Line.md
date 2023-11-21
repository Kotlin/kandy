# Marked Line

<web-summary>
Explore 'Marked Line' in Kotlin using Kandy, showcasing how to highlight key data points in line graphs.
This example compares temperatures in Berlin and Madrid with distinct visual markers.
</web-summary>

<card-summary>
Marked Line' in Kotlin with Kandy: An elegant demonstration of line graphs with added emphasis on specific data points for clearer comparisons.
</card-summary>

<link-summary>
Dive into the 'Marked Line' example using Kotlin and Kandy, where line graphs are enhanced with markers to highlight key comparisons.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Lines-->

<!---FUN line_mark-->
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
    line {
        x("month")
        y("temperature")
        color("city") {
            scale = categorical("Berlin" to Color.PURPLE, "Madrid" to Color.ORANGE)
        }
        width = 1.5
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
    line {
        x("month")
        y("temperature")
        color("city") {
            scale = categorical("Berlin" to Color.PURPLE, "Madrid" to Color.ORANGE)
        }
        width = 1.5
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

![Marked Line](line_mark.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/line/marked_line.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/ngtzOYeGxTLZhBiqTWViL9" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
