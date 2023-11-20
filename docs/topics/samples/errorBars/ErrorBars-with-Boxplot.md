# ErrorBars with Boxplot

<web-summary>
Discover the combined power of error bars and boxplot in 'ErrorBars with Boxplot' using Kandy.
This example skillfully merges two data visualization techniques to provide a comprehensive view of data spread and variability.
</web-summary>

<card-summary>
Combining Clarity: 'ErrorBars with Boxplot' in Kandy showcases enhanced data interpretation by merging error bars with boxplot.
</card-summary>

<link-summary>
Explore the 'ErrorBars with Boxplot' in Kandy for a dual approach to data visualization,
blending error bars with the descriptive statistics of boxplot.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.ErrorBars-->

<!---FUN error_bars_with_boxplot-->

```kotlin
val random = java.util.Random(42)

val valuesA = List(100) { 3.0 + random.nextGaussian() * 0.5 }
val valuesB = List(100) { 1.5 + random.nextDouble() * 4.5 }
val valuesC = valuesA.zip(valuesB).map { (it.first + it.second) / 2.0 }


val df = dataFrameOf(
    "value" to valuesA + valuesB + valuesC,
    "group" to  List(100) {"a"} + List(100) {"b"} + List(100) {"c"}
)

df.plot {
    statBoxplot("group", "value") {
        errorBars {
            x(Stat.x)
            yMin(Stat.min)
            yMax(Stat.max)
            borderLine.color(Stat.x)
        }
    }
}
```

<!---END-->

![ErrorBars with Boxplot](error_bars_with_boxplot.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/errorBars/error_bars_with_boxplot.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/NFGYJFW8oMlsu5aROAxRGq" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
