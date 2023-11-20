# ErrorBars with Line

<web-summary>
Discover how to combine error bars with line charts in the 'ErrorBars with Line' example.
This tutorial in Kandy illustrates an effective way to show data trends alongside their variability.
</web-summary>

<card-summary>
'ErrorBars with Line': A Kandy guide to integrating error bars with line plots for enhanced data interpretation.
</card-summary>

<link-summary>
Explore 'ErrorBars with Line' for a comprehensive understanding of merging error bars with line plots in Kandy.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.ErrorBars-->

<!---FUN error_bars_with_line-->
<tabs>
<tab title="Dataframe">

```kotlin
val years = listOf(2018, 2019, 2020, 2021, 2022)
val costMin = listOf(62.7, 64.7, 72.1, 73.7, 68.5)
val costMax = listOf(68.9, 71.3, 78.9, 76.5, 72.1)
val mid = costMin.zip(costMax).map { (it.first + it.second) / 2.0 }
val data = dataFrameOf(
    years.toColumn("years"),
    costMin.toColumn("min"),
    mid.toColumn("mid"),
    costMax.toColumn("max")
)

data.plot {
    x("years")
    y("mid")
    line {
        color = Color.BLUE
    }
    errorBars {
        yMin("min")
        yMax("max")
        borderLine.type = LineType.LONGDASH
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val years = listOf(2018, 2019, 2020, 2021, 2022)
val costMin = listOf(62.7, 64.7, 72.1, 73.7, 68.5)
val costMax = listOf(68.9, 71.3, 78.9, 76.5, 72.1)
val mid = costMin.zip(costMax).map { (it.first + it.second) / 2.0 }

plot {
    x(years)
    y(mid)
    line {
        color = Color.BLUE
    }
    errorBars {
        yMin(costMin)
        yMax(costMax)
        borderLine.type = LineType.LONGDASH
    }
}
```

</tab></tabs>
<!---END-->

![ErrorBars with Line](error_bars_with_line.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/errorBars/error_bars_with_line.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/NFGYJFW8oMlsu5aROAxRGq" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
