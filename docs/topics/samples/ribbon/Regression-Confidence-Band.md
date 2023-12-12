# Regression Confidence Band

<web-summary>
Explore the 'Regression Confidence Band' plot in Kotlin using Kandy to see how line smoothing techniques can be enhanced with ribbon plot.
</web-summary>

<card-summary>
'Regression Confidence Band' in Kotlin with Kandy: A seamless blend of data smoothing and ribbon plotting, ideal for portraying subtle data patterns in a visually appealing way.
</card-summary>

<link-summary>
Dive into 'Regression Confidence Band' using Kotlin and Kandy, showcasing the elegance and clarity of smoothed curves extension with ribbon plot.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Ribbon-->

<!---FUN regression_confidence_band-->

```kotlin
val xs = listOf(
    -3.0, -2.8, -2.7, -2.6, -2.6, -2.5, -2.2, -3.1, -1.5,
    -0.2,  2.0, 1.2, 2.6, 2.1, 0.1, 1.2, 1.7, 0.0, 2.8,
    2.5, 0.2, 1.3, 2.5
)
val ys = listOf(
    -1.4, -1.2, -1.4, -1.3, -1.2, -1.1, 1.5, 2.4, 1.1, -0.9,
    3.5, 1.6, -0.7, 1.2, 0.1, 3.4, 2.8, 4.2, 1.16, 4.1, 2.2,
    1.4, 5.1
)

plot {
    statSmooth(xs, ys) {
        line {
            x(Stat.x)
            y(Stat.y)
            width = 2.0
            color = Color.BLUE
        }
        ribbon {
            x(Stat.x)
            yMin(Stat.yMin)
            yMax(Stat.yMax)
            borderLine.width = 0.0
        }
    }
}
```

<!---END-->

![Smoothed Area](regression_confidence_band.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/ribbon/regression_confidence_band.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/0YhvvbuozyuKzwiQ65eMgh" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
