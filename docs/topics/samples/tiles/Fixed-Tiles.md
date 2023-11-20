# Fixed Tiles Coordinate

<web-summary>
Discover the precision of 'Fixed Tiles Coordinate' in Kandy.
This example demonstrates how to effectively anchor tile plots, ensuring consistent and accurate data representation.
</web-summary>

<card-summary>
Learn the art of precise tile plot positioning with 'Fixed Tiles Coordinate' in Kandy, enhancing data clarity and consistency.
</card-summary>

<link-summary>
Master accurate heatmap plotting with 'Fixed Tiles Coordinate' in Kandy, perfect for ensuring data precision in your visualizations.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Tiles-->

<!---FUN fixed_tile-->

```kotlin
val ys = listOf("a", "b", "c", "d")

plot {
    tiles {
        x.constant(0.0)
        y(ys)
        alpha = 0.4
        fillColor = Color.PEACH
        borderLine.width = 0.5
    }
}
```

<!---END-->

![Fixed Tiles Coordinate](fixed_tile.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/tiles/fixed_tile.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/NFGYJFW8oMlsu5aROAxRGq" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
