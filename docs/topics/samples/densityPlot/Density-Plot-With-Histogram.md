# Density Plot With Histogram

<web-summary>
Explore 'Density Plot With Histogram' in Kotlin using Kandy, where density plot is enhanced with histogram.
This example effectively showcases the usage of several statistical plots together, useful when you want to visualise distribution in several ways on one plot.
</web-summary>

<card-summary>
'Density Plot With Histogram' in Kotlin with Kandy: A powerful tool for visualising a distribution in several ways.
</card-summary>

<link-summary>
Dive into 'Density Plot With Histogram' using Kotlin and Kandy, showing multiple statistical plots.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.DensityPlot-->


<!---FUN densityPlot_with_histogram-->

```kotlin
val df = DataFrame.readCSV(
    fileOrUrl = "https://raw.githubusercontent.com/Kotlin/dataframe/master/examples/idea-examples/titanic/src/main/resources/titanic.csv",
    delimiter = ';', parserOptions = ParserOptions(locale = java.util.Locale.FRENCH)
)
val ages = df["age"].dropNulls().cast<Double>()

plot {
    histogram(ages) {
        alpha = 0.9
        fillColor = Color.BLUE
        y(Stat.density)
    }
    densityPlot(ages) {
        alpha = 0.5
        fillColor = Color.hex(0xFF6666)
    }
    layout.title = "Titanic passengers age distribution"
}
```

<!---END-->


![Density Plot With Histogram](densityPlot_with_histogram.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/histogram/densityPlot_with_histogram.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/i4hJKskkhnKmazY7pP1WXv" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
