# Smoothed Curve with Points

<web-summary>
Discover the 'Smoothed Curve with Points' example in Kotlin using Kandy, where smooth curves and distinct data points come together.
This example elegantly combines line smoothing and point plotting.
</web-summary>

<card-summary>
'Smoothed Curve with Points' in Kotlin with Kandy: A visualization that merges smooth lines with clearly marked points,
ideal for detailed trend analysis
</card-summary>

<link-summary>
Explore the 'Smoothed Line with Points' using Kotlin and Kandy, an attractive way to represent data with both smoothed lines and individual points.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Lines-->

<!---FUN smoothed_curve_with_points-->

```kotlin
val xs = listOf(-3.0, -2.5, -2.0, -1.5, -1.0, 0.0, 1.0, 1.5, 2.0, 2.5, 3.0)
val ys = listOf(-5.4, -1.2, 3.4, 0.1, -0.6, -2.1, 0.6, 2.2, 3.4, 4.5, 6.7)

plot {
    smoothLine(xs, ys, method = SmoothMethod.LOESS(span = 0.3)) {
        color = Color.GREEN
    }
    points {
        size = 4.0
        color = Color.ORANGE
        x(xs)
        y(ys)
    }
}
```

<!---END-->

![Smoothed Curve with Points](smoothed_curve_with_points.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/line/smoothed_curve_with_points.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/3dTEL0OlL6minCBIaIYLtQ" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
