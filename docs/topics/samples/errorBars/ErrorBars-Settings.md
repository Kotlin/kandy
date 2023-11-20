# ErrorBars Settings

<web-summary>
Dive into the 'ErrorBars Settings' example in Kandy to primarily customizing error bars.
This Kotlin tutorial showcases advanced techniques for tailoring error bars to fit specific data visualization needs.
</web-summary>

<card-summary>
Advanced Error Bars: Learn to customize error bars in Kandy for detailed data analysis.
</card-summary>

<link-summary>
Get hands-on with 'ErrorBars Settings' for advanced error bar customization in Kandy, perfect for detailed data analysis.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.ErrorBars-->

<!---FUN error_bars_settings-->
<tabs>
<tab title="Dataframe">

```kotlin
val years by columnOf(2018, 2019, 2020, 2021, 2022)
val costMin by columnOf(62.7, 64.7, 72.1, 73.7, 68.5)
val costMax by columnOf(68.9, 71.3, 78.9, 76.5, 72.1)
val data = dataFrameOf(years, costMin, costMax)

data.plot {
    errorBars {
        x(years)
        yMin(costMin)
        yMax(costMax)
        width = 1.1
        borderLine {
            width = 1.5
            color = Color.RED
        }
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val years = listOf(2018, 2019, 2020, 2021, 2022)
val costMin = listOf(62.7, 64.7, 72.1, 73.7, 68.5)
val costMax = listOf(68.9, 71.3, 78.9, 76.5, 72.1)

plot {
    errorBars {
        x(years)
        yMin(costMin)
        yMax(costMax)
        width = 1.1
        borderLine {
            width = 1.5
            color = Color.RED
        }
    }
}
```

</tab></tabs>
<!---END-->

![ErrorBars Settings](error_bars_settings.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/errorBars/error_bars_settings.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/NFGYJFW8oMlsu5aROAxRGq" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
