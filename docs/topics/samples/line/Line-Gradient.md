# Line Gradient

<web-summary>
Discover the 'Line Gradient' example in Kotlin using Kandy, showcasing monthly temperature changes through a dynamic color gradient.
This example demonstrates the impactful use of color transitions in line charts.
</web-summary>

<card-summary>
Kotlin 'Line Gradient' Example: An engaging display of the year's temperature fluctuations,
portrayed using a color gradient line with Kandy.
</card-summary>

<link-summary>
Explore the 'Line Gradient' in Kotlin with Kandy, a visually appealing representation of temperature variations over a year using a color gradient line.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Lines-->

<!---FUN line_color_gradient-->
<tabs>
<tab title="Dataframe">

```kotlin
val monthTemp = dataFrameOf("month", "temp")(
    "January", -5,
    "February", -3,
    "March", 2,
    "April", 10,
    "May", 16,
    "June", 20,
    "July", 22,
    "August", 21,
    "September", 15,
    "October", 9,
    "November", 3,
    "December", -2
)

monthTemp.plot {
    line {
        x("month")
        y("temp") {
            scale = continuous(-10..25) // axis scale
        }
        color("temp") {
            scale = continuous(Color.BLUE..Color.RED)
        }
        width = 3.0
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val monthTemp = mapOf(
    "month" to listOf(
        "January", "February",
        "March", "April", "May",
        "June", "July", "August",
        "September", "October", "November",
        "December"
    ),
    "temp" to listOf(-5, -3, 2, 10, 16, 20, 22, 21, 15, 9, 3, -2)
)

monthTemp.plot {
    line {
        x("month")
        y("temp") {
            scale = continuous(-10..25) // axis scale
        }
        color("temp") {
            scale = continuous(Color.BLUE..Color.RED)
        }
        width = 3.0
    }
}
```

</tab></tabs>
<!---END-->

![Line with Color Gradient](line_color_gradient.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/line/line_gradient.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/qufF2iKNOWvhxDb7nuEU1l" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
