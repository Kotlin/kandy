# Grouped ErrorBars

<web-summary>
Explore how 'Grouped ErrorBars' in Kandy can effectively represent variability across different groups.
This example demonstrates the use of error bars to compare data across multiple categories.
</web-summary>

<card-summary>
'Grouped ErrorBars': Illustrating comparative data uncertainty across categories in Kandy.
</card-summary>

<link-summary>
See how 'Grouped ErrorBars' in Kandy enhance comparative data analysis across multiple groups, providing a clear visualization of variability.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.ErrorBars-->

<!---FUN grouped_error_bars-->
<tabs>
<tab title="Dataframe">

```kotlin
val dataset = dataFrameOf(
    "time" to (1..5).toList() + (1..5).toList(),
    "min" to listOf(2.0, 3.4, 3.5, 5.5, 2.5) + listOf(1.0, 2.0, 3.0, 4.0, 3.7),
    "max" to listOf(3.0, 5.2, 5.0, 5.8, 3.4) + listOf(5.0, 4.0, 3.5, 5.0, 4.2),
    "category" to List(5) { "a" } + List(5) { "b" }
)

dataset.groupBy("category").plot {
    errorBars {
        x("time") {
            axis.breaks((1..5).toList(), format = "d")
        }
        yMin("min")
        yMax("max")
        borderLine.color("category")
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val dataset = mapOf(
    "time" to (1..5).toList() + (1..5).toList(),
    "min" to listOf(2.0, 3.4, 3.5, 5.5, 2.5) + listOf(1.0, 2.0, 3.0, 4.0, 3.7),
    "max" to listOf(3.0, 5.2, 5.0, 5.8, 3.4) + listOf(5.0, 4.0, 3.5, 5.0, 4.2),
    "category" to List(5) { "a" } + List(5) { "b" }
)

dataset.plot {
    groupBy("category") {
        errorBars {
            x("time") {
                axis.breaks((1..5).toList(), format = "d")
            }
            yMin("min")
            yMax("max")
            borderLine.color("category")
        }
    }
}
```

</tab></tabs>
<!---END-->

![Grouped ErrorBars](grouped_error_bars.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/errorBars/grouped_error_bars.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/K9TGawBhS2MXzfn2GOKWu6" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
