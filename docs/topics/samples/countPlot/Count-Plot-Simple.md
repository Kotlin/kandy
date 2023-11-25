# Count Plot Simple

<web-summary>
Explore 'Count Plot Simple' in Kotlin using Kandy to understand the distribution of a discrete variable.
This example demonstrates the effective use of count plots in visualizing the frequency of categorical data points.
</web-summary>

<card-summary>
'Count Plot Simple' in Kotlin with Kandy: A clear and straightforward representation of a discrete variable distribution,
perfect for statistical analysis and understanding data trends.
</card-summary>

<link-summary>
Dive into 'Count Plot Simple' using Kotlin and Kandy, a fundamental tool in data analysis for visualizing the distribution and frequency of categorical dataset values."
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.CountPlot-->

<!---FUN countPlot_simple-->
<tabs>
<tab title="Dataframe">

```kotlin
val dataframe = dataFrameOf(
    "categories" to listOf(
        "A", "B", "C", "C", "B",
        "A", "C", "B", "A", "B",
        "C", "A", "B", "A", "A",
        "C", "A", "A", "B", "C",
        "C", "A", "A", "C", "B",
        "C", "C", "A", "A", "A",
        "B", "C", "B", "A", "B",
        "C", "A", "A", "B", "A",
        "C", "A", "C", "A", "C"
    )
)

dataframe.plot {
    countPlot("categories")
}
```

</tab>
<tab title="Collections">

```kotlin
val categories = listOf(
    "A", "B", "C", "C", "B",
    "A", "C", "B", "A", "B",
    "C", "A", "B", "A", "A",
    "C", "A", "A", "B", "C",
    "C", "A", "A", "C", "B",
    "C", "C", "A", "A", "A",
    "B", "C", "B", "A", "B",
    "C", "A", "A", "B", "A",
    "C", "A", "C", "A", "C"
)

plot {
    countPlot(categories)
}
```

</tab></tabs>
<!---END-->


![Count Plot Simple](countPlot_simple.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/countPlot/countPlot_simple.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/FY25TfmDvSdy1BzMNheynH" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
