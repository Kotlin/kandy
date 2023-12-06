# Basic Points Plot

<web-summary>
Discover 'Basic Points Plot' in Kotlin using Kandy, a fundamental example of scatter plot visualization.
This example adeptly demonstrates how individual data points can be plotted on a graph to identify patterns and relationships.
</web-summary>

<card-summary>
'Basic Points Plot' in Kotlin with Kandy:
A straightforward scatter plot demonstration, ideal for visualizing relationships between variables through individual data points.
</card-summary>

<link-summary>
Explore 'Basic Points Plot' using Kotlin and Kandy,
a simple yet effective way to visualize data sets and uncover potential correlations with scatter plotting.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Points-->

<!---FUN basic_points_plot-->
<tabs>
<tab title="Dataframe">

```kotlin
val dataset = dataFrameOf(
    "xs" to listOf(
        5.93, 9.15, 3.76, 5.04, 2.23,
        7.47, 2.59, 11.67, 7.90, 3.71,
        0.03, 2.73, 4.61, 5.44, 1.76,
        14.46, 1.89
    ),
    "ys" to listOf(
        14.66, 13.80, 5.37, 6.40, 6.86,
        2.98, 6.69, 5.48, 3.67, 12.36,
        0.01, 14.47, 14.56, 9.19, 12.86,
        5.37, 0.90
    )
)

dataset.plot {
    points {
        x("xs")
        y("ys")
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val xs = listOf(
    5.93, 9.15, 3.76, 5.04, 2.23,
    7.47, 2.59, 11.67, 7.90, 3.71,
    0.03, 2.73, 4.61, 5.44, 1.76,
    14.46, 1.89
)
val ys = listOf(
    14.66, 13.80, 5.37, 6.40, 6.86,
    2.98, 6.69, 5.48, 3.67, 12.36,
    0.01, 14.47, 14.56, 9.19, 12.86,
    5.37, 0.90
)

plot {
    points {
        x(xs)
        y(ys)
    }
}
```

</tab></tabs>
<!---END-->

![Basic Points Plot](basic_points_plot.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/points/basic_points.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/sjtG0drafDMugIFNkRysal" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
