# Smoothed Area

<web-summary>
Explore the 'Smoothed Area' plot in Kotlin using Kandy to see how smoothing techniques can enhance area plots.
This example visualizes data with a smoothed curve.
</web-summary>

<card-summary>
'Smoothed Area' in Kotlin with Kandy: A seamless blend of data smoothing and area plotting, ideal for portraying subtle data patterns in a visually appealing way.
</card-summary>

<link-summary>
Dive into 'Smoothed Area' using Kotlin and Kandy, showcasing the elegance and clarity of smoothed curves in area plot visualization.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Area-->

<!---FUN smoothed_area-->

```kotlin
val xs = listOf(-3.0, -2.5, -2.0, -1.5, -1.0, 0.0, 1.0, 1.5, 2.0, 2.5, 3.0)
val ys = listOf(5.4, 1.2, 3.4, 0.1, 0.6, 2.1, 0.6, 2.2, 3.4, 4.5, 6.7)

plot {
    statSmooth(xs, ys, smootherPointCount = 30) {
        area {
            x(Stat.x)
            y(Stat.y)
            borderLine {
                color = Color.GREEN
                width = 2.0
            }
            alpha = 0.6
            fillColor = Color.LIGHT_GREEN
        }
    }
}
```

<!---END-->

![Smoothed Area](smoothed_area.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/area/smoothed_area.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/LxLVcHRtWWkvpGzkwgMBc8" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
