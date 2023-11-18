# Smoothed Line

<web-summary>
Explore the 'Smoothed Line' example in Kotlin using Kandy, showcasing how to create a smooth curve from a set of data points.
This example is perfect for visualizing trends in scattered data.
</web-summary>

<card-summary>
'Smoothed Line' in Kotlin with Kandy: A demonstration of smoothing techniques applied to line graphs for a clear and flowing data presentation.
</card-summary>

<link-summary>
Dive into the 'Smoothed Line' example using Kotlin and Kandy, illustrating the elegance of smoothed line graphs in data visualization.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Lines-->

<!---FUN smoothed_line-->

```kotlin
val xs = listOf(-3.0, -2.5, -2.0, -1.5, -1.0, 0.0, 1.0, 1.5, 2.0, 2.5, 3.0)
val ys = listOf(-5.4, -1.2, 3.4, 0.1, -0.6, -2.1, 0.6, 2.2, 3.4, 4.5, 6.7)

plot {
    smoothLine(xs, ys, smootherPointCount = 30) {
        width = 2.3
        color = Color.GREEN
    }
}
```

<!---END-->

![Smoothed Line](smoothed_line.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/line/smoothed_line.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/qHNYocH9JNJzV0MQ1kNw9M" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
