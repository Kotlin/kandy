# Fixed Area Coordinate

<web-summary>
Explore 'Annual Water Level Fluctuations in Reservoir' in Kotlin using Kandy to visualize how water levels change throughout the year.
This example demonstrates the seasonal variations in a reservoir, providing insights for water resource management.
</web-summary>

<card-summary>
'Annual Water Level Fluctuations in Reservoir' in Kotlin with Kandy:
An illustrative plot showing the monthly changes in water levels, highlighting seasonal impacts on reservoirs.
</card-summary>

<link-summary>
Dive into the 'Annual Water Level Fluctuations in Reservoir' using Kotlin and Kandy,
a graphical representation of water level changes over the year, essential for environmental monitoring.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Area-->

<!---FUN area_fixed-->
<tabs>
<tab title="Dataframe">

```kotlin
val month by columnOf(
    "January", "February",
    "March", "April", "May",
    "June", "July", "August",
    "September", "October", "November",
    "December"
)
val waterLvl by columnOf(4.5, 4.7, 5.0, 5.5, 6.0, 6.5, 6.7, 6.2, 5.8, 5.3, 4.8, 4.6)
val reservoirDf = dataFrameOf(month, waterLvl)

plot(reservoirDf) {
    layout {
        title = "Water Level"
        subtitle = "Annual Water Level Fluctuations in Reservoir"
        yAxisLabel = "Month"
        xAxisLabel = "Water Level (meters)"
    }

    x(month)
    y { axis.limits = 3.0..8.0 }
    line {
        y(waterLvl)
    }
    area {
        y.constant(5.0)
        borderLine.type = LineType.DOTTED
        alpha = 0.5
        fillColor = Color.RED
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val reservoirDf = mapOf(
    "month" to listOf(
        "January", "February",
        "March", "April", "May",
        "June", "July", "August",
        "September", "October", "November",
        "December"
    ),
    "waterLvl" to listOf(4.5, 4.7, 5.0, 5.5, 6.0, 6.5, 6.7, 6.2, 5.8, 5.3, 4.8, 4.6)
)

plot(reservoirDf) {
    layout {
        title = "Water Level"
        subtitle = "Annual Water Level Fluctuations in Reservoir"
        yAxisLabel = "Month"
        xAxisLabel = "Water Level (meters)"
    }

    x("month")
    y { axis.limits = 3.0..8.0 }
    line {
        y("waterLvl")
    }
    area {
        y.constant(5.0)
        borderLine.type = LineType.DOTTED
        alpha = 0.5
        fillColor = Color.RED
    }
}
```

</tab></tabs>
<!---END-->

![Fixed Area Coordinate ](area_fixed.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/area/area_fixed.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/aS4CaHvTpMAdPk9iKUsUD3" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
