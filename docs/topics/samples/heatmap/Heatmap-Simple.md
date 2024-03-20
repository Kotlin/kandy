# Heatmap Simple

<web-summary>
Explore 'Heatmap Simple' in Kotlin using Kandy to understand the distribution of a dataset with two discrete variables.
This example demonstrates the effective use of heatmaps in visualizing the frequency of data points with two categorical variables.
</web-summary>

<card-summary>
'Heatmap Simple' in Kotlin with Kandy: A clear and straightforward representation of two variables data distribution,
perfect for statistical analysis and understanding data trends.
</card-summary>

<link-summary>
Dive into 'Heatmap Simple' using Kotlin and Kandy, a fundamental tool in data analysis for visualizing the distribution and frequency of two variables dataset values."
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Heatmap-->

<!---FUN heatmap_simple-->
<tabs>
<tab title="Dataframe">

```kotlin
val dataframe = dataFrameOf(
    "days" to listOf(
        "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Sun",
        "Sat", "Thu", "Fri", "Tue", "Wed", "Sun", "Mon", "Thu",
        "Sun", "Sat", "Tue", "Mon", "Thu", "Wed", "Fri", "Sat",
        "Tue", "Sun", "Fri", "Sat", "Thu", "Mon", "Wed", "Tue",
        "Thu", "Mon", "Sun", "Fri", "Wed", "Sat", "Tue", "Thu",
        "Sat", "Tue", "Sun", "Mon", "Wed", "Fri", "Thu", "Sat",
        "Thu", "Fri", "Sun", "Tue", "Sat", "Wed", "Mon", "Thu",
        "Wed", "Tue", "Sat", "Fri", "Sun", "Thu", "Mon", "Tue",
        "Fri", "Thu", "Wed", "Sun", "Sat", "Mon", "Tue", "Thu",
        "Tue", "Wed", "Sun", "Mon", "Thu", "Sat", "Fri", "Tue",
        "Thu", "Sun", "Fri", "Sat", "Mon", "Wed", "Tue", "Thu",
        "Sat", "Mon", "Tue", "Thu", "Fri", "Sun", "Wed", "Sat",
        "Sun", "Fri", "Tue", "Thu", "Sat", "Mon", "Wed", "Sun",
        "Mon", "Wed", "Sat", "Fri", "Thu", "Tue", "Sun", "Sat",
    ),
    "drinks" to listOf(
        "soda", "tea", "coffee", "tea", "soda", "tea", "coffee", "soda",
        "coffee", "soda", "tea", "coffee", "soda", "tea", "coffee", "tea",
        "coffee", "soda", "tea", "soda", "coffee", "tea", "soda", "coffee",
        "soda", "tea", "coffee", "tea", "soda", "coffee", "tea", "soda",
        "tea", "soda", "coffee", "tea", "soda", "coffee", "soda", "tea",
        "coffee", "soda", "tea", "soda", "coffee", "tea", "soda", "coffee",
        "soda", "coffee", "tea", "soda", "coffee", "soda", "tea", "coffee",
        "soda", "coffee", "tea", "soda", "tea", "soda", "coffee", "tea",
        "tea", "coffee", "soda", "tea", "coffee", "soda", "tea", "soda",
        "tea", "soda", "coffee", "soda", "tea", "coffee", "soda", "coffee",
        "tea", "coffee", "soda", "tea", "soda", "coffee", "soda", "tea",
        "coffee", "soda", "tea", "coffee", "tea", "soda", "coffee", "soda",
        "soda", "tea", "coffee", "soda", "tea", "coffee", "soda", "tea",
        "coffee", "tea", "soda", "coffee", "tea", "soda", "coffee", "soda"
    )
)

dataframe.plot {
    heatmap("days", "drinks")
}
```

</tab>
<tab title="Collections">

```kotlin
val days = listOf(
    "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Sun",
    "Sat", "Thu", "Fri", "Tue", "Wed", "Sun", "Mon", "Thu",
    "Sun", "Sat", "Tue", "Mon", "Thu", "Wed", "Fri", "Sat",
    "Tue", "Sun", "Fri", "Sat", "Thu", "Mon", "Wed", "Tue",
    "Thu", "Mon", "Sun", "Fri", "Wed", "Sat", "Tue", "Thu",
    "Sat", "Tue", "Sun", "Mon", "Wed", "Fri", "Thu", "Sat",
    "Thu", "Fri", "Sun", "Tue", "Sat", "Wed", "Mon", "Thu",
    "Wed", "Tue", "Sat", "Fri", "Sun", "Thu", "Mon", "Tue",
    "Fri", "Thu", "Wed", "Sun", "Sat", "Mon", "Tue", "Thu",
    "Tue", "Wed", "Sun", "Mon", "Thu", "Sat", "Fri", "Tue",
    "Thu", "Sun", "Fri", "Sat", "Mon", "Wed", "Tue", "Thu",
    "Sat", "Mon", "Tue", "Thu", "Fri", "Sun", "Wed", "Sat",
    "Sun", "Fri", "Tue", "Thu", "Sat", "Mon", "Wed", "Sun",
    "Mon", "Wed", "Sat", "Fri", "Thu", "Tue", "Sun", "Sat",
)
val drinks = listOf(
    "soda", "tea", "coffee", "tea", "soda", "tea", "coffee", "soda",
    "coffee", "soda", "tea", "coffee", "soda", "tea", "coffee", "tea",
    "coffee", "soda", "tea", "soda", "coffee", "tea", "soda", "coffee",
    "soda", "tea", "coffee", "tea", "soda", "coffee", "tea", "soda",
    "tea", "soda", "coffee", "tea", "soda", "coffee", "soda", "tea",
    "coffee", "soda", "tea", "soda", "coffee", "tea", "soda", "coffee",
    "soda", "coffee", "tea", "soda", "coffee", "soda", "tea", "coffee",
    "soda", "coffee", "tea", "soda", "tea", "soda", "coffee", "tea",
    "tea", "coffee", "soda", "tea", "coffee", "soda", "tea", "soda",
    "tea", "soda", "coffee", "soda", "tea", "coffee", "soda", "coffee",
    "tea", "coffee", "soda", "tea", "soda", "coffee", "soda", "tea",
    "coffee", "soda", "tea", "coffee", "tea", "soda", "coffee", "soda",
    "soda", "tea", "coffee", "soda", "tea", "coffee", "soda", "tea",
    "coffee", "tea", "soda", "coffee", "tea", "soda", "coffee", "soda"
)

plot {
    heatmap(days, drinks)
}
```

</tab></tabs>
<!---END-->


![Heatmap Simple](heatmap_simple.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/heatmap/heatmap_simple.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/HygWJEUdQTJ3mZJQcdX5AE" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
