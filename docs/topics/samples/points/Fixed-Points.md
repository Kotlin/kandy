# Fixed Points Coordinate

<web-summary>
Discover 'Fixed Points Coordinate' in Kotlin using Kandy, a plot showcasing the use of fixed coordinates in a scatter plot.
This example is ideal for highlighting specific data points or benchmarks against a backdrop of broader data scatter.
</web-summary>

<card-summary>
'Fixed Points Coordinate' in Kotlin with Kandy: A unique scatter plot that emphasizes certain data points through fixed coordinates,
perfect for detailed comparative analysis.
</card-summary>

<link-summary>
Explore 'Fixed Points Coordinate' using Kotlin and Kandy,
where scatter plotting is refined with fixed coordinates to spotlight key data points or benchmarks.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Points-->

<!---FUN fixed_points-->

```kotlin
plot {
    points {
        x.constant(0.5)
        y(listOf(1, 2, 3, 4, 5, 6, 7))
    }
}
```

<!---END-->

![Fixed Points Coordinate](fixed_points.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/points/fixed_points.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/ogvCTfbOTPOOobHOHrhTaa" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
