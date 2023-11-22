# Line Settings

<web-summary>
Learn to customize line graphs in Kotlin using the Kandy library with the 'Line Settings' example.
This demonstration includes altering line styles and colors for a dataset of museum visitors.
</web-summary>

<card-summary>
'Line Settings' Example: A Kotlin demonstration with Kandy to customize line graphs, featuring dashed lines and color adjustments.
</card-summary>

<link-summary>
Dive into 'Line Settings' for insights on customizing line graphs in Kotlin with Kandy, showcasing a visualization of museum visitor data.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Lines-->

<!---FUN simple_line_settings-->
<tabs>
<tab title="Dataframe">

```kotlin
val museumVisitors = dataFrameOf("date", "visitors")(
    LocalDate(2023, 1, 1), 120,
    LocalDate(2023, 1, 15), 95,
    LocalDate(2023, 2, 1), 110,
    LocalDate(2023, 2, 15), 123,
    LocalDate(2023, 3, 1), 130,
    LocalDate(2023, 3, 15), 140,
    LocalDate(2023, 4, 1), 150,
    LocalDate(2023, 4, 15), 160,
    LocalDate(2023, 5, 1), 175,
    LocalDate(2023, 5, 15), 180
)

museumVisitors.plot {
    line {
        x("date")
        y("visitors")
        type = LineType.DASHED
        color = Color.PURPLE
        width = 2.5
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val date = listOf(
    LocalDate(2023, 1, 1),
    LocalDate(2023, 1, 15),
    LocalDate(2023, 2, 1),
    LocalDate(2023, 2, 15),
    LocalDate(2023, 3, 1),
    LocalDate(2023, 3, 15),
    LocalDate(2023, 4, 1),
    LocalDate(2023, 4, 15),
    LocalDate(2023, 5, 1),
    LocalDate(2023, 5, 15)
)
val visitors = listOf(120, 95, 110, 123, 130, 140, 150, 160, 175, 180)

plot {
    line {
        x(date)
        y(visitors)
        type = LineType.DASHED
        color = Color.PURPLE
        width = 2.5
    }
}
```

</tab></tabs>
<!---END-->

![Line Settings](simple_line_settings.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/line/line_settings.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/7I6X0iguozJeJAvwUMNACj" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
