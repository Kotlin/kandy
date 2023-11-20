# Simple ErrorBars

<web-summary>
Learn to depict data uncertainty with _Simple ErrorBars_ in Kandy.
This Kotlin example demonstrates how to add error bars for a clear understanding of data variability.
</web-summary>

<card-summary>
_Simple ErrorBars_ in Kandy: A quick guide on visualizing error margins in your data plots.
</card-summary>

<link-summary>
See _Simple ErrorBars_ in action: A concise example of error visualization in Kandy on GitHub and Datalore.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.ErrorBars-->

<!---FUN simple_error_bar_plot-->
<tabs>
<tab title="Dataframe">

```kotlin
val years by columnOf(2018, 2019, 2020, 2021, 2022)
val costMin by columnOf(62.7, 64.7, 72.1, 73.7, 68.5)
val costMax by columnOf(68.9, 71.3, 78.9, 76.5, 72.1)

plot {
    errorBars {
        x(years)
        yMin(costMin)
        yMax(costMax)
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
    }
}
```

</tab></tabs>
<!---END-->

![Simple ErrorBars](simple_error_bar_plot.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/errorBars/simple_error_bar.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/3qLIhIJEwT41pDtiNYy4nQ" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
