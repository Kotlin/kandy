# Functional Scatter Plot

<web-summary>
Explore 'Functional Scatter Plot' in Kotlin using Kandy, a visualization that combines scatter plotting with functional data representation.
This example is great for demonstrating how mathematical functions can be visually expressed and analyzed through scatter plots.
</web-summary>

<card-summary>
'Functional Scatter Plot' in Kotlin with Kandy: A creative intersection of math and visualization, ideal for graphically depicting the behavior of mathematical functions.
</card-summary>

<link-summary>
Dive into 'Functional Scatter Plot' using Kandy,
where the scatter plot format is used to visually interpret mathematical functions.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Points-->

<!---FUN functional_scatter_plot-->

```kotlin
val xs = (-30..30).map { it.toDouble() / 5.0 }
val function = { x: Double -> sin(x) }
val ys = xs.map(function)

plot {
    points {
        x(xs)
        y(ys)
    }
}
```

<!---END-->

![Functional Scatter Plot](functional_scatter_plot.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/points/functional_scatter_plot.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/uxcPBlCsOra5UmBUke0Dul" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
