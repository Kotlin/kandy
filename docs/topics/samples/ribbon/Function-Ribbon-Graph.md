# Function Ribbon Graph

<web-summary>
Explore 'Function Ribbon Graph' in Kotlin using Kandy to visualize complex mathematical functions.
This example uniquely demonstrates how ribbon plots can be used to represent intricate function behaviors and patterns.
</web-summary>

<card-summary>
'Function Ribbon Graph' in Kotlin with Kandy:
A mathematical journey into visualizing complex functions using ribbon plots, perfect for an analytical view of functional dynamics.
</card-summary>

<link-summary>
Dive into 'Function Ribbon Graph' using Kotlin and Kandy, where mathematics meets art in the visualization of complex functional relationships with ribbon plots.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Ribbon-->

<!---FUN ribbon_function_plot-->

```kotlin
val xs = (-1000..1000).map { it / 250.0f }
val function = { x: Float -> sin(2 * x) * cos(x / 2 - 3f) }
val yActual = xs.map(function)
val yLow = yActual.map { it - 0.25f }
val yHigh = yActual.map { it + 0.25f }

plot {
    ribbon {
        x(xs)
        yMin(yLow)
        yMax(yHigh)
        fillColor = Color.LIGHT_BLUE
    }
}
```

<!---END-->

![Functional Area Plot](ribbon_function_plot.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/ribbon/ribbon_function_plot.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/64vZ9iM91abh7MCMqNKdI0" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
