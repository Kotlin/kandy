# Basic Tile Plot

<web-summary>
Discover the power of heatmaps with 'Basic Tile Plot' in Kandy.
This example illustrates how tile plots can be used to visualize data patterns and densities effectively.
</web-summary>

<card-summary>
Visualize data density and patterns with 'Basic Tile Plot' in Kandy, a compelling example of heatmap utilization.
</card-summary>

<link-summary>
Explore data visualization through heatmaps with 'Basic Tile Plot' in Kandy, offering clear insights into data patterns and densities.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Tiles-->

<!---FUN basic_tile_plot-->

```kotlin
plot {
    tiles {
        x(listOf("A", "B"))
        y(listOf(1.0, 2.0))
    }
}
```

<!---END-->

![Basic Tile Plot](basic_tile_plot.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/tiles/basic_tile_plot.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/sluVJbVGY3OAhJ5xvil8hF" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
