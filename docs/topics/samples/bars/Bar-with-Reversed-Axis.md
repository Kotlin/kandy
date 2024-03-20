# Bar with Reversed Axis

<web-summary>
Discover 'Bar with Reversed Axis' in Kotlin using Kandy, showcasing how reversing an axis in a bar plot can provide a different perspective on data.
This example is particularly useful for visualizing scenarios where lower values hold more significance.
</web-summary>

<card-summary>
'Bar with Reversed Axis' in Kotlin with Kandy:
An innovative twist on traditional bar plots, ideal for emphasizing smaller values in data comparisons and analyses.
</card-summary>

<link-summary>
Explore 'Bar with Reversed Axis' using Kotlin and Kandy, a unique approach to bar chart visualization,
offering fresh insights by inverting the conventional axis arrangement.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Bars-->

<!---FUN bar_with_reversed_axis-->
<tabs>
<tab title="Dataframe">

```kotlin
val dataset = dataFrameOf(
    "task" to listOf("a", "b", "c", "d", "e"),
    "time" to listOf(30, 25, 20, 35, 28)
)

dataset.plot {
    bars {
        x("task")
        y("time") {
            scale = continuous(transform = Transformation.REVERSE)
        }
        fillColor = Color.hex("#07C3F2")
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val dataset = mapOf(
    "task" to listOf("a", "b", "c", "d", "e"),
    "time" to listOf(30, 25, 20, 35, 28)
)

dataset.plot {
    bars {
        x("task")
        y("time") {
            scale = continuous(transform = Transformation.REVERSE)
        }
        fillColor = Color.hex("#07C3F2")
    }
}
```

</tab></tabs>
<!---END-->

![Bar with Reversed Axis](bar_with_reversed_axis.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/bars/bar_with_reversed_axis.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/ZL1NIQsY7scs8azMypqb1d" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
