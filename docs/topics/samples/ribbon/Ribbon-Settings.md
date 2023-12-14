# Ribbon Settings

<web-summary>
Learn to customize ribbon graphs in Kotlin using the Kandy library with the 'Ribbon Settings' example.
This demonstration includes altering ribbon styles and colors for a dataset of minimal and maximal cost over years.
</web-summary>

<card-summary>
'Ribbon Settings' Example: A Kotlin demonstration with Kandy to customize ribbon graphs, featuring dashed ribbons and color adjustments.
</card-summary>

<link-summary>
Dive into 'Ribbon Settings' for insights on customizing ribbon graphs in Kotlin with Kandy, showcasing a visualization of years cost changes data.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Ribbon-->

<!---FUN ribbon_settings-->
<tabs>
<tab title="Dataframe">

```kotlin
val year by columnOf("2019", "2020", "2021", "2022", "2023")
val minCost by columnOf(56.5, 59.9, 60.8, 78.9, 75.5)
val maxCost by columnOf(58.1, 69.3, 66.4, 108.3, 92.2)
val df = dataFrameOf(year, minCost, maxCost)

df.plot {
    ribbon {
        x(year)
        y {
            axis.name = "cost"
            scale = continuous(55.0..110.0)
        }
        yMin(minCost)
        yMax(maxCost)
        fillColor = Color.hex(0x3f21e6)
        alpha = 0.65
        borderLine {
            color = Color.RED
            width = 0.8
            type = LineType.DASHED
        }
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val year = listOf("2019", "2020", "2021", "2022", "2023")
val minCost = listOf(56.5, 59.9, 60.8, 78.9, 75.5)
val maxCost = listOf(58.1, 69.3, 66.4, 108.3, 92.2)

plot {
    ribbon {
        x(year)
        y {
            axis.name = "cost"
            scale = continuous(55.0..110.0)
        }
        yMin(minCost)
        yMax(maxCost)
        fillColor = Color.hex(0x3f21e6)
        alpha = 0.65
        borderLine {
            color = Color.RED
            width = 0.8
            type = LineType.DASHED
        }
    }
}
```

</tab></tabs>
<!---END-->

![Ribbon Settings](ribbon_settings.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/ribbon/ribbon_settings.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/ZIYz9U8816bMRqSPLyfoUK" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
