# Line with Points

<web-summary>
Explore the 'Line with Points' example in Kotlin using Kandy, where a line graph is enhanced with distinct points.
This example visualizes the relationship between area and price.
</web-summary>

<card-summary>
Kotlin 'Line with Points' Example: A graphical representation combining line and points with Kandy, showcasing area vs. price.
</card-summary>

<link-summary>
See 'Line with Points' in action, a Kotlin example with Kandy that combines line graphs and point markers to represent area and price data.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Lines-->

<!---FUN line_with_points-->
<tabs>
<tab title="Dataframe">

```kotlin
val area by columnOf(30, 40, 50, 60, 70, 80, 90)
val price by columnOf(60000, 80000, 75000, 90000, 85000, 95000, 90000)

plot {
    x(price)
    y(area)
    line {
        color = Color.BLUE
        type = LineType.LONGDASH
    }
    points {
        size = 3.5
        symbol = Symbol.CIRCLE_OPEN
        color = Color.BLUE
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val area by columnOf(30, 40, 50, 60, 70, 80, 90)
val price by columnOf(60000, 80000, 75000, 90000, 85000, 95000, 90000)

plot {
    x(price)
    y(area)
    line {
        color = Color.BLUE
        type = LineType.LONGDASH
    }
    points {
        size = 3.5
        symbol = Symbol.CIRCLE_OPEN
        color = Color.BLUE
    }
}
```

</tab></tabs>
<!---END-->

![Line with Points](line_with_points.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/line/line_with_points.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/W76auNVkvh6ej4jmaqme7H" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
