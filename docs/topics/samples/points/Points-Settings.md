# Points Settings

<web-summary>
Explore 'Points Settings' in Kotlin using Kandy, a detailed guide to customizing scatter plots.
This example demonstrates various settings for point plots, including adjustments in size, color, and shape, making it an excellent resource for tailored data visualization.
</web-summary>

<card-summary>
'Points Settings' in Kotlin with Kandy: A deep dive into customizing scatter plot appearances,
perfect for creating visually distinct and informative point-based graphs.
</card-summary>

<link-summary>
Dive into 'Points Settings' using Kandy,
where scatter plots are fine-tuned with various settings to enhance clarity and visual appeal in data representation.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Points-->

<!---FUN points_settings-->
<tabs>
<tab title="Dataframe">

```kotlin
val dataset = dataFrameOf(
    "xs" to listOf(
        5.93, 9.15, 3.76, 5.04, 2.23,
        7.47, 2.59, 11.67, 7.90, 3.71,
        0.03, 2.73, 4.61, 5.44, 1.76,
        14.46, 1.89
    ),
    "ys" to listOf(
        14.66, 13.80, 5.37, 6.40, 6.86,
        2.98, 6.69, 5.48, 3.67, 12.36,
        0.01, 14.47, 14.56, 9.19, 12.86,
        5.37, 0.90
    )
)

dataset.plot {
    points {
        x("xs")
        y("ys")
        size = 10.0
        color = Color.BLUE
        symbol = Symbol.DIAMOND
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val xs = listOf(
    5.93, 9.15, 3.76, 5.04, 2.23,
    7.47, 2.59, 11.67, 7.90, 3.71,
    0.03, 2.73, 4.61, 5.44, 1.76,
    14.46, 1.89
)
val ys = listOf(
    14.66, 13.80, 5.37, 6.40, 6.86,
    2.98, 6.69, 5.48, 3.67, 12.36,
    0.01, 14.47, 14.56, 9.19, 12.86,
    5.37, 0.90
)

plot {
    points {
        x(xs)
        y(ys)
        size = 10.0
        color = Color.BLUE
        symbol = Symbol.DIAMOND
    }
}
```

</tab></tabs>
<!---END-->

![Points Settings](points_settings.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/points/points_settings.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/W74JFIy2FImeuIHH0N4C2H" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
