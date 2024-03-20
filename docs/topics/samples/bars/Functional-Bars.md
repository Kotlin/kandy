# Functional Bar Plot

<web-summary>
Discover 'Functional Bar Plot' in Kotlin using Kandy, where mathematical functions meet bar plotting.
This example illustrates the visualization of a sine function using bars, offering a unique perspective on function values.
</web-summary>

<card-summary>
'Functional Bar Plot' in Kotlin:
A creative blend of math and visualization, showcasing how bars can represent a sine function's values in a compelling format.
</card-summary>

<link-summary>
Explore 'Functional Bar Plot' with Kotlin and Kandy, a fusion of mathematical function plotting and bar charts.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Bars-->

<!---FUN functional_bars_plot-->

```kotlin
val xs = (-80..80).map { it.toDouble() / 8.0 }
val function = { x: Double -> sin(x / 1.5) }
val ys = xs.map(function)

plot {
    bars {
        x(xs)
        y(ys)
    }
}
```

<!---END-->

![Functional Bar Plot](functional_bars_plot.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/bars/functional_bars.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/ABC3MDFntS0RKuqKwl8nrg" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
