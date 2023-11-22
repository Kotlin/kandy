# Functional Area Plot

<web-summary>
Explore 'Functional Area Plot' in Kotlin using Kandy to visualize complex mathematical functions.
This example uniquely demonstrates how area plots can be used to represent intricate function behaviors and patterns.
</web-summary>

<card-summary>
'Functional Area Plot' in Kotlin with Kandy:
A mathematical journey into visualizing complex functions using area plots, perfect for an analytical view of functional dynamics.
</card-summary>

<link-summary>
Dive into 'Functional Area Plot' using Kotlin and Kandy, where mathematics meets art in the visualization of complex functional relationships with area plots.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Area-->

<!---FUN functional_area_plot-->

```kotlin
val xs = (-2000..2000).map { it.toDouble() / 500.0 }
val function = { x: Double -> sin(x) * cos(x * 2 + 1) * sin(3 * x + 2.0) }
val ys = xs.map(function)

plot {
    area {
        x(xs)
        y(ys)
    }
}
```

<!---END-->

![Functional Area Plot](functional_area_plot.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/area/functional_area.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/6i9x0ZrStJjS6xVHP7NTfj" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
