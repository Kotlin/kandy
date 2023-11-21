# Smoothed Area with Points

<web-summary>
Explore 'Smoothed Area with Points' in Kotlin using Kandy, a visualization that combines smoothed area plots with distinct data points.
This example is perfect for depicting trends and individual data points together, offering both a general overview and specific insights.
</web-summary>

<card-summary>
'Smoothed Area with Points' in Kotlin with Kandy: A harmonious blend of smoothed area charts and point markers, ideal for illustrating data trends alongside specific values.
</card-summary>

<link-summary>
Dive into 'Smoothed Area with Points' using Kotlin and Kandy, where smoothed area visualization meets individual data points, providing a comprehensive view of the data.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Area-->

<!---FUN smoothed_area_with_points-->

```kotlin
val xs = listOf(-3.0, -2.5, -2.0, -1.5, -1.0, 0.0, 1.0, 1.5, 2.0, 2.5, 3.0)
val ys = listOf(5.4, 1.2, 3.4, 0.7, 0.8, 2.1, 0.6, 2.2, 3.4, 4.5, 6.7)

plot {
    statSmooth(xs, ys, method = SmoothMethod.LOESS(span = 0.3)) {
        area {
            x(Stat.x)
            y(Stat.y)
            alpha = 0.75
            fillColor = Color.LIGHT_GREEN
            borderLine.color = Color.LIGHT_PURPLE
        }
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

![Smoothed Area with Points](smoothed_area_with_points.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/area/smoothed_area_with_points.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/IxpXHtb4fohvkg2UsfCXKa" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
