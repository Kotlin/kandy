# Density Plot Simple

<web-summary>
Explore 'Density Plot Simple' in Kotlin using Kandy to understand the distribution of a dataset.
This example demonstrates the effective use of density plots in continuous visualizing the frequency of data points.
</web-summary>

<card-summary>
'Density Plot Simple' in Kotlin with Kandy: A clear and straightforward representation of data distribution,
perfect for statistical analysis and understanding data trends.
</card-summary>

<link-summary>
Dive into 'Density Plot Simple' using Kotlin and Kandy, a fundamental tool in data analysis for visualizing the distribution and frequency of continuous dataset values."
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.DensityPlot-->

<!---FUN densityPlot_simple-->
<tabs>
<tab title="Dataframe">

```kotlin
val random = java.util.Random(42)

val dataframe = dataFrameOf(
    "sample" to List(1000) { random.nextGaussian() }
)

dataframe.plot {
    densityPlot("sample")
}
```

</tab>
<tab title="Collections">

```kotlin
val random = java.util.Random(42)

val sample = List(1000) { random.nextGaussian() }

plot {
    densityPlot(sample)
}
```

</tab></tabs>
<!---END-->


![Density Plot Simple](densityPlot_simple.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/densityPlot/densityPlot_simple.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/5u5KD2BG5k00WXOPtGTcAJ" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
