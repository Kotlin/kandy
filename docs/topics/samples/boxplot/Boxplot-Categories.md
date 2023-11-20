# Boxplot Categories

<web-summary>
Boxplot Categories in Kandy allows for an intuitive understanding of data distribution across different categories.
This example demonstrates how boxplot can effectively compare and contrast data groupings.
</web-summary>

<card-summary>
Understanding Data Diversity: 'Boxplot Categories' in Kandy visually compares data across multiple categories.
</card-summary>

<link-summary>
Dive into category-based data analysis with 'Boxplot Categories' in Kandy, showcasing comparative data distribution.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Boxplot-->

<!---FUN boxplot_categories-->

```kotlin
val random = kotlin.random.Random(42)
fun generateData(category: String) =
    dataFrameOf((0..<10).map { it.toString() }) { List(100) { random.nextDouble(0.0, 100.0) } }
        .gather(*(0..<10).map { it.toString() }.toTypedArray())
        .into("num", "value").add("category") { category }

val data = generateData("category0")
    .concat(generateData("category1"))
    .concat(generateData("category2"))

data.groupBy("category").plot {
    boxplot("num", "value") {
        boxes {
            position = Position.dodge()
        }
    }
}
```

<!---END-->

![Boxplot Categories](boxplot_categories.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/boxplot/boxplot_categories.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/NFGYJFW8oMlsu5aROAxRGq" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
