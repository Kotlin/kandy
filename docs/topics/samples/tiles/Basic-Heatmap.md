# Basic Heatmap

<web-summary>
Immerse in the basics of heatmap creation with 'Basic Heatmap' in Kandy.
This example provides a straightforward approach to visualizing matrix data through color intensity variations.
</web-summary>

<card-summary>
Get a grasp of heatmap fundamentals in 'Basic Heatmap' using Kandy.
This example demonstrates the power of visualizing complex data in a simple, color-coded format.
</card-summary>

<link-summary>
Dive into the world of heatmaps with 'Basic Heatmap' in Kandy.
This introductory example showcases how to effectively represent matrix data using color gradients.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Tiles-->

<!---FUN basic_heatmap-->

```kotlin
val random = kotlin.random.Random(42)
val cols = (List(20) { "col1" } + List(50) { "col2" } + List(70) { "col3" }).shuffled(random)
val rows = (List(40) { "row1" } + List(80) { "row2" } + List(20) { "row3" }).shuffled(random)

plot {
    heatmap(cols, rows)
}
```

<!---END-->

![Basic Heatmap](basic_heatmap.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/tiles/basic_heatmap.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/5SnrXQq7aYk9576edxKEqC" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
