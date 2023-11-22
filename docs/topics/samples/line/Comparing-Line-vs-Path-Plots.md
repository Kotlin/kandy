# Comparing Line vs. Path Plots

<web-summary>
Explore 'Comparing Line vs. Path Plots' in Kotlin using Kandy to understand the differences between line and path plots.
This example clearly demonstrates how each plot type uniquely represents data.
</web-summary>

<card-summary>
'Comparing Line vs. Path Plots' in Kotlin with Kandy:
A side-by-side demonstration showcasing the distinct characteristics of line and path plots for data visualization.
</card-summary>

<link-summary>
Dive into the 'Comparing Line vs. Path Plots' example using Kotlin and Kandy,
offering a comparative view of how line and path plots are used in graphing data.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Lines-->

<!---FUN line_and_path_comp_1-->

```kotlin
fun generateArchimedeanDataMap(n: Int = 25, k: Double = 1.0, a: Double = 1.0): Map<String, List<Double>> {
    val phi = List(n) { i -> 2.0 * PI * k * i.toDouble() / (n - 1) }
    val r = phi.map { angle -> (a * angle) / (2.0 * PI) }
    val x = (r zip phi).map { p -> p.first * cos(p.second) }
    val y = (r zip phi).map { p -> p.first * sin(p.second) }
    return mapOf("x" to x, "y" to y)
}

val aDataMap = generateArchimedeanDataMap(n = 200, k = 2.0)
```

<!---END-->

<!---FUN line_and_path_comp_2-->

```kotlin
val linePlot = plot(aDataMap) {
    line {
        x("x")
        y("y")
    }
    layout.title = "`line` layer"
}
val pathPlot = plot(aDataMap) {
    path {
        x("x")
        y("y")
    }
    layout.title = "`path` layer"
}
plotGrid(listOf(linePlot, pathPlot))
```

<!---END-->

![Comparing Line vs. Path Plots](line_and_path_comp_2.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/line/comparing_line_vs_path_plots.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/vNGa9Uj5CdUUmnC0bQwch0" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
