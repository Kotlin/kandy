# Fixed Bar Coordinate

<web-summary>
Explore 'Fixed Bar Coordinate' in Kotlin using Kandy, a unique approach to bar plotting where one of the coordinates is fixed,
creating a standard reference point for comparison. This example is ideal for analyzing categories against a constant value or benchmark.
</web-summary>

<card-summary>
'Fixed Bar Coordinate' in Kotlin with Kandy: A specialized bar plot where one axis remains constant, perfect for direct comparisons across a uniform scale.
</card-summary>

<link-summary>
Dive into 'Fixed Bar Coordinate' using Kotlin and Kandy, where bar plots are transformed by fixing one coordinate, providing a clear and consistent basis for comparison.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Bars-->

<!---FUN fixed_bar-->

```kotlin
plot {
    x(listOf(2017, 2018, 2019, 2020, 2021, 2022, 2023))
    bars {
        y.constant(100)
        width = 0.5
        fillColor = Color.GREY
        alpha = 0.3
    }
    bars {
        y(listOf(20, 100, 50, 80, 70, 10, 30))
    }
}
```

<!---END-->

![Fixed Bar Coordinate](fixed_bar.png) { border-effect="rounded" }

[//]: # (TODO)
<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/bars/fixed_bar.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/NFGYJFW8oMlsu5aROAxRGq" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
