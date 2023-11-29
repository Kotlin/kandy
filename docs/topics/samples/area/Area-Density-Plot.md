# Density Plot

<web-summary>
Discover 'Density Plot' in Kotlin using Kandy, showcasing the distribution of a data set with a density plot.
This example highlights how to visualize the probability density of a random sample, making it ideal for statistical analysis and data exploration.
</web-summary>

<card-summary>
'Density Plot' in Kotlin with Kandy: An insightful example of representing statistical data distributions,
perfect for understanding the underlying patterns in a large set of data.
</card-summary>

<link-summary>
Explore 'Density Plot' using Kotlin and Kandy to gain insights into the probability density of random Gaussian samples,
a fundamental technique in statistical visualization.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Area-->

<!---FUN density_plot-->

```kotlin
val random = java.util.Random(42)

val sample = List(1000) { random.nextGaussian() }

plot {
    densityPlot(sample)
}
```

<!---END-->

![Density Plot](density_plot.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/area/density_plot.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/H9xkacpr6igpUdWZWIATgg" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
