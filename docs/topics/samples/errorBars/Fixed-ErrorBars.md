# Fixed ErrorBars Coordinate

<web-summary>
Learn to anchor error bars at specific coordinates with 'Fixed ErrorBars Coordinate' in Kandy.
This example demonstrates precise error bar placement for targeted data analysis.
</web-summary>

<card-summary>
Discover precise error visualization with 'Fixed ErrorBars Coordinate' in Kandy, showcasing accurate error bar positioning on data plots.
</card-summary>

<link-summary>
Explore precision in error visualization with 'Fixed ErrorBars Coordinate' in Kandy, showcasing fixed coordinate error bars.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.ErrorBars-->

<!---FUN fixed_error_bars-->
<tabs>
<tab title="Dataframe">

```kotlin
val years = listOf(2018, 2019, 2020, 2021, 2022)
val costMin = listOf(62.7, 64.7, 72.1, 73.7, 68.5)
val costMax = listOf(68.9, 71.3, 78.9, 76.5, 72.1)
val data = dataFrameOf(years.toColumn("years"), costMin.toColumn("min"), costMax.toColumn("max"))

plot(data) {
    errorBars {
        x("years")
        yMin.constant(20.0)
        yMax("max")
        width = 0.5
        borderLine.width = 1.3
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val years = listOf(2018, 2019, 2020, 2021, 2022)
val costMax = listOf(68.9, 71.3, 78.9, 76.5, 72.1)

plot {
    errorBars {
        x(years)
        yMin.constant(20.0)
        yMax(costMax)
        width = 0.5
        borderLine.width = 1.3
    }
}
```

</tab></tabs>
<!---END-->

![Fixed ErrorBars Coordinate](fixed_error_bars.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/errorBars/fixed_error_bars.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/NFGYJFW8oMlsu5aROAxRGq" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
