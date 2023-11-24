# Boxplot of Experiments

<web-summary>
Explore scientific data diversity with 'Boxplot of Experiments' in Kandy.
This example uses boxplot to visualize the variation and distribution of experimental data, offering insights into the nature of scientific experiments.
</web-summary>

<card-summary>
Scientific Data Insights: 'Boxplot of Experiments' in Kandy showcases the variability and distribution in experimental data.
</card-summary>

<link-summary>
Unravel the complexity of experimental data with 'Boxplot of Experiments' in Kandy, highlighting data distribution and variation.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Boxplot-->

<!---FUN boxplot_expr-->

```kotlin
val data = dataFrameOf(
    "expr0" to listOf(
        850, 740, 900, 1070, 930, 850, 950, 980, 980,
        880, 1000, 980, 930, 650, 760, 810, 1000, 1000, 960, 960
    ),
    "expr1" to listOf(
        960, 940, 960, 940, 880, 800, 850, 880, 900, 840, 830,
        790, 810, 880, 880, 830, 800, 790, 760, 800
    ),
    "expr2" to listOf(
        880, 880, 880, 860, 720, 720, 620, 860, 970, 950,
        880, 910, 850, 870, 840, 840, 850, 840, 840, 840
    ),
    "expr3" to listOf(
        890, 810, 810, 820, 800, 770, 760, 740, 750,
        760, 910, 920, 890, 860, 880, 720, 840, 850, 850, 780
    ),
    "expr4" to listOf(
        890, 840, 780, 810, 760, 810, 790, 810, 820,
        850, 870, 870, 810, 740, 810, 940, 950, 800, 810, 870
    )
).gather("expr0", "expr1", "expr2", "expr3", "expr4").into("expr", "value")


data.plot {
    boxplot("expr", "value") {
        boxes {
            borderLine.color = Color.BLUE
        }
    }
}
```

<!---END-->

![Boxplot of Experiments](boxplot_expr.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/boxplot/boxplot_expr.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/OrbuctRi64ART95O9Z2v8u" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
