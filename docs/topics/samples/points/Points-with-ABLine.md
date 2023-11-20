# Points with ABLine

<web-summary>
Discover how to enhance scatter plots with an ABLine in Kotlin using the Kandy library.
This example illustrates the integration of a reference line to scatter plots, aiding in data analysis and trend visualization. 
</web-summary>

<card-summary>
Enhance data visualization with the "Points with ABLine" example in Kotlin using Kandy.
This plot combines scatter points with a reference line, offering deeper insights into data trends and correlations.
</card-summary>

<link-summary>
Explore data trends with 'Points with ABLine' using Kandy, blending scatter points and a reference line for deeper insights.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Points-->

<!---FUN points_with_abLine-->
<tabs>
<tab title="Dataframe">

```kotlin
val xValues by columnOf(
    7.13, 9.30, 7.84, 7.08, 5.51,
    8.40, 5.69, 11.59, 12.53, 4.98,
    10.29, 6.88, 7.38, 12.03, 0.92
)
val yValues by columnOf(
    7.05, 8.23, 6.74, 7.95, 5.38,
    7.47, 4.88, 9.17, 9.30, 6.17,
    6.58, 5.87, 6.45, 10.53, 3.13
)

plot {
    points {
        x(xValues)
        y(yValues)
        size = 7.0
        color = Color.LIGHT_BLUE
    }
    abLine {
        slope.constant(0.5)
        intercept.constant(3)
        color = Color.RED
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val xValues = listOf(
    7.13, 9.30, 7.84, 7.08, 5.51,
    8.40, 5.69, 11.59, 12.53, 4.98,
    10.29, 6.88, 7.38, 12.03, 0.92
)
val yValues = listOf(
    7.05, 8.23, 6.74, 7.95, 5.38,
    7.47, 4.88, 9.17, 9.30, 6.17,
    6.58, 5.87, 6.45, 10.53, 3.13
)

plot {
    points {
        x(xValues)
        y(yValues)
        size = 7.0
        color = Color.LIGHT_BLUE
    }
    abLine {
        slope.constant(0.5)
        intercept.constant(3)
        color = Color.RED
    }
}
```

</tab></tabs>
<!---END-->

![Points with ABLine](points_with_abLine.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/points/points_with_abLine.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/gsbsxRZgOBCgsisxg1Pvld" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
