# Fixed Line Coordinate

<web-summary>
Explore the 'Fixed Line Coordinate' example in Kotlin using Kandy, displaying weekly temperatures with a constant average line.
This example demonstrates the use of fixed lines in data visualization.
</web-summary>

<card-summary>
Kotlin 'Fixed Line Coordinate' Example: A visual representation of weekly temperatures, enhanced with a constant average line using Kandy.
</card-summary>

<link-summary>
Dive into the 'Fixed Line Coordinate' Kotlin example with Kandy, where weekly temperature trends are complemented by a constant average line.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Lines-->

<!---FUN line_fixed_coord-->
<tabs>
<tab title="Dataframe">

```kotlin
data class DayTemperature(val day: String, val temp: Int)

val weeklyTemp = listOf(
    DayTemperature("Mon", 10),
    DayTemperature("Tue", 6),
    DayTemperature("Wed", 5),
    DayTemperature("Thu", 7),
    DayTemperature("Fri", 7),
    DayTemperature("Sat", 11),
    DayTemperature("Sun", 9)
).toDataFrame()

weeklyTemp.plot {
    x("day")
    line {
        y("temp")
        color = Color.BLUE
    }
    line {
        y.constant(weeklyTemp[DayTemperature::temp].mean())
        color = Color.GREEN
        type = LineType.DOTTED
        width = 2.5
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val day = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
val temp = listOf(10, 6, 5, 7, 7, 11, 9)

plot {
    x(day)
    line {
        y(temp)
        color = Color.BLUE
    }
    line {
        y.constant(temp.average())
        color = Color.GREEN
        type = LineType.DOTTED
        width = 2.5
    }
}
```

</tab></tabs>
<!---END-->

![Fixed Line Coordinate](line_fixed_coord.png) { border-effect="rounded" }


<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/line/fixed_line_coordinate.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/FAf7aNPaKSJ4eZ6dfT0Hkt" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
