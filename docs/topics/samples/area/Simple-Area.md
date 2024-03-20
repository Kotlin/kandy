# Simple Area

<web-summary>
Discover 'Simple Area' in Kotlin using Kandy, a clear and straightforward example of area plotting.
This example visualizes cost data over several years, demonstrating the effectiveness of area charts in data representation.
</web-summary>

<card-summary>
'Simple Area' in Kotlin with Kandy: An elegant demonstration of area plots, showcasing yearly cost trends in a visually intuitive manner.
</card-summary>

<link-summary>
Explore the 'Simple Area' example using Kotlin and Kandy, a perfect illustration of how area charts can be used to depict trends over time.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Area-->

<!---FUN simple_area-->
<tabs>
<tab title="Dataframe">

```kotlin
val dataframe = dataFrameOf(
    "years" to listOf("2017", "2018", "2019", "2020", "2021", "2022", "2023"),
    "cost" to listOf(56.1, 22.7, 34.7, 82.1, 53.7, 68.5, 39.9)
)

dataframe.plot {
    area {
        x("years")
        y("cost")
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val years = listOf("2017", "2018", "2019", "2020", "2021", "2022", "2023")
val cost = listOf(56.1, 22.7, 34.7, 82.1, 53.7, 68.5, 39.9)

plot {
    area {
        x(years)
        y(cost)
    }
}
```

</tab></tabs>
<!---END-->

![Simple Area](simple_area.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/area/simple_area.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/LmZB0wrcS6YNG09OENeQsH" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
