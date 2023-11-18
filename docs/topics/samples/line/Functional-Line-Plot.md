# Functional Line Plot

<web-summary>
Explore the 'Functional Line Plot' example in Kotlin with Kandy, which visualizes a complex mathematical function.
This example demonstrates plotting intricate patterns using line plots.
</web-summary>

<card-summary>
Kotlin 'Functional Line Plot' Example: A visual journey through mathematical functions using Kandy's line plotting capabilities."
</card-summary>

<link-summary>
Dive into the 'Functional Line Plot' in Kotlin with Kandy, showcasing the art of plotting functions in a visually appealing way.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Lines-->

<!---FUN line_by_fun-->

```kotlin
val xs = (-2000..2000).map { it.toDouble() / 500.0 }
val function = { x: Double -> sin(x) * cos(x * 2 + 1) * sin(3 * x + 2.0) }
val ys = xs.map(function)

plot {
    line {
        x(xs)
        y(ys) {
            scale = continuous(-1.0..1.0)
        }
        hLine {
            yIntercept.constant(0.0)
            color = Color.RED
            type = LineType.DASHED
        }
    }
}
```

<!---END-->

![Functional Line Plot](line_by_fun.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/line/functional_line_plot.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/hIoyXct6xmGQVbmLQJnXxg" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
