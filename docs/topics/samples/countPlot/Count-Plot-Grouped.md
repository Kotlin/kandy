# Count Plot Grouped

<web-summary>
Explore 'Count Plot Grouped' in Kotlin using Kandy, a clear demonstration of grouping in count plots.
This example effectively showcases the comparison of multiple data sets side by side within the same category, ideal for comparative analysis across different groups or variables.
</web-summary>

<card-summary>
'Count Plot Grouped' in Kotlin with Kandy: A powerful tool for visual comparison,
this example highlights how to group data sets in count plots for a comprehensive comparative view.
</card-summary>

<link-summary>
Dive into 'Count Plot Grouped' using Kotlin and Kandy,
where the concept of grouped count plots is used to juxtapose different data sets within the same categories.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.CountPlot-->

<!---FUN countPlot_grouped-->
<tabs>
<tab title="Dataframe">

```kotlin
val categories = listOf(
    "easy", "medium", "hard", "medium", "easy",
    "hard", "hard", "easy", "easy", "hard", "medium",
    "hard", "easy", "easy", "easy", "medium",
    "hard", "hard", "hard", "medium", "easy",
    "hard", "medium", "hard", "hard", "hard",
    "medium", "medium", "easy", "medium", "hard",
    "hard", "easy", "hard", "medium", "medium",
    "hard", "hard", "hard", "easy", "hard",
    "hard", "easy", "medium", "medium", "hard",
    "medium", "medium", "easy", "hard", "medium",
    "hard", "medium", "easy", "easy",
)

val years = listOf(
    "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022",
    "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022",
    "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022",
    "2022", "2022", "2023", "2023", "2023", "2023", "2023", "2023",
    "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023",
    "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023",
    "2023", "2023", "2023", "2023", "2023", "2023", "2023"
)

val df = dataFrameOf(
    "category" to categories,
    "year" to years
)

df.groupBy("category").plot {
    countPlot("year")
}
```

</tab>
<tab title="Collections">

```kotlin
val categories = listOf(
    "easy", "medium", "hard", "medium", "easy",
    "hard", "hard", "easy", "easy", "hard", "medium",
    "hard", "easy", "easy", "easy", "medium",
    "hard", "hard", "hard", "medium", "easy",
    "hard", "medium", "hard", "hard", "hard",
    "medium", "medium", "easy", "medium", "hard",
    "hard", "easy", "hard", "medium", "medium",
    "hard", "hard", "hard", "easy", "hard",
    "hard", "easy", "medium", "medium", "hard",
    "medium", "medium", "easy", "hard", "medium",
    "hard", "medium", "easy", "easy",
)

val years = listOf(
    "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022",
    "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022",
    "2022", "2022", "2022", "2022", "2022", "2022", "2022", "2022",
    "2022", "2022", "2023", "2023", "2023", "2023", "2023", "2023",
    "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023",
    "2023", "2023", "2023", "2023", "2023", "2023", "2023", "2023",
    "2023", "2023", "2023", "2023", "2023", "2023", "2023"
)

val dataset = mapOf(
    "category" to categories,
    "year" to years
)

dataset.plot {
    groupBy("category") {
        countPlot("year")
    }
}
```

</tab></tabs>
<!---END-->

![Count Plot Grouped](countPlot_grouped.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/countPlot/countPlot_grouped.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/ZucIRoOmKrIyPnY6dsZBve" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
